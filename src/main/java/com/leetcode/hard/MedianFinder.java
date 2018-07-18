package com.leetcode.hard;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
    
	   // private List<Integer> elements;
	    
	    private PriorityQueue<Integer> lo;
	    
	    private PriorityQueue<Integer> hi;

	    /** initialize your data structure here. */
	    public MedianFinder() {
	      //  elements = new ArrayList<Integer>();
	        // Max PQ.
	        lo = new PriorityQueue<>(Collections.reverseOrder());
	        // Min PQ.
	        hi = new PriorityQueue<>();
	    }
	    
	    // Insertion sort in O(N) to always keep the list sorted.
	    public void addNum(int num) {
	 //       elements.add(num);
	        /* int posToInsert = Collections.binarySearch(elements,num);
	        if (posToInsert < 0)
	            posToInsert = (posToInsert + 1) * -1;
	            elements.add(posToInsert,num);*/
	        
	        /*Collections.sort(elements,new Comparator<Integer>(){
	            @Override
	            public int compare(Integer o1 , Integer o2){
	                return o1-o2;
	            }
	        }); */
	        
	        lo.offer(num);
	        hi.offer(lo.poll());
	        // Balancing the heaps as per property, lo contains at  n or n+1 elements if hi contains n.
	        if(lo.size() < hi.size()){
	            lo.offer(hi.poll());
	        }
	    }
	    
	    public double findMedian() {
	     /*    int size = elements.size();
	        double median = 0;
	        if(size %2 == 0){
	            double left = elements.get((size-1)/2);
	            double right = elements.get((size)/2);    
	            median = (left+right)/2;
	        }
	        else{
	            median = elements.get((size-1)/2);
	        }*/
	        return lo.size()> hi.size() ? lo.peek() : (lo.peek() + hi.peek())*0.5;
	    }
	}

	/**
	 * Your MedianFinder object will be instantiated and called as such:
	 * MedianFinder obj = new MedianFinder();
	 * obj.addNum(num);
	 * double param_2 = obj.findMedian();
	 */