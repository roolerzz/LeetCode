package com.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/walls-and-gates/
public class WallsAndGates {
    private static final int WALL = -1;

    private static final int GATE = 0;

    private static final int INF = Integer.MAX_VALUE;

    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> q = new LinkedList<>();
        for(int row=0; row < rooms.length; row++){
            for(int col=0; col < rooms[row].length; col++){
                if(rooms[row][col] == GATE)
                    q.offer(new int[]{row, col, 0});
            }
        }
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- >0){
                int[] currNode = q.poll();
                int currR = currNode[0];
                int currC = currNode[1];
                int currDist = currNode[2];
                // rooms[currR][currC]=currDist;
                addToQueue(rooms, q, currR+1, currC, currDist+1);
                addToQueue(rooms, q, currR-1, currC, currDist+1);
                addToQueue(rooms, q, currR, currC+1, currDist+1);
                addToQueue(rooms, q, currR, currC-1, currDist+1);

            }
        }
    }

    private void addToQueue(int[][] rooms, Queue<int[]> q ,int currR, int currC, int currDist){
        if(currR < 0 || currC <0 || currR>=rooms.length || currC >= rooms[currR].length || rooms[currR][currC] != INF) return;
        rooms[currR][currC]=currDist;
        q.add(new int[]{currR, currC, currDist});
    }

}
