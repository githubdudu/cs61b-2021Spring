package gitlet;

import java.io.File;

import static gitlet.Utils.writeContents;

/**
 * Represent the branch file or branch object. A branch file saves the hash of that commit.
 *
 * @author hdon694
 */
public class Branch {
    /**
     * Default branch name is master.
     */
    static final String DEFAULT_BRANCH_NAME = "master";
    /**
     * Name of the branch.
     */
    private String name;
    /**
     * The file that saves the hash of head of the branch.
     */
    private File branchFile;
    /**
     * The content that needs to be saved in file which is the hash of the branch head.
     */
    private String commitHash;

    public Branch() {
        this(DEFAULT_BRANCH_NAME);
    }

    public Branch(String name) {
        this.name = name;
        this.branchFile = Utils.join(Repository.BRANCH_DIR, name);
    }

    /**
     * get the relative path from two absolute paths: GITLET_DIR and branch file path.
     *
     * @return relative path of the branch head file, relative to .gitlet folder.
     */
    public String getBranchFileRelativePath() {
        return Repository.GITLET_DIR.toPath().relativize(this.branchFile.toPath()).toString();
    }

    /**
     * Set the content/hash to be saved.
     *
     * @param hash the content bo be saved.
     */
    public void setContent(String hash) {
        this.commitHash = hash;
    }

    /**
     * Save the branch to file in BRANCH_DIR directory.
     */
    public void saveBranchToFile() {
        if (!Repository.BRANCH_DIR.exists()) {
            Repository.BRANCH_DIR.mkdir();
        }
        writeContents(branchFile, this.commitHash);
    }

    /**
     * Get the name of branch.
     *
     * @return the name of branch.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the commit hash.
     *
     * @return the commit hash.
     */
    public String getCommitHash() {
        return commitHash;
    }
}
