package com.leetcode.hard;

import java.util.*;

// https://leetcode.com/problems/critical-connections-in-a-network/
// https://www.youtube.com/watch?v=V6kRqdtM_Uk
// https://www.youtube.com/watch?v=aZXi1unBdJA
// This can also be called as Finding Bridges(extension problems Articulation points) in a graph.
public class CriticalConnectionsInANetwork {

    int id;
    // Changing the adjacency list a list to a fixed size array(because the size/no of nodes is already know up front) improves the performance.
//    List<List<Integer>> graph;
    List<Integer>[] graph;
    int[] ids, low;
    boolean[] visited;

    private void createGraph(int n){
        graph = new ArrayList[n];
        for(int i=0 ; i < n ; i++)
//            graph.add(new ArrayList<>());
            graph[i]=new ArrayList<>();
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        id = 0;
        ids = new int[n];
        low = new int[n];
        visited = new boolean[n];
        List<List<Integer>> bridges = new ArrayList<>();
        createGraph(n);
//        addConnections(graph,connections);
        addConnections2(graph,connections);
//        printGraph(graph);
        for(int i=0 ;  i < n; i++){
            if(!visited[i]){
                dfs(i, -1, bridges);
            }
        }
        return bridges;
    }

    private void printGraph(List<List<Integer>> graph){
        for(int i =0 ; i < graph.size(); i++){
            List<Integer> neighbors = graph.get(i);
            System.out.println("Neighbors for node " + i +  "th are : [" + Arrays.toString(neighbors.toArray(new Integer[neighbors.size()])) + "]");
        }
    }

    private void dfs(int at, int parent, List<List<Integer>> bridges){

        visited[at] = true;
        low[at] = ids[at] = ++id;
//        for(Integer to : graph.get(at)){
        for(Integer to : graph[at]){
            if(to == parent) continue;
            if(!visited[to]){
                dfs(to, at, bridges);
                low[at] = Math.min(low[at], low[to]);
                if(ids[at] < low[to])
                    bridges.add(Arrays.asList(at, to));
            }
            else {
                low[at] = Math.min(low[at], ids[to]);
            }
        }
//        System.out.println("Visiting the node " + at + " with parent node " + parent + " Id value as " + ids[at] + " and low link value as :" + low[at]);
    }

    private void addConnections(List<List<Integer>> graph, List<List<Integer>> connections){
        for(List<Integer> conn: connections){
            addEdge(graph, conn.get(0), conn.get(1));
        }
    }

    private void addConnections2(List<Integer>[] graph, List<List<Integer>> connections){
        for(List<Integer> conn: connections){
            addEdge2(graph, conn.get(0), conn.get(1));
        }
    }
    private void addEdge2(List<Integer>[] graph, int from, int to){
        System.out.println("Adding an edge in the graph from " + from  + " to " + to );
        graph[from].add(to);
        graph[to].add(from);
    }

    private void addEdge(List<List<Integer>> graph, int from, int to){
        System.out.println("Adding an edge in the graph from " + from  + " to " + to );
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public static void main(String[] args) {
        CriticalConnectionsInANetwork ccn = new CriticalConnectionsInANetwork();
        List<List<Integer>> connections = List.of(List.of(0,1), List.of(1,2), List.of(2,0), List.of(1,3));
//                new int[][]{{0,1},{1,2},{2,0},{1,3}};
        List<List<Integer>> bridges = ccn.criticalConnections(4, connections);
        System.out.println("Bridges size is : " + bridges.size());
        for(List<Integer> bridge : bridges){
            System.out.println("Bridge found at " + bridge.get(1)  + " : " + bridge.get(1) + " .");
        }
    }
}
