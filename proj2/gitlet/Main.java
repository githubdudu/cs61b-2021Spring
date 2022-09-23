package gitlet;

/**
 * Driver class for Gitlet, a subset of the Git version-control system.
 *
 * @author dudu
 */
public class Main {

    /**
     * Usage: java gitlet.Main ARGS, where ARGS contains
     * <COMMAND> <OPERAND1> <OPERAND2> ...
     * As an entry. The args checks are in Flow.java.
     */
    public static void main(String[] args) {
        Flow flow = new Flow(args);

        String firstArg = args[0];
        switch (firstArg) {
            case "init" -> flow.init();
            case "add" -> flow.add();
            case "commit" -> flow.commit();
            case "rm" -> flow.rm();
            case "log" -> flow.log();
            case "global-log" -> flow.globalLog();
            case "find" -> flow.find();
            case "status" -> flow.status();
            case "checkout" -> flow.checkout();
            case "branch" -> System.out.println("branch");
            case "rm-branch" -> System.out.println("rm-branch");
            case "reset" -> System.out.println("reset");
            case "merge" -> System.out.println("merge");
            default -> flow.exitWithInfo(Flow.NO_EXIST_PARA);
        }
    }
}
