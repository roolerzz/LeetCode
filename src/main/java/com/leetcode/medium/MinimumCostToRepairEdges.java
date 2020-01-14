package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/discuss/interview-question/357310
public class MinimumCostToRepairEdges {

    /*
    *   Question: There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been
    *   broken disconnecting the graph. Find the minimum cost to repair the edges so that all the nodes are once
    *   again accessible from each other.
        Input:
        n, an int representing the total number of nodes.
        edges, a list of integer pair representing the nodes connected by an edge.
        edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge
        is currently broken and the cost of repairing that edge, respectively (e.g. [1, 2, 12] means to repair an
        edge between nodes 1 and 2, the cost would be 12).

        Example 1:
        Input: n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
        Output: 20
        Explanation:
        There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5].
        We can connect these components into a single component by repearing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.
    *
    * */

    private static int[] uf;

    public static int minCost(int n, int[][] edges, int[][] edgesToRepair){
        if(n == 0)return -1;
        uf = new int[n+1];
        int totalCost = 0;
        for(int i=1; i<n+1; i++)
            uf[i]=i;
        Set<String> broken = new HashSet<>();
        for(int[] brokenEdge : edgesToRepair){
            broken.add(Arrays.toString(Arrays.copyOfRange(brokenEdge,0,2)));
        }
        for(int[] edge : edges){
            if(n==1) break;
            if(broken.contains(Arrays.toString(edge))) continue;
            int p = edge[0];
            int q = edge[1];
            if(!find(p, q))
                union(p, q);
            n--;
        }
        Arrays.sort(edgesToRepair, Comparator.comparingInt(a -> a[2]));
        for(int[] brokenEdge: edgesToRepair){
            int p = brokenEdge[0];
            int q = brokenEdge[1];
            if(!find(p,q)){
                union(p, q);
                totalCost += brokenEdge[2];
                n--;
            }
        }
        return totalCost;
    }

    private static void union(int p, int q){
        uf[root(p)] = uf[root(q)];
    }

    private static boolean find(int p, int q){
        return uf[root(p)] == uf[root(q)];
    }

    private static int root(int p){
        while(p != uf[p])
            p=uf[p];
        return p;
    }

  public static void main(String[] args) {
      int[][] edges = new int[][]{{1, 2}, {2, 3}, {4, 5}, {3, 5}};
      int[][] repair = new int[][]{{1, 6, 410}, {2, 4, 800}};
      System.out.println(minCost(6, edges, repair));

      edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
      repair = new int[][]{{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
      System.out.println(minCost(5, edges, repair));
  }
}
