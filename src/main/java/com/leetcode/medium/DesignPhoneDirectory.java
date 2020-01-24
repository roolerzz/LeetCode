package com.leetcode.medium;

import java.util.*;

public class DesignPhoneDirectory {

    /*  APPROACH 1:
        Idea is We have to optimize 3 operations:
        Get: Return the first/any available number.
        Check: Check if a number is free or not.
        release: release a number.
        - Well we can use a linkedlist to optimize for Get and release, say get means, return head(remove and update to new head) and release means add to the front. Both ops can be done in O(1) time.
        - Problem with linkedList is basically with the CHeck operation where we need to check if a number is free or not.
        SOln:
        Thats why we can think of a hybrid soln, where in you sould simulate a linked list using array values.
        val[idx] represent, what is the next avalable number after idx(basically connects, idx and val[idx]).
        - Once a number is allocated we can mark it with -1. That way our check operation also becomes O(1).
    */

        int[] next;
        int pos;

        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public void DesignPhoneDirectory1(int maxNumbers) {
            next = new int[maxNumbers];
            for(int i=0; i < maxNumbers; i++){
                next[i] = (i+1)%maxNumbers;
            }
            pos = 0;
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get1() {
            if(next[pos] == -1) return -1;
            int res = pos;
            pos = next[pos];
            next[res] = -1;
            return res;
        }

        /** Check if a number is available or not. */
        public boolean check1(int number) {
            if(number <0 || number >= next.length) return false;
            return next[number] !=-1;
        }

        /** Recycle or release a number. */
        public void release1(int number) {
            if(number < 0 || number >= next.length || next[number] != -1) return;
            next[number] = pos;
            pos = number;
        }


        /*  APPROACH 2:
            Use a queue and a set. Set for faster Check operation. Queue for faster allocation.
            Get: Return the first/any available number.
            Check: Check if a number is free or not.
            release: release a number.
            - Get would be a getFirstNumber from the Queue(O(1))
            - Check : Check if the number is preset in the usedHashSet.O(1)
            - Release:(O(1))
                - remove from the hashset.
                - add back to the queue
        */
        Queue<Integer> avail;
        int max;
        // Set<Integer> usedValues;

        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public DesignPhoneDirectory(int maxNumbers) {
            max = maxNumbers;
            avail = new LinkedList<>();
            for(int i=0; i < maxNumbers; i++){
                avail.offer(i);
            }
            used = new HashSet<>();
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get() {
            if(avail.isEmpty()) return -1;
            int res = avail.poll();
            used.add(res);
            return res;
        }

        /** Check if a number is available or not. */
        public boolean check(int number) {
            if(number<0 || number >=max) return false;
            return !used.contains(number);
        }

        /** Recycle or release a number. */
        public void release(int number) {
            if(used.remove(number))
                avail.offer(number);
        }



        /* Approach 3:*/
        Set<Integer> free, used;

        /** Initialize your data structure here
         @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
        public void DesignPhoneDirectory3(int maxNumbers) {
            free = new HashSet<>();
            used = new HashSet<>();
            for(int i=0; i< maxNumbers; i++){
                free.add(i);
            }
        }

        /** Provide a number which is not assigned to anyone.
         @return - Return an available number. Return -1 if none is available. */
        public int get3() {
            if(free.isEmpty()) return -1;
            Iterator<Integer> iter = free.iterator();
            int num = -1;
            if(iter.hasNext())
                num = iter.next();
            free.remove(num);
            used.add(num);
            return num;
        }

        /** Check if a number is available or not. */
        public boolean check3(int number) {
            return free.contains(number);
        }

        /** Recycle or release a number. */
        public void release3(int number) {
            used.remove(number);
            free.add(number);
        }


/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */

}
