package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/interval-list-intersections
public class IntervalListIntersection {

    /*
    - Exists into memory.
    - Integers?
    - Free to take i/p as 2D array of List<List<Integer>>
    - disjoint, sorted.
    - What happens if there isn't any intersection, empty array.
    - Is it safe to assume the arrays won't be null, else I will do the input validation at the start. If either of the array is null or empty sized, return empty array.
    A: m    B: n O(m*n)
    Interval matching is a constant time op is:
    1:(a,b)   2:(c,d)
    ----------a---c----b------d-----
                  -----
    ---------a---c-----d----b-----
                 -------
    -- a2.start < a1.end && a1.start < a2.end
    Approach 2:
    - if(a[i].end < a[j].start) i++;
    else if(a[j].end < a[i].start) j++;
    else // Intersection, add to the list. Intersection: Math.max(a[i].start,a[j].start), Math.min(a[i].end, a[j].end);
    */

        public int[][] intervalIntersection(int[][] A, int[][] B) {
            List<int[]> res = new ArrayList();
            int i = 0;
            int j = 0;
            while(i < A.length && j < B.length){
                // A much concise/nicer way to check for overlap and increment/decrement counters to get to the next interval.
                int lo = Math.max(A[i][0],B[j][0]);
                int hi = Math.min(A[i][1], B[j][1]);
                if(lo <= hi)
                    res.add(new int[]{lo,hi});
                if(A[i][1] > B[j][1]) j++;
                else i++;
            }

            return res.toArray(new int[res.size()][2]);
        }


}
