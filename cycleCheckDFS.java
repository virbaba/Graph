import java.util.*;

/*
  Time Complexity: O(m + V + E)
  Space Complexity: O(V + E)
*/
public class Solution {

    private static boolean hasCycleDFS(int vertex, boolean[] visited, int[] parent, HashMap<Integer, ArrayList<Integer>> adjList) {
        visited[vertex] = true;

        ArrayList<Integer> neighbours = adjList.get(vertex);
        if (neighbours != null) {
            for (int neighbour : neighbours) {
                if (!visited[neighbour]){
                    parent[neighbour] = vertex;
                    if (hasCycleDFS(neighbour, visited, parent, adjList))
                        return true;
                }
                else if(parent[vertex] != neighbour)
                    return true;
                    
            }
        }
        return false;
    }

    public static String cycleDetection(int[][] edges, int n, int m) {
        // Write your code here.
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            if (!adjList.containsKey(from)) {
                adjList.put(from, new ArrayList<>());
            }
            if (!adjList.containsKey(to)) {
                adjList.put(to, new ArrayList<>());
            }
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        boolean[] visited = new boolean[n + 1];
        int[] parent = new int[n + 1];

        for (int vertex = 1; vertex <= n; vertex++) {
            if (!visited[vertex]) {
                if (hasCycleDFS(vertex, visited, parent, adjList)) {
                    return "Yes";
                }
            }
        }

        return "No";
    }

}
