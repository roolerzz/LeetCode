package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/fraction-to-recurring-decimal/
public class FractionToRecurringDecimal {
        public String fractionToDecimal(int numerator, int denominator) {
            if(numerator == 0)
                return "0";
            StringBuilder sb = new StringBuilder();
            if(numerator < 0 ^ denominator <0)
                sb.append("-");
            long dividend = Math.abs(Long.valueOf(numerator));
            long divisor = Math.abs(Long.valueOf(denominator));
            sb.append(dividend/divisor);
            long remainder = dividend%divisor;
            if(remainder == 0){
                return sb.toString();
            }

            sb.append(".");
            Map<Long, Integer> map = new HashMap<>();
            while(remainder != 0){
                if(map.containsKey(remainder)){
                    sb.insert(map.get(remainder),"(");
                    sb.append(")");
                    break;
                }
                map.put(remainder, sb.length());
                remainder *= 10;
                sb.append(String.valueOf(remainder/divisor));
                remainder %= divisor;
            }
            return sb.toString();
        }

/*
- Ideas.
Edge cases:
- Numerator/Denominator is 0.
- Numerator = MIN_VALUE, Denominator = -1.
- Either Numerator is -ve or Denom, or both.

Breaking into parts:
- How to deal with -ve sign.
- When and where to start the decimal?
    - Well if the numerator is smaller, you divided num/denom(which returns 0) and add decimal.
    - If num> den, quo is generally greater than 1. Then repeat.
- After the decimal, when to add 0 when to not.
    - Once the decimal starts, you append 0 to the remainder once, and do the divide. if the remainder(after multiplication) was still smaller, you add 0 to the quotient(sb) and continue;
- How to deal with the recurring part of it.
    - If you think about it, the recurring part only comes again if you get the same reminder that you processed before.
    - So if we can keep track of all the reminders and their positions, we can include elements from that position to the current position, and enclose them b/w brackets().

*/


}
