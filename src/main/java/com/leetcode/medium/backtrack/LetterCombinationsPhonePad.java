package com.leetcode.medium.backtrack;

import java.util.*;

//https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/793/
public class LetterCombinationsPhonePad {

    // Recursive Back Track Attempt 2
    // "23"
    public List<String> letterCombinations(String digits) {
        List<String> finalList = new ArrayList<>();
        if(digits == null || digits.length() == 0) return finalList;
        int size = digits.length();
        String[] mapping = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        generateRecursively(digits,0,size,mapping, new char[size],finalList);
        return finalList;
    }

    private void generateRecursively(String digits, int currIdx, int lastIdx, String[] mapping, char[] arr, List<String> finalList){
        if(lastIdx == currIdx){
            finalList.add(String.valueOf(arr));
            return;
        }
        String str = mapping[digits.charAt(currIdx)-'0'];
        for(int i = 0 ; i < str.length(); i++){
            arr[currIdx] = str.charAt(i);
            generateRecursively(digits, currIdx+1, lastIdx, mapping, arr,finalList);
        }
    }
	/*
	 * Iteratively using a queue. 
	    public List<String> letterCombinations(String digits) {
	        LinkedList<String> list = new LinkedList<>();
	        if(digits == null || digits.length() == 0) return list;
	        String[] mapping = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	        list.add("");
	        for(int i = 0 ; i < digits.length() ; i++){
	            while(!list.isEmpty() && list.peek().length() == i){
	                String element = list.remove();
	                for(char c : mapping[digits.charAt(i)-'0'].toCharArray())
	                    list.add(element+c);
	            }
	        }
	        return list;
	    }*/




	/* ATTEMPT FEB 24 2020. */
    /*
       - Create a empty character array equal to the length of the string. Create List<String> as result.
       - create a map of Number to character array.
       - Call backtrack(digits, 0, temp, map, result)
       - backtrack(digits, idx, temp, map, result)
           - If the current idx = length of the digits, add the copy of the string made by the temp character array into the result.
           - for every charaacter in the map corresponding to the number at the index, apply that character on the character array index, and process the next element.

   Time Complexity : O(3^n * 4^m) where n numbers had 3 characters and m numbers had 4 characters in them.
   Space : O(n)
   */
    public List<String> letterCombinations2(String digits) {
        // digits 2-9,
        // letters lower case english alphabet.
        // string length fits into the memory
        if(digits == null || digits.length() == 0) return new ArrayList<String>();
        Map<Character, char[]> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        map.put('2', "abc".toCharArray());
        map.put('3', "def".toCharArray());
        map.put('4', "ghi".toCharArray());
        map.put('5', "jkl".toCharArray());
        map.put('6', "mno".toCharArray());
        map.put('7', "pqrs".toCharArray());
        map.put('8', "tuv".toCharArray());
        map.put('9', "wxyz".toCharArray());
        backtrack(digits, new char[digits.length()], result, map, 0);
        return result;
    }

    private void backtrack(String digits, char[] temp, List<String> result, Map<Character, char[]> map, int idx){
        if(idx == digits.length()){
            result.add(new String(temp));
            return;
        }
        for(char c : map.get(digits.charAt(idx))){
            temp[idx] = c;
            backtrack(digits, temp, result, map, idx+1);
        }
    }

    public List<String> letterCombinations3(String digits) {
        // digits 2-9,
        // letters lower case english alphabet.
        // string length fits into the memory
        if(digits == null || digits.length() == 0) return new ArrayList<String>();
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', "abc".toCharArray());
        map.put('3', "def".toCharArray());
        map.put('4', "ghi".toCharArray());
        map.put('5', "jkl".toCharArray());
        map.put('6', "mno".toCharArray());
        map.put('7', "pqrs".toCharArray());
        map.put('8', "tuv".toCharArray());
        map.put('9', "wxyz".toCharArray());
        LinkedList<String> result = new LinkedList<>();
        result.add("");
        for(int i=0; i < digits.length(); i++){
            int size = result.size();
            while(size-- > 0){
                String curr = result.poll();
                for(char c : map.get(digits.charAt(i))){
                    String newElem = curr + c;
                    result.add(newElem);
                }
            }
        }
        return result;
    }


}
