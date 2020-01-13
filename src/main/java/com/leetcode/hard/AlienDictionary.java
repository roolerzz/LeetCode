package com.leetcode.hard;

import java.util.*;


// https://leetcode.com/problems/alien-dictionary/
public class AlienDictionary {

    private static final int N = 26;

    // https://leetcode.com/problems/alien-dictionary/discuss/70113/Java-BFS-Solution
    public String alienOrderBFS(String[] words) {
        if(words == null || words.length == 0) return ""; // Ask and clarify.
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i=0; i < N ; i++){
            graph.add(new HashSet<>());
        }
        int[] indegree = new int[N];
        Arrays.fill(indegree, -1);
        for(int i=0 ; i < words.length; i++){
            for(char c: words[i].toCharArray()){
                if(indegree[c-'a'] < 0)
                    indegree[c-'a'] = 0;
            }
            if(i > 0){
                String word1 = words[i-1], word2 = words[i];
                int len = Math.min(word1.length(), word2.length());
                for(int k=0 ; k < len; k++){
                    int c1 = word1.charAt(k)-'a', c2 = word2.charAt(k)-'a';
                    if(c1 != c2){
                        if(!graph.get(c1).contains(c2)){
                            graph.get(c1).add(c2);
                            indegree[c2]++;
                        }
                        break;
                    }
                    if (k == word2.length() - 1 && word1.length() > word2.length()) {
                        return "";
                    }
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < indegree.length; i++){
            if(indegree[i] == 0)
                q.add(i);
        }
        while(!q.isEmpty()){
            int curr = q.poll();
            sb.append((char)(curr + 'a'));
            for(int neigh : graph.get(curr)){
                indegree[neigh]--;
                if(indegree[neigh]==0)
                    q.add(neigh);
            }
        }
        for(int d: indegree)
            if(d > 0)
                return "";

        return sb.toString();
    }


    // https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0) return ""; // Ask and clarify.
        boolean[][] graph = new boolean[N][N];
        boolean[] containsCycle = new boolean[0];
        int[] visited = new int[N];
        formGraph(words, graph, visited);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < N; i++){
            if(visited[i]==0){
                if(!dfs(graph, visited, i, sb)) return "";
            }
        }
        return sb.reverse().toString();
    }

    private boolean dfs(boolean[][] graph, int[] visited, int v, StringBuilder sb){
        visited[v]=1;
        for(int w=0; w<N ; w++){
            if(graph[v][w]){
                if (visited[w]==1){
                    return false;
                }
                else if(visited[w]==0)
                {
                    if(!dfs(graph, visited, w, sb)) return false;
                }
            }
        }
        visited[v]=2;
        sb.append((char)(v+'a'));
        return true;
    }

    private void formGraph(String[] words,boolean[][] graph, int[] visited){
        Arrays.fill(visited, -1);
        for(int i=0; i < words.length; i++){
            for(char c: words[i].toCharArray())
                visited[c-'a']=0;
            if(i < words.length-1){
                String word1 = words[i];
                String word2 = words[i+1];
                int len = Math.min(word1.length(), word2.length());
                for(int k=0; k < len; k++){
                    char c1 = word1.charAt(k), c2 = word2.charAt(k);
                    if(c1 != c2)
                    {
                        // System.out.println("Adding edge " + c1 + " -> " + c2);
                        graph[c1-'a'][c2-'a']=true;
                        break;
                    }
                }
            }
        }
    }
}
