package com.leetcode.hard;

import java.util.*;

// https://leetcode.com/discuss/interview-question/372459/Facebook-or-Phone-Screen-or-Palindromic-Subsequences
public class AllPalindromicSubsequences {

    public static List<String> palindromicSubsequences(String str){
        // Does it fits into the memory.
        // Chat is the character set we are supporting here? Doesn't matter.
        // String null - 0,  empty? 0 or 1.
        //
        Set<String> result = new HashSet();
        ArrayList<String>[][] memo = new ArrayList[str.length()][str.length()];
        recursiveBacktrackWithMemo(str, memo, result, new ArrayList<>(), 0, str.length()-1);
//        return memo[0][str.length-1];
        return new ArrayList(result);
    }
    private static void recursiveBacktrackWithMemo(String str, List<String>[][] memo, Set<String> result, List<Character> tmp, int start, int end){
	/* if(memo[start][end] != null) return memo[start][end];
	if(start > end) */
        if(start > end)
            return;
        if(isPalindrome(tmp)){
            StringBuilder sb = new StringBuilder();
            for(char c : tmp)
                sb.append(c);
            result.add(sb.toString());
            // if(memo[start][end] == null) memo[start][end] = new ArrayList<>();
//            String palin = Arrays.toString(tmp.toArray(new Character[tmp.size()]));
            //memo[start][end].add(palin);
//            result.add(palin);
        }
        for(int i=start; i<= end; i++){
            char c = str.charAt(i);
            tmp.add(c);
            recursiveBacktrackWithMemo(str, memo, result, tmp, start+1, end);
            tmp.remove(tmp.size()-1);
//            recursiveBacktrackWithMemo(str, memo, result, tmp, start+1, end);
        }

    }

    private static boolean isPalindrome(List<Character> list){
        int start = 0, end = list.size()-1;
        while(start < end){
            if(list.get(start) != list.get(end))
                return false;
            start++;
            end--;
        }
        return list.isEmpty() ? false: true;
    }

    public static void main(String[] args) {
        String str = "abac";
        printAll(palindromicSubsequences(str));
    }

    private static void printAll(List<String> palindromes){
        for(String palin : palindromes)
            System.out.println(palin);
    }
}
