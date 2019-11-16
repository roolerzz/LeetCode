package com.leetcode.easy;

//https://www.geeksforgeeks.org/convert-decimal-fraction-binary-number/
public class DecimalFractionToBinary {

    public static String decimalFractionToBinary(String num) {
        int intPart = Integer.parseInt(num.substring(0, num.indexOf('.')));
        double fractionPart = Double.parseDouble(num.substring(num.indexOf('.')));
        String int_part = "";
        StringBuilder fraction_part = new StringBuilder();

        // Integer part.
        while(intPart > 0){
            int rem = intPart%2;
            int_part = rem + int_part;
            intPart >>= 1; // Divides the number by 2.
        }

        // Fraction part.
        while(fractionPart != 0.0) {
            System.out.println("Fraction Part is : " + fractionPart);
            fractionPart *= 2 ;
            if(fractionPart>= 1.0) {
                fraction_part.append(1);
                fractionPart -= 1 ;
            }
            else fraction_part.append(0);

        }

        return int_part + "." + fraction_part;
    }

  public static void main(String[] args) {
    //
      System.out.println(decimalFractionToBinary("3.75"));
      System.out.println(decimalFractionToBinary("3.75",1));

  }

    public static String decimalFractionToBinary(String num, int precision) {
        int intPart = Integer.parseInt(num.substring(0, num.indexOf('.')));
        double fractionPart = Double.parseDouble(num.substring(num.indexOf('.')));
        String int_part = "";
        StringBuilder fraction_part = new StringBuilder();

        // Integer part.
        while(intPart > 0){
            int rem = intPart%2;
            int_part = rem + int_part;
            intPart >>= 1; // Divides the number by 2.
        }

        // Fraction part.
        while(fractionPart != 0.0 && precision > 0) {
            precision--;
            System.out.println("Fraction Part is : " + fractionPart);
            fractionPart *= 2 ;
            if(fractionPart>= 1.0) {
                fraction_part.append(1);
                fractionPart -= 1 ;
            }
            else fraction_part.append(0);

        }

        return int_part + "." + fraction_part;
    }
}
