package com.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/shortest-distance-from-all-buildings
public class ShortestDistanceFromAllBuildings {
    /*
    IDEA 1:

    - For every 0(empty slot where a house can be built), do a BFS on the array. Put the node's value on the array and its distance from the source node. Also need a way to remember the visited vertex in each run to not run them again. Boolean 2D array? -ve values if array can be modified.
        - Pop the current node from the top of the queue. If the curr node is 2, discard.
        - if 0, process its neighbours(i.e. add them into the queue with added distance)
        - If 1, decrement the count of 1s processed and add its value to its sumOfDistance local to the variable.
        - Once the count is 0, compare with minDistGlobal variable. Update that if its more than sumOfDistance from that source.
        - Return -1 if minDistGlobal is Integer.MAX_VALUE. That means some of the buildings were not reachable from each of the node.
        Time: O(n^2) where n is the number of nodes in the graph.
        Breakdown: Can have n nodes each being 0. Each BFS takes O(N) time.
       */

        int minResZeroBFS = Integer.MAX_VALUE;
        // This is way slower 296ms and 67.3 MBs compared to the other soln which is 12ms and 53 MBs.
        public int shortestDistance2(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length ==0) return 0;

            int count = countNumber(grid, 1);
            System.out.println("Total no of 1s are : " + count);
            for(int i=0; i<grid.length; i++){
                for(int j=0; j< grid[0].length; j++){
                    if(grid[i][j] == 0){
                        bfsVisitZero(grid, i, j, count);
                    }
                }
            }
            return minResZeroBFS == Integer.MAX_VALUE? -1 : minResZeroBFS;

        }

        private void bfsVisitZero(int[][] grid, int row, int col, int count){
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            // System.out.println("Starting BFS for row : " + row + " and col : " + col + " . Count value is : " + count);
            Queue<int[]> q = new ArrayDeque<>();
            int[] dirs = {0,-1,0,1,0};
            q.offer(new int[]{row, col, 0});
            visited[row][col] = true;
            int distToAll1s = 0;
            while(!q.isEmpty()){
                int[] curr = q.poll();
                for(int d=0; d < 4; d++){
                    int newR = curr[0]+ dirs[d];
                    int newC = curr[1]+ dirs[d+1];
                    int newDist = curr[2]+1;
                    if(newR >=0 && newR < grid.length && newC>=0 && newC <grid[0].length && grid[newR][newC] != 2 && !visited[newR][newC]){
                        visited[newR][newC] = true;
                        if(grid[newR][newC] == 1){
                            count--;
                            distToAll1s+= newDist;
                            continue;
                        }
                        q.offer(new int[]{newR, newC, newDist}); // Only add 0s.

                    }
                }

            }
            // System.out.println("BFS completed for row : " + row + " and col : " + col + " . Count value is : " + count);
            if(count == 0)
                minResZeroBFS = Math.min(minResZeroBFS, distToAll1s);
        }


        private int countNumber(int[][] grid, int num){
            int c = 0;
            for(int i=0; i < grid.length; i++){
                for(int j=0; j < grid[0].length; j++){
                    if(grid[i][j] == num)
                        c++;
                }
            }
            return c;
        }


    /*
    IDEA 2:
        - If the number of 1s is drastically lesser than number of 0s, it probably make sense to to BFS for each of the 1s instead of 0s.
        - For each 1, run the bfs. Also with each level being processed, add that as the distance value to the current node.
        - Need a 2Dimensional array to keep track of the total distances for each 0, from all the 1s.
        How do you figureout if for some of the buildings, that particular 0 is not reachable. Need some extra mechanism for that.
        - Create a local 2D array which just sets the sum for that run. If that would be 0, that means that is not visited. I guess we can just skip that 0 to calculate its final distance or somehow mark that in the end to remember??
        - Also within the iteration, how to remember which one of the distances are the shortest ones?
        - Also, how do you make sure that you don't visit the element again.
    */

        final int[] dirs = {0,1,0,-1,0};

        int min = Integer.MAX_VALUE;

        public int shortestDistance(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length ==0) return 0;
            int[][] dist= new int[grid.length][grid[0].length];
            int start = 1;

            for(int i=0; i<grid.length; i++){
                for(int j=0; j< grid[0].length; j++){
                    if(grid[i][j] == 1){
                        bfsVisit(grid, dist, i, j, --start);
                    }
                }
            }
            return min == Integer.MAX_VALUE? -1 : min;

        }

        private void bfsVisit(int[][] grid, int[][] dist, int row, int col, int start){
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{row, col});
            int level = 0;
            min = Integer.MAX_VALUE;
            while(!q.isEmpty()){
                int size = q.size();
                level++;
                for(int k=0; k < size ; k++){
                    int[] curr = q.poll();
                    for(int i=1; i < dirs.length; i++){
                        int newR = curr[0] + dirs[i-1];
                        int newC = curr[1] + dirs[i];
                        if(newR >=0 && newR <grid.length && newC>=0 && newC <grid[0].length && grid[newR][newC]==start){
                            grid[newR][newC]--; // Changing a 0 to mark as visited, so as to not revisit again.
                            q.offer(new int[]{newR, newC});
                            dist[newR][newC] += level;
                            min = Math.min(min, dist[newR][newC]);
                        }
                    }
                }
            }
        }


}
