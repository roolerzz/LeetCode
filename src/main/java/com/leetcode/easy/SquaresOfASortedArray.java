package com.leetcode.easy;

import java.util.Arrays;

// https://leetcode.com/problems/squares-of-a-sorted-array/
public class SquaresOfASortedArray {
        /*
        Questions to ask:
        - Input. Integers, non-decreasing, +ve, -ve.
        - Does relative order matter for same square values compared to their original element.
        - Do we need to do it inplace? Can we assume numbers won't be big enough that int can't hold their square value.


        Approach 1:
        - Calculate an inplace square of the numbers and just sort the array. O(nlogN) algorithm. Assuming doing squares is a constant time operation due to smaller numbers.

        Approach 2: Can we do better that O(NlogN) say O(N).
            Requires extra space.
            - Find the locatin of 0 if present or the location that would be inserted to keep the ordering. It would be the location of the first positive number or zero in the array.
            Start 2 pointers 1 from 0idx call it right, and other from left of 0 index call it left.
            - Need an extra auxiliary array to store the final results.
            - compare the squares of elem @ left and right idx, putting the smaller one in the aux array. if it was right, inc it. If it was left, dec it.
            -
        */
        public int[] sortedSquares2(int[] A) { // -x-1 = i -1-i
            int[] aux = new int[A.length];
            int i = Arrays.binarySearch(A, 0); // If I is -ve, its (-(insertionpoint) - 1)
            // int idx = 0;
            if(i < 0)
                i = (-1-i);
            //System.out.println("pivot point is : " + i);
            int right = i, left = i-1;
            int ptr = 0;
            while(left >= 0 && right < A.length){
                if(Math.abs(A[left]) <= Math.abs(A[right])){
                    aux[ptr++] = A[left]*A[left];
                    left--;
                }
                else{
                    aux[ptr++] = A[right]*A[right];
                    right++;
                }
            }
            while(left >= 0)
                aux[ptr++] = A[left]*A[left--];

            while(right< A.length)
                aux[ptr++] = A[right]*A[right++];
            return aux;
        }


        public int[] sortedSquares(int[] A) { // -x-1 = i -1-i
            for(int i=0; i < A.length; i++){
                A[i] = A[i]*A[i];
            }
            Arrays.sort(A);
            return A;
        }


}
