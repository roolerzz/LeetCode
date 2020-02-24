package com.leetcode.medium;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance/
public class FilterRestrauntsByVeganFriendlyPriceAndDistance {
    /*
    - Sort the list of restraunts as per the sorting order defined(first by Rating Desc and then ID desc)
    - For each restraunt in the given list, see if that restraunt matches all the criteria. If yes, add it to the list.
    else skip it.

    Approach 2: (Same time complexity, but added space.)
    - Instead of sorting in place, we could also use priority queue that would give me elements in that order.

    Iterate through the list of restraurnts and filter by criteria first. If its a match, add array, id, review in a list. Sort the list, and return.

    */
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> res = new ArrayList<>();
        List<int[]> inter = new ArrayList<>();
        for(int[] rest : restaurants){
            if(satisfies(rest, veganFriendly, maxPrice, maxDistance))
                inter.add(new int[]{rest[0],rest[1]});
        }

        Collections.sort(inter,  (a, b) -> {
            if(a[1] != b[1]) return b[1]-a[1];
            return b[0]-a[0];
        });

        for(int[] arr : inter)
            res.add(arr[0]);

        return res;
    }
    private boolean satisfies(int[] rest, int vf, int mp, int md){
        boolean pd = mp >= rest[3] && md >= rest[4];
        return (vf == 1 ? (vf == rest[2]) : true) && pd;
    }


}
