import java.util.*;

public class Solution {
    // DETECTING CYCLING USING KAHN'S ALOG
    public static Boolean isCyclic(int[][] edges, int v, int e) {
        // firstly make adj list
        int[] inEdges = new int[v];
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            inEdges[to]++;

            if (!adjList.containsKey(from))
                adjList.put(from, new ArrayList<>());

            adjList.get(from).add(to);

        }

        boolean[] visited = new boolean[v];

        int count = checkCycle(visited, inEdges, adjList);

        return (count == v) ? false : true;

    }

    public static int checkCycle(boolean[] visited, int[] inEdges, HashMap<Integer, ArrayList<Integer>> adjList) {
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inEdges.length; i++) {
            if (inEdges[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int vertex = q.poll();
            count++;
            if (!visited[vertex]) {
                visited[vertex] = true;
                ArrayList<Integer> neigbours = adjList.get(vertex);
                if (neigbours != null) {
                    for (int neigbour : neigbours) {
                        inEdges[neigbour]--;
                        if (inEdges[neigbour] == 0)
                            q.offer(neigbour);
                    }
                }

            }
        }

        return count;
    }
}
