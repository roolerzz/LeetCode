package com.leetcode.medium;

// https://leetcode.com/problems/minesweeper/
public class Minesweeper {

        public char[][] updateBoard(char[][] board, int[] click) {
            if(board == null || board.length == 0) return board;
            int[] dr = {1, -1, 0, 0, -1, 1, -1,1};
            int[] dc = {0, 0, 1, -1, 1,1, -1, -1};
            visit(board, click[0], click[1], dr, dc);
            return board;
        }

        private void visit(char[][] board, int row, int col, int[] dr, int[] dc){
            if(!isValid(board, row, col) || !(board[row][col]=='E' || board[row][col]=='M')) return;
            // int row = click[0], col = click[1];
            if(board[row][col] != 'M'){
                int count = countMines(board, row, col, dr, dc);
                if(count >0)
                    board[row][col] = (char)(48 + count);
                else {
                    board[row][col] = 'B';
                    visitNeighbors(board, row, col, dr, dc);
                }
            }
            else
                board[row][col] = 'X';
        }

        private boolean isValid(char[][] board, int row, int col){
            return row >=0 && col >= 0 && row< board.length && col < board[row].length;
        }

        private void visitNeighbors(char[][] board, int row, int col, int[] dr, int[] dc){
            for(int k = 0; k < 8; k++){
                visit(board, row+dr[k], col+dc[k], dr, dc);
            }
        }

        private int countMines(char[][] board, int currR, int currC, int[] dr,int[] dc){
            int count = 0;
            for(int k = 0; k < 8; k++){
                int newR = currR+dr[k];
                int newC = currC+dc[k];
                if(isValid(board, newR, newC) && board[newR][newC] == 'M')
                    count++;
            }
            return count;
        }


    /*
        - visited 2d array or visited set of pair, or visited set of string of indexes. Indexes which would be valid that are !M and !E. In that case In don;t need a visisted arry, because I would have already changed the state of the previous click, from M->X, E-> B or Num.
        - Start visiting the click.
            - If the click is M, change click to X. Return.
            - else
                - Count the number of M's in the neighbors.
                    - if > 0, mark that number on the click. Return.
                    - else, mark B on that click. Recursively visit all its neighbors.
    */

}
