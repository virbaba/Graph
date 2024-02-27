import java.util.*;

public class Solution {
    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) {
        // Write your code here
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        int[] inEdges = new int[v];

        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);

            inEdges[to]++;
            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }

        boolean[] visited = new boolean[v];
        ArrayList<Integer> ans = new ArrayList<>();

        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited[vertex] && inEdges[vertex] == 0)
                BFSTraversal(vertex, inEdges, visited, ans, adjList);
        }

        return ans;
    }

    public static void BFSTraversal(int vertex, int[] inEdges, boolean[] visited, ArrayList<Integer> ans,
            HashMap<Integer, ArrayList<Integer>> adjList) {

        Queue<Integer> q = new LinkedList<>();
        q.offer(vertex);
        visited[vertex] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            ans.add(curr);
            ArrayList<Integer> neighbours = adjList.getOrDefault(curr, new ArrayList<>());
            for (int neighbour : neighbours) {
                inEdges[neighbour]--;
                if (inEdges[neighbour] == 0 && !visited[neighbour]) {
                    q.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }
    }
}
