package com.leetcode.medium;

//https://leetcode.com/problems/search-in-rotated-sorted-array/
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ (Handles duplicates)
public class FindRotatedSortedArray {


    // 1. Brute force soln -O(N). Search through every index in the array and match with the target element.
    // 2. But the B.F soln doesn't utilize additional information given in the array that the array is sorted and rotated.
    // Optimized Binary Search O(logN)
    // So the idea is in a Sorted rotated array, First set of x elements would increase, then a pivot element(where drop happens),
    // and starts increasing till end after that. Find the middle element and compare it with the target or the lo element.
    // 2.1  If the middle element is equal to the target, return that index.
    // 2.2  If the lo elem is lesser than or equal to middle elem, then that means the strip from lo-mid is increasing. That means
    // if the target element lies b/w lo to mid, go left, otherwise go right.
    // 2.3 If the lo elem is greater than the middle elem, mid lies on the strip after the drop. That means strip from mid-hi is increasing.
    // That means if the target elem lies b/w mid to high, go right, else go left.
    public int findElem(int[]arr, int elem) {
        if(arr != null){
            int lo = 0 , hi = arr.length-1;
            while(lo <= hi) {
                int mid = lo + (hi-lo)/2;
                if(arr[mid] == elem) return mid;
                else if(arr[lo] <= arr[mid]){
                    if(elem >= arr[lo] && elem < arr[mid]){
                        hi = mid-1;
                    }
                    else
                        lo = mid+1;
                }
                else{
                    if(elem <= arr[hi] && elem >arr[mid]){
                        lo = mid+1;
                    }
                    else
                        hi = mid-1;
                }
            }
        }
        return -1;
    }

  public static void main(String[] args) {

        FindRotatedSortedArray s1= new FindRotatedSortedArray();
        int[] arr1 = new int[]{4,5,6,7,8,9,10,1,2,3};
        int[] arr2 = new int[]{2,3,4,5,6,7,8,9,10,1};
        int[] arr3 = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] arr4 = new int[]{8,9,10,1,2,3,4,5,6,7};
        int[] arr5 = new int[]{5,5,5,5,5,5,6,7,1,2,3,4,5,5,5,5,5};
        int[] arr6 = new int[]{5,5,5,5,5,5,6,7,7,7};

        System.out.println(s1.findElem(arr1, 7) == 3);
        System.out.println(s1.findElem(arr1, 3) == 9);
        System.out.println(s1.findElem(arr2, 2) == 0);
        System.out.println(s1.findElem(arr2, 10) == 8);
        System.out.println(s1.findElem(arr2, 1) == 9);
        System.out.println(s1.findElem(arr3, 7) == 6);
        System.out.println(s1.findElem(arr3, 3) ==2);
        System.out.println(s1.findElem(arr4, 9) == 1);
        System.out.println(s1.findElem(arr4, 1) == 3);
        System.out.println(s1.findElem(arr1, 11) == -1);
        System.out.println(s1.findElem(arr2, 19) == -1);
        System.out.println(s1.findElem(arr3, -1) == -1);
        System.out.println(s1.findElem(arr4, -20) == -1);
        System.out.println(s1.findElem(arr5, 6) == 6);
        System.out.println(s1.findElem(arr6, 6) == 6);


  }
}
