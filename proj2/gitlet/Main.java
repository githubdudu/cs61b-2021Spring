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
     * Add -- Adds a copy of the file as it currently exists to the staging area (see the
     * description of the <code>commit</code> command).
     * <p>
     * Commit -- Saves a snapshot of tracked files in the current commit and staging area so they
     * can be restored at a later time, creating a new commit.
     * <p>
     * Checkout -- Checkout is a kind of general command that can do a few different things
     * depending on what its arguments are. There are 3 possible use cases.
     * <p>
     *  <ol>
     *     <li>
     *        Takes the version of the file as it exists in the head commit and puts it in the working
     *        directory, overwriting the version of the file that’s already there if there is one. The new
     *        version of the file is not staged.
     *     </li>
     *     <li>
     *      Takes the version of the file as it exists in the commit with the given id, and puts it in
     *      the working directory, overwriting the version of the file that’s already there if there is
     *      one. The new version of the file is not staged.
     *     </li>
     *     <li>
     *      Takes all files in the commit at the head of the given branch, and puts them in the working
     *      directory, overwriting the versions of the files that are already there if they exist. Also,
     *      at the end of this command, the given branch will now be considered the current branch
     *      (HEAD). Any files that are tracked in the current branch but are not present in the
     *      checked-out branch are deleted. The staging area is cleared, unless the checked-out branch is
     *      the current branch (see Failure cases below).
     *     </li>
     *  </ol>
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
                validateNumArgs("commit", args, 2);
                Repository.commitCommand(args[1]);
                break;
            case "rm":
                validateNumArgs("rm", args, 2);
                Repository.rmCommand(args[1]);
                break;
            case "checkout":
                if (args[1].equals("--")) {
                    validateNumArgs("checkout", args, 3);
                    Repository.checkoutCommand(args[2]);
                } else if (args[2].equals("--")) {
                    validateNumArgs("checkout", args, 4);
                    Repository.checkoutCommand(args[1], args[3]);
                } else {
                    validateNumArgs("checkout", args, 2);
                    Repository.checkoutBranchCommand(args[1]);
                }
                break;
                // A not exist command.
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
