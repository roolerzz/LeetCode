package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/word-ladder/
public class WordLadder {
    /*
    Question:

    - What if the endWord doesn't exist into the dictionary.
    - What if no transformation is possible.
    - What if multiple transofmration is possible(Matters in case you are asked to return the paths. Multiple shortest paths.)
    - Duplicate Strings in the dictionary.
    - What is the expectation around the size of the strings? If they are too big, instead actually string strings as nodes in the graphs, we can map each of the string to the integer and graph would have numbers as the nodes.
    */
    /*
    LC Help. Trying not to make an explicit graph. just trying to tranform the strings to next strings.
    Time:
    For n strings each of length m,
    Time = O(n * m * 26)
    Each string is processed only once. Process of each involves changing all the m positions of the string with 26 different characters.
    Space: O(n*m)
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        // Acting as both visited set and for faster lookup(Instead of saying these are the vertices that have been visited, this contains nodes which are yet to be processed.).
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0) {
                char[] curr = queue.poll().toCharArray();
                for(int j=0; j < curr.length; j++){
                    char tmp = curr[j];
                    for(char c='a' ; c <= 'z' ; c++){
                        curr[j] = c;
                        String next = new String(curr);
                        if(set.contains(next)){
                            if(next.equals(endWord)) return count+1;
                            queue.add(next);
                            set.remove(next);
                        }
                    }
                    curr[j] = tmp;
                }
            }
            count++;
        }
        return 0;
    }


    private class Node {
        String key;
        int level;
        Node(String key, int level){
            this.key = key;
            this.level = level;
        }
    }

    /*
    2 Way Bidirection BFS from top to bottom that restricts the search space of the algorithm reducing the branches to look at.
    */

//      public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if(!wordList.contains(endWord)) return 0;
//         // Acting as both visited set and for faster lookup(Instead of saying these are the vertices that have been visited, this contains nodes which are yet to be processed.).
//          Set<String> unique = new HashSet<>(wordList);
//          Set<String> topVisited = new HashSet<>();
//          Set<String> bottomVisited = new HashSet<>();

//          Queue<Node> topQ = new LinkedList<>();
//          Queue<Node> bottomQ = new LinkedList<>();
//          topQ.add(new Node(beginWord, 0));
//          bottomQ.add(new Node(endWord, 0));
//          int count = 1;
//          while(!topQ.isEmpty() && !bottomQ.isEmpty()){
//              int size = queue.size();
//              while(size-- > 0) {
//                  char[] curr = queue.poll().toCharArray();
//                  for(int j=0; j < curr.length; j++){
//                      char tmp = curr[j];
//                      for(char c='a' ; c <= 'z' ; c++){
//                          curr[j] = c;
//                          String next = new String(curr);
//                          if(set.contains(next)){
//                              if(next.equals(endWord)) return count+1;
//                              queue.add(next);
//                              set.remove(next);
//                          }
//                      }
//                      curr[j] = tmp;
//                  }
//              }
//              count++;
//          }
//          return 0;
//     }



    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Map<String, List<String>> graph = new HashMap<>();
        wordList.add(0,beginWord);
        Set<String> unique = new HashSet<>(wordList);
        formgraphTryAllChars(wordList, graph, unique);
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                String parent = q.poll();
                seen.add(parent);
                for(String child : graph.getOrDefault(parent, new ArrayList<>())){
                    if(seen.contains(child)) continue;
                    q.offer(child);
                    if(child.equals(endWord))
                        return level+1;
                }
            }
            level++;
        }
        return 0;
    }

    private void formgraphTryAllChars(List<String> wordList, Map<String, List<String>> graph, Set<String> unique){
        for(int i=0; i < wordList.size()-1; i++){
            String word1 = wordList.get(i);
            char[] arr = word1.toCharArray();
            char[] newArr = Arrays.copyOf(arr, arr.length);
            for(int j=0 ; j < arr.length; j++){
                for(int k=0; k < 26 ; k++){
                    char oldChar = newArr[j];
                    char newChar = (char)('a' + k);
                    if(newChar == oldChar)
                        continue;
                    newArr[j] = newChar;
                    String word2 = new String(newArr);
                    if(unique.contains(word2)){
                        graph.putIfAbsent(word1, new ArrayList<>());
                        graph.putIfAbsent(word2, new ArrayList<>());
                        graph.get(word1).add(word2);
                        graph.get(word2).add(word1);
                    }
                    newArr[j] = oldChar;
                }

            }
        }
    }




    /*
    String nodes in the graph.
    */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Map<String, List<String>> graph = new HashMap<>();
        wordList.add(0,beginWord);
        formgraphWithPairsStrings(wordList, graph);
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                String parent = q.poll();
                seen.add(parent);
                for(String child : graph.getOrDefault(parent, new ArrayList<>())){
                    if(seen.contains(child)) continue;
                    q.offer(child);
                    if(child.equals(endWord))
                        return level+1;
                }
            }
            level++;
        }
        return 0;
    }

    private void formgraphWithPairsStrings(List<String> wordList, Map<String, List<String>> graph){
        for(int i=0; i < wordList.size()-1; i++){
            for(int j=i+1; j < wordList.size(); j++){
                String word1 = wordList.get(i);
                String word2 = wordList.get(j);
                if(isOneCharAway(word1, word2)){
                    graph.putIfAbsent(word1, new ArrayList<>());
                    graph.putIfAbsent(word2, new ArrayList<>());
                    graph.get(word1).add(word2);
                    graph.get(word2).add(word1);
                }
            }
        }
    }




    /*
        String nodes mapped to integer values to save space in the Graph. For a dense graph, there would be so many string objects lying around in the memory. If Strings are too big, that could be a problem.
    */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<String, Integer> nodeMap = new HashMap<>();
        wordList.add(0,beginWord);
        mapGraphStringsToInteger(wordList, nodeMap);
        // Map<String, String> parentMap = new HashMap<>();
        formgraphWithPairs(wordList, graph, nodeMap);
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(nodeMap.get(beginWord));
        // parentMap.put(beginWord, beginWord);
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Integer parent = q.poll();
                seen.add(parent);
                for(Integer child : graph.getOrDefault(parent, new ArrayList<>())){
                    if(seen.contains(child)) continue;
                    q.offer(child);
                    if(child == nodeMap.get(endWord))
                        return level+1;
                }
            }
            level++;
        }
        return 0;
    }

    private boolean isOneCharAway(String word1, String word2){
        int count = 0;
        for(int i=0; i < Math.min(word1.length(), word2.length()); i++){
            if(word1.charAt(i) != word2.charAt(i))
                count++;
            if(count == 2)
                return false;
        }
        return true;
    }

    private void formgraphWithPairs(List<String> wordList, Map<Integer, List<Integer>> graph, Map<String, Integer> nodeMap){
        for(int i=0; i < wordList.size()-1; i++){
            for(int j=i+1; j < wordList.size(); j++){
                String word1 = wordList.get(i);
                String word2 = wordList.get(j);
                if(isOneCharAway(word1, word2)){
                    // System.out.println(word1 + " and " + word2 + " are 1 character away. Adding edge b/w " + nodeMap.get(word1) + " and " + nodeMap.get(word2));
                    graph.putIfAbsent(nodeMap.get(word1), new ArrayList<>());
                    graph.putIfAbsent(nodeMap.get(word2), new ArrayList<>());
                    graph.get(nodeMap.get(word1)).add(nodeMap.get(word2));
                    graph.get(nodeMap.get(word2)).add(nodeMap.get(word1));
                }
            }
        }
    }

    private void mapGraphStringsToInteger(List<String> wordList, Map<String, Integer> nodeMap){
        for(int i=0; i < wordList.size(); i++){
            // System.out.println("mapped " + wordList.get(i) + "  at " + i  + "th position.");
            nodeMap.put(wordList.get(i),i);
        }
    }


}
