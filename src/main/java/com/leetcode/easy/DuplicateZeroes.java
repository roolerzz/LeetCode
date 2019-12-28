package com.leetcode.easy;

// https://leetcode.com/problems/duplicate-zeros/
public class DuplicateZeroes {
        /*
            - Calculate the length of the array.
            - Iterate from index zero, find the index which would be the last element in the resulting array. Counting non-zero element as 1, and zero as 2. When your count becomes the length of the array, stop. This would be your temp.
            - End = arr.length-1;
            - while(temp >=0){
                arr[end--] = arr[temp];
                if(arr[temp]==0)
                    arr[end--]=arr[temp];
                temp--;
            }
            [1,0,2,3,0,4,5,0]
            len=8 count =0  temp=5 end=7 [1,0,0,2,3,0,0,4]
            int temp=-1;
            while(count<len){
            arr[temp] !=0 count +=1;
            else count+= 2;
            temp++;
            }
            end = 7
        - I/P fits into memory.
        - Duplicates +ve, -ves doesn't matter.
        - what if array is null/empty, 1 sized etc.
        - [0]
        - [0,0,0,4] => [0,0,0,0]
        */
        public void duplicateZeros(int[] arr) {
            if(arr == null || arr.length <1) return;
            int end = arr.length-1;
            int count = 0;
            int last = -1;
            while(count < arr.length){
                count += 1;
                if(arr[last+1] == 0)
                    count += 1;
                last++;
            }
            // This can only happen if the last element was a zero. Note that there isn't enough space for 2Zeroes to be placed in the final array. To deal with that,we would decrement the last by 1(So that in the generic copying we don't consider the . last array) and manually copying one of the zeros, and let the rest of the program run as usual.
            if(count > arr.length)
            {
                last--;
                arr[end--] = 0;
            }
            while(last >=0) {
                arr[end--] = arr[last];
                if(arr[last] == 0 && end >=0)
                    arr[end--] = arr[last];
                last--;
            }
        }

}
