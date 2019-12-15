package com.companyspecific.Stripe;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HashMapComparator {

    /*
     * Four part question:
     *  - Implement a system that looks up the min value with the given key in a list of hashmaps. Return the hashmap
     *  - Given a sort order (desc/ asc), find the max/min value with the given key.
     *  - implement a class Comparator, which has a method compare. You can init this class by sort order(desc/ asc), and you can use compare method to compare two hashmaps with the given order.
     *  - Given a list of compare orders and keys (directions), find the hashmap that matches the directions. If the first direction gives equal, then go to the second. And so on.
     * */


    /*
    *  https://www.glassdoor.com/Interview/Given-a-list-of-compare-orders-and-keys-directions-find-the-hashmap-that-matches-the-directions-If-the-first-direction-QTN_2960569.htm
    */
    public Map<String, Integer> findMinValForKey(List<Map<String, Integer>> listOfMaps, String key){
        Map<String, Integer> res = null;
        int minVal = Integer.MAX_VALUE;
        for(Map<String,Integer> givenMap : listOfMaps){
            if(!givenMap.containsKey(key)) continue;
            if(givenMap.get(key) < minVal) {
                minVal = givenMap.get(key);
                res = givenMap;
            }
        }
        return res;
    }

    public Map<String, Integer> findValForKeyWithSortOrder(List<Map<String, Integer>> listOfMaps, String key, int sortOrder){
        Map<String, Integer> res = null;
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for(Map<String,Integer> givenMap : listOfMaps){
            if(!givenMap.containsKey(key)) continue;
            switch (sortOrder) {
                case -1:
                    if(givenMap.get(key) < minVal){
                        minVal = givenMap.get(key);
                        res = givenMap;
                    }
                    break;
                case 1:
                    if(givenMap.get(key) > maxVal){
                        maxVal = givenMap.get(key);
                        res = givenMap;
                    }
                    break;
            }

        }
        return res;
    }

    private class CustomComparator implements Comparator<Map<String, Integer>>{

        int sortOrder = 0;

        CustomComparator(int sortOrder){
            if(sortOrder != 1 &&sortOrder != -1) throw new IllegalArgumentException();
            this.sortOrder = sortOrder;
        }

        @Override
        public int compare(Map<String, Integer> obj1, Map<String, Integer> obj2){
            // What do you mean by comparing two hashmaps with given order.
            return -1;
        }
    }

    public Map<String, Integer> part4(List<Map<String, Integer>> listOfMaps, List<String> keys, List<Integer> sortOrders){
        return null;
    }
}

