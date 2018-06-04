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
	        return sum;
	    }

}
