package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInMaze {

	/* Given a 2D maze of 0s and 1s, Find the length of shortest path from a given source and destination.
	 * */
	private static boolean[][] visited;
	
	private static int[] row = {-1,0,0,1};
	
	private static int[] col = {0,-1,1,0};
	
	private class Node{
		int x,y,dist; // coordinates and distance from source.
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	private void BFS(int[][] mat, int srcX, int srcY, int destX, int destY) {
		visited = new boolean[ROWS][COLS];
		Queue<Node> q = new ArrayDeque<>();
		visited[srcX][srcY] = true;
		q.add(new Node(srcX,srcY,0));
		int min_dist = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Node node = q.poll();
			srcX = node.x;
			srcY = node.y;
			int dist = node.dist;
			if(srcX == destX && srcY ==destY) {
				min_dist = dist;
				break;
			}
			for(int k = 0; k < 4 ; k++) {
				int x = srcX+row[k];
				int y = srcY+col[k];
				if(isValid(mat,visited,x, y)) {
					visited[x][y] = true;
					q.add(new Node(x,y,dist+1));
				}
			}
		}
		
		if(min_dist != Integer.MAX_VALUE) {
			System.out.println("Shortest path is of distance : " + min_dist);
		}
		else {
			System.out.println("No shortest path exists");
		}
		
		
	}
	
	private boolean isValid(int[][] mat, boolean[][] visited2, int i, int j) {
		return (i>=0 && j>= 0 && i<ROWS && j <COLS && mat[i][j] !=0 && !visited[i][j]);
	}

	private static final int ROWS = 10;
	
	private static final int COLS = 10;
	
	public static void main(String[] args) {
		  int[][] mat =
		        {
		            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
		            { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },
		            { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },
		            { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
		            { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },
		            { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },
		            { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
		            { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
		            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },
		            { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
		        };
		  ShortestPathInMaze obj = new ShortestPathInMaze();
		  obj.BFS(mat, 0, 0, 7, 5);
	}
	
	

}
