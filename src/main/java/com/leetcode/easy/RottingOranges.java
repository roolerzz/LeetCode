package com.leetcode.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


// https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
    /*
        - Similar to multi source shortest paths.
        - Starting at the rotten tomatoes, we will do a Bredth first search(need the shortest path). At each level of BFS, we will increment the day count.
        - Start with adding all the rotten tomatoes location to the list.
    */

// https://leetcode.com/problems/rotting-oranges/discuss/305746/java-95
        public int orangesRotting(int[][] grid) {
            if(grid == null || grid.length == 0) return -1;
            return bfs(grid);
        }

        private int bfs(int[][] grid){
            int days = 0;
            List<int[]> rottenLocations = findRottenLoc(grid);
            int freshCnt =0;
            Queue<int[]> q = new ArrayDeque<>();
            for(int i=0 ; i < grid.length ; i++){
                for(int j=0; j < grid[i].length; j++){
                    if(grid[i][j]==2)
                        q.offer(new int[]{i, j});
                    else if (grid[i][j] == 1)
                        freshCnt++;
                }
            }
            if(freshCnt ==0) return 0;
            int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
            while(!q.isEmpty()){
                int size = q.size();
                while(size-- >0) {
                    int[] curr = q.poll();
                    int currR= curr[0];
                    int currC= curr[1];
                    for(int[] d: dir){
                        int nextR = curr[0] + d[0];
                        int nextC = curr[1] + d[1];
                        if(nextR < 0 || nextC <0 || nextR >= grid.length || nextC >= grid[nextR].length || grid[nextR][nextC] != 1 ) continue;
                        grid[nextR][nextC]=2;
                        freshCnt--;
                        q.offer( new int[]{nextR, nextC});
                    }
                }
                days++;
            }
            return freshCnt==0 ? days-1: -1;
        }

        private boolean freshLeft(int[][] grid){
            for(int i=0 ; i < grid.length ; i++){
                for(int j=0; j < grid[i].length; j++){
                    if(grid[i][j]== 1) return true;
                }
            }
            return false;
        }

        private boolean isValid(int[][] grid, int i, int j){
            return i>=0 && j >=0 && i < grid.length && j < grid[i].length && grid[i][j]==1;
        }

        private List<int[]> findRottenLoc(int[][] grid){
            List<int[]> res = new ArrayList<>();

            return res;
        }

}
