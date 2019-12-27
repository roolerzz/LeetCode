package com.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

//https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {

	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		TrappingRainWater instance = new TrappingRainWater();
		System.out.println(instance.trap(height));
	}


    /*
    O(N^2) algorithm.
        int runningSum = 0;
        Water at ith index trapped would be,
        - a = Starting i-1 to 0 find the greatest value is greater than ith.
        - b = starting i+1 to n-1 find the greatest value which is greater than ith.
        - If both exists, water trapped at ith index would be runningSum += Min(a,b) - element[i];
        return running Sum
    */

	public int trapBruteForce(int[] height) {
		if(height == null || height.length == 0) return 0;
		int sum = 0;
		for(int i = 1; i < height.length-1;i++){
			int maxLeft = height[i];
			int maxRight = height[i];
			for(int j=i-1; j >=0; j--){
				maxLeft = Math.max(maxLeft, height[j]);
			}
			for(int k=i+1; k < height.length; k++){
				maxRight = Math.max(maxRight, height[k]);
			}
			sum += Math.min(maxLeft,maxRight) - height[i];
		}
		return sum;
	}

	public int trapExtraSpace(int[] height) {
		if(height == null || height.length == 0) return 0;
		int sum = 0;
		int[] left = new int[height.length];
		int[] right = new int[height.length];
		int size = height.length;
		left[0]=height[0];
		for(int i=1 ; i < size; i++){
			left[i] = Math.max(left[i-1],height[i]);
		}
		right[size-1]=height[size-1];
		for(int j=size-2; j >=0; j--){
			right[j] = Math.max(right[j+1],height[j]);
		}
		for(int k = 1; k < size-1;k++){
			sum += Math.min(left[k],right[k]) - height[k];
		}
		return sum;
	}


	public int trapStack(int[] height) {
		if(height == null || height.length == 0) return 0;
		int sum = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		int size = height.length;
		stack.push(0);
		int curr= 1;
		while(curr < size){
			while(!stack.isEmpty() && height[stack.peek()] < height[curr]){
				int top = stack.pop();
				if(stack.isEmpty())
					break;
				int dist = curr - stack.peek() -1;
				int bounded_height = Math.min(height[curr], height[stack.peek()])-height[top];
				sum += dist * bounded_height;
			}
			stack.push(curr++);
		}
		return sum;
	}
	public int trap(int[] height) {
		if(height == null || height.length == 0) return 0;
		int sum = 0;
		int left = 0, right = height.length-1, left_max = 0, right_max = 0;
		while(left < right){
			if(height[left] < height[right]){
				if(height[left] >= left_max)
					left_max = height[left];
				else
					sum += left_max-height[left];
				left++;
			}
			else{
				if(height[right] >= right_max)
					right_max = height[right];
				else
					sum += right_max-height[right];
				right--;
			}
		}
		return sum;
	}
}
