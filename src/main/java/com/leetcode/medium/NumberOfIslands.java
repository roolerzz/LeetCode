package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/number-of-islands
public class NumberOfIslands {
    /*
        - Use a Set<String> to remember the positions seen already(i + ":" + j)
        - For every pair of (row,col) in the matrix,
            - If that element is not visited yet,
                - Run DFS(elem)
                - INcrement number of islands.
        return number of islands.
        - DFS()
            - if element/index is not valid, return
            - mark it as visited.
            - recursively visit its neighbors.
    */

        void dfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;

            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0';
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++num_islands;
                        dfs(grid, r, c);
                    }
                }
            }

            return num_islands;
        }


        public int numIslandsDFSREC(char[][] grid) {
            if(grid == null || grid.length == 0) return 0;
            Set<String> visited = new HashSet<>();
            int noOfIslands = 0;
            for(int row = 0 ; row < grid.length; row++) {
                for(int col = 0; col < grid[row].length; col++){
                    if(grid[row][col] == '1')
                    {
                        dfs(grid, visited, row, col);
                        noOfIslands++;
                    }
                }
            }
            return noOfIslands;
        }

        private void dfs(char[][] grid, Set<String> visited, int row, int col){
            if(row < 0 || col < 0 || row>= grid.length || col>=grid[row].length || grid[row][col]=='0') return;
            visited.add(row + ":" + col);
            dfs(grid, visited, row+1, col);
            dfs(grid, visited, row, col+1);
            dfs(grid, visited, row-1, col);
            dfs(grid, visited, row, col-1);
        }

        private boolean isValid(char[][] grid, Set<String> visited, int row, int col){
            return row >= 0 && row < grid.length && col >=0 && col < grid[row].length && !visited.contains(row +":" + col) && grid[row][col] == '1';
        }

        public int numIslandsBFS(char[][] grid) {
            if(grid == null || grid.length == 0) return 0;
            Deque<Integer> rows = new ArrayDeque<>();
            Deque<Integer> cols = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            int noOfIslands = 0;
            int[] dr = {1,-1,0,0};
            int[] dc = {0,0,1,-1};
            for(int row = 0 ; row < grid.length; row++){
                for(int col = 0; col < grid[row].length;col++){
                    if(isValid(grid, visited, row, col)){
                        rows.add(row);
                        cols.add(col);
                        while(!rows.isEmpty()){
                            int currR = rows.poll();
                            int currC = cols.poll();
                            if(!isValid(grid, visited, currR, currC)) continue;
                            visited.add(currR + ":" + currC);
                            addToDeque(rows, cols, currR, currC, dr, dc);
                        }
                        noOfIslands++;
                    }
                }
            }
            return noOfIslands;
        }


        public int numIslands2(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;

            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        ++num_islands;
                        grid[r][c] = '0'; // mark as visited
                        Queue<Integer> neighbors = new LinkedList<>();
                        neighbors.add(r * nc + c);
                        while (!neighbors.isEmpty()) {
                            int id = neighbors.remove();
                            int row = id / nc;
                            int col = id % nc;
                            if (row - 1 >= 0 && grid[row-1][col] == '1') {
                                neighbors.add((row-1) * nc + col);
                                grid[row-1][col] = '0';
                            }
                            if (row + 1 < nr && grid[row+1][col] == '1') {
                                neighbors.add((row+1) * nc + col);
                                grid[row+1][col] = '0';
                            }
                            if (col - 1 >= 0 && grid[row][col-1] == '1') {
                                neighbors.add(row * nc + col-1);
                                grid[row][col-1] = '0';
                            }
                            if (col + 1 < nc && grid[row][col+1] == '1') {
                                neighbors.add(row * nc + col+1);
                                grid[row][col+1] = '0';
                            }
                        }
                    }
                }
            }

            return num_islands;
        }


        public int numIslandsDFSIter(char[][] grid) {
            if(grid == null || grid.length == 0) return 0;
            Set<String> visited = new HashSet<>();
            Deque<Integer> cols = new ArrayDeque<>();
            Deque<Integer> rows = new ArrayDeque<>();
            int[] dr = {1, -1, 0, 0};
            int[] dc = {0, 0, 1, -1};
            int noOfIslands = 0;
            for(int row = 0 ; row < grid.length; row++) {
                for(int col = 0; col < grid[row].length; col++){
                    if(isValid(grid, visited, row,col))
                    {
                        rows.push(row);
                        cols.push(col);
                        while(!rows.isEmpty()){
                            int currR = rows.removeLast();
                            int currC = cols.removeLast();
                            if(!isValid(grid, visited, currR, currC)) continue;
                            visited.add(currR+ ":" + currC);
                            addToDeque(rows, cols, currR, currC, dr, dc);
                        }
                        noOfIslands++;
                    }
                }
            }
            return noOfIslands;
        }

        private void addToDeque(Deque<Integer> rows, Deque<Integer> cols, int currR, int currC, int[] dr, int[] dc){
            for(int i=0 ; i < 4 ; i++){
                rows.offer(currR + dr[i]);
                cols.offer(currC + dc[i]);
            }
        }

    /*
    Use of direction array :  int[] d = {0, 1, 0, -1, 0};
    */

}
