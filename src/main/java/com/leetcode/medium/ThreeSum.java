package com.leetcode.medium;


import java.util.*;

// https://leetcode.com/problems/3sum/
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int start = i+1, end = nums.length-1, remainingSum = -(nums[i]);
            while(start < end){
                    int sum = nums[start] + nums[end];
                    if(sum == remainingSum){
                        res.add(Arrays.asList(nums[i], nums[start],nums[end]));
                        start++;
                        end--;
                        while(start < end && nums[start] == nums[start-1]) start++;
                        while(start < end && nums[end] == nums[end+1]) end--;
                    }
                    else if (sum < remainingSum)
                        start++;
                    else end--;
            }
        }
        return res;
    }


    public static List<List<Integer>> threeSumApproach2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            for(int j = i+1; j < nums.length-1; j++){
                if(j > i+1 && nums[j-1] == nums[j])
                   continue;
                int key = -(nums[i] + nums[j]);
                int keyIdx = Arrays.binarySearch(nums, j+1, nums.length, key);
                if(keyIdx >=0){
                    res.add(Arrays.asList(nums[i], nums[j], nums[keyIdx]));
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSumBruteForce(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
//        List<List<Integer>> res = new ArrayList<>();
         if(nums == null || nums.length < 3)
             return new ArrayList<>(res);
        for(int i = 0 ; i < nums.length-2; i++){
            for (int j = i+1 ; j < nums.length-1; j++){
                for (int k = j+1; k < nums.length; k++){
                    if(nums[i] + nums[j] + nums[k] == 0){
                        List<Integer> tempList = Arrays.asList(nums[i],nums[j], nums[k]);
                        Collections.sort(tempList);
                        res.add(tempList);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
//        List<List<Integer>> uniqueLists = findThreeSum();
//        System.out.println(uniqueLists.size());
////        Iterator itr = uniqueLists.iterator();
//        for(List<Integer> list : uniqueLists){
////            System.out.println(list.toString());
//        }
//
//        List<int[]> uniqueListsWithArray = findThreeSumWithArrays();
//        System.out.println(uniqueListsWithArray.size());
////        Iterator itr = uniqueLists.iterator();
//        for(int[] arr: uniqueListsWithArray){
//            System.out.println(Arrays.toString(arr));
//        }

        List<List<Integer>> res = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for(List<Integer> list : res) {
            System.out.println(list.toString());
        }

    }

    private static List<List<Integer>> findThreeSum(){
        Set<List<Integer>> res = new HashSet<>();
        Set<int[]> res2 = new HashSet<>();
        List<Integer> one = Arrays.asList(1,2,3);
        Collections.sort(one);
        res.add(one);
        List<Integer> two = Arrays.asList(3,2,1);
        Collections.sort(two);
        res.add(two);
        return new ArrayList<>(res);
    }
    private static List<int[]> findThreeSumWithArrays(){
        Set<int[]> res = new HashSet<>();
        int[] one = new int[]{1,2,3};
        Arrays.sort(one);
        res.add(one);
        int[] two = new int[]{3,2,1};
        Arrays.sort(two);
        res.add(two);
        return new ArrayList<>(res);
    }


}
