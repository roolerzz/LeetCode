package com.leetcode.easy;

public class CompareVersions {

	public static void main(String[] args) {
		CompareVersions ver = new CompareVersions();
		System.out.println(ver.compareVersion("0.1", "1.1"));
	}

	    public int compareVersion(String version1, String version2) {
	        version1 = removeTrailingZeroes(version1);
	        version2 = removeTrailingZeroes(version2);
	        String[] arrV1 = version1.split("\\.");
	        String[] arrV2 = version2.split("\\.");
	        int i=0, j=0, m = arrV1.length, n = arrV2.length;
	        int val1 = 0;
	        int val2 = 0;
	        while(i < m && j < n){
	            val1 = Integer.parseInt(arrV1[i]);
	            val2 = Integer.parseInt(arrV2[j]);
	            int result = val1 - val2;
	            if(result < 0) 
	                return -1;
	            else if (result > 0 ) 
	                return 1;
	            else {
	                i++;
	                j++;
	            }
	        }
	        if(i == m && j == n)
	            return 0;
	        else if(i < m){
	                return 1;
	        }
	        else{
	                return -1;
	        }
	            
	    }
	    
	    private String removeTrailingZeroes(String version){
	        int i = version.length()-1;
	        while(i > 0){
	            char c = version.charAt(i);
	            if(c == '0')
	                i--;
	            else if (c == '.')
	                i--;
	            else
	                break;
	        }
	        return version.substring(0,i+1);
	    }
}
