package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

public class Blob implements Serializable {
    /**
     * Deal Blob file, prepare its hashcode and DIR
     */
    private String sourceName;
    private File file;
    private File blob;
    private String hashcode;

    public Blob(String source) {
        this.sourceName = source;
        this.file = join(Repository.CWD, source);

        this.hashcode = sha1(readContents(file));
        this.blob = join(Repository.OBJECTS_DIR, hashcode);
    }


    public String getSourceName() {
        return sourceName;
    }

    public String getHashcode() {
        return hashcode;
    }

    public String save() {
        if (!blob.exists()) {
            writeContents(blob, readContents(file));
        }
        return hashcode;
    }

    public void deleteInWorkingDIR() {
        if (blob.exists()) {
            blob.delete();
        }
    }

}
