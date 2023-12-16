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
        int[] parent = new int[v]; // parent in DFS tree
        boolean[] isArticulation = new boolean[v]; // to mark articulation points
        int currentTime = 0;
        
        Arrays.fill(parent, -1); // initialize parent array
        
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                findArticulationPointsUtil(i, visited, disc, low, adjList, parent, isArticulation, currentTime);
            }
        }
        
        for (int i = 0; i < v; i++) {
            if (isArticulation[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    private void findArticulationPointsUtil(int u, boolean[] visited, int[] disc, int[] low, 
                                           List<Integer>[] adjList, int[] parent, boolean[] isArticulation, int currentTime) {
        int children = 0; // count of child nodes in DFS tree
        visited[u] = true;
        disc[u] = low[u] = currentTime++;
        
        for (int v : adjList[u]) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                findArticulationPointsUtil(v, visited, disc, low, adjList, parent, isArticulation, currentTime);
                low[u] = Math.min(low[u], low[v]);
                
                // Check for articulation points using low values
                if (parent[u] == -1 && children > 1) {
                    isArticulation[u] = true;
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    isArticulation[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
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
