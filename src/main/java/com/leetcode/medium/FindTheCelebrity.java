package com.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/find-the-celebrity
class Relation {
    // Dummy.
    public boolean knows (int a, int b){
        return false;
    }
}

public class FindTheCelebrity extends Relation {

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

    /*
        - Use a integer indexed array which keeps track of how many people knows this person.
        - Now use a double for loop
            -0-n-1
             - 0-n-1 where i != j
                - if it knows, add to the count.
        - Go through the count array and return index of where count= n - 1, else no celebrity(-1);
        -


    */
    // https://leetcode.com/problems/find-the-celebrity/discuss/71240/AC-Java-solution-using-stack
    public int findCelebrity1(int n) {
        if(n <=0 ) return -1;
        Deque<Integer> stack = new LinkedList<>();
        for(int i=0; i < n; i++){
            stack.push(i);
        }

        while(stack.size()>1){
            int a = stack.pop();
            int b = stack.pop();
            if(knows(a,b))
                stack.push(b);
            else
                stack.push(a);
        }

        int c = stack.pop();
        for(int i=0 ; i < n ; i++){
            if(c != i && (knows(c,i) || !knows(i, c)))
                return -1;
        }
        return c;
    }
    // https://leetcode.com/problems/find-the-celebrity/discuss/144815/Logical-Thinking-with-Clear-Java-Code
    /*
     The first pass is to pick out the candidate. If candidate knows i, then switch candidate.
    The second pass is to check whether the candidate is valid. - if candidate knows someboy except itself or someone except itself knows candidate.
	Then this candidate is invalid, and since we picked out the only possible candidate in first pass, we don't have a celeb.

    Proof of first pass:
    Assume the candidate = K after first pass. Then we know from 0 - K-1 are definitely not candidate.
	Because our candidate starts with 0, and we only switch when candidate know someone - knows(candidate, i).
	That's why it came to K because all those previous person disqualify.
	Also since K knows no one after k+1 - n-1, because candidate didn't get updated in the rest of the loop.
	k+1 - n-1 can't be celeb as well because there is at least one person (K) doesn't know them. - This disqualify as celeb.
    */
    public int findCelebrity(int n) {
        if(n <=0 ) return -1;
        int candidate = 0;
        for(int i=1; i < n; i++){
            if(knows(candidate, i))
                candidate = i;
        }

        for(int i=0 ; i < n ; i++){
            if(candidate != i && (knows(candidate,i) || !knows(i, candidate)))
                return -1;
        }
        return candidate;
    }

}
