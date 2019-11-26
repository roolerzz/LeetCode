package com.leetcode.easy;

// https://leetcode.com/problems/first-bad-version/
public class FirstBadVersion {

    boolean isBadVersion(int version){
        return false; // TODO
    };

    public int firstBadVersion(int n) {
        if(n <=0) return -1;
        int start = 1, end = n;
        int badVersion = Integer.MAX_VALUE;
        while(start <= end){
            int mid = start + (end-start)/2;
            if(!isBadVersion(mid)) start = mid + 1;
            else {
                badVersion = Math.min(badVersion,mid);
                end = mid-1;
            }
        }
        return badVersion;
    }
}
