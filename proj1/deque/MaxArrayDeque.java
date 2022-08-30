package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> co;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.co = c;
    }

    public T max() {
        if (size() == 0) {
            return null;
        }

        int maxDex = 0;
        for (int i = 0; i < size(); i += 1) {
            int cmp = co.compare((T) get(i), (T) get(maxDex));
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return (T) get(maxDex);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }

        int maxDex = 0;
        for (int i = 0; i < size(); i += 1) {
            int cmp = c.compare((T) get(i), (T) get(maxDex));
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return (T) get(maxDex);
    }
}
