package com.leetcode.easy;

import java.util.Arrays;

//https://leetcode.com/problems/fibonacci-number/
// Time complexity of vanilla recursive solutions is O(Golden_ratio ^ n) tighter bound. Sometime also said as O(2^n)
// https://www.geeksforgeeks.org/time-complexity-recursive-fibonacci-program/
public class NthFibonacciNumber {

    public int nthFiboRec(int n) {
        validate(n);
        int[] fibo = new int[n+1];
        Arrays.fill(fibo, -1);
        fibo[0] =0;
        fibo[1]=1;
        return nthFiboRec(n, fibo);
    }

    private void validate(int n) {
        if (n < 0) throw new IllegalArgumentException();
    }

    private int nthFiboRec(int n, int[] fibo) {
        if(alreadyComputed(n, fibo)) return fibo[n];
        int result = nthFiboRec(n-1, fibo) + nthFiboRec(n-2,fibo);
        fibo[n]= result;
        return fibo[n];
    }

    private boolean alreadyComputed(int n, int[] fibo){
        return fibo[n] != -1;
    }

    public int nthFiboIterative(int n) {
        validate(n);
        int[] fibo = new int[n+1];
        fibo[0]=0;
        fibo[1]=1;
        for(int i = 2; i <= n ;i++){
            fibo[i] = fibo[i-2] + fibo[i-1];
        }
        return fibo[n];
    }


  public static void main(String[] args) {
        NthFibonacciNumber fibObj = new NthFibonacciNumber();
        int recN = 6;
        int iterN = 6;
    System.out.println("Recursive calculating fibo "+ recN + " : "  + fibObj.nthFiboRec(recN));
    System.out.println("Iterative calculating fibo "+ iterN + " : "  + fibObj.nthFiboRec(iterN));
  }
}
