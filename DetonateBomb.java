import java.util.*;

class Solution {
    public int maximumDetonation(int[][] bombs) {
        // Adjacency List to store bombs that can trigger each other
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        int n = bombs.length;

        // Build the adjacency list
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long radius = (long) bombs[i][2] * bombs[i][2];
                    long xDistance = (long) (bombs[j][0] - bombs[i][0]);
                    long yDistance = (long) (bombs[j][1] - bombs[i][1]);
                    long centerDistance = (xDistance * xDistance) + (yDistance * yDistance);

                    // If bomb j is within the range of bomb i
                    if (radius >= centerDistance) {
                        adjList.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                    }
                }
            }
        }

        // Function to perform DFS and count the number of bombs that can be detonated
        boolean[] visited;
        int maxDetonate = 0;

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            maxDetonate = Math.max(maxDetonate, dfsTraversal(i, adjList, visited));
        }

        return maxDetonate;
    }

    // DFS to count the number of bombs detonated
    private int dfsTraversal(int vertex, HashMap<Integer, ArrayList<Integer>> adjList, boolean[] visited) {
        visited[vertex] = true;
        int count = 1; // Counting the current bomb

        for (int neighbor : adjList.getOrDefault(vertex, new ArrayList<>())) {
            if (!visited[neighbor]) {
                count += dfsTraversal(neighbor, adjList, visited);
            }
        }

        return count;
    }
}
