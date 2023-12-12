/*
  Time Complexity: O(m) + O(n) + O(n . (n+2m))
*/
import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Solution {

    public static int getMin(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 1; i < distance.length; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }


    public static ArrayList<ArrayList<Integer>> calculatePrimsMST(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        HashMap<Integer, List<int[]>> adjList = new HashMap<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            int distance = edge.get(2);

            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[] { to, distance });
            adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new int[] { from, distance });
        }

        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        boolean[] visited = new boolean[n + 1];

        while (true) {
            int minIndex = getMin(distance, visited);
            if (minIndex == -1) {
                break; // All vertices have been visited
            }

            visited[minIndex] = true;
            List<int[]> neighbours = adjList.getOrDefault(minIndex, new ArrayList<>());
            for (int[] neighbour : neighbours) {
                int child = neighbour[0];
                int childDis = neighbour[1];
                if (!visited[child] && childDis < distance[child]) {
                    parent[child] = minIndex;
                    distance[child] = childDis;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(parent[i]);
            edge.add(i);
            edge.add(distance[i]);
            ans.add(edge);
        }

        return ans;
    }
}
