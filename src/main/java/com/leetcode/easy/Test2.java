package com.leetcode.easy;

import org.w3c.dom.Node;

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
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        pq.offer(n1);
        pq.offer(n2);
        pq.offer(n3);
        for(Node n : pq) {
            System.out.print("Node Val is :" + n.val);
        }
        System.out.println();
        System.out.println("Updating node 1 value to 4");
        n1.val = 4;
        for(Node n : pq) {
            System.out.print("Node Val is :" + n.val);
        }
        System.out.println();
    }

    private static class Node {
        int val;
        Node(int val){
            this.val = val;
        }
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
