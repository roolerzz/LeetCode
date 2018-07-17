package com.leetcode.easy;

public class ReverseWordsInString {
	  
	public void reverseWords(char[] str) {
	        char[] copy = new char[str.length];
	        int j = str.length-1;
	        int start = 0;
	        int nextSpace = 0;
	        while(j >= 0){
	            while(nextSpace < str.length && str[nextSpace] != ' ')
	                    nextSpace++;
	            int end = nextSpace -1;
	            while(end >= start)
	                    copy[j--] = str[end--];
	            if(j > 0) copy[j--] = ' ';
	            start = ++nextSpace;
	        }
	      System.arraycopy(copy, 0, str, 0, str.length); 
	}
	
	public static void main(String[] args) {
		ReverseWordsInString rev = new ReverseWordsInString();
		char[] str = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
		rev.reverseWords(str);
		for(char c : str)
			System.out.println(c);
	}
}
