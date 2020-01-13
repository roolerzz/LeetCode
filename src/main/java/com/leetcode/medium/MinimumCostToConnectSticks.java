package com.leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/minimum-cost-to-connect-sticks/
public class MinimumCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        int totalCost = 0;
        Queue<Integer> minPQ = new PriorityQueue<>();
        for(int stick : sticks)
            minPQ.offer(stick);
        while(minPQ.size()>1){
            int cost = minPQ.poll() + minPQ.poll();
            minPQ.offer(cost);
            totalCost += cost;
        }
        return totalCost;
    }

}
