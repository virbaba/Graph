import java.util.*;
public class Solution {

    public static List<List<Integer>> findBridges(int[][] edges, int v, int e) {

        // Write your code here!
        List<List<Integer>> result = new ArrayList<>();

        // Create an adjacency list representation of the graph
        List<Integer>[] adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            adjList[src].add(dest);
            adjList[dest].add(src);
        }

        boolean[] visited = new boolean[v];
        int[] disc = new int[v]; // discovery time of vertices
        int[] low = new int[v]; // earliest visited vertex reachable from subtree rooted with i
        int currentTime = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                currentTime = bridgeUtil(i, -1, visited, disc, low, adjList, result, currentTime);
            }
        }

        return result;

    }

    private static int bridgeUtil(int u, int parent, boolean[] visited, int[] disc, int[] low,
            List<Integer>[] adjList, List<List<Integer>> result, int currentTime) {
        visited[u] = true;
        disc[u] = low[u] = currentTime++;

        for (int v : adjList[u]) {
            if (!visited[v]) {
                currentTime = bridgeUtil(v, u, visited, disc, low, adjList, result, currentTime);
              // here current node update own lowest discovery time by lowest discovery time of all neighbours
                low[u] = Math.min(low[u], low[v]);

                if (disc[u] < low[v]) {
                    List<Integer> bridgeEdge = new ArrayList<>();
                    bridgeEdge.add(u);
                    bridgeEdge.add(v);
                    result.add(bridgeEdge);
                }
              // if already visited mean some edge exist from another node to neighbour of current node
            } else if (v != parent) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        return currentTime;
    }

}
