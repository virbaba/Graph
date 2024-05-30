import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int node;
    int distance;

    public Node(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node n) {
        return Integer.compare(this.distance, n.distance);
    }
}

public class Solution {
    public static ArrayList<ArrayList<Integer>> calculatePrimsMST(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        // Create adjacency list
        HashMap<Integer, List<int[]>> adjList = new HashMap<>();
        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            int dis = edge.get(2);

            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[] { to, dis });
            adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new int[] { from, dis });
        }

        int[] distance = new int[n + 1];
        int[] parent = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        // Initialize distances
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));

        while (!q.isEmpty()) {
            Node currNode = q.poll();
            int pNode = currNode.node;

            if (visited[pNode]) continue;
            visited[pNode] = true;

            List<int[]> neighbours = adjList.getOrDefault(pNode, new ArrayList<>());

            for (int[] neighbour : neighbours) {
                int cNode = neighbour[0];
                int cNodeDis = neighbour[1];

                if (!visited[cNode] && cNodeDis < distance[cNode]) {
                    parent[cNode] = pNode;
                    distance[cNode] = cNodeDis;
                    q.offer(new Node(cNode, cNodeDis));
                }
            }
        }

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (parent[i] != 0) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(parent[i]);
                list.add(i);
                list.add(distance[i]);
                ans.add(list);
            }
        }

        return ans;
    }
}
