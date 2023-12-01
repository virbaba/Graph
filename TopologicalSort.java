/*
  TIME COMPLEXITY : O(V+E)
  SPACE COMPLEXITY : O(V+E)
*/
import java.util.* ;
import java.io.*; 
public class Solution 
{
    public static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> edges, int v, int e) 
    {
        // Write your code here
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for(ArrayList<Integer> edge : edges){
            int from = edge.get(0);
            int to = edge.get(1);
            if(!adjList.containsKey(from)){
                adjList.put(from, new ArrayList<>());
            }
            adjList.get(from).add(to);
        }

        boolean[] visited = new boolean[v];
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> st = new Stack<>();

        for(int vertex = 0; vertex < v; vertex++){
            if(!visited[vertex]){
                sort(vertex, visited, st, adjList);
            }
        }

        if(st.isEmpty())
            ans.add(0);

        while(!st.isEmpty()){
            ans.add(st.pop());
        }

        return ans;
        
    }
    public static void sort(int vertex, boolean[] visited, Stack<Integer> st, HashMap<Integer, ArrayList<Integer>> adjList){
        
        visited[vertex] = true;

        ArrayList<Integer> neigbours = adjList.get(vertex);
        if(neigbours != null){
            for(int neigbour : neigbours){
                if(!visited[neigbour])
                    sort(neigbour, visited, st, adjList);
            }
        }

        st.push(vertex);
        
    }
}
