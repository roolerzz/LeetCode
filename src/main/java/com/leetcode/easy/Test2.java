package com.leetcode.easy;

import java.util.*;

public class Test2 {
    private static void printArray(int[][] intervals){
        for(int[] intr : intervals){
            System.out.println(intr[0] + " : "  +intr[1]);
        }
    }



    private static StringBuilder trimTrailingNulls(StringBuilder sb){
        /* "[1,2,3,null,null,4,5,null,null,"*/
        //"[1,null,null," 9 length 13(0-12)
        int lastIdx = sb.lastIndexOf("null,");
        while(lastIdx == sb.length()-5)
        {
            sb.delete(lastIdx, sb.length());
            lastIdx = sb.lastIndexOf("null,");
        }
        return sb;
    }

  public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("[");
        sb.append("1,null,null,");
        sb = trimTrailingNulls(sb);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        System.out.println(sb.toString());
//        System.out.println("8".compareTo("3"));
//      System.out.println("3".compareTo("8"));
//      System.out.println("3".compareTo("3"));
        //        int id = 0;
//      System.out.println(id++);
//        System.out.println(id++);
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
