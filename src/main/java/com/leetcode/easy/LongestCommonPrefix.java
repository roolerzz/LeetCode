package com.leetcode.easy;

// https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {
    /*
    Time:
    O(n*d), for n strings each of length d on average.
    Space:O(n*d);
    - If array have less than 2 elements return "".
    - Put all the elements onto the stack.
    - As long as there are 2 strings on the stack
        - Pop off the top 2 strings.
        - Find the logest common prefix(O(len))
        - push the lcp on top of stack.
        - Repeat.
    - return stack.pop();

    Instead of putting all the elements onto the stack, start with result being initialized to the first element(idx 0) on the stack. Or not use stack at all.
    - Starting from idx 1, take eleme at that index and the result.
    Space : O(d).
    */
    public String longestCommonPrefix1(String[] strs) {
        if(strs == null || strs.length < 1) return "";
        String res = strs[0];
        for(int i=1; i < strs.length; i++){
            int len = Math.min(res.length(), strs[i].length());
            int j = 0;
            while (j < len){
                if(res.charAt(j) != strs[i].charAt(j))
                    break;
                j++;
            }
            res = strs[i].substring(0,j);
        }
        return res;
    }

    /*
    Vertical Scanning;
    */
    public String longestCommonPrefix2(String[] strs) {
        if(strs == null || strs.length < 1) return "";
        for(int i=0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(int j=1; j < strs.length; j++){
                if(i == strs[j].length() || c != strs[j].charAt(i))
                    return strs[0].substring(0,i);
            }
        }
        return strs[0];
    }

    /*
    Divide and conquer. Divide set of n strings into 2 halfs. Find LCP of left, LCP of right, and then find LCP of left and right. return that.
    Time: Worst case O(S), s is total number of characters in the array = m*n for n strings of avg length m. In best case O(minLen*n). If the minStrings gets processed the first, then rest of the strings only gets compared until the min Length.
    Space Complexity : MlogN. logN recursive calls each with memory head of O(m) of storing each string length.
    */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length < 1) return "";
        return lcpDC(strs, 0, strs.length-1);
    }

    private String lcpDC(String[] strs, int start, int end){
        if(start== end)
            return strs[start];
        int mid = start + (end-start)/2;
        String leftLCP = lcpDC(strs, start, mid);
        String rightLCP = lcpDC(strs, mid + 1, end);
        int i=0;
        for(; i < Math.min(leftLCP.length(), rightLCP.length()); i++)
            if(leftLCP.charAt(i) != rightLCP.charAt(i))
                break;
        return leftLCP.substring(0,i);
    }


}
