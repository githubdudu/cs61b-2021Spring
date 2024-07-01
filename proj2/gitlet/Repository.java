package gitlet;

import java.io.File;
import java.util.*;

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
        Commit commit = new Commit(initStage, initDate, initMessage, null);
        commit.saveCommitToFile();

        // Create a master branch and save it.
        // Save the hash of commit as the content of branch head.
        Branch defaultBranch = new Branch();
        defaultBranch.setCommitHash(commit.getHash());
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
     * In real git, multiple files may be added at once. In gitlet, only one file may be added at
     * a time.
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
        String fileHash = saveBlobContent(file);

        // Read the index from current staging index, update, and then save.
        StagingArea indexStaging = getCurrentStaging();
        indexStaging.put(filename, fileHash);
        indexStaging.saveStagingToFile();

    }

    /**
     * gitlet commit [message] command.
     * A commit command that is not a merge commit.
     *
     * @param message the commit message.
     */
    public static void commitCommand(String message) {
        commitCommand(message, null);
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
    public static void commitCommand(String message, String secondParent) {
        // Read the index from current commit.
        Commit lastCommit = getLastCommit();

        // Read the index from current staging index.
        StagingArea indexStaging = getCurrentStaging();

        // Failure case 1
        if (lastCommit.hasSameIndex(indexStaging)) {
            System.out.println("No changes added to the commit.");
            System.exit(0);
        }

        // Failure case 2
        if (message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }

        Commit newCommit = new Commit(indexStaging, new Date(), message, getLastCommitHash(),
                secondParent);
        newCommit.saveCommitToFile();

        // Read the branch from branch file.
        Branch branch = getCurrentBranch();
        // Move the pointer of branch
        branch.setCommitHash(newCommit.getHash());
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
        Commit lastCommit = getLastCommit();

        // Read the index from current staging index.
        StagingArea indexStaging = getCurrentStaging();

        // Failure cases
        if (!lastCommit.containsFile(filename) && !indexStaging.containsFile(
                filename)) {
            System.out.println("No reason to remove the file.");
            System.exit(0);
        }

        indexStaging.removeFile(filename);
        indexStaging.saveStagingToFile();

        if (lastCommit.containsFile(filename)) {
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
     * @param commitID the id of commit, can be 40 full-length hash or shorter hash.
     * @param filename the name of file that to be recovered.
     */
    public static void checkoutCommand(String commitID, String filename) {
        String commitIDLength40 = getFullCommentID(commitID);
        // Failure case
        if (commitIDLength40 == null || !join(COMMITS_DIR, commitIDLength40).exists()) {
            System.out.println("No commit with that id exists.");
            System.exit(0);
        }

        Commit source = Commit.readFromFile(commitIDLength40);
        String targetFileID = source.getFileHash(filename);
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

        // Get the name of current branch.
        String currentBranchName = getCurrentBranchName();
        // Failure case. Check if two branches are same branch.
        if (branchName.equals(currentBranchName)) {
            System.out.println("No need to checkout the current branch.");
            System.exit(0);
        }

        // Read from target branch file.
        Branch sourceBranch = Branch.readFromFile(branchName);
        checkoutTargetCommit(sourceBranch.getCommitHash());

        // Change HEADER
        saveTheHEADER(sourceBranch);
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
     * where the two hexadecimal numerals following "Merge:" consist of
     * the first seven digits of the first and second parents' commit ids, in that order.
     * The first parent is the branch you were on when you did the merge; the second is that of the
     * merged-in branch. </li>
     *
     * <p>Error cases: No</p>
     * <p>Utilize Commit.toString() method</p>
     * <p>Commits history is a linked list data structure</p>
     */
    public static void logCommand() {
        String hash = getLastCommitHash();
        while (true) {
            Commit commit = Commit.readFromFile(hash);
            // Prepend '===' and append empty line to the commit message.
            System.out.printf("===%n%s%n", commit);

            if (commit.isInitCommit()) {
                break;
            }
            hash = commit.getParent();
        }
    }

    /**
     * gitlet global-log command.
     * Like log, except displays information about all commits ever made. The order of the commits
     * does not matter.
     */
    public static void globalLogCommand() {
        List<String> commitFiles = plainFilenamesIn(COMMITS_DIR);
        if (commitFiles == null) {
            return;
        }
        for (String commitFile : commitFiles) {
            Commit commit = Commit.readFromFile(commitFile);
            // Prepend '===' and append empty line to the commit message.
            System.out.printf("===%n%s%n", commit);
        }
    }

    /**
     * gitlet find [commit message] command.
     * <p>
     * Prints out the ids of all commits that have the given commit message, one per line.
     * If there are multiple such commits, it prints the ids out on separate lines.
     * The commit message is a single operand; to indicate a multiword message,
     * put the operand in quotation marks, as for the commit command below.
     * <p>
     * Failure cases: If no such commit exists, prints the error message "Found no commit with that
     * message."
     *
     * @param message a single operand; to indicate a multiword message, put the operand in
     *                quotation marks.
     */
    public static void findCommand(String message) {
        List<String> commitFiles = plainFilenamesIn(COMMITS_DIR);
        if (commitFiles == null) {
            return;
        }
        boolean found = false;
        for (String commitFile : commitFiles) {
            Commit commit = Commit.readFromFile(commitFile);
            if (commit.findMessage(message)) {
                found = true;
                System.out.println(commit.getHash());
            }
        }
        if (!found) {
            System.out.println("Found no commit with that message.");
        }
    }

    /**
     * gitlet status command.
     * <p>
     * For example:
     * <p>
     * === Branches ===
     * <p>
     * *master
     * <p>
     * other-branch
     * <p>
     * <br>
     * === Staged Files ===
     * <p>
     * wug.txt
     * <p>
     * wug2.txt
     * <p>
     * <br>
     * === Removed Files ===
     * <p>
     * goodbye.txt
     * <p>
     * <br>
     * === Modifications Not Staged For Commit ===
     * <p>
     * junk.txt (deleted)
     * <p>
     * wug3.txt (modified)
     * <p>
     * <br>
     * === Untracked Files ===
     * <p>
     * random.stuff
     * <p>
     *
     * <p>First, we say the entry in the index is equal when both fileName and fileHash are same.
     * <p>For the indexing of last commit: C1 and current indexing: C2, those staged files are
     * diff(C2, C1), removed files are diff(C1, C2) </p>
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
     *                 // If the file is not in the staging area, it is untracked.
     *                 // files present in the working directory but neither staged for addition nor
     *                 // tracked.
     *                 // This includes files that have been staged for removal, but then re-created
     *                 // without Gitlet’s knowledge
     */
    public static void statusCommand() {

        // Read the index from current commit
        Commit lastCommit = getLastCommit();
        StagingArea currentStaging = getCurrentStaging();
        Set<Map.Entry<String, String>> lastIndexingFileSet = lastCommit.fileEntrySet();
        Set<Map.Entry<String, String>> currentStagingFileSet = currentStaging.fileEntrySet();
        TreeSet<String> stagedFiles = new TreeSet<>();
        TreeSet<String> removedFiles = new TreeSet<>();
        // Staged files
        for (Map.Entry<String, String> entry : currentStagingFileSet) {
            if (!lastIndexingFileSet.contains(entry)) {
                stagedFiles.add(entry.getKey());
            }
        }
        // Removed files
        for (String filename : lastCommit.getFileNames()) {
            if (!currentStaging.containsFile(filename)) {
                removedFiles.add(filename);
            }
        }
        // All files in working directory
        List<String> fileLists = plainFilenamesIn(CWD);
        // Prepare modified files
        Set<String> modifiedFiles = new TreeSet<>();
        Set<String> untrackedFiles = new TreeSet<>();

        // Prepare modified files - Deleted files
        for (Map.Entry<String, String> entry : currentStagingFileSet) {
            if (fileLists == null || !fileLists.contains(entry.getKey())) {
                modifiedFiles.add(entry.getKey() + " (deleted)");
            }
        }

        // Prepare the untracked files
        if (fileLists != null) { // Might be null if the directory is empty
            for (String fileName : fileLists) {

                if (!currentStaging.containsFile(fileName)) {
                    untrackedFiles.add(fileName);
                } else {
                    Map.Entry<String, String> fileEntry = new AbstractMap.SimpleEntry<>(
                            fileName,
                            sha1(readContentsAsString(join(CWD, fileName))));
                    if (!currentStagingFileSet.contains(fileEntry)) {
                        modifiedFiles.add(fileName + " (modified)");
                    }
                }
            }
        }
        // Display the branches
        System.out.println("=== Branches ===");
        printBranchStatus();
        System.out.println();
        // Display the staged files
        System.out.println("=== Staged Files ===");
        for (String fileName : stagedFiles) {
            System.out.println(fileName);
        }
        System.out.println();
        // Display the removed files
        System.out.println("=== Removed Files ===");
        for (String fileName : removedFiles) {
            System.out.println(fileName);
        }
        System.out.println();
        // Display the modifications not staged for commit
        System.out.println("=== Modifications Not Staged For Commit ===");
        for (String fileName : modifiedFiles) {
            System.out.println(fileName);
        }
        System.out.println();
        // Display the untracked files
        System.out.println("=== Untracked Files ===");
        for (String fileName : untrackedFiles) {
            System.out.println(fileName);
        }
        System.out.println();
    }

    /**
     * gitlet branch [branch name] command.
     * <p>
     * Creates a new branch with the given name, and points it at the current head commit.
     * <p>
     * Failure cases: If a branch with the given name already exists,
     * print the error message A branch with that name already exists.
     *
     * @param branchName the new branch name.
     */
    public static void branchCommand(String branchName) {
        // Failure case
        if (join(BRANCH_DIR, branchName).exists()) {
            System.out.println("A branch with that name already exists.");
            System.exit(0);
        }

        Branch newBranch = new Branch(getCurrentBranch(), branchName);
        newBranch.saveBranchToFile();
    }

    /**
     * gitlet rm-branch [branch name] command.
     * <p>
     * Deletes the branch with the given name.
     * <p>
     * Failure cases:
     * If a branch with the given name does not exist, print the error message
     * A branch with that name does not exist.
     * If you try to remove the branch you’re currently on, print the error message
     * Cannot remove the current branch.
     *
     * @param branchName
     */
    public static void rmBranchCommand(String branchName) {
        File branchFile = join(BRANCH_DIR, branchName);
        // Failure case
        if (!branchFile.exists()) {
            System.out.println("A branch with that name does not exist.");
            System.exit(0);
        }

        // Failure case
        if (branchName.equals(getCurrentBranchName())) {
            System.out.println("Cannot remove the current branch.");
            System.exit(0);
        }

        // Restrict delete branch file
        if (!branchFile.getParentFile().getName().equals("heads")) {
            throw new IllegalArgumentException("Not deleting branch file.");
        }
        branchFile.delete();
    }

    /**
     * gitlet reset [commit id] command.
     * <p>
     * Checks out all the files tracked by the given commit.
     * <p>
     * Failure cases:
     * If no commit with the given id exists, print "No commit with that id exists."
     *
     * @param commitID the commit id that the files will be checked out.
     */
    public static void resetCommand(String commitID) {
        String commitIDLength40 = getFullCommentID(commitID);
        // Failure case
        if (commitIDLength40 == null || !join(COMMITS_DIR, commitIDLength40).exists()) {
            System.out.println("No commit with that id exists.");
            System.exit(0);
        }

        // Change files in working directory.
        checkoutTargetCommit(commitIDLength40);
        // Change the currentBranch head.
        Branch currentBranch = getCurrentBranch();
        currentBranch.setCommitHash(commitIDLength40);
        currentBranch.saveBranchToFile();
    }

    /**
     * gitlet merge [branch name] command.
     * <p>
     * Merges files from the given branch into the current branch.
     * <p>
     * If the split point is the same commit as the given branch, then we do nothing;
     * the merge is complete, and the operation ends with the message
     * "Given branch is an ancestor of the current branch."
     * <p>
     * If the split point is the current branch, then the effect is to check out the
     * given branch, and the operation ends after printing the message
     * "Current branch fast-forwarded."
     * <p>
     * Otherwise, we continue with the steps below.
     * <p>
     * If the file is same in both branches.
     * <p>
     *      A A0 A* A   B* B  B0  C* C  C0   D  D*  D  D
     *      A A1 A  A*  B* B  B0  C  C* C1   D1 D1  D* D1
     *      A A1 A  A*  B  B* B1  C* C  C0   D2 D2  D2 D*
     * Merge:A A1 A  A*  B  B* B1  C  C* C1   conflict
     *
     * @param branchNameMergeFrom the branch name that will be merged from.
     */
    public static void mergeCommand(String branchNameMergeFrom) {
        mergeFailCases(branchNameMergeFrom);
        String currentBranchHash = getLastCommitHash();
        String targetBranchHash = Branch.readFromFile(branchNameMergeFrom).getCommitHash();

        // Merge is complete or fast-forwarded.
        String lca = lowestCommonAncestor(currentBranchHash, targetBranchHash);
        if (lca.equals(targetBranchHash)) {
            System.out.println("Given branch is an ancestor of the current branch.");
            System.exit(0);
        }
        if (lca.equals(currentBranchHash)) {
            checkoutBranchCommand(branchNameMergeFrom);
            System.out.println("Current branch fast-forwarded.");
            System.exit(0);
        }

        // Otherwise, continue merge.
        // it is like diff3 in git.
        Commit currentCommit = getLastCommit();
        Commit givenCommit = Commit.readFromFile(targetBranchHash);
        Commit lcaCommit = Commit.readFromFile(lca);
        StagingArea newIndex = getCurrentStaging();
        // If there is an untracked file
        if (hasUntrackedFile(givenCommit)) {
            System.out.println(
                    "There is an untracked file in the way; "
                            + "delete it, or add and commit it first.");
            System.exit(0);
        }

        for (String fileName : givenCommit.getFileNames()) {
            // Case 1
            if (modified(fileName, lcaCommit, givenCommit) && notModified(fileName, lcaCommit,
                    currentCommit)) {
                writeContents(
                        join(CWD, fileName),
                        readBlobContent(givenCommit.getFileHash(fileName)));
                newIndex.put(fileName, givenCommit.getFileHash(fileName));
            }
            // Case 5
            if (!lcaCommit.containsFile(fileName) && !currentCommit.containsFile(fileName)) {
                writeContents(
                        join(CWD, fileName),
                        readBlobContent(givenCommit.getFileHash(fileName)));
                newIndex.put(fileName, givenCommit.getFileHash(fileName));
            }
        }
        // case 6
        for (String fileName : currentCommit.getFileNames()) {
            if (notModified(fileName, lcaCommit, currentCommit) && !givenCommit.containsFile(
                    fileName)) {
                restrictedDelete(fileName);
                newIndex.removeFile(fileName);
            }
        }
        // case 8
        boolean isConflict = false;
        for (String fileName : lcaCommit.getFileNames()) {
            if (isConflict(fileName, lcaCommit, currentCommit, givenCommit)) {
                String contentOfCurrentFile = currentCommit.containsFile(fileName)
                        ? readBlobContent(currentCommit.getFileHash(fileName)) : "";
                String contentOfGivenFile = givenCommit.containsFile(fileName)
                        ? readBlobContent(givenCommit.getFileHash(fileName)) : "";
                String contentOfMerged = String.format("<<<<<<< HEAD\n%s=======\n%s>>>>>>>",
                        contentOfCurrentFile, contentOfGivenFile);

                writeContents(join(CWD, fileName), contentOfMerged);
                String fileHash = saveBlobContent(join(CWD, fileName));
                newIndex.put(fileName, fileHash);
                isConflict = true;
            }
        }
        newIndex.saveStagingToFile();
        commitCommand(
                String.format("Merged %s into %s.", branchNameMergeFrom, getCurrentBranchName()),
                targetBranchHash);
        if (isConflict) {
            System.out.println("Encountered a merge conflict.");
        }
    }

    private static void mergeFailCases(String branchNameMergeFrom) {
        // If there are staged additions or removals present.
        if (!getLastCommit().hasSameIndex(getCurrentStaging())) {
            System.out.println("You have uncommitted changes.");
            System.exit(0);
        }
        // If the given branch does not exist.
        if (!join(BRANCH_DIR, branchNameMergeFrom).exists()) {
            System.out.println("A branch with that name does not exist.");
            System.exit(0);
        }
        // If attempting to merge a branch with itself
        if (branchNameMergeFrom.equals(getCurrentBranchName())) {
            System.out.println("Cannot merge a branch with itself.");
            System.exit(0);
        }

    }

    private static boolean modified(String file, Commit commitA, Commit commitB) {
        return commitA.containsFile(file) && commitB.containsFile(file)
                && !commitA.getFileHash(file).equals(commitB.getFileHash(file));
    }

    private static boolean notModified(String file, Commit commitA, Commit commitB) {
        return commitA.containsFile(file) && commitB.containsFile(file)
                && commitA.getFileHash(file).equals(commitB.getFileHash(file));
    }

    private static boolean isConflict(String file, Commit commitA, Commit commitB, Commit commitC) {
        return (modified(file, commitB, commitC))
                || (!commitC.containsFile(file) && modified(file, commitA, commitB))
                || (!commitB.containsFile(file) && modified(file, commitA, commitC));
    }


    //
    // Below is helper method
    //

    /**
     * Create as many as the folders that used in a gitlet system.
     */
    private static void initGitletFolder() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current"
                    + " directory.");
            System.exit(0);
        }

        GITLET_DIR.mkdir();
        BRANCH_DIR.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
    }

    /**
     * Save the branch relative path to HEADER file.
     * Windows users especially should beware that the file separator character
     * is / on Unix (or MacOS) and '\' on Windows.
     * So if you form file names in your program by concatenating some directory names
     * and a file name together with explicit /s or \s, you can be sure that it won’t work
     * on one system or the other.
     * Java provides a system-dependent file separator character
     * (System.getProperty("file.separator")), or you can use the multi-argument constructors to
     * File.
     *
     * @param branch the branch that the header is pointing to.
     */
    private static void saveTheHEADER(Branch branch) {
        String relativePath = "heads" + "/" + branch.getName();
        writeContents(HEAD_POINTER, relativePath);
    }


    /**
     * Return the content of HEADER, the ref of the branch file.
     *
     * @return the content of HEADER, the ref of the branch file.
     */
    private static String readHEADERFromFile() {
        String rawPath = readContentsAsString(Repository.HEAD_POINTER);
        return rawPath.replace("/", File.separator);
    }

    /**
     * Get the current staging area by reading file "index" from disk.
     *
     * @return the current staging area.
     */
    private static StagingArea getCurrentStaging() {
        return StagingArea.readFromFile();
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
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Get the last commit object.
     *
     * @return the last commit object.
     */
    private static Commit getLastCommit() {
        return Commit.readFromFile(getLastCommitHash());
    }

    /**
     * Get the current branch name.
     *
     * @return the current branch name.
     */
    private static String getCurrentBranchName() {
        return join(GITLET_DIR, readHEADERFromFile()).getName();
    }

    /**
     * Get the current branch object.
     *
     * @return the current branch object.
     */
    private static Branch getCurrentBranch() {
        return Branch.readFromFile(getCurrentBranchName());
    }

    /**
     * Save the content of file as blob with the hash of the file content as the file name.
     *
     * @param file the file to be saved as blob.
     * @return the hash of the file content.
     */
    private static String saveBlobContent(File file) {
        String fileHash = sha1(readContentsAsString(file));
        if (!BLOBS_DIR.exists()) {
            BLOBS_DIR.mkdir();
        }
        File blobFile = join(BLOBS_DIR, fileHash);
        if (!blobFile.exists()) {
            writeContents(blobFile, readContentsAsString(file));
        }
        return fileHash;
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

    /**
     * Checkout the target commit.
     *
     * @param commitId the commit id.
     */
    private static void checkoutTargetCommit(String commitId) {
        Commit sourceCommit = Commit.readFromFile(commitId);

        // Find the untracked file.
        // The current commit.
        Commit lastCommit = getLastCommit();

        if (hasUntrackedFile(sourceCommit)) {
            System.out.println(
                    "There is an untracked file in the way; "
                            + "delete it, or add and commit it first.");
            System.exit(0);
        }

        // Overwriting the files that in the set from current commit and blobs.
        for (String filename : lastCommit.getFileNames()) {
            // Remove.
            if (!sourceCommit.containsFile(filename)) {
                restrictedDelete(join(CWD, filename));
            }
        }
        for (String fileName : sourceCommit.getFileNames()) {
            if (!lastCommit.containsFile(fileName)) {
                // Add
                writeContents(join(CWD, fileName),
                        readBlobContent(sourceCommit.getFileHash(fileName)));
            } else if (!sourceCommit.getFileHash(fileName).equals(
                    lastCommit.getFileHash(fileName))) {
                // Replace
                writeContents(join(CWD, fileName),
                        readBlobContent(sourceCommit.getFileHash(fileName)));
            }
        }

        // Save the index staging file.
        sourceCommit.saveStagingToFile();
    }

    /**
     * Check if there is any untracked file in the working directory.
     *
     * @return true if there is any untracked file in the working directory.
     */
    private static boolean hasUntrackedFile(Commit targetCommit) {
        List<String> fileLists = plainFilenamesIn(CWD);

        if (fileLists != null) {
            for (String file : fileLists) {
                if (!getCurrentStaging().containsFile(file) && !getLastCommit().containsFile(
                        file) && targetCommit.containsFile(file)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Get the full commit id by full length of hash (40) or shorter hash.
     * Requirement for shortest length is 4.
     * Only return commit if no other object exists with a SHA-1 identifier that starts with the
     * same six digits.
     *
     * @param commitID the commit id. Maybe full length or shorter.
     * @return the full length of commit id.
     */
    private static String getFullCommentID(String commitID) {
        if (commitID.length() == 40) {
            return commitID;
        }
        if (commitID.length() < 4) {
            return null;
        }
        List<String> commitFiles = plainFilenamesIn(COMMITS_DIR);
        if (commitFiles == null) {
            return null;
        }

        // Count the number of files that start with the commitID.
        int count = 0;
        int index = 0;
        for (int i = 0; i < commitFiles.size(); i++) {
            if (commitFiles.get(i).startsWith(commitID)) {
                count++;
                index = i;
            }
        }
        if (count == 1) {
            return commitFiles.get(index);
        }
        return null;
    }

    /**
     * Print the branch status.
     * Entries should be listed in lexicographic order.
     */
    private static void printBranchStatus() {
        List<String> branchFiles = plainFilenamesIn(BRANCH_DIR);
        if (branchFiles == null) {
            return;
        }

        Collections.sort(branchFiles);
        // Print the current branch with a '*' in front of it.
        String currentBranch = getCurrentBranchName();
        // Print the rest of the branches.
        for (String branchFile : branchFiles) {
            if (branchFile.equals(currentBranch)) {
                System.out.printf("*%s%n", currentBranch);
            } else {
                System.out.println(branchFile);
            }
        }
    }

    /**
     * LCA of a DAG.
     * Given a DAG and two vertices v and w, find the lowest common ancestor (LCA) of v and w.
     * The LCA of v and w is an ancestor of v and w that has no descendants that are also ancestors
     * of v and w.
     *
     * @param commitA the hash of the current branch.
     * @param commitB the hash of the target branch.
     */
    private static String lowestCommonAncestor(String commitA, String commitB) {

        // 1. Define the depth of a vertex v in a DAG to be the length of the longest path from
        // a root to v.
        Map<String, Integer> depth = depthMap(commitA, commitB);

        Set<String> commitAAncestors = ancestors(commitA);
        Set<String> commitBAncestors = ancestors(commitB);
        commitAAncestors.retainAll(commitBAncestors);
        return commitAAncestors.stream().max(Comparator.comparingInt(depth::get)).get();
    }

    /**
     * Depth is max distance from root. The depth of root is 0.
     * We visit nodes in a depth-first fashion. We’ll encounter a lot of unseen vertices and
     * append them the visitStack, until at some point we’ll hit a root and set its depth to 0.
     * At this point we start backtracking, pop()-ing nodes from visit as soon as we compute their
     * depth.
     *
     * @param commitA the commit node A
     * @param commitB the commit node B
     * @return a map of the depth of each ancestor commit of A and B.
     */
    private static Map<String, Integer> depthMap(String commitA, String commitB) {
        Map<String, Integer> depth = new HashMap<>();
        Deque<String> visitStack = new LinkedList<>(); // use as stack: push(), peek(), pop()
        visitStack.push(commitA);
        visitStack.push(commitB);
        while (!visitStack.isEmpty()) {
            String currentHash = visitStack.peek();
            Commit currentCommit = Commit.readFromFile(currentHash);
            if (currentCommit.isInitCommit()) {
                depth.put(currentHash, 0);
                visitStack.pop();
            } else {
                String parent = currentCommit.getParent();
                if (parent != null && !depth.containsKey(parent)) {
                    visitStack.push(parent);
                }
                String secondParent = currentCommit.getSecondParent();
                if (secondParent != null && !depth.containsKey(secondParent)) {
                    visitStack.push(secondParent);
                }
                if (currentHash.equals(visitStack.peek())) {
                    int secondParentDepth = secondParent == null ? 0 : depth.get(secondParent);
                    int currentDepth = Math.max(depth.get(parent), secondParentDepth) + 1;
                    depth.put(currentHash, currentDepth);
                    visitStack.pop();
                }
            }
        }
        return depth;
    }

    /**
     * Get the ancestors of a commit.
     *
     * @param commitHash the commit hash.
     * @return the ancestors of a commit.
     */
    private static Set<String> ancestors(String commitHash) {
        Set<String> ancestors = new HashSet<>();
        Deque<String> visitStack = new LinkedList<>();
        visitStack.push(commitHash);
        while (!visitStack.isEmpty()) {
            String currentHash = visitStack.pop();
            if (!ancestors.contains(currentHash)) {
                ancestors.add(currentHash);
                Commit currentCommit = Commit.readFromFile(currentHash);
                String parent = currentCommit.getParent();
                if (parent != null) {
                    visitStack.push(parent);
                }
                String secondParent = currentCommit.getSecondParent();
                if (secondParent != null) {
                    visitStack.push(secondParent);
                }
            }
        }
        return ancestors;
    }
}
