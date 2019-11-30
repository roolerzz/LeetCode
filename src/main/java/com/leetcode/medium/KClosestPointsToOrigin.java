package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/k-closest-points-to-origin/
public class KClosestPointsToOrigin {

    /*
    I/P
    - How is my i/p given. Does all the points fits into the memory?
    - A 2-D array where 2nd dimension would be of size 2? or a list/array of points ds.
    - Given n points, find k closest ones.
    - In case of null i/p or empty points. return empty array.
    - What happens in case my k is greater than number of elements?
    o/p
    - K closest points.
    - Values of K? -ve{}, 0{}, +ve.
    - Integers.
    - Can there be duplicate points in the array.

    1 approach:
    - Iterate through all the points, find their distance to the origin, make it into a
    single d.s, sort the data structure, using distance as the key. Return a new sub-array from 0-k-1. O(NLogN) O(N)extra space.
    - Using a MaxPQ of size K, comparator would be using both the points,
    return the difference of distance from origin. Keep on adding those to MaxPQ,
    once it goes over the size of k+1, remove the element(largest). N-K max elements,
    leaving me with K smallest elemets. O(NLogN) O(N)extra space.
    Time: O(NLogN Insertion of N elements + (N-K)log(N-K) removal of top) Space: O(N)
    - First closest elem: O(N*K), O(N) , O(N) hashmap to remember the elements that we have seen already.
    */


    public static int[][] kClosest2(int[][] points, int K) {
        if(points == null || points.length == 0 || K <=0) return new int[][]{};
        PriorityQueue<int[]> minPQ = new PriorityQueue<>(K,(a, b) -> {
            double distA = (a[1])*(a[1]) + (a[0])*(a[0]);
            double distB = (b[1])*(b[1]) + (b[0])*(b[0]);
            return (distA == distB)? 0 : (distA< distB) ? 1 : -1;
        });

        for(int[] point : points){
            minPQ.add(point);
            if(minPQ.size() > K) minPQ.poll();
        }
        int[][] res = new int[K][2];
        minPQ.toArray(res);
        return res;
    }

    public static int[][] kClosest(int[][] points, int K) {
        if(points == null || points.length == 0 || K <=0) return new int[][]{};
        Arrays.sort(points, Comparator.comparingDouble(a -> ((a[1])*(a[1]) + (a[0])*(a[0]))));
        return Arrays.copyOfRange(points,0,K);
    }

    public static int[][] kClosestQuickSelect(int[][] points, int K) {
        if(points == null || points.length == 0 || K <=0) return new int[][]{};
        // p = findPivotPositionK(arr, start, end) // This has also adjusted the array such that element to left of k are smaller(not necessarily sorted amongst themselves) and
        // elements to the right are bigger.
        // if p < k , go right.
        // if p > k, go left.
        // else return subarray from 0,k.  // Check for off by one. [[1,2]]
        int start = 0, end = points.length-1;
        while(start <= end){ // TODO Check off by 1.
            int pivot = findPivotPosition(points, start, end);
            if(pivot < K-1) start = pivot + 1;
            else if(pivot > K-1) end = pivot - 1;
            else break;
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    private static int findPivotPosition(int[][] points, int start, int end){
        int[] pivotInterval = points[start];
        int i = start+1;
        int j = end;
        // 5, 7, 4, 2, 1, 9  // 5, 1, 4, 2|  9,  7   i = 1 j = 5
        // [[1,2]]

//        Arrays.sort();
        while(i <= j) {
            if(smaller(pivotInterval, points[i])){
                swap(points, i,j);
                j--;
            }
            else i++;
        }
        swap(points, i-1, start);
        return i-1;
    }

    private static boolean smaller(int[] interval1, int[] interval2){
        return (interval1[0]*interval1[0]+ interval1[1]*interval1[1]) < (interval2[0]*interval2[0]+ interval2[1]*interval2[1]);
    }

    private static void swap(int[][] points, int i, int j){
        int[] temp = points[i];
        points[i]=points[j];
        points[j] = temp;
    }

    public static void main(String[] args) {

        int[][] points = {{1,3},{-2,2},{1,1},{-1,-1}}; //
        int[][] res = kClosestQuickSelect(points, 2);
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));

        System.out.println(Arrays.toString(points[0]));
        System.out.println(Arrays.toString(points[1]));
        System.out.println(Arrays.toString(points[2]));
        System.out.println(Arrays.toString(points[3]));

    }
}
