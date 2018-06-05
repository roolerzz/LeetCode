package com.leetcode.hard;

import static org.junit.Assert.assertEquals;

//https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {

	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		TrappingRainWater instance = new TrappingRainWater();
		System.out.println(instance.trap(height));
	}
	
	  public int trap(int[] height) {
		    int sum = 0;
		  //  int size = height.length;
		    for (int i = 1; i < height.length - 1; i++) {
		        int max_left = 0, max_right = 0;
		        for (int j = i; j >= 0; j--) { //Search the left part for max bar size
		            max_left = Math.max(max_left, height[j]);
		        }
		        for (int j = i; j < height.length ; j++) { //Search the right part for max bar size
		            max_right = Math.max(max_right, height[j]);
		        }
		        sum += Math.min(max_left, max_right) - height[i];
		    }
		    return sum;
			  // My initial solution which Didn't take into account few cases, thus failed.
		    /*	        int sum = 0;
		    	        if(height == null || height.length < 3)
		    	            return sum;
		    	     
		    	        for(int i = 0 ; i < height.length-2; i++){
		    	            if(height[i] == 0) continue;
		    	        	int candidate = height[i];
		    	            int potentialSum = 0;
		    	            int j = i+1;
		    	            while(j < height.length && height[j] < candidate){
		    	                potentialSum += candidate-height[j];
		    	                j++;
		    	            }
		    	            if(j < height.length && height[j] >= candidate){
		    	                sum += potentialSum;
		    	                i = j-1;
		    	            }
		    	            //else
		    	             //   i++;
		    	        }
		    	        return sum;*/
	    }

}
