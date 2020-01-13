package com.leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/path-with-maximum-minimum-value/
public class PathWithMaximumMinimumValue {
    private static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

    public static int maximumMinimumPath(int[][] matrix) {
        // Each node in the priority queue stores maximum score path out of all the paths that leads to the node. There could be multiple nodes representing the same position in the array due to the reason that it can be reached from either of the 4 direction. The way comparator is designed is, which ever path to that position is the maximum one, that would be picked up to be processed next. We needed a way in which to process the nodes. We cannnot randomly process nodes, if we intend to find the max score path leading to a position.
        Queue<int[]> minPQ = new PriorityQueue<>((a, b)-> b[2]-a[2]);
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        minPQ.offer(new int[]{0, 0, matrix[0][0]});
        while(!minPQ.isEmpty()){
            int[] cell = minPQ.poll();
            int currR = cell[0];
            int currC = cell[1];
            int currMaxScore = cell[2];
            if(currR == matrix.length-1 && currC == matrix[0].length-1)
                return currMaxScore;
            visited[currR][currC]=true;
            for(int[] dir: dirs){
                int nextR = currR + dir[0];
                int nextC = currC + dir[1];
                if(!isValid(matrix, nextR, nextC, visited)) continue;
                int nextMaxScore = Math.min(currMaxScore, matrix[nextR][nextC]);
                minPQ.offer(new int[]{nextR, nextC, nextMaxScore});
            }
        }
        return -1;
    }

    private static boolean isValid(int[][] matrix, int currR, int currC, boolean[][] visited){
        return currR >=0 && currC >=0 && currR < matrix.length && currC < matrix[currR].length && !visited[currR][currC];
    }
}
