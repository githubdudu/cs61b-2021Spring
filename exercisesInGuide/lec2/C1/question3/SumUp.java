import java.lang.Integer;
public class SumUp {
    public static void main(String[] args) {
        // Assuming args are numbers
        int answer = 0;
        for(int i = 0; i < args.length; i++) {
            answer += Integer.parseInt(args[i]);
        }
        System.out.println(answer);
    }
}