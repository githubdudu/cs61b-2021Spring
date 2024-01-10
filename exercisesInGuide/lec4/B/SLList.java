/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList {
	private static class IntNode {
		public int item;
		public IntNode next;

		public IntNode(int i, IntNode n) {
			item = i;
			next = n;
		}
	}

	/** The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;


	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new IntNode(63, null);
		size = 0;
	}

	public SLList(int x) {
		sentinel = new IntNode(63, null);
		sentinel.next = new IntNode(x, null);
		size = 1;
	}

	/** Exercise Level B 2 */
	public SLList(int[] arr) {
		this();
		for (int i = arr.length - 1; i >= 0 ; i--) {
			this.addFirst(arr[i]);
		}
	}

 	/** Adds x to the front of the list. */
 	public void addFirst(int x) {
 		sentinel.next = new IntNode(x, sentinel.next);
 		size = size + 1;
 	}

 	/** Returns the first item in the list. */
 	public int getFirst() {
 		return sentinel.next.item;
 	}

	/** Exercise Level B 1 */
	public void deleteFirst() {
		sentinel.next = sentinel.next.next;
		size = size - 1;
	}

 	/** Adds x to the end of the list. */
 	public void addLast(int x) {
 		size = size + 1;

 		IntNode p = sentinel;

 		/* Advance p to the end of the list. */
 		while (p.next != null) {
 			p = p.next;
 		}

 		p.next = new IntNode(x, null);
 	}

 	/** Returns the size of the list. */
 	public int size() {
 		return size;
 	}

	public static void main(String[] args) {
 		/* Creates a list of one integer, namely 10 */
 		SLList L = new SLList();
 		L.addLast(20);
 		System.out.println(L.size());

		int[] arr = new int[]{1, 2, 3, 4, 5};
		 SLList L2 = new SLList(arr);
		 System.out.println(L2.size());
 	}
}