package com.leetcode.medium;

import java.util.*;

//
public class AllNodesDistanceKFromTargetNodeBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    /*
    - Non empty tree. Non-duplicate node values, K < 1000, Node value < 500, target node in the tree.
    - 2 types of nodes
        - Child of target node.(do a Breadth first traversal from the target node, and add all the child in the result when reached at that level)
        - Not a child(Dist K Node is child of one of the parent nodes of Target)
            - Starting from Root, we can determine the distance from the root of 5, and basically all the nodes.
            Doesnt work- If looking for node at dist K from the target, Look for nodes with dist(K-dist(target from root))
            -
    - Traverse the tree, and form a graph of nodes in the tree such that every edge in the tree represent 2 edges(of undirected graph b/w child and parent.)
    - Once the graph is formed, start DFS/BFS from the target node, and once the dist+1 at each new level = K, add to the list of results.
    */


    // O(N) for making the graph out of tree traversal. O(N) for DFS for finding nodes at distance K.
    public List<Integer> distanceKDFS(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Set<TreeNode> visited = new HashSet<>();
        formGraph(root, graph);
        dfs(graph,visited, target, K, res, 0);
        return res;
    }



    // O(N) for making the graph out of tree traversal. O(N) for DFS for finding nodes at distance K.
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Set<TreeNode> visited = new HashSet<>();
        formGraph(root, graph);
        bfs(graph,visited, target, K, res);
        return res;
    }

    private void bfs(Map<TreeNode,List<TreeNode>> graph,Set<TreeNode> visited ,TreeNode target, int K, List<Integer> res){
        Queue<TreeNode> levelNodes = new LinkedList<>();
        levelNodes.add(target);

        while(!levelNodes.isEmpty()){
            int size = levelNodes.size();
            K--;
            while(size > 0){
                TreeNode curr = levelNodes.poll();
                visited.add(curr);
                if(curr == null) continue;
                if(K == 0)
                    res.add(curr.val);
                for(TreeNode child : graph.getOrDefault(curr,new ArrayList<>())){
                    if(!visited.contains(child))
                        levelNodes.add(child);
                }

                size--;
            }
            if(K == 0) break;
        }
    }


    private void dfs(Map<TreeNode,List<TreeNode>> graph,Set<TreeNode> visited ,TreeNode curr, int K, List<Integer> res, int dist){
        visited.add(curr);
        if(dist == K) res.add(curr.val);
        // So that it doesn't throw null pointer exception in case of null value(shouldn't happen, or in case of key doesn't exist into the map. Say just root node with 0 children, no graph edges would be there so the map would be empty.)
        for(TreeNode child : graph.getOrDefault(curr, new ArrayList<>())){
            if(!visited.contains(child)){
                dfs(graph, visited, child, K, res, dist+1);
            }
        }
    }


    private void formGraph(TreeNode curr, Map<TreeNode,List<TreeNode>> graph){
        if(curr == null) return;
        if(curr.left != null) addEdges(curr, curr.left, graph);
        if(curr.right != null) addEdges(curr, curr.right, graph);
        formGraph(curr.left, graph);
        formGraph(curr.right, graph);
    }

    private void addEdges(TreeNode parent, TreeNode child, Map<TreeNode,List<TreeNode>> graph){
        List<TreeNode> parentList = graph.getOrDefault(parent, new ArrayList<TreeNode>());
        List<TreeNode> childList = graph.getOrDefault(child, new ArrayList<TreeNode>());
        parentList.add(child);
        childList.add(parent);
        graph.put(parent, parentList);
        graph.put(child, childList);
    }



}
