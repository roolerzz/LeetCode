package com.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

public class Test2 {

  public static void main(String[] args) {
    //
//      char zero = '0';
//      char one = '1';
//
//      System.out.print("Zero Character: ");
//      System.out.println(zero);
//      System.out.print("One Character: ");
//      System.out.println(one);
//      System.out.print("Zero Integer: ");
//      System.out.println((int)zero);
//      System.out.print("One Integer: ");
//      System.out.println((int)one);
//
//      int intZero = 0;
//      int intOne = 1;
//
//      System.out.print("Zero Integer: ");
//      System.out.println(intZero);
//      System.out.print("One Integer: ");
//      System.out.println(intOne);
//      System.out.print("Zero Character: ");
//      System.out.println((char)zero);
//      System.out.print("One Character: ");
//      System.out.println((char)one);

      int[][] arr = {{0,5},{3,7},{1,2}};
//      Arrays.sort(arr, (a1, a2) -> a1[0]-a2[0]);
//      Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        for(int[] inArr : arr) {
            System.out.print(inArr[0] + " : ");
            System.out.println(inArr[1]);
        }

//      for(int i  = 0 ; i < arr.length; i++ ) {
//          System.out.println();
//          for(int j = 0 ; j < arr[0].length; j++) {
//              System.out.print(arr[i][j] + " -> ");
//          }
//      }


  }
}
