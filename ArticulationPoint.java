import java.util.*;

public class ArticulationPoints {

    private List<Integer> findArticulationPoints(int[][] edges, int v, int e) {
        List<Integer> result = new ArrayList<>();
        
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
        boolean[] isArticulation = new boolean[v]; // to mark articulation points
        int currentTime = 0;
        
        Arrays.fill(parent, -1); // initialize parent array
        
        for (int vertex = 0; vertex < v; vertex++) {
            if (!visited[i]) {
                findArticulationPointsUtil(vertex, -1, visited, disc, low, adjList, isArticulation, currentTime);
            }
        }
        
        for (int i = 0; i < v; i++) {
            if (isArticulation[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private void findArticulationPointsUtil(int vertex, int parent, boolean[] visited, int[] disc, int[] low, 
                                           List<Integer>[] adjList, boolean[] isArticulation, int currentTime) {
        int children = 0; // count of child nodes in DFS tree
        visited[vertex] = true;
        disc[vertex] = low[vertex] = currentTime++;
        
        for (int neighbour : adjList[vertex]) {
            if (!visited[neighbour]) {
                children++;
                findArticulationPointsUtil(neighbour, vertex, visited, disc, low, adjList, isArticulation, currentTime);
                low[vertex] = Math.min(low[vertex], low[neighbour]);
                
                // Check for articulation points using low values
                if (parent == -1 && children > 1) {
                    isArticulation[vertex] = true;
                }
                if (parent != -1 && low[neighbour] >= disc[vertex]) {
                    isArticulation[u] = true;
                }
            } else if (parent != neighbour) {
                low[vertex] = Math.min(low[vertex], disc[neighbour]);
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {1, 3}, {3, 4}, {4, 5}, {5, 3}};
        int v = 6;
        int e = edges.length;
        
        ArticulationPoints apFinder = new ArticulationPoints();
        List<Integer> articulationPoints = apFinder.findArticulationPoints(edges, v, e);
        
        System.out.println("Articulation points in the graph:");
        for (int point : articulationPoints) {
            System.out.println(point);
        }
    }
}
