package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/meeting-rooms-ii/
public class MeetingRoomsII {

    /*
    [ 0:30, 5:10, 15:20 ]

    0----5----10----15----20-----------30
         ------
     ------------------------------------
                     -------
        ----

        20
    30
    maxCount =3
    Count = 2

     0, 1, 2, 3, 4, 5, 6
    10, 8, 9, 6, 5, 4, 3

    */
    /*

    Questions?
    - How big is the input? Does it fits into the memory.
    - How exactly is my interval given. Pair of integers in and array? 2d array?

    Algorithm:
    1) Sort the interval array by start times.
    2) The only time a meeting would be required would be when a new interval is added right.
    3) Maintain a MIN-PQ which would have the end times of the meetings currently in place. Why min PQ. Well at each new meeting start(insertion), we need to know which meeting has ended, and Min-PQ can give us the earliest ended meeting. We need to remove all the keys in MinPQ, which are lesser than the new starting interval's start time.
    4. Max Count size: At each insertion into PQ, Math.max(maxCount, pq.size()).
    */

    public int minMeetingRoomsPQ(int[][] intervals) {
        if(intervals == null) return 0;
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int maxSize = 0;
        for(int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            while(!minPQ.isEmpty() && minPQ.peek() <= start){
                minPQ.poll();
            }
            minPQ.add(end);
            maxSize = Math.max(maxSize, minPQ.size());
        }
        return maxSize;
    }

    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        int[] st = new int[intervals.length];
        int[] end = new int[intervals.length];
        for(int i = 0 ; i< intervals.length; i++){
            st[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(st);
        Arrays.sort(end);
        int usedRooms = 1;
        // 1,2,3,8,10,11
        // 7,10,12,19,20,30
        for(int stptr = 1, endptr = 0; stptr < st.length;) {
            if(st[stptr] >= end[endptr])
            {
                endptr++;
            }
            stptr++;
            usedRooms = Math.max(usedRooms, stptr-endptr);
        }
        return usedRooms;
    }

}
