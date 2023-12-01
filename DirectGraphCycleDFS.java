/*
  Time Complexity: O(V + E)
  Space Complexity: O(V + E)
*/
import java.util.*;

public class Solution {
  public static boolean detectCycleInDirectedGraph(int n, ArrayList<ArrayList<Integer>> edges) {
    // Write your code here.
    HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);

            // Initialize adjacency list
            adjList.putIfAbsent(from, new ArrayList<>());
            adjList.get(from).add(to);
        }

        boolean[] visited = new boolean[n + 1];
        boolean[] recStack = new boolean[n + 1]; // To keep track of vertices in recursion stack

        for (int vertex = 1; vertex <= n; vertex++) {
            if (!visited[vertex]) {
                if (checkCycle(vertex, visited, recStack, adjList)) {
                    return true; // Cycle detected
                }
            }
        }
        return false; // No cycle found
  }

  public static boolean checkCycle(int vertex, boolean[] visited, boolean[] recStack,
                                     HashMap<Integer, ArrayList<Integer>> adjList) {
        visited[vertex] = true;
        recStack[vertex] = true;

        if (adjList.containsKey(vertex)) {
            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    if (checkCycle(neighbor, visited, recStack, adjList)) {
                        return true;
                    }
                } else if (recStack[neighbor]) {
                    return true; // Cycle detected
                }
            }
        }

        recStack[vertex] = false; // Remove the vertex from recursion stack when backtracking
        return false;
    }
}
