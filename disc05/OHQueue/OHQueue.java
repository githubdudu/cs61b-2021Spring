import java.util.Iterator;

public class OHQueue implements Iterable<OHRequest> {
    OHRequest curr;
    public OHQueue(OHRequest queue) {
        curr = queue;
    }

    public Iterator<OHRequest> iterator() {
        return new TYIterator(curr);
    }

    public static void main(String[] args) {
        OHRequest s9 = new OHRequest("thank u", "THANKYOU GUY4", null);
        OHRequest s8 = new OHRequest("thank u", "THANKYOU GUY3", s9);
        OHRequest s7 = new OHRequest("thank u", "THANKYOU GUY2", s8);
        OHRequest s6 = new OHRequest("thank u", "THANKYOU GUY1", s7);
        OHRequest s5 = new OHRequest("I deleted all of my files", "Allyson", s6);
        OHRequest s4 = new OHRequest("conceptual: what is Java", "Omar", s5);
        OHRequest s3 = new OHRequest("git: I never did lab 1", "Connor", s4);
        OHRequest s2 = new OHRequest("help", "Hug", s3);
        OHRequest s1 = new OHRequest("elp", "Itai", s2);

        OHQueue o = new OHQueue(s1);
        for (OHRequest ohRequest: o) {
            System.out.println(ohRequest.name);
        }

    }
}