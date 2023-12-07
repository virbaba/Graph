import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Solution {

    public static ArrayList<ArrayList<Integer>> printAllPaths(int n, int m, ArrayList<ArrayList<Integer>> edges,
            int source, int destination) {
        // Write your code here

         HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);

            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }

        boolean[] visited = new boolean[n + 1];
        ArrayList<Integer> path = new ArrayList<>();
        path.add(source);
        HashSet<ArrayList<Integer>> pathSet = new HashSet<>();

        check(source, destination, visited, adjList, path, pathSet);

        return new ArrayList<>(pathSet);

    }

    public static void check(int source, int destination, boolean[] visited,
                             HashMap<Integer, ArrayList<Integer>> adjList,
                             ArrayList<Integer> path, HashSet<ArrayList<Integer>> pathSet) {
        if (source == destination) {
            pathSet.add(new ArrayList<>(path));
            return;
        }

        visited[source] = true;
        ArrayList<Integer> neighbours = adjList.get(source);
        if (neighbours != null) {
            for (int neighbour : neighbours) {
                if (!visited[neighbour]) {
                    path.add(neighbour);
                    check(neighbour, destination, visited, adjList, path, pathSet);
                    path.remove(path.size() - 1);
                }
            }
        }
        visited[source] = false;
    }
}
