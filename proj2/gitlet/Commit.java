package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
    /** The folder stores commits */
    static final File COMMIT_FOLDER = join(Repository.GITLET_DIR, "commits");
    /** The files contained in this Commit. */
    private Set<String> files;
    /** The time stamp of this Commit. Using The (Unix) Epoch. */
    private int time;

    /** The message of this Commit. */
    private String message;

    public Commit() {
        this(new HashSet<String>(), 0, "initial commit");
    }

    public Commit(Set<String> files, int time, String message) {
        this.files = files;
        this.time = time;
        this.message = message;
    }

    /* TODO: fill in the rest of this class. */
    public String saveCommit() {
        String hash = Utils.sha1(serialize(this));
        writeObject(join(COMMIT_FOLDER, hash), this);
        return hash;
    }
}
