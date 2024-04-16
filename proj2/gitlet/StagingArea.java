package gitlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StagingArea implements Serializable {
    private Map<String, String> index;

    public StagingArea() {
        index = new HashMap<>();
    }

    /**
     * Read from staging file
     */
    public static StagingArea fromFile() {

        return Utils.readObject(Repository.INDEX, StagingArea.class);
    }

    public void saveStaging() {

        Utils.writeObject(Repository.INDEX, this);
    }


    public void addFile(String filename, String fileHash) {
        index.put(filename, fileHash);
    }

    public void removeFile(String filename, String fileHash) {
        index.remove(filename);
    }

    public Map<String, String> getIndex() {
        return index;
    }
}
