import java.util.*;

public class Solution {
    public static int[] bellmonFord(int n, int m, int src, List<List<Integer>> edges) {
        // Write your code here.
        int[] distance = new int[n + 1];
        Arrays.fill(distance, 100000000);
        distance[src] = 0;
        for (int i = 1; i < n; i++) {
            for (List<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);
                if (distance[u] + wt < distance[v])
                    distance[v] = distance[u] + wt;
            }
        }

        // check for negative cycle

        // for (List<Integer> edge : edges) {
        //     int u = edge.get(0);
        //     int v = edge.get(1);
        //     int wt = edge.get(2);
        //     if (distance[u] + wt < distance[v])
        //         return distance;
        // }

        return distance;

    }
}
