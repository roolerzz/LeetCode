package com.codesignal.medium;

// https://app.codesignal.com/company-challenges/mz/zCYv3tuxRE4JajQNY

/*

Sometimes a player is offered so many quests during a game that it's difficult to complete them all. Time is short, and naturally each player wants to complete as many quests as possible while maximizing the points they earn. Here is the scenario:

PlayerOne has a long list of quests, but only timeForQuests hours to complete them. The ith quest should be completed in hi hours, and the reward for it is pointsi. Each quest can be completed only once. Calculate the maximum number of points that PlayerOne can earn.

Example

For h = [1, 4, 2], points = [2, 3, 2], and timeForQuests = 4, the output should be
questEfficiencyItem(h, points, timeForQuests) = 4.

PlayerOne has 4 hours to complete the quests, so it is possible to earn:

2 points for the first quest;
3 points for the second quest;
2 points for the third quest;
2 + 2 = 4 points for the first and the third quests.
So, the maximum number of points PlayerOne can earn is 4.

For h = [1, 4, 2], points = [2, 5, 2], and timeForQuests = 4, the output should be
questEfficiencyItem(h, points, timeForQuests) = 5.

Completing the second quest gives 5 points, which is greater than solving the first and the third quests (2 + 2 = 4 points).

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer h

Array of positive integers.

Guaranteed constraints:
1 ≤ h.length ≤ 15,
1 ≤ h[i] ≤ 15.

[input] array.integer points

Array of positive integers. The ith element represents the points given upon completion of the ith quest.

Guaranteed constraints:
points.length = h.length,
1 ≤ points[i] ≤ 100.

[input] integer timeForQuests

The number of hours PlayerOne has to complete the quests.

Guaranteed constraints:
4 ≤ timeForQuests ≤ 50.

[output] integer

The maximum number of points PlayerOne can earn.
Your code should run in linear time of the number of quests and timeForQuests to pass all the tests, i.e.
O(h.length * timeForQuests).


* */
public class QuestEfficiencyItem {


    int[] hours;
    int[] pts;
    int questEfficiencyItem(int[] h, int[] points, int timeForQuests) {
        hours = h;
        pts = points;
        int[][] memo = new int[points.length][timeForQuests+1];
        return maxNumber(0, timeForQuests, memo);
    }

    private int maxNumber(int idx , int remainingTime, int[][] memo){
        int max = Integer.MIN_VALUE;
        if(idx == hours.length || remainingTime < 0) return 0;
        if(memo[idx][remainingTime] != 0) return memo[idx][remainingTime];
        max = Math.max(max, maxNumber(idx+1, remainingTime, memo));
        if(remainingTime-hours[idx] >= 0)
            max = Math.max(max, maxNumber(idx+1, remainingTime-hours[idx], memo) + pts[idx]);
        memo[idx][remainingTime] = max;
        return max;
    }


/*
    - Try all possible combination of quests(all possible subsets). 2^n
        - Discard the invalid ones(where the total sum exceeds time for quests)
        - Out of the remaining, take the one with highest time.

    - dp[i, time] : What is the Maximum number of points We can make given a time and index i onwards.
        - At each i, we have 2 possibilities.
        - Either taking it might give us max points or leaving it.
        so
        dp[i,t] = Math.max(dp[i+1,t], dp[i+1,t-h[i]] + points[i]);

        max Number of points (index i, remaining )
            if i == length
                return 0;
            - int max = Integer.MIN_VALUE;
            max = Math.max(maxNumber(i+1, remaining), maxNumber(i+1, remaining-h[i] + points[i]);

*/


}
