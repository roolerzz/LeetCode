package com.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;

// https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
public class MinimumDominoRotationsForEqualRow {
        /*

            - Validations?
                - What if arrays are of un-equal length. -1
                - both null/empty? return -1
            - If all the values in either array are same, return 0. No rotation required.
            - Scan through both the array to find the number which can be made equal across both the arrays.
            - We are looking for a number whose count(sum of occurences) across both the arrays, equals to length of either of the array. If we can't found the count, return -1.
        - Finding the count can be done by Hashmap or using an array(if numbers were in some range). 2 Hashmaps?
        - What is there are more than 1 number(At max there can be 2 numbers whose sum equal to the lenght of the array). In that case rotations= len/2?
    - Not always possible. In the list of potential candidate, take both.
        - Start matching the list with one of the arrays(preferrably the 1 which has more count). Starting from index0.
        - If arr1[idx] != number && arr2[idx] != number return -1, otherwise rotation++;

    - For that number, Len of the array-Max(count of that number in arr1, count of that number in arr2)
        */
        public int minDominoRotations3(int[] A, int[] B) {
            if(A != null && B != null && A.length == B.length){
                int[] ct1 = new int[6];
                int[] ct2 = new int[6];
                for(int i=0; i < A.length ;i++){
                    ct1[A[i]-1]++;
                    ct2[B[i]-1]++;
                }
                int[] potential = new int[]{-1,-1};
                int counter = 0;
                boolean valueFound = false;
                for(int i=0; i < 6; i++){
                    if(ct1[i]+ct2[i] >= A.length)
                    {
                        potential[counter++]=i+1;
                        valueFound = true;
                    }
                }

                if(valueFound){
                    for(int val: potential){
                        // validate if possible.
                        // If yes, return len-Max(ct1[val-1], ct2[val-1])
                        int i=0;
                        for(; i < A.length ; i++){
                            if(A[i] != val && B[i] != val)
                                break;
                        }
                        if(i == A.length){
                            // Means match found. Rotations required
                            return A.length-Math.max(ct1[val-1],ct2[val-1]);
                        }
                    }
                }


            }
            return -1;
        }

        public int minDominoRotations4(int[] A, int[] B) {
            int[] numberOfA = new int[7];
            int[] numberOfB = new int[7];
            int[] same = new int[7];
            for(int i = 0; i < A.length; i++) {
                numberOfA[A[i]]++;
                numberOfB[B[i]]++;
                if(A[i] == B[i]) same[A[i]]++;
            }

            for(int i = 1; i <= 6; i++) {
                if(numberOfA[i] + numberOfB[i] - same[i] == A.length) {
                    return Math.min(numberOfA[i] - same[i], numberOfB[i] - same[i]);
                }
            }
            return -1;
        }

        public int minDominoRotations2(int[] A, int[] B){
            int rotations = check(A[0], A, B, A.length);
            if(rotations != -1 || A[0] == B[0]) return rotations;
            return check(B[0], A, B, A.length);
        }


        private int check(int num, int[] A, int[] B, int n){
            int rotationsA =0, rotationsB = 0;
            for(int i=0; i < n ; i++){
                if(A[i] != num && B[i]!= num) return -1;
                else if(A[i] != num) rotationsA++;
                else if(B[i] != num) rotationsB++;
            }
            return Math.min(rotationsA, rotationsB);
        }


        // Approach 3: LC.
        public int minDominoRotations(int[] A, int[] B) {
            HashSet<Integer> s = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));
            int[] countA = new int[7], countB = new int[7];
            for (int i = 0; i < A.length; ++i) {
                s.retainAll(new HashSet<>(Arrays.asList(A[i], B[i])));
                countA[A[i]]++;
                countB[B[i]]++;
            }
            for (int i : s)
                return A.length - Math.max(countA[i], countB[i]);
            return -1;
        }


}
