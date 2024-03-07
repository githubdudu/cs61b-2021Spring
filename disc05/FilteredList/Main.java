import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PositiveFilter pf = new PositiveFilter();
        List<Integer> list= new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        FilteredList<Integer> fl= new FilteredList<Integer>(list, pf);
        for (Integer i : fl) {
            System.out.println(i);
        }
    }
}
