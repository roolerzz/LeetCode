package com.leetcode.hard;

public class MedianOfTwoSortedArrays {

	 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        if(nums1.length > nums2.length)
	            return findMedianSortedArrays(nums2,nums1);
	        int m = nums1.length;
	        int n = nums2.length;
	        int lo = 0;
	        int hi = m;
	        while(lo <= hi){
	            int partX = lo + (hi-lo)/2;
	            int partY = (m+n+1)/2-partX;
	            int maxLeftX=(partX==0)?Integer.MIN_VALUE:nums1[partX-1];
	            int maxLeftY=(partY==0)?Integer.MIN_VALUE:nums2[partY-1];
	            int minRightX=(partX==m)?Integer.MAX_VALUE:nums1[partX];
	            int minRightY=(partY==n)?Integer.MAX_VALUE:nums2[partY];
	            
	            // if Partitioned exactly into half such than elements to left half are less than elements to the right, return the median.
	            if(maxLeftX <= minRightY && maxLeftY <=minRightX){
	                // if total number of is even, find the average of max of left, and min of right. 
	                if((m+n)%2 == 0){
	                    return (Math.max(maxLeftX,maxLeftY)+ Math.max(minRightX,minRightY))/2;
	                }
	                //else return max of 2 lefts.
	                else
	                    return Math.max(maxLeftX,maxLeftY);
	            }
	            else if(maxLeftX > minRightY)
	            // if partition element in nums1 is too big than min elemnt in right sub array of nums2. Go Left.
	            {
	                hi = partX-1;
	            }
	            // else go right.
	            else{
	                lo = partX+1;
	            }
	        }
	        throw new IllegalArgumentException("Input arguments were wrong.");
	    }
	
	public static void main(String[] args) {
		MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
		int[] nums1 = {2};
		int[] nums2 = {};
		System.out.println(median.findMedianSortedArrays(nums1, nums2));
	}

}
