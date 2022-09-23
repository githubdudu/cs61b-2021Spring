package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

public class Blob implements Serializable {
    /**
     * Deal Blob file, prepare its hashcode and DIR
     */
    private String filename;
    private File file;
    private File blob;
    private String hashcode;

    public Blob(String filename) {
        this.filename = filename;
        this.file = join(Repository.CWD, filename);

        this.hashcode = sha1(readContents(file));
        this.blob = join(Repository.OBJECTS_DIR, hashcode);
    }

    public Blob(String filename, String hashcode) {
        this.filename = filename;
        this.file = join(Repository.CWD, filename);

        this.hashcode = hashcode;
        this.blob = join(Repository.OBJECTS_DIR, hashcode);
    }
    public String getFilename() {
        return filename;
    }

    public String getHashcode() {
        return hashcode;
    }

    public String fileToBlob() {
        // Never re-write blob
        if (!blob.exists()) {
            writeContents(blob, readContents(file));
        }
        return hashcode;
    }

    public String blobToFile() {
        // Overwrite
        writeContents(file, readContents(blob));
        return filename;
    }

    public void deleteFile() {
        if (file.exists()) {
            file.delete();
        }
    }

}
