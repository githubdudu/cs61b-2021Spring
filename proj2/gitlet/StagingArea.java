package gitlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public void put(String fileName, String fileHash) {
        index.put(fileName, fileHash);
    }


    /**
     * Check if the index contains the file that specified by filename.
     *
     * @param fileName the name of file.
     * @return true if the index contains the file.
     */
    public boolean containsFile(String fileName) {
        return index.containsKey(fileName);
    }

    /**
     * Remove the file from index by filename.
     *
     * @param fileName the name of file.
     * @return the hash of the file.
     */
    public String removeFile(String fileName) {
        return index.remove(fileName);
    }

    /**
     * Get the hash of the file by filename.
     *
     * @param fileName the name of file.
     * @return the hash of the file.
     */
    public String getFileHash(String fileName) {
        return index.get(fileName);
    }

    /**
     * Get the file names set in the index.
     *
     * @return the set of file names.
     */
    public Set<String> getFileNames() {
        return index.keySet();
    }

    /**
     * Returns a Set view of the fileName and fileHash mappings contained in this index.
     *
     * @return A set view of the mappings contained in this index.
     */
    public Set<Map.Entry<String, String>> fileEntrySet() {
        return index.entrySet();
    }

    /**
     * Compares the specified object with this stagingArea for equality. Returns true if the given
     * object is also a StagingArea and the two stagingAreas have the same index. More formally,
     * two stagingAreas s1 and s2 represent the same indexing if s1.index().equals(s2.index()).
     *
     * @param o object to be compared for equality with this stagingArea
     * @return true if the specified object is equal to this stagingArea
     */
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

    /**
     * Returns the hash code value for this stagingArea.
     *
     * @return the hash code value for this stagingArea
     */
    @Override
    public int hashCode() {
        return index.hashCode();
    }
}
