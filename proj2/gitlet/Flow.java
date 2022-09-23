package gitlet;

import java.util.List;

import static gitlet.Repository.*;
import static gitlet.Utils.plainFilenamesIn;

public class Flow {

    private String[] args;

    /**
     * SOME Error messages
     */
    public static final String NO_PARAMETER = "Please enter a command. ";
    public static final String NO_EXIST_PARA = "No command with that name exists.";
    public static final String INCORRECT_PARA = "Incorrect operands.";
    private static final String NOT_INIT_YET = "Not in an initialized Gitlet directory.";

    private static final String INIT_CONFLICT = "A Gitlet version-control system already exists in the current directory.";
    private static final String FILE_NOT_EXIST = "File does not exist.";
    private static final String COMMIT_MSG_MISSING = "Please enter a commit message.";
    private static final String NO_CHANGES = "No changes added to the commit.";
    private static final String NO_REASON_TO_REMOVE = "No reason to remove the file.";


    public Flow(String[] args) {
        // Check the args
        if (args.length == 0) {
            exitWithInfo(Flow.NO_PARAMETER);
        }
        this.args = args;
    }

    public void init() {
        if (args.length != 1) {
            exitWithInfo(INCORRECT_PARA);
        }

        if (isInitialized()) {
            exitWithInfo(INIT_CONFLICT);
        }

        // Create folders
        GITLET_DIR.mkdir();
        OBJECTS_DIR.mkdir();
        COMMITS_DIR.mkdir();
        REFS_DIR.mkdir();
        REFS_HEAD_DIR.mkdir();

        // Create commit
        String MESSAGE = "initial commit";
        Index index = new Index();
        Commit c = new Commit(MESSAGE, index, null);
        String hashcode = c.saveWithZeroTime();
        // Deal with the branch
        setBranch("master", hashcode);
        // Update the hash HEAD points to TODO: Optimize by Path.xxx
        setHead("refs/heads/master");
    }

    /** Add means "UPDATE".
     * Staging the file for addition. Addition means never delete blob files.
     * Add the filename and its hashcode to the Index (map), and save the blob. In this way, the file has been staged.
     * To list the staging files, compare index and last commit.
     */
    public void add() {
        checkArgsAndEnv(2);

        String filename = args[1];
        // Read index
        Index index = new Index();
        // Add file (file name, Hashcode)
        if (fileExist(filename)) {
            /** Prepare a blob for file */
            Blob blob = new Blob(filename);

            blob.save();
            index.add(blob);
        } else {
            exitWithInfo(FILE_NOT_EXIST);
        }
    }

    /** A commmit just save an Index, parent(hashcode) and other metadata (timestamp and message).
     *  Most of the work has been done in Index.
     *  After save, the index is same with commit, means staging area is cleared.
     */
    public void commit() {
        checkArgsAndEnv(2, COMMIT_MSG_MISSING);

        String message = args[1];
        // Read index, if empty, exit.
        Index index = new Index();
        if (index.isEmpty()) {
            exitWithInfo(NO_CHANGES);
        }

        // Parent commit
        String parentHash = getLastCommitHash();
        // Create a commit
        Commit cm = new Commit(message, index, parentHash);
        // Write down
        String hashcode = cm.save();
        // Update the head of branch
        setBranch("master", hashcode);
    }

    /**
     * Delete the key(filename) in index. If traced in commit, rm(Unix) in working DIR.
     * Or Error
     * Changes to Index.
     * */
    public void rm() {
        checkArgsAndEnv(2);

        String filename = args[1];
        // Read from file
        Index index = new Index();
        Commit lastCommit = readLastCommit();

        // Delete source file if traced in commit
        if(lastCommit.hasFile(filename)) {
            Blob blob = new Blob(filename);
            blob.deleteInWorkingDIR();
        // remove will return value. If no key exist, return null
        } else if(index.remove(filename) == null) {
            exitWithInfo(NO_REASON_TO_REMOVE);
        }
    }

    /** Only need to read commits, like a linked list */
    public void log() {
        checkArgsAndEnv(1);

        String lastCommitHash = getLastCommitHash();
        while(lastCommitHash != null) {
            Commit lastCommit = Commit.readFromHash(lastCommitHash);
            logCommit(lastCommitHash, lastCommit);

            lastCommitHash = lastCommit.getParentHash();
        }
    }

    public void globalLog() {
        checkArgsAndEnv(1);

        List<String> commitsList = plainFilenamesIn(COMMITS_DIR);
        for (String commitHash: commitsList) {
            Commit commit = Commit.readFromHash(commitHash);
            logCommit(commitHash, commit);
        }
    }




    public void exitWithInfo(String info) {
        System.out.println(info);
        System.exit(0);
    }

    private void checkArgsAndEnv(int validArgNum) {
        checkArgsAndEnv(validArgNum, INCORRECT_PARA);
    }
    /** Check Gitlet env and args */
    private void checkArgsAndEnv(int validArgNum, String errorMsg) {
        /** Check args and Judge if initialized. */
        if(args.length != validArgNum) {
            exitWithInfo(errorMsg);
        }
        if (!isInitialized()) {
            exitWithInfo(NOT_INIT_YET);
        }
    }
}
