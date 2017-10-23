package kattis.isaric.tarjan;

import java.util.Stack;

/**
 * Created by isaric on 23.10.17..
 */
public class TarjanSCC {

  private boolean[] marked;
  private int[] id;
  private int[] low;
  private int pre;
  private int count;
  private Stack<Integer> stack;

  public TarjanSCC(Digraph G) {
    marked = new boolean[G.V()];
    stack = new Stack<Integer>();
    id = new int[G.V()];
    low = new int[G.V()];
    for (int v = 0; v < G.V(); v++) {
      if (!marked[v]) dfs(G, v);
    }

    assert check(G);
  }

  private void dfs(Digraph G, int v) {
    marked[v] = true;
    low[v] = pre++;
    int min = low[v];
    stack.push(v);
    for (int w : G.adj(v)) {
      if (!marked[w]) dfs(G, w);
      if (low[w] < min) min = low[w];
    }
    if (min < low[v]) {
      low[v] = min;
      return;
    }
    int w;
    do {
      w = stack.pop();
      id[w] = count;
      low[w] = G.V();
    } while (w != v);
    count++;
  }


  /**
   * Returns the number of strong components.
   * @return the number of strong components
   */
  public int count() {
    return count;
  }


  public boolean stronglyConnected(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    return id[v] == id[w];
  }

  public int id(int v) {
    validateVertex(v);
    return id[v];
  }

  private boolean check(Digraph G) {
    TransitiveClosure tc = new TransitiveClosure(G);
    for (int v = 0; v < G.V(); v++) {
      for (int w = 0; w < G.V(); w++) {
        if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
          return false;
      }
    }
    return true;
  }

  private void validateVertex(int v) {
    int V = marked.length;
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
  }

}
