package gitlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        // Create the blank indexing, aka staging area.
        StagingArea initStage = new StagingArea();
        initStage.saveStagingToFile();

        // Create initial commit mega data.
        Date initDate = new Date(0);
        String initMessage = "initial commit";

        // Create and save initial commit.
        Commit commit = new Commit(initStage, initDate, initMessage, getLastCommitHash());
        commit.saveCommitToFile();

        // Create a master branch and save it.
        // Save the hash of commit as the content of branch head.
        Branch defaultBranch = new Branch();
        defaultBranch.setContent(commit.getHash());
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
        Commit lastCommit = Commit.readFromFile(getLastCommitHash());
        StagingArea indexFromCommit = lastCommit.getStaging();

        // Read the index from current staging index.
        StagingArea indexStaging = StagingArea.readFromFile();

        // Read the branch from branch file.
        String ref = readHEADERFromFile();
        Branch branch = Branch.readFromFile(join(GITLET_DIR, ref).getName());

        // Failure case 1
        if (indexFromCommit.equals(indexStaging)) {
            System.out.println("No changes added to the commit.");
            System.exit(0);
        }

        // Failure case 2
        if (message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }

        Commit newCommit = new Commit(indexStaging, new Date(), message, getLastCommitHash());
        newCommit.saveCommitToFile();

        // Move the pointer of branch
        branch.setContent(newCommit.getHash());
        branch.saveBranchToFile();

    }

    /**
     * gitlet rm [filename] command.
     * <p>
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
        Commit lastCommit = Commit.readFromFile(getLastCommitHash());
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
        indexStaging.saveStagingToFile();

        if (indexFromCommit.getIndex().containsKey(filename)) {
            restrictedDelete(filename);
        }

    }

    /**
     * gitlet checkout -- [file name] command
     * <p>
     * The first kind use of checkout, recover file from latest commit.
     * <p>
     * Just locate the ID of last commit, and call another checkoutCommand().
     * <p>
     * Failure cases:
     * If the file does not exist in the previous commit, abort, printing the error
     * message "File does not exist in that commit." Do not change the CWD.
     *
     * @param filename the name of file that to be recovered.
     */
    public static void checkoutCommand(String filename) {
        checkoutCommand(getLastCommitHash(), filename);
    }

    /**
     * gitlet checkout [commit id] -- [file name] command
     * <p>
     * The second kind use of checkout, recover file from a given commit.
     * <p>
     * Get the index of that commit. Read source file from blob folders and write target file in
     * working directory.
     * <p>
     * <p>
     * Failure cases:
     * If no commit with the given id exists, print "No commit with that id exists."
     * Otherwise, if the file does not exist in the given commit, print the same message as for
     * failure case 1.
     * Do not change the CWD.
     *
     * @param commitID the id of commit, can be 40 length hash or first 6 digits of hash.
     * @param filename the name of file that to be recovered.
     */
    public static void checkoutCommand(String commitID, String filename) {
        // Failure case
        if (!join(COMMITS_DIR, commitID).exists()) {
            System.out.println("No commit with that id exists.");
            System.exit(0);
        }

        Commit source = Commit.readFromFile(commitID);
        String targetFileID = source.getStaging().getIndex().get(filename);
        // Failure case
        if (targetFileID == null) {
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }
        String content = readBlobContent(targetFileID);

        writeContents(join(CWD, filename), content);
    }


    /**
     * gitlet checkout [branch name] command
     * <p>
     * <p>
     * The third use of checkout, recover all the files from the head of the given branch.
     * Checks of failure cases should be done before doing anything else.
     * Takes all files in the commit at the head of the given branch, and puts them in the working
     * directory, overwriting the versions of the files that are already there if they exist. Also,
     * at the end of this command, the given branch will now be considered the current branch
     * (HEAD). Any files that are tracked in the current branch but are not present in the
     * checked-out branch are deleted. The staging area is cleared, unless the checked-out branch is
     * the current branch.
     * <p>
     * Failure cases:
     * If no branch with that name exists, print "No such branch exists."
     * If that branch is the current branch, print "No need to checkout the current branch."
     * If a working file is untracked in the current branch and would be overwritten by the
     * checkout, print "There is an untracked file in the way; delete it, or add and commit it
     * first." and exit; perform this check before doing anything else.
     * For this check, it should:
     * 1. Find the untracked files.
     * 2. See if it will be overwritten by the checkout.
     * Do not change the CWD.
     *
     * @param branchName the branch name.
     */
    public static void checkoutBranchCommand(String branchName) {
        File branchFile = join(BRANCH_DIR, branchName);

        // Failure case.
        if (!branchFile.exists()) {
            System.out.println("No such branch exists.");
            System.exit(0);
        }
        // Read from target branch file.

        Branch sourceBranch = Branch.readFromFile(branchName);
        Commit sourceCommit = Commit.readFromFile(sourceBranch.getCommitHash());

        // Get the name of current branch.
        String currentBranchName = join(GITLET_DIR, readHEADERFromFile()).getName();
        // Failure case. Check if two branches are same branch.
        if (branchName.equals(currentBranchName)) {
            System.out.println("No need to checkout the current branch.");
            System.exit(0);
        }

        // Find the untracked file.
        // The current commit.
        Commit lastCommit = Commit.readFromFile(getLastCommitHash());
        List<String> fileLists = plainFilenamesIn(CWD);
        List<String> untrackedFileLists = new ArrayList<>();
        if (fileLists != null) {
            untrackedFileLists = fileLists.stream().filter((f) -> lastCommit.getStaging().getIndex().containsKey(
                    f)).collect(Collectors.toList());

        }

        for (String untrackedFile : untrackedFileLists) {
            if (sourceCommit.getStaging().getIndex().containsKey(untrackedFile)) {
                System.out.println(
                        "There is an untracked file in the way; delete it, or add and commit it first.");
                System.exit(0);
            }
        }

        // Overwriting the files that in the set from current commit.
        for (String key : lastCommit.getStaging().getIndex().keySet()) {
            if (sourceCommit.getStaging().getIndex().containsKey(key)) {
                // Replace.
                String id = sourceCommit.getStaging().getIndex().get(key);
                String content = readContentsAsString(join(BLOBS_DIR, id));
                writeContents(join(CWD, key), content);
            } else {
                // Remove.
                restrictedDelete(join(CWD, key));
            }
        }

        // Change HEADER and index
        StagingArea newIndex = sourceCommit.getStaging();
        newIndex.saveStagingToFile();

        saveTheHEADER(branchFile);
    }

    /**
     * gitlet log command.
     * <p>
     * Shows commit’s history. Display commits with the most recent at the top.
     *
     * <li>There is a === before each commit and an empty line after it. </li>
     * <li>As in real Git, each entry displays the unique SHA-1 id of the commit object. </li>
     * <li>The timestamps displayed in the commits reflect the current timezone, not UTC.
     * Your timezone might be different depending on where you live, and that’s fine.</li>
     * <li>For merge commits (those that have two parent commits), add a line just below the first.
     * where the two hexadecimal numerals following “Merge:” consist of
     * the first seven digits of the first and second parents’ commit ids, in that order.
     * The first parent is the branch you were on when you did the merge; the second is that of the merged-in branch. </li>
     *
     * <p>Error cases: No</p>
     * <p>Utilize Commit.toString() method</p>
     * <p>Commits history is a linked list data structure</p>
     */

    public static void logCommand() {
        String hash = getLastCommitHash();
        while (true) {
            Commit commit = Commit.readFromFile(hash);
            System.out.println(commit.formattedCommitHistory(hash));

            if(commit.isInitCommit()) break;
            hash = commit.getParent();
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
        Commit lastCommit = Commit.readFromFile(getLastCommitHash());
        StagingArea indexFromCommit = lastCommit.getStaging();
    }

    //
    // Below is helper method
    //

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
        String relativePath = Repository.GITLET_DIR.toPath().relativize(branch.getBranchFile().toPath()).toString();
        writeContents(HEAD_POINTER, relativePath);
    }


    /**
     * Save the file relative path into HEADER file.
     *
     * @param file the file to which the header is pointing. The file is either a branch file
     *             that stores a commit hash, or just a commit object/file.
     */
    private static void saveTheHEADER(File file) {
        String relativePath = Repository.GITLET_DIR.toPath().relativize(file.toPath()).toString();
        writeContents(HEAD_POINTER, relativePath);
    }

    /**
     * Return the content of HEADER, the ref of the branch file.
     *
     * @return the content of HEADER, the ref of the branch file.
     */
    private static String readHEADERFromFile() {
        return readContentsAsString(Repository.HEAD_POINTER);
    }

    /**
     * Get the hash of last commit from branch file.
     *
     * @return the hash string.
     */
    private static String getLastCommitHash() {
        try {
            String ref = readHEADERFromFile();
            String hash = readContentsAsString(join(Repository.GITLET_DIR, ref));

            return hash;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Return the content of file by specified id/hash.
     *
     * @param targetFileID id/hash of the file.
     * @return the content of file.
     */
    private static String readBlobContent(String targetFileID) {
        return readContentsAsString(join(BLOBS_DIR, targetFileID));
    }
}
