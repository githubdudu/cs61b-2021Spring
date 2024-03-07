import java.util.List;
public class PositiveFilter<Integer extends Comparable> implements Predicate<Integer>{
    @Override
    public boolean test(Integer x){
        return x.compareTo(30) > 0;
//        return true;
    };


}
