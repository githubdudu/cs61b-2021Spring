package gitlet;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import static gitlet.Utils.*;

// : any imports you need here

/**
 * Represents a gitlet repository.
 * It's a good idea to give a description here of what else this Class
 * does at a high level.
 *
 * @author hdon694
 */
public class Repository {
    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /**
     * The branch pointer directory -- ".gitlet/heads/"
     */
    public static final File BRANCH_DIR = join(GITLET_DIR, "heads");
    /**
     * The blobs directory -- ".gitlet/objects/"
     */
    public static final File BLOBS_DIR = join(GITLET_DIR, "objects");
    /**
     * The commits object directory -- ".gitlet/commits/"
     */
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");

    /**
     * The HEAD pointer
     */
    public static final File HEAD_POINTER = join(GITLET_DIR, "HEADER");
    /**
     * The index file for staging.
     */
    public static final File INDEX = join(GITLET_DIR, "index");
    /* : fill in the rest of this class. */

    /**
     * gitlet init command
     * <p>
     * <ol>
     * <li>Create folders.
     * <li>Create new index and save it to file.
     * <li>Create new commit and save to file, and get the hash.
     * <li>Create master branch and using the commit hash as the content of branch object, save
     * it to file.
     * <li>Create a HEADER as pointer, save the relative path of master branch object to HEAD
     * </ol>
     */
    public static void initCommand() {
        initGitletFolder();

        // Create the indexing, aka staging area.
        StagingArea initStage = new StagingArea();
        initStage.saveStagingToFile();

        // Create initial commit mega data.
        Date initDate = new Date(0);
        String initMessage = "initial commit";

        // Create and save initial commit.
        Commit commit = new Commit(initStage, initDate, initMessage);
        String commitHash = commit.saveCommitToFile();

        // Create a master branch and save it.
        // Save the hash of commit as the content of branch head.
        Branch defaultBranch = new Branch();
        defaultBranch.setContent(commitHash);
        defaultBranch.saveBranchToFile();

        // Create a HEAD pointer, HEADER.
        // Save the relative path of saved branch object to HEADER.
        saveTheHEADER(defaultBranch);
    }


    /**
     * gitlet add [filename] command.
     * <p>
     * <ol>
     * <li>Read the file to be added.</li>
     * <li>Save file as blob and get the hash.</li>
     * <li>Read the indexing file (of staging).</li>
     * <li>Update the index(HashMap) by {@code <filename, fileHash>} pairs. </li>
     * </ol>
     * It is not necessary to save or update the real staging areas.
     * We calculate the staging area that displayed in status command by compare the indexing file
     * and the index from current commit.
     * <p>
     * In real git, multiple files may be added at once. In gitlet, only one file may be added at a time.
     * <p>
     * Failure cases: File does not exist.
     *
     * @param filename the file to be added
     */
    public static void addCommand(String filename) {
        File file = join(CWD, filename);

        // Failure cases.
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        // Save file and calculate the sha1 of the file
        String fileHash = sha1(readContentsAsString(file));
        if (!BLOBS_DIR.exists()) {
            BLOBS_DIR.mkdir();
        }
        File blobFile = join(BLOBS_DIR, fileHash);
        if (!blobFile.exists()) {
            writeContents(blobFile, readContentsAsString(file));
        }

        // Read the index from current staging index, update, and then save.
        StagingArea indexStaging = StagingArea.readFromFile();
        indexStaging.updateIndex(filename, fileHash);
        indexStaging.saveStagingToFile();

    }

    /**
     * gitlet commit [message] command.
     * <ol>
     *     <li>Just save the latest index as new commit. </li>
     *     <ol>
     *         <li>If the index of current is identical to the index from latest commit, abort
     *         this commit. </li>
     *         <li>If the commit message is blank, print error. Abort. </li>
     *         <li>Save the latest index. </li>
     *     </ol>
     *     <li>Update branch head. </li>
     * </ol>
     * Failure cases:
     * <p>
     * 1. If no files have been staged, abort. Print the message "No changes added to
     * the commit."
     * <p>
     * 2. Every commit must have a non-blank message. If it doesn't, print the error
     * message "Please enter a commit message."
     * <p>
     * It is not a failure for tracked files to be missing from the working directory or changed
     * in the working directory. Just ignore everything outside the .gitlet directory entirely.
     * <p></p>
     * <p>
     * Differences from real git: In real git, commits may have multiple parents (due to merging)
     * and also have considerably more metadata.
     *
     * @param message commit message. Must be non-blank.
     */
    public static void commitCommand(String message) {
        // Read the index from current commit.
        Commit lastCommit = Commit.readCommitFromFile(getLastCommitHash());
        StagingArea indexFromCommit = lastCommit.getStaging();

        // Read the index from current staging index.
        StagingArea indexStaging = StagingArea.readFromFile();

        // Failure case 1
        if (indexFromCommit.equals(indexStaging)) {
            System.out.println("No changes added to the commit.");
        }

        // Failure case 2
        if (message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }

        Commit newCommit = new Commit(indexStaging, new Date(), message);
        String hash = newCommit.saveCommitToFile();

        String branchFile = Arrays.toString(readContents(Repository.HEAD_POINTER));
        writeContents(join(BRANCH_DIR, branchFile), hash);

    }

    /**
     * Unstage the file if it is currently staged for addition. If the file is tracked in the
     * current commit, stage it for removal and remove the file from the working directory if the
     * user has not already done so (do not remove it unless it is tracked in the current commit).
     * <p>
     * Failure cases: If the file is neither staged nor tracked by the head commit, print the error
     * message "No reason to remove the file."
     *
     * @param filename the name of the file to be removed from staging area.
     */
    public static void rmCommand(String filename) {
        // Read the index from current commit.
        Commit lastCommit = Commit.readCommitFromFile(getLastCommitHash());
        StagingArea indexFromCommit = lastCommit.getStaging();

        // Read the index from current staging index.
        StagingArea indexStaging = StagingArea.readFromFile();

        // Failure cases
        if (!indexFromCommit.getIndex().containsKey(filename) && !indexStaging.getIndex().containsKey(
                filename)) {
            System.out.println("No reason to remove the file.");
            System.exit(0);
        }

        indexStaging.getIndex().remove(filename);

        if (indexFromCommit.getIndex().containsKey(filename)) {
            restrictedDelete(filename);
        }

    }

    /**
     * TODO:
     * <li>Compare these indexes (HashMap) to decide whether:</li>
     *  <ol>
     *      <li>add this file.(If staging version is different from the version in current
     *      commit.)</li>
     *      <li>Do not stage. (If staging version of the file is identical to the version in
     *      current commit.)</li>
     *      <li>Remove it from the stage. (Same with last condition and the file already is in
     *      the staging area.)</li>
     *  </ol>
     * </ol>
     * For example:
     * Current Staging:      1,2,3, ,5*
     * Staging from commit:  1,2, ,4,5*
     */
    public static void statusCommand() {


        // Read the index from current commit
        Commit lastCommit = Commit.readCommitFromFile(getLastCommitHash());
        StagingArea indexFromCommit = lastCommit.getStaging();
    }


    /**
     * Create as many as the folders that used in a gitlet system.
     */
    private static void initGitletFolder() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current" + " directory.");
            System.exit(0);
        }

        GITLET_DIR.mkdir();
        BRANCH_DIR.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
    }

    /**
     * Save the branch relative path to HEADER file.
     *
     * @param branch the branch that the header is pointing to.
     */
    private static void saveTheHEADER(Branch branch) {
        writeContents(HEAD_POINTER, branch.getBranchFileRelativePath());
    }

    /**
     * Get the hash of last commit.
     *
     * @return the hash string.
     */
    private static String getLastCommitHash() {
        String branchFile = Arrays.toString(readContents(Repository.HEAD_POINTER));
        String hash = Arrays.toString(readContents(join(Repository.BRANCH_DIR, branchFile)));

        return hash;
    }
}
