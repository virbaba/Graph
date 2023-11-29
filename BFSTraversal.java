/*
  Time Complexity: O(V + E)
  Space Complexity: O(V)
*/
import java.util.*;
public class Solution {
    public static List<Integer> bfsTraversal(int n, List<List<Integer>> adj){
        // Write your code here
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        
        q.offer(0);
        while(!q.isEmpty()){
            int ele = q.poll();
            if(!visited[ele]){
                visited[ele] = true; // visite as true
                ans.add(ele);
                List<Integer> l = adj.get(ele);
                for(int e : l){
                    q.offer(e);
                }
            }
        }

        
        return ans;


    }
}

