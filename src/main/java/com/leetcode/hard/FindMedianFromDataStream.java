package com.leetcode.hard;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/find-median-from-data-stream/solution/
class FindMedianFromDataStream {
    
	    private PriorityQueue<Integer> leftHalf;
	    private PriorityQueue<Integer> rightHalf;
	    /** initialize your data structure here. */
	    public FindMedianFromDataStream() {
	        // Max PQ.
	        leftHalf = new PriorityQueue<>(Collections.reverseOrder());
	        // Min PQ.
	        rightHalf = new PriorityQueue<>();
	    }
	    
	    // Insertion sort in O(N) to always keep the list sorted.
	    public void addNum(int num) {
	        leftHalf.offer(num);
	        rightHalf.offer(leftHalf.poll());
	        // Balancing the heaps as per property, leftHalf contains at  n or n+1 elements if rightHalf contains n.
	        if(leftHalf.size() < rightHalf.size()){
	            leftHalf.offer(rightHalf.poll());
	        }
	    }
	    
	    public double findMedian() {
	        return leftHalf.size()> rightHalf.size() ? leftHalf.peek() : (leftHalf.peek() + rightHalf.peek())*0.5;
	    }
	}

	/**
	 * Your FindMedianFromDataStream object will be instantiated and called as such:
	 * FindMedianFromDataStream obj = new FindMedianFromDataStream();
	 * obj.addNum(num);
	 * double param_2 = obj.findMedian();
	 */