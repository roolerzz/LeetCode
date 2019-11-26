package com.leetcode.easy;

// https://leetcode.com/problems/valid-palindrome/
public class ValidPalindrome {
    public boolean isPalindromeOld(String s) {
        int i =0, j = s.length()-1;
        while(i < j) {
            if(!Character.isLetterOrDigit(s.charAt(i))) i++;
            else if (!Character.isLetterOrDigit(s.charAt(j))) j--;
            else {
                if(!isEqual(s.charAt(i++),s.charAt(j--)))
                    return false;
            }
        }
        return true;
    }
    private boolean isEqual(char c1, char c2){
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    // So the idea is instead of using helper function isLetterOrDigit to find what a valid character looks like, we create a charMap to maintain the values for possible characters.
    // In case of invalid character, its gonna have default int values in the array of 0.
    // for values 0-9, we assign them value in the array 1-10,
    // for values a-z and A-Z, we assign them values 11-36.
    // https://leetcode.com/problems/valid-palindrome/discuss/39993/3ms-java-solution(beat-100-of-java-solution)
    public boolean isPalindrome(String s) {
        int[] charMap = new int[256];
        for(int i = 0 ; i< 10 ; i++) {
            charMap['0'+i] = (int)(i+1);
        }
        // For a-z, and A-Z
        for(int i = 0; i < 26; i++){
            charMap['a'+i] = charMap['A'+i] = (int)(i+11);
        }
        int i =0, j = s.length()-1;
        while(i < j) {
            if(charMap[s.charAt(i)] == 0) i++;
            else if (charMap[s.charAt(j)] == 0) j--;
            else {
                if(charMap[s.charAt(i++)] != charMap[s.charAt(j--)])
                    return false;
            }
        }
        return true;
    }
}


