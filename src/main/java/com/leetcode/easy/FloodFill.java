package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;

//https://leetcode.com/problems/flood-fill/
// CTCI 8.6
public class FloodFill {

  public static void main(String[] args) {
      FloodFill f = new FloodFill();
      int[][] colors = new int[3][3];
      f.fillPaint(colors);
      f.print2Dmatrix(colors);
      f.paintFill(colors, 2,2,5);
      f.print2Dmatrix(colors);
  }

    public void paintFill(int[][] colors, int i, int j, int colorToFill) {
        if (colors == null ) return;
        if(!isValidIdx(colors,i,j)) return ;
        HashSet<Pair> uniqueIdx = new HashSet<>();
        dfsFill(colors, i, j, colorToFill, colors[i][j], uniqueIdx);
    }

    private void dfsFill(int[][] colors, int i, int j, int colorToFill, int currVal, HashSet<Pair> uniqueIdx) {
        if(!isValidIdx(colors, i, j) || colors[i][j] != currVal || uniqueIdx.contains(new Pair(i, j))) return;
        colors[i][j] = colorToFill;
        uniqueIdx.add(new Pair(i,j));
        dfsFill(colors, i+1, j, colorToFill, currVal, uniqueIdx);
        dfsFill(colors, i, j+1, colorToFill, currVal, uniqueIdx);
        dfsFill(colors, i, j-1, colorToFill, currVal, uniqueIdx);
        dfsFill(colors, i-1, j, colorToFill, currVal, uniqueIdx);
    }

    private boolean isValidIdx(int[][] colors, int i, int j) {
        return i>=0 && i < colors.length && j >= 0 && j < colors[0].length;
    }

    private void fillPaint(int[][] colors){
        int counter = 1;
        for(int i = 0; i < colors.length; i++) {
            Arrays.fill(colors[i],counter++);
        }
    }

    private void print2Dmatrix(int[][] colors){
        for(int i = 0; i < colors.length; i++) {
            for(int j = 0 ; j< colors[0].length;j++){
                System.out.print(colors[i][j]);
                System.out.print("-->");
            }
            System.out.println();
        }
    }


    private class Pair{
        int i, j;
        Pair(int i, int j){
            this.i = i;
            this.j = j;
        }

        public boolean equals(Object obj) {
            if(this == obj) return true;
            if (obj == null) return false;
            if(obj.getClass() != this.getClass()) return false;
            Pair that = (Pair)obj;
            return this.i != that.i && this.j != that.j;
        }

        public int hashCode(){
            int hash = 17;
            hash = 31*hash + this.i;
            hash = 31*hash + this.j;
            return hash;
        }


    }
}
