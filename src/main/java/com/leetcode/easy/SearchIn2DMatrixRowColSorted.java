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

    // Various approaches:
    // 1. Brute force - O(M*N) Search through every element in the matrix. Doesn't utilizes any information given.
    // 2. Binary search over each row. O(M*log(N)). Utilizes the fact that elements are sorted within each row. Still doesn't utilizes
    // more information that they are also sorted column wise.
    // 3. Looking at an element at index(i,j) what can you tell?? Well, element on left and above it are smaller and ones to
    // the right and lower are larger. Looking at element if we can compare it with the one we are looking for, do we know, which
    // direction should we move. Probaby not because for each case, we have 2 options. Except if we start from specific directions.
    // Consider the corner elements of the matrix. Well, for 0,0 if you get elements bigger, it could be on either side. For n,n, if
    // you get element smaller it could be on the either side. Interesting corner elements are N,0 and 0,N. e.g. for 0,N,
    // If you compare the searching elements with 0,N it could be either greater or smaller. If elem we are looking for is smaller,
    // we can go left(basically discard all the elements below it) or if greater, we can go below(discard all the elements to the left of it on that row).
    // In the worst case, we would be at the opposite corner. Worst case time complexity would be O(M+N).
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
