package com.leetcode.easy;

import java.util.Arrays;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii
public class BestTimeToBuyAndSellStockII {

    public int maxProfitRec(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        return recursiveProfit(prices,0);
    }

    /*
    Approach I:
    Brute Force. Time complexity: Exponential. N^N. Space : O(N)
    // For every possible compbination(or pair) of buy/sell, get the profit, and recurse on the subproblem(sellIdx + 1);
    */
    private int recursiveProfit(int[] prices, int s){
        if(s >=prices.length) return 0;
        int max= 0;
        for(int start = s; start < prices.length; start++){
            int maxProfit = 0;
            for(int sellIdx = start + 1; sellIdx < prices.length; sellIdx++){
                if(prices[start] < prices[sellIdx]){
                    int profit = prices[sellIdx]-prices[start] + recursiveProfit(prices, sellIdx+1);
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
            max = Math.max(max,maxProfit);
        }
        return max;
    }

    public int maxProfit(int[] prices){
        if(prices == null || prices.length < 2) return 0;
        int[] memo = new int[prices.length];
        Arrays.fill(memo,-1);
        return maxProfitTopDownDP(prices, memo, 0);
        // return memo[0];
    }

    private int maxProfitTopDownDP(int[] prices, int[] memo, int start){
        if(start == prices.length) return 0;
        if(memo[start] != -1) return memo[start];
        int maxProfit = 0;
        for(int i = start ; i < prices.length-1; i++){
            int maxProfitBuyAtI = 0;
            for(int j= i+1; j < prices.length; j++){
                if(prices[j] > prices[i])
                    maxProfitBuyAtI = Math.max(maxProfitBuyAtI, prices[j]-prices[i] + maxProfitTopDownDP(prices, memo, j+1));
            }
            maxProfit = Math.max(maxProfit, maxProfitBuyAtI);
        }
        memo[start] = maxProfit;
        return memo[start];
    }

    /*
        a1, a2, a3... ai. ..... an-1
        buy : ai. what is the max profit ?
        profit(i) // Try for all possible Is. from 0-N-2.
        maxProfit - max(maxProfit or for price[j]-price[i] for j from i+1 to N-1 + profit(j).
        */

    /* Approach II
        Basically Observing it on a graph, the maximum benefit that we can get is from peak/valley gains. For every peak, following a valley, add the profit(peak[i]-valley[j]) to the result.

    */
    public int maxProfitII(int[] prices){
        if(prices == null || prices.length <2 ) return 0;
        int maxProfit = 0;
        int i=0;
        int peak = prices[0];
        int valley = prices[0];
        while(i < prices.length-1){
            while(i <prices.length-1 && prices[i] >= prices[i+1])
                i++;
            valley = prices[i];
            while(i < prices.length-1 && prices[i] <= prices[i+1])
                i++;
            peak = prices[i];
            maxProfit += peak-valley;
        }
        return maxProfit;
    }

    /*
    Approach III:
    Profit that you get from a diff b/w a valley and subsequent peak is the sums of gains b/w consecutive elements
    */
    public int maxProfitIII(int[] prices){
        if(prices == null || prices.length == 0) return 0;
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i-1] < prices[i])
                maxProfit += prices[i]-prices[i-1];
        }
        return maxProfit;
    }


}
