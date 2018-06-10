package com.leetcode.medium.backtrack;

import java.util.ArrayList;
import java.util.List;

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
	}
