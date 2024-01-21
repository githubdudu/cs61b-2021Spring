public class WQUWithPathCompression{
  private int[] parent;
  private int[] weight;
  
  public WQUWithPathCompression(int n) {
    parent = new int[n];
    weight = new int[n];
    for(int i = 0; i < n; i++) {
      parent[i] = i;
      weight[i] = 1;
    }
  }

  public int weight(){
    return weight;
  }

  private int find(int p){
    int root = p;
    while(root != parent[root]) {
      root = parent[root];
    }
    pathCompression(p, root);
    return root;
  }

  private void pathCompression(int p, int root) {
    int tmp = p;
    while(parent[tmp] != root) {
      tmp = parent[p];
      parent[p] = root;
      p = tmp;
    }
  }

  public boolean isConnected(int p, int q){
    return find(p) == find(q);
  }

  public void connect(int p, int q){
    int rootP = find(p);
    int rootQ = find(q);
    if(isConnected(rootP, rootQ)) {
      return;
    }
    parent[rootP] = rootQ;
    weight[rootQ] += weight[rootP];
  }

}