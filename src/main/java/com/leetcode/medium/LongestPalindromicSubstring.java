package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


// https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {

    /*
    Approach 1: brute force.
        - Find all the possible substrings b/w length 1-N (N^2) substrings. Substrings of length K(n-k+1)
        - Check if that specific substring is a palindrome(O(N) if string is of length N).
        - Total runtime O(N^3).
   */

    public String longestPalindrome(String s) {
        // char[] str = s.toCharArray();
//        List<String> strs = generateAllPossibleSubstrings(s);
//        Collections.sort(strs, Comparator.comparingInt(str-> str.length()));
//        findLongestPalindrome(strs);
        int windowSize = s.length();
        while(windowSize>0){
            for(int i=0; i<s.length()-windowSize+1; i++){
                int j = i+windowSize-1;
                if(isPalindrome(s, i, j))
                    return s.substring(i, j+1);
            }
            windowSize--;
        }

        return "NOT FOUND";
//        return longest;
    }

    String longest = "";
    public String longestPalindrome4(String s) {
        // char[] str = s.toCharArray();
        List<String> strs = generateAllPossibleSubstrings(s);
        Collections.sort(strs, Comparator.comparingInt(str-> str.length()));
        findLongestPalindrome(strs);
        return longest;
    }
    private List<String> generateAllPossibleSubstrings(String s){
        List<String> res = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            for(int j=i+1; j<=s.length();j++){
                res.add(s.substring(i, j));
            }
        }
        return res;
    }

    private void findLongestPalindrome(List<String> strs){
        for(int i=strs.size()-1; i>=0; i--){
            if(isPalindrome(strs.get(i), 0, strs.get(i).length()-1))
            {
                longest = strs.get(i);
                break;
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }


     /*
    Approach 2:O(N^2 solution)
        - leftMostIdx = length-1;
        - rightMostIdx = 0
        - Match(s, left, right)
            - If left < 0 || right > end || left != right return
            - if right-left> rightMostIdx-leftMostIdx, update globals to indicate the max
            - Match(s, left-1, right+1)
        - For every possible chracter at index i in the string, find the longest palindroming substring with middle element as             index ith or find the longest palindropmic substring at index <i and >i+1 If it matches add those number of character.
                - Match characters i-1(to 0) with i+1 to N-1
                OR
                - Match characters i-1(to 0) to i+2(to N-1).
            - It should be within the bounds on the left and the right.
        - Once you have the left and right index, return that substring.
    */

    int leftMost = 0, rightMost = -1;
    public String longestPalindrome2(String s) {
        char[] str = s.toCharArray();
        for(int i=0; i < str.length; i++){
            match(str, i, i);
            if(i+1 < str.length)
                match(str, i, i+1);
        }
        // System.out.println("LeftMost is: " + leftMost + " and rightMost is : " + rightMost);
        return s.substring(leftMost, rightMost+1);
    }

    private void match(char[] str, int left, int right){
        if(left < 0 || right >= str.length || str[left] != str[right]) return;
        if(right-left > rightMost-leftMost){
            leftMost = left;
            rightMost = right;
        }
        match(str, left-1, right+1);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        String str = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(lps.longestPalindrome(str));
    }
}
