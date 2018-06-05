package com.leetcode.hard;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

//https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {

	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		TrappingRainWater instance = new TrappingRainWater();
		System.out.println(instance.trap(height));
	}
	
	  public int trap(int[] height) {
		  if(height == null || height.length <3) return 0;
		  int sum = 0;
		  int left = 0, right = height.length-1;
		  int leftmax = 0, rightmax = 0;

		  while(left < right) {
			  if(height[left] < height[right]) {
				  if(height[left] > leftmax)
					  leftmax = height[left];
				  else 
					  sum += leftmax - height[left];
				  ++left;
			  }
			  else {
				  if(height[right] > rightmax)
					  rightmax = height[right];
				  else 
					  sum += rightmax - height[right];
				  --right;
			  }
		  }
		  
		  return sum;
	    }

}
