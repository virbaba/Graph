/*
  Time Complexity: O(V + E + m)
  Space Complexity: O(V + E)
*/
import java.util.*;

public class Solution {

    private static boolean hasCycleBFS(int start, HashMap<Integer, ArrayList<Integer>> adjList, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[visited.length];

        Arrays.fill(parent, -1);
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            ArrayList<Integer> neighbours = adjList.get(current);
            if (neighbours != null) {
                for (int neighbour : neighbours) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.offer(neighbour);
                        parent[neighbour] = current;
                    } else if (parent[current] != neighbour) {
                        // If the adjacent vertex is visited and not the parent of the current node,
                        // then there is a cycle
                        return true;
                    }
                }
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

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (hasCycleBFS(i, adjList, visited)) {
                    return "Yes";
                }
            }
        }

        return "No";
    }

}
