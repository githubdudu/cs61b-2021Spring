package gitlet;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

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
    public static final File BRANCH_DIR = join(GITLET_DIR, "heads");

    public static final File BLOBS_DIR = join(GITLET_DIR, "objects");

    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");

    /* TODO: fill in the rest of this class. */
    public static void init() {
        initGitletFolder();
        Commit commit = new Commit();
        String commitHash = commit.saveCommit();

        File branch = join(GITLET_DIR, "heads/master");
        writeContents(branch, commitHash);

        File head = join(GITLET_DIR, "HEAD");
        writeContents(head, branch.getPath() + "master");
    }

    public static void initGitletFolder() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current" +
                    " directory.");
            System.exit(0);
        }

        GITLET_DIR.mkdir();

        File file = join(GITLET_DIR, "heads");
        file.mkdir();

        file = join(GITLET_DIR, "objects");
        file.mkdir();

        file = join(GITLET_DIR, "commits");
        file.mkdir();
    }

    public static void add(String filename) {
        File file = join(CWD, filename);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        // Calculate the sha1 for the file
        String fileHash = sha1(readContents(file));

        // Read from current commit



        // Read from staging file
        File stagingFile = join(GITLET_DIR, "staging");
        TreeSet<String> stagingArea;
        if (stagingFile.exists()) {
            stagingArea = readObject(stagingFile, TreeSet.class);
        } else {
            stagingArea = new TreeSet<>();
        }

        if(stagingArea.contains(fileHash)) {

        }
    }

}
