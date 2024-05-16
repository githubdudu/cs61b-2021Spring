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

    /**
     * Parent commit in the commit tree.
     */
    private String parent;
    /**
     * For merge commits, they have two parent commits.
     */
    private String secondParent;

    public Commit(StagingArea staging, Date date, String message, String parent) {
        this(staging, date, message, parent, null);
    }

    public Commit(StagingArea staging, Date date, String message, String parent, String secondParent) {
        this.staging = staging;
        this.date = date;
        this.message = message;
        this.parent = parent;
        this.secondParent = secondParent;
    }
    /**
     * Save commit instance to file under COMMITS_DIR directory, file name is the hash of instance.
     */
    public void saveCommitToFile() {

        if (!Repository.COMMITS_DIR.exists()) {
            Repository.COMMITS_DIR.mkdir();
        }
        writeObject(join(Repository.COMMITS_DIR, getHash()), this);
    }

    /**
     * Return hash of This Commit instance.
     *
     * @return hash of This Commit instance.
     */
    public String getHash() {
        return Utils.sha1((Object) serialize(this));
    }

    /**
     * Read commit by its hash from COMMITS_DIR directory.
     *
     * @param hash The 40 length hash string of that commit object
     * @return A Commit object
     */
    public static Commit readFromFile(String hash) {
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

    public String getParent() {
        return parent;
    }

    public String getSecondParent() {
        return secondParent;
    }

    public boolean isInitCommit() {
        return parent == null;
    }
    public boolean isMerged() {
        return secondParent == null;
    }

    @Override
    public String toString() {
        String template = isMerged() ? "" : String.format(
                "%s %s%n",
                parent.substring(0, 6),
                secondParent.substring(0, 6));
        return String.format(template + "Date: %1$ta %1$tb %1$td %1$tT %1$tY %1$tz%n%2$s", date, message);
    }

    public String formattedCommitHistory(String id) {
        return String.format("===%ncommit %s%n%s%n", id, this);
    }
}
