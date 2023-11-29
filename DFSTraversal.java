/*
  Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    public static ArrayList<ArrayList<Integer>> depthFirstSearch(int v, int e, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for (ArrayList<Integer> list : edges) {
            int from = list.get(0);
            int to = list.get(1);

            if (!adjList.containsKey(from)) {
                adjList.put(from, new ArrayList<>());
            }
            if (!adjList.containsKey(to)) {
                adjList.put(to, new ArrayList<>());
            }
            adjList.get(from).add(to);
            adjList.get(to).add(from); // For undirected graph
        }

        boolean[] visited = new boolean[v];
        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited[vertex]) {
                ArrayList<Integer> list = new ArrayList<>();
                dfs(vertex, visited, list, adjList);
                ans.add(list);
            }
        }

        return ans;
    }

    public static void dfs(int vertex, boolean[] visited, ArrayList<Integer> list,
        HashMap<Integer, ArrayList<Integer>> adjList) {
        visited[vertex] = true;
        list.add(vertex);

        ArrayList<Integer> neighbours = adjList.get(vertex);
        if (neighbours != null) {
            for (int neighbour : neighbours) {
                if (!visited[neighbour])
                    dfs(neighbour, visited, list, adjList);
            }
        }
    }
}
