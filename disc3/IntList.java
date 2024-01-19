public class IntList {
  public int first;
  public IntList rest;
  public IntList (int f, IntList r) {
    this.first = f;
    this.rest = r;
  }

  public static void evenOdd(IntList lst) {
    if(lst == null || lst.rest == null) {
      return;
    }
    IntList even = lst;
    IntList oddHead = lst.rest;
    while(even.rest !=null && even.rest.rest != null) {
      IntList tmp = even.rest;
      even.rest = even.rest.rest;
      tmp.rest = even.rest.rest;
      even = even.rest;
    }
    even.rest = oddHead;
  }
  public static IntList[] partition(IntList lst, int k) {
    IntList[] array = new IntList[k];
    int index = 0;
    IntList L = lst;
    while(L != null){
      IntList tmp = L;
      L = L.rest;
      tmp.rest = array[index];
      array[index] = tmp;
      index = (index + 1) % k;
    }
    return array;
  }
  public static void main(String[] args) {
    IntList L = new IntList(5, null);
    L = new IntList(2, L);
    L = new IntList(4, L);
    L = new IntList(1, L);
    L = new IntList(3, L);
    L = new IntList(0, L);
    IntList.evenOdd(L);
    IntList[] LL = IntList.partition(L, 3);
  }
}
