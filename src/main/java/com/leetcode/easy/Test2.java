package com.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test2 {
    private static void printArray(int[][] intervals){
        for(int[] intr : intervals){
            System.out.println(intr[0] + " : "  +intr[1]);
        }
    }


  public static void main(String[] args) {

//        int[][] intervals = new int[][]{{10,15},{1,4},{9,15},{2,5}};
//        printArray(intervals);
//        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
//        printArray(intervals);

//        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));



      //
      char zero = '0';
      char one = '1';
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
      int intZero = 0;
      int intOne = 1;

      System.out.print("Zero Integer: ");
      System.out.println(intZero);
      System.out.print("One Integer: ");
      System.out.println(intOne);
      System.out.print("Zero Integer casted to character: ");
      System.out.println((char)intZero);
      System.out.print("One Integer casted to character: ");
      System.out.println((char)intOne);
      System.out.print("Zero Character: ");
      System.out.println(zero);
      System.out.print("One Character: ");
      System.out.println(one);
      System.out.print("Zero Character casted to Int: ");
      System.out.println((int)zero);
      System.out.print("One Character casted to Integer: ");
      System.out.println((int)one);
//      int[][] arr = {{0,5},{3,7},{1,2}};
//      int[][] arr = {{}};
//      int[] arr = new int[2];
//      int[] arr = {};
//      int[] arr2 = new int[0];
//      int[] nums=null;
//      System.out.println(Arrays.toString(arr2));
//      PriorityQueue<int[]> minPQ = new PriorityQueue<>();
//      int[][] res = new int[3][3];
//      minPQ.toArray(res);
//      int a[] = new int[]{-1,-1};
//      int b[] = new int[]{3,3};
//
//      int x = Math.abs(a[1]-0);


      //int distA = (Math.sqrt(Math.pow(Math.abs(a[1]-0), 2) + Math.pow(Math.abs(a[0]-0),2)));
      //int distB = (Math.sqrt(Math.pow(Math.abs(b[1]-0), 2) + Math.pow(Math.abs(b[0]-0),2)));
//      Arrays.sort(arr, (a1, a2) -> a1[0]-a2[0]);
//      Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
//        for(int[] inArr : arr) {
//            System.out.print(inArr[0] + " : ");
//            System.out.println(inArr[1]);
//        }

//      for(int i  = 0 ; i < arr.length; i++ ) {
//          System.out.println();
//          for(int j = 0 ; j < arr[0].length; j++) {
//              System.out.print(arr[i][j] + " -> ");
//          }
//      }


  }
}
