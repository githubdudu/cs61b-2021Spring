public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /**
     * Very tricky as well as inefficient.
     * @param x
     */
    public void addFirst(int x) {
        insert(x);
    }

    private void insert(int x) {
        if(this.rest == null) {
            this.rest = new IntList(x, null);
            return;
        }
        int temp = this.first;
        this.first = x;
        this.rest.insert(temp);
    }

    public static void main(String[] args){
        IntList L = new IntList(15, null);
        L.addFirst(10);
        L.addFirst(5);
        int x = L.first;
    }
}