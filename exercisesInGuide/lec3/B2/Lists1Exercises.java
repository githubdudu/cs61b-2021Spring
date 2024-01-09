public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. */
    public static IntList incrList(IntList L, int x) {
        IntList pointer = new IntList(0, null);
        IntList temp = pointer;
        while(L != null) {
            temp.rest = new IntList(L.first + x, null);
            L = L.rest;
            temp = temp.rest;
        }
        return pointer.rest;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList ans = L;
        while(L != null) {
            L.first = L.first + x;
            L = L.rest;
        }
        return ans;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
         System.out.println(L.get(1));
         System.out.println(incrList(L, 3));
         IntList incrL = incrList(L, 3);
//         System.out.println(dincrList(L, 3));
         IntList dincrL = dincrList(L, 3);
    }
}