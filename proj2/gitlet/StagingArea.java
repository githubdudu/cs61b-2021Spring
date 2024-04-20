package gitlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent the index. In real git, index is a tree structure that saved all the files or
 * directories that have been traced or managed. By comparing the indexes, we can defer the
 * staged files and the files staged to be removed.
 *
 * @author hdon694
 */
public class StagingArea implements Serializable {
    /**
     * The index map used to trace files by filename and hash pairs.
     * <p>
     * In real git, this should be tree structure. But this gitlet project, only "flat" directory.
     */
    private Map<String, String> index;

    public StagingArea() {
        index = new HashMap<>();
    }

    /**
     * Read index from saved file ".gitlet/index".
     */
    public static StagingArea readFromFile() {

        return Utils.readObject(Repository.INDEX, StagingArea.class);
    }

    /**
     * Save this instance to file ".gitlet/index".
     */
    public void saveStagingToFile() {

        Utils.writeObject(Repository.INDEX, this);
    }

    /**
     * Update index by filename and hash pairs.
     *
     * @param fileName the name of file.
     * @param fileHash the hash of file.
     */
    public void updateIndex(String fileName, String fileHash) {
        index.put(fileName, fileHash);
    }

    public void removeFile(String filename, String fileHash) {
        index.remove(filename);
    }

    public Map<String, String> getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StagingArea that = (StagingArea) o;

        return index.equals(that.index);
    }

    @Override
    public int hashCode() {
        return index.hashCode();
    }
}
