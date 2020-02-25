package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0) return res;
        int currR = 0, currC = 0;
        res.add(matrix[currR][currC]);
        int[][] dirs= {{0,1},{1,0},{0,-1},{-1,0}};
        int currDir = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[currR][currC] = true;
        int elemsToVisit = matrix.length*matrix[0].length-1;
        while(elemsToVisit > 0){
            int nextR = currR + dirs[currDir][0];
            int nextC = currC + dirs[currDir][1];
            if(!isValid(matrix, nextR, nextC, visited)){
                currDir = (currDir + 1 )%4;
                continue;
            }
            res.add(matrix[nextR][nextC]);
            visited[nextR][nextC] = true;
            currR = nextR;
            currC = nextC;
            elemsToVisit--;
        }
        return res;
    }


    private boolean isValid(int[][] matrix, int row, int col, boolean[][] visited){
        return row >=0 && col >= 0 && row < matrix.length && col < matrix[0].length && !visited[row][col];
    }

}
