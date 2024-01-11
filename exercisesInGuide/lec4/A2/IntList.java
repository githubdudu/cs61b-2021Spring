
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public void addLast(int x) {
        this.square(x);

    }


    /** Exercise A 2 */
    private IntList square (int x) {

        IntList p = this;
        while(p != null && p.rest != null) {
            IntList sqr = new IntList(p.first * p.first, p.rest);
            p.rest = sqr;
            p = p.rest.rest;
        }
        p.rest = new IntList(p.first * p.first, null);
        p.rest.rest = new IntList(x, null);
        return p;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if(this.rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }


    /** Returns the ith value in this list.*/
    public int get(int i) {
        if (i == 0) return this.first;
        return this.rest.get(i - 1);
    }

    // Additional method to print the elements of the list
    public void printList() {
        IntList current = this;
        while (current != null) {
            System.out.print(current.first + " ");
            current = current.rest;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        IntList L = new IntList(2, null);
        L = new IntList(1, L);
        L.printList();

        L.addLast(5);
        L.printList();

        L.addLast(7);
        L.printList();
    }
}