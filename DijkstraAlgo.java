/*
  Let the node at which we are starting be called the initial node. Let the distance of node Y be the distance from the initial node to Y. 
  Dijkstra's algorithm will initially start with infinite distances and will try to improve them step by step.

Mark all nodes unvisited. Create a set of all the unvisited nodes called the unvisited set.
Assign to every node a tentative distance value: set it to zero for our initial node and to infinity for all other nodes. 
During the run of the algorithm, the tentative distance of a node v is the length of the shortest path discovered so far between the node v and the starting node. 
Since initially no path is known to any other vertex than the source itself (which is a path of length zero), all other tentative distances are initially set to infinity. 
Set the initial node as current.[17]
For the current node, consider all of its unvisited neighbors and calculate their tentative distances through the current node. 
Compare the newly calculated tentative distance to the one currently assigned to the neighbor and assign it the smaller one. For example, 
if the current node A is marked with a distance of 6, and the edge connecting it with a neighbor B has length 2, then the distance to B through A will be 6 + 2 = 8. 
If B was previously marked with a distance greater than 8 then change it to 8. Otherwise, the current value will be kept.
When we are done considering all of the unvisited neighbors of the current node, mark the current node as visited and remove it from the unvisited set. 
A visited node will never be checked again (this is valid and optimal in connection with the behavior in step 6.:
that the next nodes to visit will always be in the order of 'smallest distance from initial node first' so any visits after would have a greater distance).
If the destination node has been marked visited (when planning a route between two specific nodes) or 
if the smallest tentative distance among the nodes in the unvisited set is infinity (when planning a complete traversal; 
occurs when there is no connection between the initial node and remaining unvisited nodes), then stop. The algorithm has finished.
Otherwise, select the unvisited node that is marked with the smallest tentative distance, set it as the new current node, and go back to step 3.
*/
import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int distance, node;

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

	public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Integer>> edges, int v, int e, int source) {
		// Write your code here.
		 HashMap<Integer, List<int[]>> adjList = new HashMap<>();

        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            int distance = edge.get(2);

            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{to, distance});
            adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new int[]{from, distance});
        }

        ArrayList<Integer> distances = new ArrayList<>(Collections.nCopies(v, Integer.MAX_VALUE));
        distances.set(source, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.node;
            int currentDistance = current.distance;


            List<int[]> neighbors = adjList.getOrDefault(currentNode, new ArrayList<>());
            for (int[] neighbor : neighbors) {
                int neighborNode = neighbor[0];
                int edgeDistance = neighbor[1];
                int newDistance = distances.get(currentNode) + edgeDistance;

                if (newDistance < distances.get(neighborNode)) {
                    distances.set(neighborNode, newDistance);
                    pq.offer(new Node(neighborNode, newDistance));
                }
            }
        }

        return distances;
	}
}
