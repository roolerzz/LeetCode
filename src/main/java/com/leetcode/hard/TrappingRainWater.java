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
		  // Instead of calculating the max_left and max_right for each array index everytime, lets just precompute that using 2 aux arrays in 1 pass. O(N). 
		  // Now the same approach works in O(N) time and O(N) aux space. For each entry in the array, calculate the amount of water it traps by finding the Max bar length(greater than itself) on either
		  // side, and the water it can hold is minimum of those minus its current height. 
		  if(height == null || height.length <3) return 0;
		  int sum = 0;
		  Stack<Integer> st = new Stack<>();
		  int current = 0;
		  while(current < height.length) {
			  while(!st.isEmpty() && height[current] > height[st.peek()]) {
				  int top = st.pop();
				  if(st.isEmpty()) // Nothing to bound the above element.
					  break;
				  int distance = current-st.peek()-1;
				  int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
				  sum += distance * bounded_height;
			  }
			  st.push(current++);
		  }
		  return sum;
	    }

}
