import java.util.*;

public class FilteredList<T> implements Iterable<T> {
    List<T> L;
    Predicate<T> filter;

    public FilteredList(List<T> L, Predicate<T> filter) {
        this.L = L;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator(L, filter);
    }

    public class FilterIterator implements Iterator<T> {
        int index;
        Predicate<T> filter;

        FilterIterator(List<T> L, Predicate<T> filter) {
            this.index = 0;
            this.filter = filter;
        }

        @Override
        public boolean hasNext() {
            while(index < L.size() && !filter.test(L.get(index))) {
                index++;
            }
            return index < L.size();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            T ans = L.get(index);
            index += 1;
            return ans;
        }
    }
}