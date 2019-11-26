package com.leetcode.easy;

// https://leetcode.com/problems/add-strings
public class AddStrings {

    // My Verbose solution.
    public String addStringsVerbose(String num1, String num2) {
        if((num1 == null && num2 == null) || (num1.length() == 0 && num2.length() == 0)) return "";
        if(num1.length()< num2.length()) return addStrings(num2,num1);

        int idxB = num1.length()-1, idxS = num2.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0, sum = 0;
        while(idxB >=0) {
            sum = carry;
            if(idxS >=0){
                sum += num2.charAt(idxS)-'0';
                //Integer.parseInt(String.valueOf());
                idxS--;
            }
            sum += num1.charAt(idxB)-'0';
            //Integer.parseInt(String.valueOf(num1.charAt(idxB)));
            idxB--;
            carry = sum /10;
            sum %=10;
            sb.append(sum);
        }
        if(carry != 0)
            sb.append("1");
        return sb.reverse().toString();
    }

    // Someone's concise solution.
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();

        while(i >= 0 || j >= 0 || carry != 0) {
            if(i >= 0) carry += num1.charAt(i--) - '0';
            if(j >= 0) carry += num2.charAt(j--) - '0';
            sb.append(carry % 10);
            carry /= 10;
        }

        return sb.reverse().toString();
    }
}
