package gitlet;

import static gitlet.Utils.*;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Represents a gitlet commit object.
 * <p>
 * No tree structure in classic Git. Just deal with files in commits.
 * Metadata consists only of a timestamp and message.
 *
 * @author dudu
 */
public class Commit implements Serializable {

    private static final String FILE_DOES_NOT_EXIST_IN_COMMITS = "File does not exist in that commit.";
    private static final String NO_COMMITS_WITH_ID = "No commit with that id exists.";
    /** Last commit hashcode */
    private String parentHash;
    /** The timestamp of this Commit. The timestamp for initial commit is 0(in Unix). */
    private Date timestamp;
    /** The message of this Commit. */
    private String message;
    /** The traced files. Key: filename, Value: Hash Value */
    private Index files;

    public Commit(String message, Index filenames, String parentHash) {
        this.message = message;
        this.parentHash = parentHash;
        this.files = filenames;
    }

    public String save() {
        timestamp = new Date();
        byte[] bStream = serialize(this);
        String hashcode = sha1(bStream);

        File f = new File(Repository.COMMITS_DIR, hashcode);
        writeContents(f, bStream);
        return hashcode;
    }

    /** Used for init() */
    public String saveWithZeroTime() {
        timestamp = new Date(Long.valueOf(0));
        byte[] bStream = serialize(this);
        String hashcode = sha1(bStream);

        File f = new File(Repository.COMMITS_DIR, hashcode);
        writeContents(f, bStream);

        return hashcode;
    }

    public boolean hasFile(String filename) {
        return files.hasFile(filename);
    }

    public boolean hasFile(Blob b) {
        return files.hasFile(b);
    }

    public boolean hasSameFile(String filename, String hashcode) {
        return files.hasSameFile(filename, hashcode);
    }

    public boolean hasSameFile(Blob b) {
        return files.hasSameFile(b);
    }

    public void recoverFile(String filename) {
        /** Use "try-catch" in case the blob is not exist, or not in commit */
        try {
            files.recoverFile(filename);
        } catch (GitletException e) {
            throw error(FILE_DOES_NOT_EXIST_IN_COMMITS);
        }
    }
    public String getParentHash() {
        return parentHash;
    }

    public String getMessage() {
        return message;
    }

    public void log() { //TODO the merge case
        System.out.println(toString());
    }

    @Override
    public String toString() {
        /** This Formatter is not necessary. This is enforced showing english version Monday,
         * not Chinese charactor "Monday".
         * In git, the format just use locale. So this can be removed when submit to Gradescope */
        Formatter formatter = new Formatter(Locale.US);
        return formatter.format("Date: %1$ta %1$tb %1$td %1$tT %1$tY %1$tz\n%2$s", timestamp, message).toString();
    }


    public static Commit readFromHash(String hashcode) {
        File commit = join(Repository.COMMITS_DIR, hashcode);
        if (commit.exists()) {
            return readObject(commit, Commit.class);
        } else {
            throw error(NO_COMMITS_WITH_ID);
        }
    }
}
