package gitlet;

import java.io.Serializable;
import java.util.HashMap;

import static gitlet.Utils.*;

public class Index implements Serializable {
    /**
     * Deal with indexing, also as stage status.
     * There is a copy of Index in commits.
     * Difference between index file and index in commits is staged file. Index can be seen as a staging area.
     * <p>
     * Using Hashmap to save indexed file. Keys are filename string, values are blob's hashcode string.
     */

    private HashMap<String, String> index = new HashMap<>();

    @SuppressWarnings("unchecked") // Can't avoid when read from outside
    public Index() {
        if (Repository.STAGE_FILE.exists()) {
            this.index = readObject(Repository.STAGE_FILE, HashMap.class);
        }
    }

    public boolean isEmpty() {
        return index.isEmpty();
    }

    public boolean hasFile(String filename) {
        return index.containsKey(filename);
    }

    public boolean hasFile(Blob b) {
        return hasFile(b.getFilename());
    }

    public boolean hasSameFile(String filename, String hashcode) {
        return hasFile(filename) && index.get(filename).equals(hashcode);
    }

    public boolean hasSameFile(Blob b) {
        return hasSameFile(b.getFilename(), b.getHashcode());
    }

    public String add(Blob b) {
        index.put(b.getFilename(), b.getHashcode());
        save();
        return b.getHashcode();
    }

    // Not exist key, return null
    public String remove(String filename) {
        String remove = index.remove(filename);
        save();
        return remove;
    }

    public void recoverFile(String filename) {
        if(hasFile(filename)) {
            Blob blob = new Blob(filename, index.get(filename));
            blob.blobToFile();
        } else {
            throw error("File does not exist in that index.");
        }
    }

    public void save() {
        writeObject(Repository.STAGE_FILE, index);
    }
}
