package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {

        private class Interval{
            int start;
            int end;
            Interval(int start, int end){
                this.start = start;
                this.end = end;
            }
        }

        public int[][] merge(int[][] intervals) {
            if(intervals == null || intervals.length < 2) return intervals;

            List<Interval> intList = new ArrayList<>();
            List<Interval> resList = new ArrayList<>();
            for(int i = 0; i < intervals.length; i++){
                intList.add(new Interval(intervals[i][0],intervals[i][1]));
            }

//          Collections.sort(intList,(i1, i2) -> i1.start-i2.start);
            Collections.sort(intList, Comparator.comparingInt(value -> value.start));

            resList.add(intList.get(0));
            for(int i = 1; i < intList.size();i++){
                Interval prev = resList.get(resList.size()-1);
                Interval curr = intList.get(i);
                if(overlaps(prev,curr))
                    prev.end = Math.max(curr.end, prev.end);
                else
                {
                    resList.add(curr);
                }

            }
            int[][] resInt = new int[resList.size()][2];
            for(int i =0; i< resList.size();i++){
                resInt[i][0] = resList.get(i).start;
                resInt[i][1] = resList.get(i).end;
            }
            return resInt;

        }
        private boolean overlaps(Interval prev, Interval curr){
            return prev.end >= curr.start && prev.start <= curr.end;
        }


}
