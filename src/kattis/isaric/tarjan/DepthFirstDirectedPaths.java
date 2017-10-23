package kattis.isaric.tarjan;

/**
 * Created by isaric on 23.10.17..
 */
public class DepthFirstDirectedPaths {

  private boolean[] marked;
  private int[] edgeTo;
  private final int s;

  public DepthFirstDirectedPaths(Digraph G, int s) {
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    this.s = s;
    validateVertex(s);
    dfs(G, s);
  }

  private void dfs(Digraph G, int v) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(G, w);
      }
    }
  }

  private void validateVertex(int v) {
    int V = marked.length;
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

  public boolean marked(int v) {
    return marked[v];
  }

}
