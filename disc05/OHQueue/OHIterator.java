import java.util.Iterator;
import java.util.NoSuchElementException;

public class OHIterator implements Iterator<OHRequest> {
    OHRequest curr;

    public OHIterator(OHRequest queue) {
        curr = queue;
    }

    public boolean isGood(String description) {
        return description != null && description.length() > 5;
    }

    public boolean hasNext() {
        while(curr != null && !isGood(curr.description)) {
            curr = curr.next;
        }
        return curr != null;
    }
    public OHRequest next() {
        if(hasNext()) {
            OHRequest temp = curr;
            curr = curr.next;
            return temp;
        }
        throw new NoSuchElementException();
    }
}
