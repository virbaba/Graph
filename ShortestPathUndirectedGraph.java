import java.util.*;
public class Solution {

	public static LinkedList<Integer> shortestPath(int[][] edges, int n, int m, int s, int t) {
		// Write your code here.
		// firstly make adjList
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

		// we needed visited array
		boolean[] visited = new boolean[n + 1];
		int[] parent = new int[n + 1];
		Arrays.fill(parent, -1);

		for(int vertex = s; vertex <= n; vertex++){
			if(!visited[vertex]){
				BFSTraversal(vertex, visited, parent, adjList);
			}
		}

		LinkedList<Integer> ans = new LinkedList<>();
		ans.offer(t);
		while(parent[t] != -1){
			ans.offer(parent[t]);
			t = parent[t];
		}

		Collections.reverse(ans);
		return ans;
	}

	public static void BFSTraversal(int vertex, boolean[] visited, int[] parent, HashMap<Integer, ArrayList<Integer>> adjList){
		Queue<Integer> q = new LinkedList<>();

		q.add(vertex);
		visited[vertex] = true;
		while(!q.isEmpty()){
			int current = q.poll();
			ArrayList<Integer> neigbours = adjList.get(current);
			if(neigbours != null){
				for(int neigbour : neigbours){
					if(!visited[neigbour]){
						visited[neigbour] = true;
						parent[neigbour] = current;
						q.offer(neigbour);
					}
				}
			}
		}
	}

} 
