package com.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {

    /*

    - Repeat and validate understanding of the questsion.(2-3 min)
    - Ask clarification questions about input, expectation, edge cases.(1min)
    - Come up with brute force. Tell the time and space complexity.(2-3 mins)
    - See if you can spot the repeat work and improve from there or can use a different data structure that can help speed up some part of data access.(5-10 mins)
    - Write down the psuedo-code or map of the problem before actually jumping onto it.(2 min)
    - Go ahead and quickly implement the solution.(3-5)
    - Say the time and space complexity of this soln.
    - Verification. Try a dry run of the solution. ALso check off by 1 errors.

    Questions:
    - What if no edge in the graph. Similar to not strongly connected. Has to be Strongly connected graph. If not, then not bipartite??
    - need to color the vertices using 2 different colors. If we reach a vertex to color that with color a but that is already colored with colored b, then that is not bipartite graph.
    - use integegr array visited[] to represent red(-1), black(1) and 0(not visited).
    - Can be done both by using DFS(color changes from parent to childern) or BFS(Queue. Color changes after every level)
    -

    Questions:

    Space complexity of BFS?
    */

    private static final int BLACK = 1;

    // private static final int RED = -1;
    private static final int NOT_VISITED = 0;

    private boolean isBipartite = true;

    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        for(int start = 0; start < graph.length; start++){
            if(visited[start] == NOT_VISITED)
                bfs(graph, visited, start, BLACK);
        }
        return isBipartite;
    }

    private void dfs(int[][] graph, int[] visited, int v, int color){
        if(!isBipartite) return;
        if(visited[v] != NOT_VISITED) {
            if(visited[v] != color)
                isBipartite = false;
            return;
        }
        visited[v] = color;
        for(int w : graph[v]){
            dfs(graph, visited,w, (-1*color));
        }
    }


    private void bfs(int[][] graph, int[] visited, int start, int color){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int currColor = color;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- >0){
                int v = q.poll();
                if(visited[v] != NOT_VISITED){
                    if(visited[v] == currColor) continue;
                    if(visited[v] != currColor){
                        isBipartite = false;
                        return;
                    }
                }
                System.out.println("Coloring node: " + v + " with the color " + (currColor == 1 ? "BLACK": "RED"));
                visited[v] = currColor;
                for(int w : graph[v]){
                    // if(w !=v)
                    System.out.println("Going to add to queue child " + w + " for the parent " + v);
                    q.offer(visited[w]);
                }
            }
            // System.out.println("Going to change the current color from " + (currColor == BLACK ? "BLACK" : "RED") + " to " + (currColor==BLACK? "RED":"BLACK"));
            System.out.println("Color before change :" + currColor);
            currColor = -1 * currColor;
            System.out.println("Color after change :" + currColor);

        }
    }

    public static void main(String[] args) {
        IsGraphBipartite bp = new IsGraphBipartite();
        int[][] input = {{1,3},{0,2},{1,3},{0,2}};
        System.out.println(bp.isBipartite(input));
    }
}
