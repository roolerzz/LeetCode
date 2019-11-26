package com.leetcode.easy;

import java.util.Stack;

// https://leetcode.com/problems/valid-palindrome-ii/solution/
public class ValidPalindromeII {

    public boolean validPalindromeRec(String s) {
        if(s == null || s.length() == 0) return false;
        return isRecPalin(s,0,s.length()-1,false);
    }

    private boolean isRecPalin(String str, int l, int r, boolean del){
        if(l >=r) return true;
        if(str.charAt(l) == str.charAt(r))
            return isRecPalin(str,l+1,r-1,del);
        else if(del) return false;
        else{
            return isRecPalin(str,l+1,r,true) || isRecPalin(str,l,r-1,true);
        }
    }


    public boolean validPalindrome(String s) {
        if(s == null || s.length() == 0) return false;
        Stack<State> st = new Stack<>();
        st.push(new State(0,s.length()-1,false));
        while(!st.isEmpty()){
            State curr = st.pop();
            int l = curr.l, r = curr.r;
            boolean del = curr.del;
            if(l >= r) return true;
            else if (s.charAt(l) == s.charAt(r))
                st.push(new State(l+1,r-1,del));
            else if (del) continue;
            else {
                st.push(new State(l+1,r,true));
                st.push(new State(l,r-1,true));
            }
        }
        return false;
    }

    private class State{
        int l, r;
        boolean del;
        State(int l, int r, boolean del){
            this.l = l;
            this.r = r;
            this.del = del;
        }
    }

    public boolean isPalindrome(CharSequence s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    public boolean validPalindromeBruteForce(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c = sb.charAt(i);
            sb.deleteCharAt(i);
            if (isPalindrome(sb)) return true;
            sb.insert(i, c);
        }
        return isPalindrome(s);
    }

}
