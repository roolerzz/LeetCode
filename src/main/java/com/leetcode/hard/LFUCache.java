package com.leetcode.hard;

import com.sun.jdi.IntegerValue;

import java.util.*;

// https://leetcode.com/problems/lfu-cache/
public class LFUCache {

    private Map<Integer, Integer> keyToValue;
    private Map<Integer, Integer> keyToFreq;
    private TreeMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    private HashMap<Integer, LinkedHashSet<Integer>> freqToKeysO1;
    private int capacity;

    int minFreq; // Used for O(1) implementation where we don't use treemap to keep frequencies sorted, but we just track
    // the min frequencies using min variable.

    public LFUCache(int capacity) {
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new TreeMap<>();
        freqToKeysO1 = new HashMap<>();
        this.capacity = capacity;
    }

    // https://leetcode.com/problems/lfu-cache/discuss/200375/105ms-Java-with-Explanations
    public int get1(int key) {
        if(!keyToValue.containsKey(key)) return -1;
        promoteKey1(key);
        return keyToValue.get(key);
    }

    private void promoteKey1(int key){
        int prevFreq = keyToFreq.get(key);
        int newFreq = prevFreq+1;
        keyToFreq.put(key, newFreq);
        freqToKeys.get(prevFreq).remove(key);
        if(freqToKeys.get(prevFreq).isEmpty()) freqToKeys.remove(prevFreq);
        freqToKeys.putIfAbsent(newFreq, new LinkedHashSet<>());
        freqToKeys.get(newFreq).add(key);
    }

    public void put1(int key, int value) {
        if (capacity == 0) return;
        // If key already exists, set to the new value. update location?
        if(keyToValue.containsKey(key)){
            keyToValue.put(key, value);
            promoteKey1(key);
            return;
        }

        if(keyToValue.size() == capacity)
            invalidate();
        // if capacity, invalidate() as per LFU, LRU rules.
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet());
        freqToKeys.get(1).add(key);
    }

    private void invalidate1(){
        // Find the lowest frequency in treeMap.
        int minFreq = freqToKeys.firstKey();
        int keyToRemove = -1;
        // For the returned linked Hashset, get the first key in the linked list and remove.
        Iterator<Integer> iter = freqToKeys.get(minFreq).iterator();
        if(iter.hasNext()){
            keyToRemove = iter.next();
            iter.remove();
        }
        if(!iter.hasNext())
            freqToKeys.remove(minFreq);

        keyToValue.remove(keyToRemove);
        keyToFreq.remove(keyToRemove);
        // Remove key  from keyToValue, keyToFreq
    }

    public int get(int key) {
        if(!keyToValue.containsKey(key)) return -1;
        promoteKey(key);
        return keyToValue.get(key);
    }

    private void promoteKey(int key){
        int prevFreq = keyToFreq.get(key);
        int newFreq = prevFreq+1;
        keyToFreq.put(key, newFreq);
        freqToKeysO1.get(prevFreq).remove(key);
        if(prevFreq == minFreq && freqToKeysO1.get(prevFreq).size()==0)
            minFreq++;
        if(freqToKeysO1.get(prevFreq).isEmpty()) freqToKeysO1.remove(prevFreq);
        freqToKeysO1.putIfAbsent(newFreq, new LinkedHashSet<>());
        freqToKeysO1.get(newFreq).add(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        // If key already exists, set to the new value. update location?
        if(keyToValue.containsKey(key)){
            keyToValue.put(key, value);
            promoteKey(key);
            return;
        }

        if(keyToValue.size() == capacity)
            invalidate();
        // if capacity, invalidate() as per LFU, LRU rules.
        minFreq=1;
        keyToValue.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeysO1.putIfAbsent(1, new LinkedHashSet());
        freqToKeysO1.get(1).add(key);
    }

    private void invalidate(){
        // Find the lowest frequency in treeMap.
        // int minFreq = freqToKeys.firstKey();
        int keyToRemove = -1;
        // For the returned linked Hashset, get the first key in the linked list and remove.
        Iterator<Integer> iter = freqToKeysO1.get(minFreq).iterator();
        if(iter.hasNext()){
            keyToRemove = iter.next();
            iter.remove();
        }
        if(!iter.hasNext())
            freqToKeysO1.remove(minFreq);
        keyToValue.remove(keyToRemove);
        keyToFreq.remove(keyToRemove);
        // Remove key  from keyToValue, keyToFreq
    }
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}

// Inspired by: http://dhruvbird.com/lfu.pdf and this LC soln below
// https://leetcode.com/problems/lfu-cache/discuss/94515/Java-O(1)-Accept-Solution-Using-HashMap-DoubleLinkedList-and-LinkedHashSet

class LFUCacheO1 {

    class Node {
        int count;
        LinkedHashSet<Integer> keys;
        Node prev, next;
        Node(int count, Node prev, Node next){
            this.count = count;
            keys = new LinkedHashSet<>();
            this.prev = prev;
            this.next= next;
        }
    }

    private Node head;

    private int capacity;

    private HashMap<Integer, Integer> valueHash;

    private HashMap<Integer, Node> nodeHash;

    public LFUCacheO1(int capacity){
        this.capacity = capacity;
        valueHash = new HashMap<>();
        nodeHash = new HashMap<>();
        head = new Node(0,null, null);
        // This is for making doubly linked-list circular, so that we don;t need to deal with null pointers, while eviction.
        head.next = head;
    }

    public int get(int key){
        if(valueHash.containsKey(key)){
            promote(key);
            return valueHash.get(key);
        }
        return -1;
    }
    // This method is invoked when a key is touched either via a get or if the value is updated against an
    // already existing key.
    private void promote(int key){
        Node node = nodeHash.get(key);
        node.keys.remove(key);
        // If the next node either doesn't exist or isn't of the next frequency(curr + 1), in both the cases, we have
        // to create a new node.
        if(node.next == null || node.next.count != node.count+1){
            node.next = createNode(node.count+1, node, node.next);
        }
        node.next.keys.add(key);
        nodeHash.put(key, node.next);
        if(node.keys.size() ==0)
            remove(node);
    }

    // Helper method that helps to create a new node and to update the given prev/next nodes in the doubly linked list.
    private Node createNode(int count, Node prev, Node next){
        Node newNode = new Node(count, prev, next);
        if(prev != null)
            prev.next = newNode;
        if(next != null)
            next.prev = newNode;
        return  newNode;
    }

    // This method removes the given node for a doubly linked list.
    private void remove(Node node){
        // This assumes that we have a separate dummy head node which won't ever go away. This is for simplicity
        // for delete operations.
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value){
        if(capacity ==0) return ;
        if(valueHash.containsKey(key)){
            valueHash.put(key, value);
            promote(key);
            return;
        }
        if(valueHash.size() == capacity){
            evict();
        }
        valueHash.put(key, value);
        insertAtHead(key);
    }

    private void insertAtHead(int key){
        // Inserts node with frequency/count as 1 next to the head.
        if(head.next == null || head.next.count != 1){
            createNode(1, head, head.next);
        }

        head.next.keys.add(key);
        nodeHash.put(key, head.next);
    }

    private void evict(){
        Node lowestFreq = head.next;

        Iterator itr = lowestFreq.keys.iterator();
        // In case of Multiple LFU elements with same count, find the one which was LRU.
        int firstKey = -1;
        if(itr.hasNext()){
            firstKey = (Integer)itr.next();
            itr.remove();
//            System.out.println("Key To be removed is " + firstKey);
        }
        if(!itr.hasNext()){
            remove(lowestFreq);
        }
        valueHash.remove(firstKey);
        nodeHash.remove(firstKey);
    }
    public static void main(String[] args) {
        LFUCacheO1 o1 = new LFUCacheO1(2);
        o1.put(1,1);
        o1.put(2,2);
        System.out.println(o1.get(1));
        o1.put(3,3);
        System.out.println(o1.get(2));
        System.out.println(o1.get(3));
        o1.put(4,4);
        System.out.println(o1.get(1));
        System.out.println(o1.get(3));
        System.out.println(o1.get(4));
//        LFUCacheO1 o1 = new LFUCacheO1(1);
//        o1.put(2,1);
//        System.out.println(o1.get(2));
//        o1.put(3,2);
//        System.out.println(o1.get(2));
//        System.out.println(o1.get(3));
    }
}
