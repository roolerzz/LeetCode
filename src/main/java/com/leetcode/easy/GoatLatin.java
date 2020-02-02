package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GoatLatin {
    /*

    - Repeat and validate understanding of the questsion.
    - Ask clarification questions about input, expectation, edge cases.
    - Come up with brute force. Tell the time and space complexity.
    - See if you can spot the repeat work and improve from there or can use a different data structure that can help speed up some part of data access.
    - Write down the psuedo-code or map of the problem before actually jumping onto it.
    - Say the time and space complexity of this soln.
    - Verification. Try a dry run of the solution. ALso check off by 1 errors.

    - Convert the string into array of strings(split by space).
    - For each String in the array, traverse through index i from 0-...
        - Convert to string builder.
        - If the first character is Consonent
            - Remove the first letter and append at end.
        - append ma at end
        - append (i+1) times a.
        - convert back to string and replace at index i.
    - Need to join back the array with space b/w each word.

    Questions:
    - CharacterSet.
    - Does string fits into memory.
    - Would my words can have 1 space or multiple white spaces. Leading/Trailing white spaces?

    Time Complexity: O(N^2) because of adding those number of As, and Substring method which takes O(w) where w is the length of the word
    Space Complexity: O(N^2) because of number of as being added at each step.
    */
    public String toGoatLatin(String S) {
        if(S == null || S.length() < 1) return S;
        String[] strs = S.trim().split(" ");
        StringBuilder res = new StringBuilder();
        StringBuilder as = new StringBuilder("a");
        for(int i=0; i< strs.length; i++){
            StringBuilder sb = new StringBuilder(strs[i]);
            char firstChar = strs[i].charAt(0);
            if(!isVowel(firstChar)){
                sb.deleteCharAt(0);
                sb.append(firstChar);
            }
            sb.append("ma");
            sb.append(as);
            as.append('a');
            // for(int k=0; k < i+1; k++){
            // sb.append('a');
            // }
            res.append(sb + " ");
        }
        // Remove the last space.
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }

    public String toGoatLatin2(String S) {
        if(S == null || S.length() < 1) return S;
        // String[] strs = S.trim().split(" ");
        StringBuilder res = new StringBuilder();
        int start = 0, end = start;
        int i=0;
        while(end < S.length()){
            while(end < S.length() && S.charAt(end) != ' ')
                end++;
            end = end-1; // Takes to the last element of the string.
            StringBuilder sb = new StringBuilder(S.substring(start,end+1));
            char firstChar = S.charAt(start);
            if(!isVowel(firstChar)){
                sb.deleteCharAt(0);
                sb.append(firstChar);
            }
            sb.append("ma");
            for(int k=0; k < i+1; k++){
                sb.append('a');
            }
            res.append(sb);
            res.append(" ");
            i++;
            start = end+2;
            end = start;
        }
        // Remove the last space.
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }


    private boolean isVowel(char c){
        Set<Character> vowels = new HashSet();
        vowels.addAll(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        return vowels.contains(c);
    }
}