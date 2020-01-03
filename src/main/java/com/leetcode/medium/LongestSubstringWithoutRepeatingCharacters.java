package com.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingCharacters {
        /*
        - How exactly is my input given(String?) Fits into the memory?
        - What is my character set? Letters in the english alphabet? Uppercase/lowercase?ASCII, UNICODE etc.
        - What happens if you see duplicate results(in terms of length, any is fine?)
        - Worst case: length of size 1.
        - return length.
        Approach 1:
        - Consider all possible substrings(O(N^2)) of the input of length b/w 1-N. For each substring, see if that doesn't have repeating character, mark that as candidate for longest substring.
            - Checking for repeat character takes O(N).
        - Total runtime: O(N^3)
        Approach 2:
        - Reduce the re-work, recalculation from approach 1.
        - Say for e.g. you know the answer to sub(i,j), and checking if sub(i,j+1) has duplicate characters, should be either have duplicate chars if sub(i,j) had dups, or if j+1 char is dups in characters seen so far b/w i-j, else doesn't have repeating chars.
        - O(N^2) and no extra space(instead of using hashmaps, just use an array of size 26, to mark if you have seen the character before.)

        Approach 3(O(N)):
        - Sliding window approach:
        - Starting with index i=0. j=i+1.
        - int maxLength=0
        - boolean[] seen;
        - i=0, j=i;
        - while i < end && j is less than end
            // - seen[arr[j]-'a']=true;
            if(i == j){
                seen[i] = true;
                j=j+1;
                continue;
            }
            - Increment j if arr[j] non-repeating character. Add to the seen array.
            - else increment I. remove arr[i] from the seen array.
        */
        public int lengthOfLongestSubstring(String s) {
            if(s == null || s.length()==0) return 0;
            int max = 1;
            // int start =0, end = start;
            int[] pos = new int[256];
            for(int start = 0, end = 0; end< s.length(); end++){
                if(pos[s.charAt(end)] != 0){
                    start = Math.max(start, pos[s.charAt(end)]);
                }
                max = Math.max(max, end-start+1);
                pos[s.charAt(end)] = end+1;

            }
            return max;
        }

        public int lengthOfLongestSubstring3(String s) {
            if(s == null || s.length()==0) return 0;
            int max = 1;
            int start =0, end = start;
            boolean[] seen = new boolean[256];
            while(start < s.length() && end< s.length()){
                if(!seen[s.charAt(end)]){
                    seen[s.charAt(end++)] = true;
                    max = Math.max(max, end-start);

                }
                else
                    seen[s.charAt(start++)] = false;
            }
            return max;
        }
        public int lengthOfLongestSubstring2(String s) {
            if(s == null || s.length()==0) return 0;
            int max = 1;
            for(int i=0 ; i < s.length(); i++){
                Set<Character> seen = new HashSet<>();
                int j=i;
                while(j < s.length() && !seen.contains(s.charAt(j)))
                {
                    seen.add(s.charAt(j));
                    j++;
                }
                max= Math.max(max, j-i);
            }
            return max;
        }

        public int lengthOfLongestSubstring1(String s) {
            if(s == null || s.length()==0) return 0;
            int max = 1;
            for(int i=0 ; i < s.length(); i++){
                for(int j=i+1; j < s.length(); j++){
                    if(!haveRepeatingChars(s, i, j))
                        max = Math.max(max, j-i+1);
                }
            }
            return max;
        }

        private boolean haveRepeatingChars(String s, int i, int j){
            // boolean[] seen = new boolean[26];
            Set<Character> seen = new HashSet<>();
            for(int temp = i; temp <= j; temp++){
                // if(seen[s.charAt(temp)-'a'])
                if(seen.contains(s.charAt(temp)))
                    return true;
                seen.add(s.charAt(temp));
            }
            return false;
        }



}
