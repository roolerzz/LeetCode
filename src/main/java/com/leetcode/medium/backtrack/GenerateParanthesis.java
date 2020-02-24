package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/794/
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

  // Attempt I
      private void backtrack(String str, int open, int close, int max,  List<String> result){
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
    }



    /* ================================================ Jan 2020 ================================================ */

    /*
        - Brute force solution would be to generate all permutations of 2*n sized character array. O(2^2n)
    - Validate if each one of them is valid. O(n)
    Total Runtime : O(2^2n * n)
    Space : O(2^2n * n).
    */
    public List<String> generateParenthesis3(int n) {
        List<String> candidates = new ArrayList<>();
        List<String> res = new ArrayList<>();
        char[] temp = new char[2*n];
        bruteforce(temp, candidates,0, 0, 0, n);
        for(String str : candidates)
            if(validParanthesis(str, n))
                res.add(str);
        return res;
    }

    private boolean validParanthesis(String str, int n){
        int balance = 0;
        for(char ch : str.toCharArray()){
            //char ch = str.charAt(i);
            if(ch == '(')
                balance++;
            else
                balance--;
            if(balance < 0)
                return false;
        }
        //System.out.println("String : " + str + " is valid paran = " + (balance == 0));
        return balance == 0;
    }

    private void bruteforce(char[] temp, List<String> candidates,int idx, int left, int right, int n){
        if(idx == temp.length)
        {
            candidates.add(new String(temp));
            return;
        }
        temp[idx] = '(';
        bruteforce(temp, candidates, idx+1, left+1, right, n);
        temp[idx] = ')';
        bruteforce(temp, candidates, idx+1, left, right+1, n);

    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        char[] temp = new char[2*n];
        recBacktrack(temp, res,0, 0, 0, n);
        return res;
    }


    /*
    Approach 2:
    Backtrack.Once you know that the paranthesis are not balanced, donot go that branch.
    - If the current index reaches the end, that means till now we have only added valid chars, and thus this string can be added into the result.
    - At each level, if there can be more of left paranthesis can be tried, use left, and recurse.
    - If right can be tried, try right, and recurse.
    */
    private void recBacktrack(char[] temp, List<String> res,int idx, int left, int right, int n){
        if(idx == temp.length)
        {
            res.add(new String(temp));
            return;
        }

        if(left < n){
            temp[idx] = '(';
            recBacktrack(temp, res, idx+1, left+1, right, n);
        }

        if(right < n && right < left){
            temp[idx] = ')';
            recBacktrack(temp, res, idx+1, left, right+1, n);
        }
    }
}
