package com.leetcode.easy;
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeToBuyAndSellStockI {
        /*
            1,2,3,4,5,6
            8
            8,7
            7,1,5,3,6,4
            7,6,4,3,1

    */
    /*
    Q:
    - What are my values? Integers, non -ve?
    - Definitely can have duplicates. Can be all inc, all dec, same etc. For test cases.
    - What if my array size < 2, maxProfit = 0;
    1) Brute force. For each pair of idx buy, sell wehere buy < sell, find diff. If diff > currMax, update currMax. O(N^2)
    2) Start with a number(min/globalmin). As long as the values are greater than that number, keep the diff. If the diff is greater than maxDiff, update maxProfit, and globalMin. As soon as you see a smaller value than that, update currMin and continue.
    */



        // Brute force.
        public int maxProfitBrute(int[] prices) {
            if(prices== null || prices.length < 2) return 0;
            int maxProfit = 0;
            for(int b = 0 ; b < prices.length-1; b++){
                for(int s = b+1; s < prices.length; s++){
                    maxProfit = Math.max(maxProfit, prices[s]-prices[b]);
                }
            }
            return maxProfit;
        }
        /* [7,1,5,3,0,6,4] */
        public int maxProfit(int[] prices) {
            if(prices== null || prices.length < 2) return 0;
            int maxProfit = 0;
            int minIdx = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[minIdx] <= prices[i])
                    maxProfit = Math.max(maxProfit, prices[i]-prices[minIdx]);
                else
                    minIdx = i;
            }
            return maxProfit;
        }
}
