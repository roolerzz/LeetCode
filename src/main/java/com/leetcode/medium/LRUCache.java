package com.leetcode.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache extends LinkedHashMap<Integer,Integer> {
    /*
        Cache(capacity +ve)
        ops:
        - Val get(key) // +ve value if key exists, otherwise -1. Touching the item. So this becomes most recently used.
        - put(Key, Val) // When reached capacity, invalidate the least recently used item.

        - What types for my keys and Vals? Integer, Generic?
        - Should my DS need to support null values? For keys, it would return IllegalArgException, but for val, Does that mean null should delete the corresponding key. Or can we disallow null.

        HashMap<Key,Node> inserts, delete, puts(O(1)) . Remove the least recently used. remove the head of the LL.
        {Head: 1:1 -> 2:2 -> 3:3 -> 4.4 Tail.}
             <-     <-     <-

        - Create HashMap of Key, Node.
        - Each node represent a key , value. Node in doubly linked list. Has prev, and next ptrs(helps in deletion).
        - Head and tail Nodes as the global variable of the LRU Cache.
        - Put(key,val)
            - If element exists, Update value in Hashmap. Delete the node in the list. Insert at the tail.
            - If not,
                If size = capacity, delete head.(decrement size)
                Insert at the tail of LL. Put the <Key,node> in the hashmap.(increment size)
        - delete(Key)
            - If hashMap Doesn't contains, return null.
            - If does, remove the K,Node from hashmap. Delete the Node from Doubly LL. Return the value from the node.(decrement size)
        - get(key)
            - If hashMap doesn't have key, return -1.
            - Get the node from the hashmap. Delete the node, and insert into the list Tail.

    */

    private int capacity2;

    public int get2(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put2(int key, int value) {
        super.put(key,value);
    }

    // @Override
    protected boolean removeEldestEntry2(Map.Entry<Integer,Integer> eldest){
        return size() > capacity;
    }

    // // private LinkedHashMap<Integer, Integer> lMap;
    //   public LRUCache(int capacity) {
    //     super(capacity, 0.75F, true);
    //     this.capacity = capacity;
    // }

    class Node {
        int key,val;
        Node prev,next;
        Node(){}
        Node(int key, int val){
            this.key = key;
            this.val = val;
            // this.prev = prev;
            // this.next = next;
        }
    }
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int capacity;
    private int size;

    public  LRUCache(int capacity) {
        if(capacity < 1) return;
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node x = map.get(key);
        if(x == null) return -1;
        moveToHead(x);
        return x.val;
    }

    public void put(int key, int value) {
        Node x = map.get(key);
        if(x != null){
            x.val = value;
            moveToHead(x);
            return;
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addNode(newNode);
        size++;
        if(size > capacity){
            Node tail = popTail();
            map.remove(tail.key);
            size--;
        }
    }

    private void addNode(Node node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    private void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }
    private Node popTail(){
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

