package kattis.isaric;

import java.util.LinkedList;
import kattis.Kattio;
import kattis.isaric.tarjan.Digraph;
import kattis.isaric.tarjan.TarjanSCC;

/**
 * Created by isaric on 22.10.17..
 * Based predominantly on the work of Robert Sedgwick and Kevin Wayne's
 * Algorithms course from Stanford and other sources. I have not been so
 * rigorously trained in algorithms and data structures so I had to look
 * for existing sources. I'm sorry if this violates the plagiarism rule
 * but I would have not been able to solve the exercise otherwise.
 */
public class StatementCounter {

  private int numberOfCases;
  private Kattio kattio;

  public StatementCounter(int n, Kattio kattio) {
    numberOfCases = n;
    this.kattio = kattio;
  }

  public static void main(String[] args) {
    Kattio kattio = new Kattio(System.in);
    if (kattio.hasMoreTokens()) {
      int n = kattio.getInt();
      StatementCounter statementCounter = new StatementCounter(n, kattio);
      statementCounter.run();
    }
  }

  public void run() {
    for (int i = 0; i < numberOfCases; i++) {
      performAnalysis();
    }
  }

  private void performAnalysis() {
    int numberOfStatements = kattio.getInt();
    int numberOfImplications = kattio.getInt();
    if (numberOfImplications == 0) {
      System.out.println(numberOfStatements);
      return;
    }

    Digraph graph = new Digraph(numberOfStatements);
    for (int i = 0; i < numberOfImplications; i++) {
      graph.addEdge(getAndNormalizeIndex(), getAndNormalizeIndex());
    }
    TarjanSCC tarjan = new TarjanSCC(graph);
    if (tarjan.count() == 1) {
      System.out.println(0);
      return;
    }
    int outDegree[] = new int[tarjan.count()];
    int inDegree[] = new int[tarjan.count()];
    for (int i = 0; i < numberOfStatements; i++) {
      LinkedList<Integer> current = graph.adjLinked(i);
      for (int j = 0; j < current.size(); j++) {
        int next = current.get(j);
        if (tarjan.id(i) == tarjan.id(next)) {
          continue;
        }
        outDegree[tarjan.id(i)]++;
        inDegree[tarjan.id(next)]++;
      }
    }
    int countIn = 0;
    int countOut = 0;
    for (int i = 0; i < tarjan.count(); i++) {
      if (outDegree[i] == 0) {
        countOut++;
      }
      if (inDegree[i] == 0) {
        countIn++;
      }
    }
    System.out.println(Math.max(countIn, countOut));
  }

  private int getAndNormalizeIndex() {
    return kattio.getInt() - 1;
  }
}
