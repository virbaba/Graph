/*
  TIME COMPLEXITY : ELOG(E)
*/
import java.util.*;

public class Solution {
    // finding parent and doing path compression
    public static int findPar(int[] parent, int node){
        if(node == parent[node])
            return node;
        
        parent[node] = findPar(parent, parent[node]);
        return parent[node];
    }

    // union the two component
    public static void unionByRank(int[] parent, int[] rank, int node1, int node2){
        int par1 = findPar(parent, node1);
        int par2 = findPar(parent, node2);

        if(rank[par1] == rank[par2]){
            parent[par2] = par1;
            rank[par1]++;
        }
        else if(rank[par1] < rank[par2]){
            parent[par1] = par2;
        }
        else{
            parent[par2] = par1;
        }
    }
    public static int kruskalMST(int n,int [][]edges) {
        //Write your code here
        int[] parent = new int[n+1];
        int[] rank = new int[n+1];

        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        // in kruskalMST firstly sort the edges according to weight
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
        int totalWeights = 0;
        // traverse all
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            int weight = edge[2];
            int par1 = findPar(parent, node1);
            int par2 = findPar(parent, node2);
            
            // if parent of both vertex is not equall then connect both 
            // and store their weight if equall do nothing
            if (par1 != par2) {
                totalWeights += weight;
                unionByRank(parent, rank, node1, node2);
            }
        }

        

        return totalWeights;
        
    }
}
