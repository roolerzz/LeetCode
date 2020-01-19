package com.leetcode.medium;

// https://leetcode.com/problems/divide-two-integers/
public class DivideTwoIntegers {

  /*
   *   Retrictions:
   *   1) Can't use division/multiplication/modulus operator.
   *   2) Both dividend and divisor are 32 bit signed Integers.
   *   3) Divisor will never be 0
   *   4) Assume we are dealing with environment where we can only deal with integers(i.e long and other datatypes are not allowed)
   *
   *   Basics:
   *   Definition of Division: The Process of calculating the number of times one number is contained within another number.
   *   Also, to turn a -ve number +ve or a +ve number negative do the 2s compliment of the number.
   *       - 2s compliment can be calculated by doing bitwise negation of the number and adding 1 to it
   *       ~(x) + 1 = -x
   *
   * */

  // Brute force. Use the definition of division. Calculate the number of times we can remove
  // divisors out from
  // the dividend. Goes TLE on edge cases like dvdnd: 2147483647 dvs : 2
    // Runtime is O(dividend).
  public int divide1(int dividend, int divisor) {
        /*
        Cases:
            - Both divisor and dividend is +ve
            - dividend is +ve
            - divisor is +ve
            - Both are -ve.
        */
      int sign = (divisor < 0)^(dividend <0) ? -1 : 1;
      if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
      int count = 0;
      dividend = Math.abs(dividend);
      divisor = Math.abs(divisor);
      while(dividend-divisor >= 0){
          dividend -= divisor;
          count++;
      }
      return sign == -1 ?-count : count;
    }

    /* Problem with the naive approach is that its too slow(linear). For e.g. 10 divide 3, every iteration we are
       taking 3 away from dividend resulting 7, 4, 1... So we took 3 away 3 times.
       In the improved approach what we would try to do is try to take the bigger chunk(or multiple) out of the divisor
       from the dividend in 1 single operation.
        Since we can't use regular operators multiply/division/modulo, next thing that should come to mind is the bitwise
        operator, namely left shift and right shift operator. Okay, so what are the different multiples of divisor we can
        take away from the dividend.
        We would try to take as big a chunk of (dividend * 2^n) out from the dividend. In above example,
        10/3: we would try to take 3*2^1=6 out from 10, since we can't do that for n=2(which leads to 12 which is bigger than the dividend)

        10-6  = 4

        dividend = 4
        Repeat the above steps
        take max chunk of 3*2^n times away from the remaining dividend, 3*2^0 = 3.
        4-3*1 = 1

        Dividend =1
        Now since dividend < divisor, we can't take anything away from it.
        Total Quotient = 2+1 = 3
        Using a multiple of 2 to take bigger fraction out, we are trying to get in the logrithmic time complexity in terms
        of input size.
    */
    public int divide2(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int result= 0;

        while(dividend - divisor >0){
            int pow = 0; // 2^0 = 1
            while(dividend - (divisor<<(1+pow))> 0){
                pow++;
            }
            result += 1<<pow;
            dividend -= (divisor<<pow);
        }
        return (dividend ==0) == (divisor==0) ? result: -result;
    }


    // https://leetcode.com/problems/divide-two-integers/discuss/13467/Very-detailed-step-by-step-explanation-(Java-solution)
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int sign = (dividend < 0) ^ (divisor<0) ? -1 : 1;
        long absDvd = Math.abs((long)dividend);
        long absDvs = Math.abs((long)divisor);
        long result = 0;
        while(absDvd >= absDvs){
                long tmp = absDvs, count = 1;
                while(tmp <= absDvd){
                    tmp <<= 1;
                    count <<= 1;
                }
            result += (count>>1);
            absDvd -= (tmp>>1);
        }
        return (sign == 1) ? (int)result : (int)-result;
    }


}
