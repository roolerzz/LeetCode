package com.leetcode.hard;

import java.util.*;
// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed
// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/discuss/323134/JAVA-SOLUTION-List-MaplessInteger-LinkedHashSetlessIntegergreatergreater
public class InsertDeleteGetRandomO1DuplicatesAllowed {
    /*
        Insert O(1) : Hashtable, ArrayList, LinkedList. Duplicate?
        Remove: O(1) : HashTable, ArrayList if removal at end, Doubly LinkedList if know the node to be deleted.
        getRandom: O(1): ArrayList where we can select an index and should be able to access the key on that index.


        1 Idea was to keep a List numbers at each index in the list, and Key to Index mapping in the hashmap. Not very helpful in getRandom. Or we could also just keep a Pair Ds, with Value and count in it. Would help in swapping(if needed in remove). Probability is not linearly related to number of same values collection contains.
        - HashMap: Key to List of index where that element is present. Insertion would be insertion at the end of the arraylist, and list of hashmap. Removal at the end.

        Idea 2: Keep the list of indexes against each key in the hashmap.
        Map<Key, List<Indexes>> map;
        List<Key> keys;

        insert:
            - insert at the end of the list. O(1)
            - add the index into the list corresponding to the key in the hashmap. O(1) + O(1) = O(1)
        remove
            - For the key, find the last of the indexes.O(1) + O(1)
            - Swap last element'(last index) with the given key's last index. O(1)+O(1)+O(1)
            - remove the last element from the list.
        Get Random:
            - O(1) also the probability is linearly related.
    */

        Map<Integer, Set<Integer>> map;
        List<Integer> list;
        Random generator;

        /** Initialize your data structure here. */
        public InsertDeleteGetRandomO1DuplicatesAllowed() {
            map = new HashMap<>();
            list = new ArrayList<>();
            generator = new Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            int idx = list.size();
            map.putIfAbsent(val, new HashSet<Integer>());
            map.get(val).add(idx);
            list.add(val);
            return true;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
       /*
        1, 1, 3, 3, 3, 4, 4, 1, 1
        HashMap
        1: 0, 7, 1
        4: 5, 6
        3: 2, 3, 4

        1I, 1I, 2I, 2I, 2I, 1R, 1R, 2R, 1I, 2R, GetRandom*10
        1, 2, 2, 2,
        1:0
        2:2,3,1

        4I, 3I, 4I, 2I, 4I, 4R, 3R, 4R, 4R
        4: 3, 4, 2, 4
        4: 0, 2, 4
        3: 1
        2: 3


    */

        public boolean remove(int val) {
            if(!map.containsKey(val)) return false;
            int idx = map.get(val).iterator().next();
            map.get(val).remove(idx);
            if(idx < list.size()-1){
                int lastVal = list.get(list.size()-1);
                map.get(lastVal).remove(list.size()-1);
                map.get(lastVal).add(idx);
                list.set(idx, lastVal);
            }
            if(map.get(val).isEmpty())
                map.remove(val);
            list.remove(list.size()-1);
            return true;
        }


        /** Get a random element from the collection. */
        public int getRandom() {
            int randomNum = generator.nextInt(list.size());
            //System.out.println("List Size is: " + list.size() + "and element accessed is : " + randomNum);
            return list.get(randomNum);
        }

    /**
     * Your RandomizedCollection object will be instantiated and called as such:
     * RandomizedCollection obj = new RandomizedCollection();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
    public static void main(String[] args) {
        //
        InsertDeleteGetRandomO1DuplicatesAllowed d1 = new InsertDeleteGetRandomO1DuplicatesAllowed();
        System.out.println(d1.insert(0));
        System.out.println(d1.remove(0));
        System.out.println(d1.insert(-1));
        System.out.println(d1.remove(0));
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());
        System.out.println(d1.getRandom());


    }
}
