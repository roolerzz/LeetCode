package com.leetcode.easy;

import java.util.*;

public class Test2 {
    private static void printArray(int[][] intervals){
        for(int[] intr : intervals){
            System.out.println(intr[0] + " : "  +intr[1]);
        }
    }


  public static void main(String[] args) {
        int id = 0;
      System.out.println(id++);
        System.out.println(id++);
  }

  private int[] twosum(int[] arr){
//      int[] arr = new int[10];
      Map<Integer, Integer> seen = new HashMap<>();
      seen.put(1,1);
      for(int i=0; i < 10; i++){
//          return new int[2]{i, seen.get(arr [i])};
      }
      return new int[]{};
  }
}
