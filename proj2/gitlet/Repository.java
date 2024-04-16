package gitlet;

import java.io.File;
import java.util.Date;
import java.util.Map;

import static gitlet.Utils.*;

// TODO: any imports you need here

/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author Dudu
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

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
    public static final File HEAD_POINTER = join(GITLET_DIR, "HEAD");
    /**
     * The index file for staging.
     */
    public static final File INDEX = join(GITLET_DIR, "index");
    /* TODO: fill in the rest of this class. */
    public static void init() {
        initGitletFolder();

        // Create the init commit
        StagingArea initStage = new StagingArea();
        Date initDate = new Date(0);
        String initMessage = "initial commit";

        Commit commit = new Commit(initStage, initDate, initMessage);
        String commitHash = commit.saveCommit();

        // Create a master branch and save it.
        // Save the hash of commit as the content of branch head
        Branch defaultBranch = new Branch();
        defaultBranch.setContent(commitHash);
        defaultBranch.saveFile();

        // Create a HEAD pointer.
        // Save the relative path of saved branch object to pointer HEAD
        saveTheHEAD(defaultBranch);
        // TODO: get the relative path from two absulute paths.
    }

    private static void saveTheHEAD(Branch branch) {
        writeContents(HEAD_POINTER, branch.getBranchFileRelativePath());
    }

    /**
     * Create as many as the folders that used in a gitlet system.
     */
    private static void initGitletFolder() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current" +
                    " directory.");
            System.exit(0);
        }

        GITLET_DIR.mkdir();
        BRANCH_DIR.mkdir();
        BLOBS_DIR.mkdir();
        COMMITS_DIR.mkdir();
    }

    public static void add(String filename) {
        File file = join(CWD, filename);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        // Calculate the sha1 for the file
        String fileHash = sha1(readContents(file));

        // Read the index from current commit

        // Read the index from current staging index

        // Search the fileHash in these indexes.


    }

}
