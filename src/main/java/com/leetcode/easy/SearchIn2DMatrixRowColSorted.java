package com.leetcode.easy;

//https://leetcode.com/problems/search-a-2d-matrix/
public class SearchIn2DMatrixRowColSorted {

    private class Pair{
        int row;
        int col;
        Pair(int row, int col){
            this.row = row;
            this.col = col;
        }

        public String toString(){
            return "X : "  + row + " Y : " + col;
        }

    }

    public Pair findElement(int[][] mat, int elem) {
        if (mat == null) return null;
        int currX = mat[0].length-1;
        int currY = 0;
        while(currX >= 0 && currY < mat.length) {
            int currElem = mat[currX][currY];
            if(elem == currElem)
                return new Pair(currX,currY);
            else if (elem < currElem)
                currX--;
            else
                currY++;
        }
        return null;
    }

  public static void main(String[] args) {
    SearchIn2DMatrixRowColSorted  s = new SearchIn2DMatrixRowColSorted();
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
    int elem = 7;
        System.out.print(s.findElement(mat, elem));
  }
}
