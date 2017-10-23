package kattis.isaric.tarjan;

import java.util.LinkedList;

/**
 * Created by isaric on 23.10.17..
 */
public class Digraph {

  private static final String NEWLINE = System.getProperty("line.separator");

  private final int V;
  private int E;
  private LinkedList<Integer>[] adj;
  private int[] indegree;

  public Digraph(int V) {
    this.V = V;
    this.E = 0;
    indegree = new int[V];
    adj = new LinkedList[V];
    for (int v = 0; v < V; v++) {
      adj[v] = new LinkedList<>();
    }
  }

  public int V() {
    return V;
  }

  private void validateVertex(int v) {
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

  public void addEdge(int v, int w) {
    validateVertex(v);
    validateVertex(w);
    adj[v].add(w);
    indegree[w]++;
    E++;
  }

  public Iterable<Integer> adj(int v) {
    validateVertex(v);
    return adj[v];
  }

  public LinkedList<Integer> adjLinked(int v) {
    return adj[v];
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(V + " vertices, " + E + " edges " + NEWLINE);
    for (int v = 0; v < V; v++) {
      s.append(String.format("%d: ", v));
      for (int w : adj[v]) {
        s.append(String.format("%d ", w));
      }
      s.append(NEWLINE);
    }
    return s.toString();
  }
}
