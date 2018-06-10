package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;

class GenerateParanthesis {
	
	public static void main(String[] args) {
		GenerateParanthesis paranthesis = new GenerateParanthesis();
		List<String> strings = paranthesis.generateParenthesis(3);
		for(String str : strings)
			System.out.println(str);
		
	}
	
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        //backtrack("" , 0 , 0, n, result);
        generateBacktrack(result, 0,0,new char[2*n],n,0);
        return result;
    }
    
    // Attempt 2.
  private void generateBacktrack(List<String> result, int left, int right, char[] arr, int n, int currIdx){
      if(left < right || left > n)
          return;
      if(left == n && right == n)
      {
        result.add(String.valueOf(arr));
        return;
      }
      arr[currIdx] = '(';
      generateBacktrack(result, left+1, right, arr, n, currIdx+1);
      arr[currIdx] = ')';
      generateBacktrack(result, left, right+1, arr, n, currIdx+1);
  }
    /*  private void backtrack(String str, int open, int close, int max,  List<String> result){
        if(str.length() == (2*max))
        {
            result.add(str);
            return ;
        }
        else{
            if(open < max)
                backtrack(str+"(" , open+1, close, max, result);
            if(close < open)
                backtrack(str + ")" , open, close+1, max, result);
        }
    }*/
}
