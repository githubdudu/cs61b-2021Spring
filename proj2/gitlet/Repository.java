package gitlet;

import java.io.File;
import java.util.HashMap;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *
 *  Abstract the code just like in the lab 6.
 *  @author dudu
 */
public class Repository {
    /**
     * Do required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     *
     * .gitlet/ -- top level folder for all gitlet
     *    - objects/ -- folder containing all of blobs and commits.
     *    - objects/commits -- folder containing all of commits.
     *    - refs/heads -- folder containing the branches info. File name is branch name, and contents is hashcode of commits.
     *    - index/ -- folder containing the stage files.
     *    - HEAD -- file containing the head ref.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The objects directory. Saving objects. */
    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");
    /** The commits directory. Saving commits. */
    public static final File COMMITS_DIR = join(GITLET_DIR, "objects", "commits");
    /** The reference directory. Save pairs of hashcode and name */
    public static final File REFS_DIR = join(GITLET_DIR, "refs");
    public static final File REFS_HEAD_DIR = join(GITLET_DIR, "refs", "heads");
    /** The stage file. Save the staged file info */
    public static final File STAGE_FILE = join(GITLET_DIR, "index");
    /** The HEAD file. */
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");


    public static boolean isInitialized() {
        // In git source code, there is a soft test for a "look-like" git directory
        // Includes: - an objects/ directory, - a refs/ directory, - a HEAD file
        if (!GITLET_DIR.exists() || !COMMITS_DIR.exists() || !REFS_HEAD_DIR.exists() || !HEAD_FILE.exists()) {
            return false;
        }
        // Check HEAD file
        if (!isHeadFileValid()) {
            return false;
        }
        return true;
    }

    private static boolean isHeadFileValid() { // TODO
        return true;
    }

    public static void setBranch(String branch, String hashcode) {
        File f = join(REFS_HEAD_DIR, branch);
        writeContents(f, hashcode);
    }

    public static void setHead(String path) {
        // Set HEAD
        writeContents(HEAD_FILE, path);
    }

    public static boolean fileExist(String filename) {
        return join(CWD, filename).exists();
    }

    public static String getLastCommitHash() {
        String headPath = readContentsAsString(HEAD_FILE);
        // Parse path string
        String branchName = headPath.substring(headPath.lastIndexOf("/") + 1);
        File branchHead = join(REFS_HEAD_DIR, branchName);
        return readContentsAsString(branchHead);
    }

    public static Commit readLastCommit() {
        return Commit.readFromHash(getLastCommitHash());
    }

    public static void logCommit(String hashcode, Commit commit) {
        System.out.println("===");
        System.out.println("commit " + hashcode);
        commit.log();
        System.out.println();
    }
}
