package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/coin-change-2/
// CTCI 8.7
public class CoinChangeII {
    private class Result{
        int ways;
        List<List<Integer>> result;

        Result(int ways, List<List<Integer>> result) {
            this.ways = ways;
            this.result = result;
        }
    }

    public Result noOfWays2019(int num, int[] currencies){
        if(num < 0 || currencies == null || currencies.length == 0) return new Result(-1, null);
        int[] ways = new int[num+1];
        List<List<Integer>> result = new ArrayList();
        List<Integer> buffer= new ArrayList();
        ways[0]=0;
        ways[1]=1;
        return new Result(noOfWaysRec2019(num,currencies, ways, result, buffer),result);
    }

    private int noOfWaysRec2019(int currIdx, int[] currencies, int[] ways, List<List<Integer>> result, List<Integer> buffer){
        if(!isValid(currIdx, ways)) return 0;
        if(isAlreadyComputed(currIdx,ways)) {
            result.add(new ArrayList<>(buffer));
            return ways[currIdx];
        }
        int noOfWays = 0;
        for(int currency : currencies) {
            buffer.add(currency);
            noOfWays += noOfWaysRec2019(currIdx-currency, currencies, ways, result, buffer);
            buffer.remove((Integer)currency);
            if(currency == currIdx)
                noOfWays += 1;
        }
        ways[currIdx] =noOfWays;
        return ways[currIdx];
    }

    private boolean isValid(int currIdx, int[] ways) {
        return currIdx >= 0 && currIdx < ways.length;
    }

    private boolean isAlreadyComputed(int currIdx, int[] ways){
        return ways[currIdx]!=0;
    }

  public static void main(String[] args) {
    CoinChangeII c2 = new CoinChangeII();
    int num = 5;
    int[] currencies = new int[]{1,2,5};
    Result result = c2.noOfWays2019(num,currencies);
    System.out.println("Number of ways of making a number " + num + " using currencies : " + currencies + " are : " + result.ways);
    c2.printResults(result);
  }

  private void printResults(Result result) {
        List<List<Integer>> results = result.result;
        int counter = 1;
        for(List<Integer> r : results){
            System.out.print("Printing list " + counter++ + ": " + r);
        }
  }
}
