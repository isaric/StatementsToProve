package kattis.isaric;

import kattis.Kattio;

import java.util.ArrayList;

/**
 * Created by isaric on 22.10.17..
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
    int n = kattio.getInt();
    StatementCounter statementCounter = new StatementCounter(n, kattio);
    statementCounter.run();
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
    if (numberOfImplications == numberOfStatements) {
      System.out.println(0);
      return;
    }
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    initializeGraph(graph, numberOfStatements);
    for (int i = 0; i < numberOfImplications; i++) {
      graph.get(getAndNormalizeIndex()).add(getAndNormalizeIndex());
    }
    System.out.println(DSF(graph));
  }

  private int DSF(ArrayList<ArrayList<Integer>> graph) {
    ArrayList<Integer> steps = new ArrayList<>();
    for (ArrayList<Integer> node : graph) {
      int counter = 0;
      steps.add(visit(node, graph, counter));
    }
    return steps.stream().max(Integer::compareTo).get();
  }

  private int visit(ArrayList<Integer> node, ArrayList<ArrayList<Integer>> graph, int counter) {
    if (node.isEmpty()) {
      return counter;
    }
    for (Integer connection : node) {
      int temp = visit(graph.get(connection), graph, counter++);
      if (counter < temp) {
        counter = temp;
      }
    }
    return counter;
  }

  private int getAndNormalizeIndex() {
    return kattio.getInt() - 1;
  }

  private void initializeGraph(ArrayList<ArrayList<Integer>> graph, int numberOfStatements) {
    for (int i = 0; i < numberOfStatements; i++) {
      graph.add(new ArrayList<>());
    }
  }
}
