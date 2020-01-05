package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/group-anagrams/
public class GroupAnagrams {
    /*
        Time: O(n*(klogk))
        Space: O(n*k)
        */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            // System.out.println("HashValue for String : " + str + " is : " + h);
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }



    // Computes the hashcode of the array of counts of the characters.
    /* Time : O(n*k) Where n is the number of strings of average size k.
    Space: O(n*k)
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();
        for(String str : strs){
            int h = hash(str);
            map.putIfAbsent(h, new ArrayList<String>());
            map.get(h).add(str);
        }

        return new ArrayList<>(map.values());
    }

    private int hash(String str){
        int[] count = new int[26];
        for(int i=0; i < str.length(); i++){
            count[str.charAt(i)-'a']++;
        }
        return Arrays.hashCode(count);
    }

    /*
    Counts the occurence of each character in the string and convert into the form: b1..d4 etc. Now Anagrams would be encoded to the same resulting string which can be used as a key in the hashmap to group the anagrams together.

    */
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            String enc = encode(str);
            map.putIfAbsent(enc, new ArrayList<String>());
            map.get(enc).add(str);
        }
        return new ArrayList<>(map.values());
    }
    private String encode(String str){
        int[] count = new int[26];
        for(int i=0; i < str.length(); i++){
            count[str.charAt(i)-'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<count.length; i++){
            if(count[i] >0){
                sb.append((char)('a'+i));
                sb.append(count[i]);
            }
        }
        return sb.toString();
    }


}
