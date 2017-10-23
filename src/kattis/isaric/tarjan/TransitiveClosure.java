package kattis.isaric.tarjan;

/**
 * Created by isaric on 23.10.17..
 */
public class TransitiveClosure {

  private DepthFirstDirectedPaths[] tc;

  public TransitiveClosure(Digraph G) {
    tc = new DepthFirstDirectedPaths[G.V()];
    for (int v = 0; v < G.V(); v++) {
      tc[v] = new DepthFirstDirectedPaths(G, v);
    }
  }

  public boolean reachable(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    return tc[v].marked(w);
  }

  private void validateVertex(int v) {
    int V = tc.length;
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

}
