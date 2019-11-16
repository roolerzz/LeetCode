package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/path-sum-iii/
public class PathSumIII {

    private class Node {
        int data;
        Node left, right;
    }

    public List<List<Integer>> findAllPaths(Node root, int sum){
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> buffer = new ArrayList<>();
        findAllPathsRec(root, result, buffer, sum, 0);
        return result;
    }

    private void findAllPathsRec(Node curr, List<List<Integer>> result, ArrayList<Integer> buffer, int sum, int level){
        if(curr == null) return;
        int tmp = sum;
        buffer.add(curr.data);
        for(int i = level; i > -1 ; i++) {
            tmp = buffer.get(i);
            if(tmp == 0) addToResult(result, buffer, i, level);
        }

        ArrayList<Integer> copy1 = (ArrayList<Integer>)buffer.clone();
        ArrayList<Integer> copy2 = (ArrayList<Integer>)buffer.clone();
        findAllPathsRec(curr.left, result, copy1, sum, level+1);
        findAllPathsRec(curr.right, result, copy2, sum, level+1);
    }

    private void addToResult(List<List<Integer>> result, ArrayList<Integer> buffer, int start, int end) {
        List<Integer> temp = new ArrayList<>();
        for(int i = start; i  <= end; i++) {
            temp.add(buffer.get(i));
        }
        result.add(temp);
    }

  public static void main(String[] args) {
    //
      System.out.print("Main Ran.");
  }
}
