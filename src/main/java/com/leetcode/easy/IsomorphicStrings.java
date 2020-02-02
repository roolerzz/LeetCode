package com.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/isomorphic-strings
public class IsomorphicStrings {
    /*
    Questions:
    - How exactly is my input given. Strings in the memory?
    - What is the characterset we are looking to handle.
    - Can same rule be applied to digits as well?
    - Sanitization?
        - Handle, null, empty, not equal length.

    Brute Force:
    - For each character c in the first string s, find the matching character at the same index in t call it as match. for every occurence of c in the s, at the same index it t, see if you find a matching character. If not, return false.
    - Once you do this for all the characters, return true.
    O(N^2) O(1) space.

    Improved Time complexity:
    - Use a hashmap to store the mapping and do everything in 1 pass.
    - for every index in the string s, if the char at that index in s is not present in the map, add it to the map.
    - If already present, see if the mapped character matches to the one at same index in t. If not, return false.
    - return true otherwise at the end.
    */
    public boolean isIsomorphic1(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        Map<Character,Character> smap = new HashMap<>();
        Map<Character,Character> tmap = new HashMap<>();
        for(int i=0; i < s.length(); i++){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if((smap.containsKey(sc) && smap.get(sc) != tc )|| (tmap.containsKey(tc) && tmap.get(tc) != sc))
                return false;
            if(!smap.containsKey(sc) || !tmap.containsKey(tc)){
                tmap.put(tc,sc);
                smap.put(sc,tc);
                continue;
            }

        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        int[] smap = new int[256];
        int[] tmap = new int[256];
        Arrays.fill(smap, -1);
        Arrays.fill(tmap, -1);
        for(int i=0; i < s.length(); i++){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if((smap[sc] != -1 && smap[sc] != tc )|| (tmap[tc] != -1 && tmap[tc] != sc))
                return false;
            if(smap[sc] == -1 || tmap[tc] == -1){
                tmap[tc] = sc;
                smap[sc]= tc;
                continue;
            }

        }
        return true;
    }

    /*
    - Instead of saving the mapping of characters, say we store the index/location those characters occur. when these characters are found again at ith index, their previously stored location should match. If they doesn't, that means one of the character is different that what was mapped before.


    "The main idea is to store the last seen positions of current (i-th) characters in both strings. If previously stored positions are different then we know that the fact they're occuring in the current i-th position simultaneously is a mistake. We could use a map for storing but as we deal with chars which are basically ints and can be used as indices we can do the whole thing with an array."
    */
    public boolean isIsomorphic3(String s, String t) {
        int[] mmap = new int[512];
        for(int i=0; i < s.length(); i++){
            if(mmap[s.charAt(i)] != mmap[t.charAt(i) + 256])
                return false;
            mmap[s.charAt(i)] = mmap[t.charAt(i)+256] = i;
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return false;
        Map<Character,Integer> smap = new HashMap<>();
        Map<Character,Integer> tmap = new HashMap<>();
        for(int i=0; i < s.length(); i++){
            int indexS = smap.getOrDefault(s.charAt(i), -1);
            int indexT = tmap.getOrDefault(t.charAt(i), -1);
            if(indexS != indexT)
                return false;
            smap.put(s.charAt(i),i);
            tmap.put(t.charAt(i),i);
        }
        return true;
    }


}
