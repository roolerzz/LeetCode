package com.leetcode.medium.backtrack;
//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/797/
public class WordSearch {

	// 2nd Attempt DFS. Used extra storage to keep track of what has already been seen. 
	public boolean wordSearch(char[][] board, String target) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		for(int row = 0 ; row < board.length ; row++) {
			for(int col = 0 ; col < board[0].length ; col++) {
				if(findDFSWay(board,visited,row,col,0,target))
					return true;
			}
		}
		return false;
	}
	
	private boolean findDFSWay(char[][] board, boolean[][] visited, int row, int col, int idx,String target) {
		if(idx == target.length())
			return true;
		if(!isValid(board,row,col)) return false;
		if(!visited[row][col] && board[row][col] == target.charAt(idx)) {
			visited[row][col] = true;
			boolean result = findDFSWay(board,visited,row+1,col,idx+1,target) 
					|| findDFSWay(board,visited,row-1,col,idx+1,target)
					|| findDFSWay(board,visited,row,col+1,idx+1,target)
					|| findDFSWay(board,visited,row,col-1,idx+1,target);
			visited[row][col] = false;
			return result;
		}
		return false;
	}
	
	public static void main(String[] args) {
		WordSearch search = new WordSearch();
		char[][] board = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}
				};
		System.out.println(search.wordSearch(board, "ABCCED"));
	}


	private boolean isValid(char[][] board, int row, int col) {
		return (row >=0 && row < board.length && col>=0 && col < board[row].length);
	}

	public boolean exist(char[][] board, String word) {
	        if(board == null || board.length == 0 || word == null || word.length() == 0) return false;
	        //boolean[][] used = new boolean[board.length][board[0].length];
	        for(int row = 0 ; row < board.length; row++){
	            for(int col = 0 ; col < board[row].length; col++){
	                if(recursiveHelper(board,row,col,word,0/*,used*/))
	                    return true;
	            }
	        }
	        return false;
	    }
	    
	// DFS + Back tracking to avoid extra space usage.
	    private boolean recursiveHelper(char[][] board, int row, int col, String word, int pos/*, boolean[][] used*/){
	        if(pos == word.length())
	            return true;
	        if(inBounds(board,row,col) /* && !used[row][col]*/ && board[row][col] == word.charAt(pos)){
	            char backup = board[row][col];
	            board[row][col]='-';
	            
	            //used[row][col] = true;
	            boolean res = recursiveHelper(board,row-1,col,word,pos+1/*,used*/)
	            || recursiveHelper(board,row,col-1,word,pos+1/*,used*/)
	            || recursiveHelper(board,row+1,col,word,pos+1/*,used*/)
	            || recursiveHelper(board,row,col+1,word,pos+1/*,used*/);
	            //used[row][col] = false;
	            board[row][col]= backup;
	            return (res);
	        }
	        else
	            return false;
	    }
	    
	    private boolean inBounds(char[][] board, int row, int col){
	        if(row>=0 && row < board.length && col >=0 && col< board[row].length)
	            return true;
	        return false;
	    }
	}