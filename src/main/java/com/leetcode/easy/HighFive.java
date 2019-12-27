package com.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/high-five/
public class HighFive {
    /*
    - Sort the input array using student id as the first sort key and student score as the 2nd sort key.
    - Access the last element to know the number of student. noOfStudents = items[items.length-1][0]
    - res = new int[noOfStudents][2];// Initialize the internal array.
    - Start i from index 0. Student id= items[i][0];
        - Start j=i;
        - increment j until items[j][0] = items[j+1][0] while j+1 < N
        - int sum = 0;
        -  for(int k=j; k> j-5;k--){
            sum += items[k][1];
            }
        - res[studentID-1][0]=studentId;
        - res[studentID-1][1]=sum/5;
        }

    -
    */

        public int[][] highFive(int[][] items) {
            if(items.length <5) return new int[][]{};
            Arrays.sort(items,new Comparator<int[]>(){
                @Override
                public int compare(int[] item1, int[] item2){
                    if(item1[0] != item2[0]) return item1[0]-item2[0];
                    return item1[1]-item2[1];
                }
            });
            for(int i=0; i < items.length; i++){
                System.out.println("Student ID: " + items[i][0] + " Score: "+items[i][1]);
            }
            int noOfStud = items[items.length-1][0];
            int[][] res = new int[noOfStud][2];
            int i=0;
            while(i<items.length){
                int studentID = items[i][0];
                int j=i;
                while(j+1 < items.length && items[j][0] == items[j+1][0]) j++;
                int sum =0;
                int count=0;
                for(int k=j; k >j-5 && k >= 0; k--){
                    System.out.println("Adding for studentID : " + studentID+ " the number for avg: "+ items[k][1]);
                    sum += items[k][1];
                    if(items[k][0] == studentID) count++;
                }
                // if(count == 5){
                res[studentID-1][0] = studentID;
                res[studentID-1][1] = sum/5;
                // }
                i = j+1;
            }
            return res;
        }

}
