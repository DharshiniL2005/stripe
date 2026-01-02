
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Shortdis {
  public static void bfs(int s, List<List<Integer>> graph) {
    int n = graph.size();
    Queue<Integer> q = new LinkedList<>();
    boolean[] vis = new boolean[n];
    int[] dis = new int[n];
    vis[s] = true;
    dis[s] = 0;
    q.add(s);
    while (!q.isEmpty()) {
      int node = q.poll();

      for (int nei : graph.get(node)) {
        if (!vis[nei]) {
          vis[nei] = true;
          dis[nei] = dis[node] + 1;
          q.add(nei);
        }
      }
    }
    System.out.println("Shortest path from node" + s + ":");
    for (int i = 0; i < n; i++) {
      System.out.print("  node" + i + "->" + dis[i]);
    }
  }

  public static void main(String[] args) {
    int n = 5;
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    graph.get(0).add(1);
    graph.get(0).add(2);
    graph.get(1).add(3);
    graph.get(2).add(4);
    bfs(0, graph);
  }
}
