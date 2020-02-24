package com.companyspecific.Instacart;

public class TwoDMatrixMoveStartBottomLeft {

  public static void main(String[] args) {



  }

    // Can there be any null characters at any position.
    // What to do if invalid index is given.
    // Does it need to work like a snake game where you exit one side and get come back from another side.

    // Starting from the start position(0,0) being the lower left corner(mat.length-1,0), move x directions right and y directions up.
    public Character move(char[][] mat, int xcol, int yrow){
      int startRow = mat.length-1;
      int startCol = 0;
      int newR = startRow - yrow;
      int newC = startCol + xcol;
      if(!isValid(mat.length, mat[0].length, newR, newC)) return null;
      return mat[newR][newC];
    }

    private boolean isValid(int rowS, int colS, int newR, int newC){
      return newR >=0 && newC >=0 && newR < rowS && newC < colS;
    }


}
