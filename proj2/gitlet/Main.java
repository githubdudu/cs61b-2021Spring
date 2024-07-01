package gitlet;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author hdon694
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     * <p>
     * Runs these COMMANDS: init, add,
     * <p>
     * Init -- Creates a new Gitlet version-control system in the current directory.
     * <p>
     * Add [filename] -- Adds a copy of the file as it currently exists to the staging area (see the
     * description of the <code>commit</code> command).
     * <p>
     * Commit [message] -- Saves a snapshot of tracked files in the current commit and staging area so they
     * can be restored at a later time, creating a new commit.
     * <p>
     * Checkout -- Checkout is a kind of general command that can do a few different things
     * depending on what its arguments are. There are 3 possible use cases.
     * <p>
     *  <ol>
     *     <li>
     *        checkout -- [file name]
     *        Takes the version of the file as it exists in the head commit and puts it in the working
     *        directory, overwriting the version of the file that’s already there if there is one. The new
     *        version of the file is not staged.
     *     </li>
     *     <li>
     *      checkout [commit id] -- [file name]
     *      Takes the version of the file as it exists in the commit with the given id, and puts it in
     *      the working directory, overwriting the version of the file that’s already there if there is
     *      one. The new version of the file is not staged.
     *     </li>
     *     <li>
     *      checkout [branch name]
     *      Takes all files in the commit at the head of the given branch, and puts them in the working
     *      directory, overwriting the versions of the files that are already there if they exist. Also,
     *      at the end of this command, the given branch will now be considered the current branch
     *      (HEAD). Any files that are tracked in the current branch but are not present in the
     *      checked-out branch are deleted. The staging area is cleared, unless the checked-out branch is
     *      the current branch (see Failure cases below).
     *     </li>
     *  </ol>
     * Log -- Starting at the current head commit, display information about each commit backwards
     *        along the commit tree until the initial commit.
     *        Following the first parent commit links, ignoring any second parents found in merge commits.
     *
     * <p>
     * Global-log -- Like log, except displays information about all commits ever made.
     *
     * <p>
     * find [commit message] -- Prints out the ids of all commits that have the given commit message, one per line.
     *
     * <p>
     *     status -- Displays what branches currently exist, and marks the current branch with a *.
     *     Also displays what files have been staged for addition or removal.
     *
     * <p>
     *     branch [branch name] -- Creates a new branch with the given name, and points it at the current
     *     head node.
     *
     * <p>
     *     rm-branch [branch name] -- Deletes the branch with the given name.
     *
     * <p>
     *     reset [commit id] -- Checks out all the files tracked by the given commit.
     *
     * <p>
     *     merge [branch name] -- Merges files from the given branch into the current branch.
     *
     * <p>
     * The place to store old copies of files and other metadata: ".gitlet".
     * <p>
     * Some commands have failure cases with a specified error message.
     * If your program ever encounters one of these failure cases,
     * it must print the error message and not change anything else.
     * <p>
     * There are some failure cases you need to handle that don’t apply to a particular command.
     * Here they are:
     * <li>1. If a user doesn't input any arguments, print the message
     * {@code Please enter a command.}
     * and exit. </li>
     *
     * <li>2. If a user inputs a command that doesn't exist, print the message {@code No command
     * with that name exists.} and exit.</li>
     *
     * <li>3. If a user inputs a command with the wrong number or format of operands, print the
     * message {@code Incorrect operands.} and exit.</li>
     *
     * <li>4. If a user inputs a command that requires being in an initialized Gitlet working
     * directory (i.e., one containing a .gitlet subdirectory), but is not in such a directory,
     * print the message {@code Not in an initialized Gitlet directory.}</li>
     * <p>
     * To exit your program immediately, you may call System.exit(0).
     */
    public static void main(String[] args) {
        // what if args is empty?
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }

        String firstArg = args[0];
        switch (firstArg) {
            case "init":
                // handle the `init` command
                validateNumArgs("init", args, 1);
                Repository.initCommand();
                break;
            case "add":
                // handle the `add [filename]` command
                validateNumArgs("add", args, 2);
                Repository.addCommand(args[1]);
                break;
            // TODO: FILL THE REST IN
            case "commit":
                // handle the `commit [message]` command
                validateNumArgs("commit", args, 2);
                Repository.commitCommand(args[1]);
                break;
            case "rm":
                // handle the `rm [filename]` command
                validateNumArgs("rm", args, 2);
                Repository.rmCommand(args[1]);
                break;
            case "checkout":
                if (args.length == 2) {
                    // handle the `checkout [branch name]` command
                    Repository.checkoutBranchCommand(args[1]);
                    return;
                } else if (args[1].equals("--")) {
                    // handle the `checkout -- [file name]` command
                    validateNumArgs("checkout", args, 3);
                    Repository.checkoutCommand(args[2]);
                } else if (args[2].equals("--")) {
                    // handle the `checkout [commitId] -- [file name]` command
                    validateNumArgs("checkout", args, 4);
                    Repository.checkoutCommand(args[1], args[3]);
                } else {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                break;
            case "log":
                validateNumArgs("log", args, 1);
                Repository.logCommand();
                break;
            case "global-log":
                validateNumArgs("global-log", args, 1);
                Repository.globalLogCommand();
                break;
            case "find":
                validateNumArgs("find", args, 2);
                Repository.findCommand(args[1]);
                break;
            case "status":
                validateNumArgs("status", args, 1);
                Repository.statusCommand();
                break;
            case "branch":
                validateNumArgs("branch", args, 2);
                Repository.branchCommand(args[1]);
                break;
            case "rm-branch":
                validateNumArgs("rm-branch", args, 2);
                Repository.rmBranchCommand(args[1]);
                break;
            case "reset":
                validateNumArgs("reset", args, 2);
                Repository.resetCommand(args[1]);
                break;
            case "merge":
                validateNumArgs("merge", args, 2);
                Repository.mergeCommand(args[1]);
                break;

            // Handle non-exist commands.
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);

        }
    }

    /**
     * A helper function validates the number of arguments.
     *
     * @param cmd  command name
     * @param args the actual arguments
     * @param n    expected count of the arguments
     */
    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }
}
