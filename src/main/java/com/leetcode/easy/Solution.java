package com.leetcode.easy;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {

    String[] arr = {"one","two","three","four"};
   
    if(ifCombines(arr,"oneone"))
    System.out.println("oneone");
    if(ifCombines(arr,"oneonc"))
    System.out.println("oneonc");
    if(ifCombines(arr,""))
    System.out.println("");
    
    if(ifCombines(arr,"fouroneone"))
    System.out.println("fouroneone");
    
  }

private static boolean ifCombines(String[] arr, String target){
    return ifCombinesRecursively(arr,target);
}

private static boolean ifCombinesRecursively(String[] arr, String target){
      
  if(target.equals("")) return true;

   boolean res = false;
  for(int i = 0 ; i < arr.length ; i++){
    if(target.startsWith(arr[i]))
       res = ifCombinesRecursively(arr, target.substring(arr[i].length()));
    if(res)
       break;
}
  
  return res; 
      
 }
  
}
/* 
Your previous Plain Text content is preserved below:

Given a set of strings, e.g. {"on", “one”, “cat”, “two”, “four”}, and a target string, e.g. “fouroneone”, return true if the target string is composed (by concatenation) of elements from the set.   

// recrusiveBacktrack(target, arr, index){
  if target empty return true;
  // oneone
  
  For each element in array, 
    if target start with element, 
      recursivielyCall(target-elmenet,arr,index+1)
      
      recursivelyCall(target, arr, index+1);



}
“fouroneone” -> true
“onecat” -> true
“fouron” -> false
“twone” -> false
// fouroneone
// can take 1 element more than once.
// if none elements matches stop. 
// if string is empty, return true.
// n elements in array, each entry size m(target string). / n*m total compares.
// Recusive backtrack solution.


 */