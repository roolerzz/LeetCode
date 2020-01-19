package com.leetcode.easy;

// https://leetcode.com/problems/climbing-stairs/
public class ClimbingStairs {
    /*
   Bottom Up DP further space optimized
   Time: O(n)
   space: O(n) for the memo table.
*/
    public int climbStairs(int n) {
        if(n <1) return 0;
        if(n == 1 || n == 2) return n;
        int a=1;
        int b=2;
        for(int i=3; i<=n;i++)
        {
            int temp = a+b;
            a = b;
            b = temp;
        }
        return b;
    }

    /*
    Bottom Up DP.
    Time: O(n)
    space: O(n) for the memo table.
*/
    public int climbStairsBottomUp(int n) {
        if(n <1) return 0;
        if(n == 1 || n == 2) return n;
        int[] memo = new int[n+1];
        memo[1]=1;
        memo[2]=2;
        for(int i=3; i<=n;i++)
            memo[i] = memo[i-1]+memo[i-2];
        return memo[n];
    }


    /*
        DP with Memo table;
        Time: O(n)
        space: O(n) for implicit stack and O(n) for the memo table.
    */
    public int climbStairsTopDown(int n) {
        if(n <1) return 0;
        int[] memo = new int[n+1];
        return waysTopDown(n, memo);
    }

    public int waysTopDown(int n, int[] memo){
        if(n == 1 || n == 2) return n;
        if(memo[n] != 0) return memo[n];
        int res = waysTopDown(n-2, memo)+waysTopDown(n-1,memo);
        memo[n] = res;
        return memo[n];
    }

    // Think about how many number of ways to reach position i.
    // well we can reach pos i from i-2(doing a 2 jump) and i-1(doing a 1 jump)
    // If you were thinking that you can do 2 types of jump from i-2, a 1 jump and a 2 jump, you are right, but the 1 jump solution shouldn't be accounted separately in i-2 because that would already be accounted in all possible ways we can jump from i-1;
    /*
    Time: O(2^n) Subproblemn tree is n level deep and doubling in size at every level starting 1.
    Space: O(n) because the sub-problem tree is n level deep.
    */
    public int climbStairsRecursive(int n) {
        if(n <1) return 0;
        return waysRecursive(n);
    }

    public int waysRecursive(int n){
        if(n == 1 || n == 2)
            return n;
        return waysRecursive(n-2)+waysRecursive(n-1);
    }

}
