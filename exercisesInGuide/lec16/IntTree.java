import java.util.LinkedList;

class IntTree {
  public IntTree(int data, IntTree left, IntTree right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public final int data;
  public IntTree left, right;

  public static IntTree mergeRight(IntTree T, IntTree L) {
    // Deque used as LIFO stacks, preference to legacy Stack class
    DequeMemo<IntTree> stack = new DequeMemo<IntTree>();
    stack = pushLeftPath(stack, T);

    while (L != null) {
      if (L.data < stack.peek().data) {
        // Insert to the left

        // check if left is null
        // if left is not null, insert into last poped node
        if (stack.peek().left == null) {
          stack.peek().left = L;
        } else {
          stack.lastPopped().right = L;
        }
        // increase the stack
        stack.push(L);
        // Move L to next element
        IntTree tmp = L;
        L = L.right;
        tmp.right = null;
      } else if (L.data == stack.peek().data) {

        stack = updateStack(stack);
        // Move L to next element
        IntTree tmp = L;
        L = L.right;
        tmp.right = null;
      } else {
        stack = updateStack(stack);
      }
    }

    return T;
  }

  public static DequeMemo<IntTree> pushLeftPath(DequeMemo<IntTree> stack, IntTree tree) {
    while (tree != null) {
      stack.push(tree);
      tree = tree.left;
    }
    return stack;
  }

  public static DequeMemo<IntTree> updateStack(DequeMemo<IntTree> stack) {
    IntTree head = stack.pop();
    stack = pushLeftPath(stack, head.right);
    return stack;
  }

  static class DequeMemo<E> extends LinkedList<E> {
    private E last = null;

    @Override
    public E pop() {
      this.last = super.pop();
      return this.last;
    }

    public E lastPopped() {
      return this.last;
    }
  }

  public static void main(String[] args) {
    IntTree T = new IntTree(12, null, null);
    T.left = new IntTree(
        3,
        null,
        new IntTree(10,
            new IntTree(8, null, null),
            null));
    T.right = new IntTree(
        16,
        new IntTree(14, null, null),
        new IntTree(27, null, null));
    IntTree L = new IntTree(1, null,
        new IntTree(11, null,
            new IntTree(26, null, null)));

    T = mergeRight(T, L);
    T = mergeRight(T, L);
  }
}