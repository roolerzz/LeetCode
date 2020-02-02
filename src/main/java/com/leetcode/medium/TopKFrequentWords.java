package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords {
    /*
    - Count the occurence of each word.
    - Create a Array of List of Strings as buckets.
    - Iterate through the hashmap and put these elements(strings/words) into their respective buckets as per their frequency counts.
    - Sort each of the buckets before returning the list. // This step could have worst case implication of NlogN when all the n elements fall into the same buckets.
        - How about keeping a priority queue of worst size N that keeps lexicographically smaller elements at each place in the bucket. O(N) to for priority queue of size N.
    - kLogk
    */
    public List<String> topKFrequent1(String[] words, int k) {
        Map<String, Integer> wordCount = new HashMap<>();
        for(String word : words)
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        PriorityQueue<String>[] buckets = new PriorityQueue[words.length+1];
        for(Map.Entry<String, Integer> entry : wordCount.entrySet()){
            int freq = entry.getValue();
            String key = entry.getKey();
            if(buckets[freq] == null){
                buckets[freq] = new PriorityQueue<String>();
            }
            buckets[freq].add(key);
        }
        List<String> res = new ArrayList<>();
        for(int i=buckets.length-1; i >=0 && res.size() < k; i--){
            if(buckets[i] == null) continue;
            int noOfElems = buckets[i].size(), j=0;
            while (j++ < noOfElems && res.size() < k){
                res.add(buckets[i].poll());
            }
        }
        return res;
    }


    /*
    - Create a wordCount map for each of the word.
    - Create a MinPriorityQueue such that elements on the top is the one with the minimumCount. In case of tie, lexicographically larger string should come to the top. Idea is, since we are only looking for k most frequent words, we will discard n-k smaller words from the heap(bound by size k).
    - Return the K elements from the heap.
    */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCount = new HashMap<>();
        for(String word : words)
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        PriorityQueue<String> minPQ = new PriorityQueue<String>((a,b) -> {
            if(wordCount.get(a) != wordCount.get(b)) return wordCount.get(a)-wordCount.get(b);
            return b.compareTo(a);
        });

        for(String key : wordCount.keySet()){
            minPQ.add(key);
            if(minPQ.size() > k)
                minPQ.poll();
        }

        List<String> res = new ArrayList<>();
        while(!minPQ.isEmpty())
            res.add(minPQ.poll());
        Collections.reverse(res);
        return res;
    }

    /*
    - Create a wordCount map for each of the word.
    - Add all the words to the list.
    - Sort the list using a custom comparator in the order such that keys with greater count comes first. In case of tie, lexicographically smaller string comes first.
    */
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> wordCount = new HashMap<>();
        for(String word : words)
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        List<String> res = new ArrayList<>(wordCount.keySet());
        Collections.sort(res, (a,b) -> {
            if(wordCount.get(a) != wordCount.get(b)) return wordCount.get(b)-wordCount.get(a);
            return a.compareTo(b);
        });
        return res.subList(0,k);
    }


}
