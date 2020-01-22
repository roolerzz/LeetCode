package com.leetcode.medium;

public class ConstructQuadTree {

    // Definition for a QuadTree node.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }
    /*
        - createNode(int[][] grid, int x, int y, distHor, distVer)
            // if (distHor && distV == 0) only 1 node
            - Check if all the values in the grid are same, create a leaf node with that value
            - else
                - create a non-leaf node called as curr.
                - curr.topLeft = createNode()
                - curr.topRight = createNode()
                - curr.bottomLeft = createNode()
                - curr.bottomRight = createNode()
            return currNode.

    Time complexity:
    N^2 logN. How??
    - For a grid of size N*N, lets say subproblem size was N.
    Amount of work done other than recursive calls? scanning each cell. O(N^2).
    Total Work for Subproblem Size N - O(N^2). Subprolem divided into 4 subproblem of size N/2.
    Total Work for subproblem size N/2 - O(N^2/4) * 4 = O(N^2)...
    .
    .
    .
    logN level  = N^2.
    Total of all logN levels(N^2 logN).
    Also by master theorm:
    T(N) = 4T(N/2) + Theta(N^2). a = 4, b = 2 f(n) = Theta(N^2).
    f(N) = Theta(N^logb a) = Theta(N^2)
    CASE2: T(N) = N^logb a logN = N^2logN.

    */
    public Node construct1(int[][] grid) {
        if(grid == null || grid.length==0)
            return null;
        return createNode(grid, 0, 0, grid.length);
    }

    private Node createNode(int[][] grid, int row, int col, int len){
        if(allSame(grid, row, col, len))
            return new Node(grid[row][col] == 1, true, null, null, null, null);
        Node curr = new Node(false, false, null, null, null, null);
        curr.topLeft = createNode(grid, row, col, len/2);
        curr.topRight = createNode(grid, row, col+ len/2, len/2);
        curr.bottomLeft = createNode(grid, row + len/2, col, len/2);
        curr.bottomRight = createNode(grid, row + len/2, col+ len/2, len/2);
        return curr;
    }

    private boolean allSame(int[][] grid, int row, int col, int len){
        int val = grid[row][col];
        for(int i = row; i < row+len; i++){
            for(int j=col; j < col+ len; j++){
                if(val != grid[i][j])
                    return false;
            }
        }
        return true;
    }

    /*
    - Doesn't do the full scanning of the matrix at each point. Just subdivides into 4 subproblems, and in constant time, adjust the pointers if needed.
    - Time complexity (O(N^2)).
    */
    public Node construct(int[][] grid) {
        if(grid == null || grid.length==0)
            return null;
        return helper(grid, 0, 0, grid.length);
    }

    private Node helper(int[][] grid, int row, int col, int len){
        Node curr = new Node(grid[row][col] == 1, true, null, null, null, null);
        if(len == 1) return curr;
        Node topLeft = createNode(grid, row, col, len/2);
        Node topRight = createNode(grid, row, col+ len/2, len/2);
        Node bottomLeft = createNode(grid, row + len/2, col, len/2);
        Node bottomRight = createNode(grid, row + len/2, col+ len/2, len/2);
        if(!topLeft.isLeaf || !topRight.isLeaf || !bottomLeft.isLeaf || !bottomRight.isLeaf || topLeft.val != topRight.val || topRight.val != bottomLeft.val || bottomLeft.val != bottomRight.val){
            curr.topLeft = topLeft;
            curr.topRight = topRight;
            curr.bottomLeft = bottomLeft;
            curr.bottomRight = bottomRight;
            curr.isLeaf = false;
        }
        return curr;
    }


}
