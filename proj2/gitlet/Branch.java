package gitlet;

import java.io.File;

import static gitlet.Utils.writeContents;

public class Branch {
    final static String DEFAULT_BRANCH_NAME = "master";
    private String name;
    private File branchFile;
    private String commitHash;

    public Branch() {
        this(DEFAULT_BRANCH_NAME);
    }

    public Branch(String name) {
        this.name = name;
        this.branchFile = Utils.join(Repository.BRANCH_DIR, name);
    }

    public String getBranchFileRelativePath() {
        return Repository.CWD.toPath().relativize(this.branchFile.toPath()).toString();
    }

    public void setContent(String commitHash) {
        this.commitHash = commitHash;
    }

    public void saveFile() {
        if(!Repository.BRANCH_DIR.exists()) {
            Repository.BRANCH_DIR.mkdir();
        }
        writeContents(branchFile, this.commitHash);
    }

    public String getName() {
        return name;
    }

    public String getCommitHash() {
        return commitHash;
    }
}
