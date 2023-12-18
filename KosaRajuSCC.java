import java.util.*;

public class Solution {

	/*
		KOSARAJU'S Algo
		1. Stroe Topological sort into stack
		2. Transpose the matrix
		3. Do DFS according to stack nodes on the transpose graph.
	*/
	public static int stronglyConnectedComponents(int v, ArrayList<ArrayList<Integer>> edges) {
		// Write your code here.
		HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> tAdjList = new HashMap<>();
		for(ArrayList<Integer> edge: edges){
			int from = edge.get(0);
			int to = edge.get(1);

			adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
			tAdjList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
		}

		boolean[] visited = new boolean[v];
		Stack<Integer> st = new Stack<>();

		for(int vertex = 0; vertex < v; vertex++){
			if(!visited[vertex]){
				DFSTraversal(vertex, visited, adjList, st);
			}
		}
		int count = 0;
		visited = new boolean[v];
		while(!st.isEmpty()){
			int vertex = st.pop();
			if(!visited[vertex]){
				TDFSTraversal(vertex, visited, tAdjList);
				count++;
			}
		}

		return count;
	}
	public static void DFSTraversal(int vertex, boolean[] visited, HashMap<Integer, ArrayList<Integer>> adjList, Stack<Integer> st){
		visited[vertex] = true;

		ArrayList<Integer> neighbours = adjList.getOrDefault(vertex, new ArrayList<>());
		for(int neighbour : neighbours){
			if(!visited[neighbour]){
				DFSTraversal(neighbour, visited, adjList, st);
			}
		}

		st.push(vertex);
	}

	public static void TDFSTraversal(int vertex, boolean[] visited, HashMap<Integer, ArrayList<Integer>> adjList){
		visited[vertex] = true;

		ArrayList<Integer> neighbours = adjList.getOrDefault(vertex, new ArrayList<>());
		for(int neighbour : neighbours){
			if(!visited[neighbour]){
				TDFSTraversal(neighbour, visited, adjList);
			}
		}

	}
}
