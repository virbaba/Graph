import java.util.* ;
import java.io.*; 
public class Solution {
    public static Boolean pathInAGraph(int n, int edges[][], int source, int destination) {
        // Write your code here..
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];

            if(!adjList.containsKey(from)){
                adjList.put(from, new ArrayList<>());
            }
            if(!adjList.containsKey(to)){
                adjList.put(to, new ArrayList<>());
            }

            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        boolean[] visited = new boolean[n];

        return check(source, destination, visited, adjList);
    }

    public static boolean check(int source, int destination, boolean[] visited, HashMap<Integer, ArrayList<Integer>> adjList){
        if(source == destination)
            return true;
        
        visited[source] = true;
        ArrayList<Integer> neigbours = adjList.get(source);
        if(neigbours != null){
            for(int neigbour : neigbours){
                if(!visited[neigbour]){
                    if(check(neigbour, destination, visited, adjList))
                        return true;
                }
            }
        }
        return false;
    }

}
