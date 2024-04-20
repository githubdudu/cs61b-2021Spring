package gitlet;

import java.io.Serializable;
import java.util.Date;

import static gitlet.Utils.*;

/**
 * Represents a gitlet commit object. Helper to read and write Commit file/object and get staging
 * file from commit.
 *
 * @author hdon694
 */
public class Commit implements Serializable {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /**
     * The staging snap in this Commit.
     */
    private StagingArea staging;
    /**
     * The time stamp of this Commit. Using The (Unix) Epoch.
     */
    private Date date;

    /**
     * The message of this Commit.
     */
    private String message;

    public Commit(StagingArea staging, Date date, String message) {
        this.staging = staging;
        this.date = date;
        this.message = message;
    }

    /**
     * Save commit instance to file under COMMITS_DIR directory, file name is the hash of instance.
     *
     * @return hash of the file.
     */
    public String saveCommitToFile() {
        String hash = Utils.sha1(serialize(this));
        writeObject(join(Repository.COMMITS_DIR, hash), this);
        return hash;
    }

    /**
     * Read commit by its hash from COMMITS_DIR directory.
     *
     * @param hash The 40 length hash string of that commit object
     * @return A Commit object
     */
    public static Commit readCommitFromFile(String hash) {
        return readObject(join(Repository.COMMITS_DIR, hash), Commit.class);
    }

    /**
     * Return the stage relates to this Commit.
     *
     * @return staging saved in Commit
     */
    public StagingArea getStaging() {
        return staging;
    }
}
