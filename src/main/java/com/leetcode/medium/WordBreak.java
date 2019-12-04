package com.leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/word-break
public class WordBreak {
        /*
        I/P
        - Does my I/Ps fit into the memory?
        - If i/p already has space,
        - non empty string s.
        - dictionary(non-empty words)
        -
        O/P
        boolean T/F

        - For every subproblem of size i
           - Cut all possible prefixes result = substring(i,j) && (j onwards); for j from i+1 to N.
        T(i) = prefix(i+j) && T(i+j) for every j b/w i+1 to N-1-i.
        return T(0)
        */
        public boolean wordBreak(String s, List<String> wordDict) {
            if(s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) return false;
            Boolean[] memo = new Boolean[s.length()];
            // Arrays.fill(memo,-1);
            return wordBreakDP(s, new HashSet(wordDict), 0, memo);

        }

        private boolean wordBreakDP(String s, Set<String> wordDict, int idx, Boolean[] memo){
            if (idx == s.length()) return true;
            if(memo[idx] != null) return memo[idx];
            for(int j = idx+1; j <= s.length(); j++){
                if(wordDict.contains(s.substring(idx, j)) && wordBreakDP(s,wordDict, j, memo))
                    return memo[idx] = true;
            }
            return memo[idx] = false;
        }

}
