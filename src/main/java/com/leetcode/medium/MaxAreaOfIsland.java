package com.leetcode.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// https://leetcode.com/problems/max-area-of-island/
public class MaxAreaOfIsland {

        /*
        - Non-empty 2D array of 0,1.
        - Max area 0 if no island. All zeroes or not a group(atleast 2)?
        Initialize Global_Count = 0.
        - Find the size of the biggest connected component.
        - To remember the visited indexes, create a set of string, where each index would represent a unique string "i:j".
            - For each of the unvisited node, do count = 1 dfs(count=count+1)
            - If Invalid Location(Index out of bound, or already visited or not a 1) return.
            - Else increment the count check to see if the current count is greater than global Count, update that value.
        // - Requires extra space of N if there are total of N elements.

        [1,0,0
         1,1,0
         0,1,1]

        Visited : {}

        [[0]]
        [[1]]
        [[0,0,0,0,0,0,1,0,0]]
        [[1],[0],[1]]



        */
        int globalCt = 0;

        public int maxAreaOfIslandDFSRec(int[][] grid) {
            // || (grid.length == 1 && grid[0].length==0 && grid[0][0] == 0)
            if(grid == null || grid.length == 0) return 0;
            Set<String> visited = new HashSet<>();
            int[][] componentSize = new int[grid.length][grid[0].length];

            for(int i = 0; i < grid.length; i++){
                for(int j = 0; j < grid[i].length; j++){
                    // componentSize[i][j] = grid[i][j] == 1 ? 1 : 0;
                    if(isValid(grid, i, j, visited))
                        dfs(grid,componentSize, i, j, i, j, visited);
                }
            }
            return globalCt;
        }

        private void dfs(int[][]grid, int[][] componentSize, int rootI, int rootJ, int i, int j, Set<String> visited){
            if(!isValid(grid, i, j, visited))
                return;
            componentSize[rootI][rootJ]++;
            globalCt = Math.max(globalCt, componentSize[rootI][rootJ]);
            visited.add(i + ":" + j);
            dfs(grid, componentSize, rootI, rootJ, i+1, j, visited);
            dfs(grid, componentSize, rootI, rootJ, i-1, j, visited);
            dfs(grid, componentSize, rootI, rootJ, i, j+1, visited);
            dfs(grid, componentSize, rootI, rootJ, i, j-1, visited);
        }

        private boolean isValid(int[][] grid, int i, int j, Set<String> visited){
            return i >= 0 && i < grid.length && j >= 0 && j< grid[i].length && grid[i][j]==1 && !visited.contains(i + ":" + j);
        }


        public int maxAreaOfIslandRec(int[][] grid){
            boolean[][] seen = new boolean[grid.length][grid[0].length];
            int ans = 0;
            for(int r = 0 ; r < grid.length; r++){
                for(int c = 0 ; c < grid[0].length; c++){
                    ans = Math.max(ans, area(grid,seen, r,c));
                }
            }
            return ans;
        }

        private boolean isValidRec(int[][] grid, int i, int j, boolean[][] seen){
            return i >= 0 && i < grid.length && j >= 0 && j< grid[i].length && grid[i][j]==1 && !seen[i][j];
        }

        private int area(int[][] grid, boolean[][] seen, int i, int j){
            if(!isValidRec(grid, i, j, seen)) return 0;
            seen[i][j] = true;
            return 1 + area(grid, seen, i+1, j) + area(grid, seen, i-1, j) + area(grid, seen, i, j-1) + area(grid, seen, i, j+1);
        }

    /*
        DFS Iterative
        boolean[][] seen to know what has already been seen.
        double forloop to conside each i,j pair. Max(globalCt, count for the ij).
            int count;
            Stack<Integer> row;
            StacK<Integer> col;
            add i,j to stack.
            while stack is not empty,
                pop currI, currJ.
                if !isValid() continue
                if isValid()
                    count += 1;
                    globalCt Max of global and count.
                    mark visited.
                    addtoStack all 4 directions.
    */

        public int maxAreaOfIsland(int[][] grid){
            boolean[][] seen = new boolean[grid.length][grid[0].length];
            int maxCount = 0;
            int[] dr = new int[]{1,-1,0,0};
            int[] dc = new int[]{0,0,1,-1};
            for(int r = 0 ; r < grid.length; r++) {
                for(int c = 0 ; c < grid[0].length;c++){
                    if(!isValidRec(grid, r, c, seen)) continue;
                    System.out.println("Starting Global Processing of i:j as " + r + ":" + c);
                    int count = 0;
                    Deque<int[]> stack = new LinkedList<>();
                    stack.push(new int[]{r, c});
                    seen[r][c] = true;
                    while(!stack.isEmpty()){
                        int[] idx = stack.pop();
                        int currR = idx[0], currC = idx[1];
                        System.out.println("Visiting connected components idx as " + currR+ " : "+ currC+
                                " of i:j as " + r + ":" + c);
                        count += 1;
//                        seen[currR][currC] = true;
                        addNeighbors(grid, stack, currR, currC, dr, dc, seen);
                    }
                    maxCount = Math.max(maxCount, count);
                }
            }
            return maxCount;
        }

        private void addNeighbors(int[][] grid, Deque<int[]> stack, int currR, int currC, int[] dr, int[] dc, boolean[][] seen){
            for(int k = 0 ; k< 4 ; k++){
                int nr = currR + dr[k];
                int nc = currC + dc[k];
                if(isValidRec(grid, nr, nc, seen))
                {
                    seen[nr][nc] = true;
                    stack.push(new int[]{nr, nc});
                }
            }
        }

  public static void main(String[] args) {
    MaxAreaOfIsland island = new MaxAreaOfIsland();
    int[][] input = new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
    System.out.println(island.maxAreaOfIsland(input));

  }

    /*
        BFS

    */

        // public int maxAreaOfIsland(int[][] grid){
        //     // bool
        // }




}
