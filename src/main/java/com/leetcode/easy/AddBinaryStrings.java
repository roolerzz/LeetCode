package com.leetcode.easy;

import java.math.BigInteger;

// https://leetcode.com/problems/add-binary/
public class AddBinaryStrings {

    public String addBinaryXORBigIntegers(String big, String small) {
        BigInteger bigInt = new BigInteger(big,2);
        BigInteger smallInt = new BigInteger(small,2);
        BigInteger result, carry;
        BigInteger zero = new BigInteger("0",2);
        while(smallInt.compareTo(zero) != 0) {
            result = bigInt.xor(smallInt);
            carry = bigInt.and(smallInt).shiftLeft(1);
            bigInt = result;
            smallInt = carry;
        }
        return bigInt.toString(2);
    }
    public String addBinaryXOR(String big, String small) {
        int bigInt = Integer.parseInt(big,2);
        int smallInt = Integer.parseInt(small,2);
        int result = 0, carry =0;
        int x = bigInt;
        int y = smallInt;

        while(y != 0) {
            result = x ^ y;
            carry = (x & y) << 1;
            x = result;
            y = carry;
        }
        return Integer.toBinaryString(x);
    }

    public String addBinary(String big, String small) {
        int endB = big.length()-1;
        int endS = small.length()-1;
        if(endB < endS) return addBinary(small,big); // If big isn't really big yet, call the main method again to
        // make sure first agrument is the bigger one.

        int sum = 0, carry = 0;
        StringBuilder sb = new StringBuilder();
        while(endB >= 0){
            sum = carry;
            if(endS >=0) {
                if(small.charAt(endS) == '1')
                    sum += 1;
                //int c2 = small.charAt(endS)-'0';
                //sum += c2;
                endS--;
            }
            if(big.charAt(endB) =='1')
                //int c1 = big.charAt(endB)-'0';
                //sum +=c1;
                sum +=1;
            endB--;

            carry = sum /2;
            sum = sum %2;
            sb.insert(0,sum);
        }
        if(carry !=0) {
            sb.insert(0,'1');
        }
        return sb.toString();
    }

}
