package com.leetcode.medium;

// https://leetcode.com/problems/water-and-jug-problem/
public class WaterAndJugProblem {

    public boolean canMeasureWater(int x, int y, int z) {
        // for any 2 integers x and y, a*x + b*z = d where d is GCD of x and y.
        // if z is a multiple of gcd of x and y, then yes we can measure x using x and y.
        // if z is greater than the sum of what can be filled in x and y, then we can't measure that.
        return (z == 0 || ( x + y >= z && z % gcd(x, y) == 0));
    }

    private int gcd(int x, int y){
        if(x < y) return gcd(y,x);
        return y == 0? x : gcd(y, x%y);
    }
}
