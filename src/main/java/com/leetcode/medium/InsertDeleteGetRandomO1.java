package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/insert-delete-getrandom-o1
public class InsertDeleteGetRandomO1 {
    /*
        Idea is to use a HashMap<Integer,Integer> that stores the key against the index on the list where the key exists.
        Insert: Add the element to the end of the list and add the entry into the hashmap as key:idx. O(1) due to O(1) insertion into hashmap and O(1) insertion into end of the list.
        Remove: Find the index of the element to be deleted. Swap the current element with the last element in the list updating the hashmap entries as well. remove the last element and the hashmap entry. O(1) on average, as swap of 2 elements is constant time op also, remove from the end of the list is O(1).
        getRandom(): Find a random number b/w 0-list.length(). Return the key at that Index. Accessing an element by index is O(1).

    */

    Map<Integer, Integer> keyIdxMap;
    List<Integer> keys;
    Random generator;


    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        keyIdxMap = new HashMap<>();
        keys = new ArrayList<>();
        generator = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(keyIdxMap.containsKey(val)) return false;
        keys.add(val);
        keyIdxMap.put(val, keys.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!keyIdxMap.containsKey(val)) return false;
        int lastElement = keys.get(keys.size()-1);
        int idx = keyIdxMap.get(val);
        keys.set(idx, lastElement);
        keyIdxMap.put(lastElement, idx);
        keys.remove(keys.size()-1);
        keyIdxMap.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return keys.get(generator.nextInt(keys.size()));
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
