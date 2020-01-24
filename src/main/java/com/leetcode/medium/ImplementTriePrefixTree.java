package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/implement-trie-prefix-tree/
public class ImplementTriePrefixTree {

    class TrieNode {
        // Might be slightly slower than referencing in the array but
        // space efficient since we only stores the children that we need.
        // Also more flexible since we are not restricted by character set size etc.
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;

    }

    TrieNode rootNode;


        class Node {
            Node[] links;
            int R = 26;
            boolean isEnd;
            Node(){
                links = new Node[R];
            }

            public boolean containsKey(char ch){
                return links[ch-'a'] != null;
            }

            public Node get(char ch){
                return links[ch-'a'];
            }

            public void put(char ch, Node node){
                links[ch-'a'] = node;
            }

            public void setEnd(){
                isEnd = true;
            }

            public boolean isEnd(){
                return isEnd;
            }
        }

        Node root;
        /** Initialize your data structure here. */
        public ImplementTriePrefixTree() {
            root = new Node();
            rootNode= new TrieNode();
        }

        /** Inserts a word into the trie. */
    public void insert(String word) {
        Node node = root;
        for(int i=0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!node.containsKey(ch))
                node.put(ch, new Node());
            node = node.get(ch);
        }
        node.setEnd();
    }



    /** Inserts a word into the trie. */
    public void insertHashMapVersion(String word) {
        TrieNode node = rootNode;
        for(int i=0; i < word.length(); i++){
            char ch = word.charAt(i);
//            if(!node.children.containsKey(ch))
//                node.put(ch, new Node());
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

        private Node searchPrefix(String prefix){
            Node node = root;
            for(int i = 0 ; i < prefix.length(); i++){
                char ch = prefix.charAt(i);
                if(node.containsKey(ch))
                    node = node.get(ch);
                else return null;
            }
            return node;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node node = searchPrefix(word);
            return node != null && node.isEnd();
        }

    /** Returns if the word is in the trie. */
    public boolean searchHashMapVersion(String word) {
        TrieNode cur = rootNode;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.isEnd;
    }


    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWithHashMapVersion(String prefix) {
        TrieNode cur = rootNode;
        for(int i = 0;i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.children.get(c) == null) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return true;
    }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node node = searchPrefix(prefix);
            return node != null;
        }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

}
