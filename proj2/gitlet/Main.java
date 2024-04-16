package gitlet;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author Dudu
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
     *
     *
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
     * <li>1. If a user doesn’t input any arguments, print the message {@code Please enter a command.}
     * and exit. </li>
     *
     * <li>2. If a user inputs a command that doesn’t exist, print the message {@code No command
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
                Repository.init();
                break;
            case "add":
                // handle the `add [filename]` command
                validateNumArgs("add", args, 2);
                Repository.add(args[1]);
                break;
            // TODO: FILL THE REST IN
            // A not exist command.
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);

        }
    }

    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }
}
