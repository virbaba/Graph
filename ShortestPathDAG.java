import java.util.*;

public class Solution {
    public static int[] shortestPathInDAG(int n, int m, int[][] edges) {
        // Write your code here
        HashMap<Integer, ArrayList<HashMap<Integer, Integer>>> adjList = new HashMap<>();

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            if (!adjList.containsKey(from)) {
                adjList.put(from, new ArrayList<>());
                adjList.get(from).add(new HashMap<>());
            }

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(to, weight);
            adjList.get(from).add(map);
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<>();

        int[] shortestPath = new int[n];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[0] = 0;

        for (int vertex = 0; vertex < n; vertex++) {
            if (!visited[vertex]) {
                DFSTravarsal(vertex, visited, st, adjList);
            }
        }

        while (!st.isEmpty()) {
            int vertex = st.pop();
            if (shortestPath[vertex] != Integer.MAX_VALUE) {
                ArrayList<HashMap<Integer, Integer>> neighboursList = adjList.get(vertex);
                if (neighboursList != null) {
                    for (HashMap<Integer, Integer> map : neighboursList) {
                        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            int neighbour = entry.getKey();
                            int weight = entry.getValue();
                            int total = shortestPath[vertex] + weight;
                            shortestPath[neighbour] = Math.min(shortestPath[neighbour], total);
                        }
                    }
                }
            }
        }
        // Convert unreachable nodes (-1) to the specified output format (3 -> -1)
        for (int i = 0; i < n; i++) {
            if (shortestPath[i] == Integer.MAX_VALUE) {
                shortestPath[i] = -1;
            }
        }


        return shortestPath;
    }

    public static void DFSTravarsal(int vertex, boolean[] visited, Stack<Integer> st,
            HashMap<Integer, ArrayList<HashMap<Integer, Integer>>> adjList) {

        visited[vertex] = true;

        ArrayList<HashMap<Integer, Integer>> neigboursList = adjList.get(vertex);
        if (neigboursList != null) {
            for (HashMap<Integer, Integer> map : neigboursList) {
                Set<Integer> neigbours = map.keySet();
                for (Integer neigbour : neigbours) {
                    if (!visited[neigbour]) {
                        DFSTravarsal(neigbour, visited, st, adjList);
                    }
                }
            }
        }
        st.push(vertex);
    }
}
