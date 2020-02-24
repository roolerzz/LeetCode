package com.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/happy-number/solution/
public class HappyNumber {
    public boolean isHappy2(int n) {

        Set<Integer> unique = new HashSet<>();
        unique.add(1);
        while(!unique.contains(n)){
            unique.add(n);
            int temp = n;
            int sum = 0;
            while(temp != 0){
                int lastDig = temp%10;
                sum += lastDig * lastDig;
                temp /= 10;
            }
            n = sum;
        }
        return n==1;
    }

    public boolean isHappy(int n){
        int slowRunner = n;
        int fastRunner = getNext(n);
        while(fastRunner != 1 && slowRunner != fastRunner){
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner== 1;
    }

    private int getNext(int n){
        int sum = 0;
        while(n > 0){
            int d = n % 10;
            n = n/10;
            sum += d*d;
        }
        return sum;
    }


}
