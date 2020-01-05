package com.leetcode.hard;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
public class SerializeAndDeserializeBinaryTree {

    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private static final String SPLITTER = ",";
    private static final String NULLSTR = "n";

    /*  #################################### APPROACH 1 RECURSIVE-DFS PRE-ORDER ##################################### */
    /* Idea is to use Pre-order traversal of the Binary Tree. Append n if the node is null. */
    public String serialize(TreeNode root) {
        StringBuilder sb= new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode curr, StringBuilder sb){
        if(curr == null)
            sb.append(NULLSTR).append(SPLITTER);
        else {
            sb.append(curr.val).append(SPLITTER);
            buildString(curr.left, sb);
            buildString(curr.right, sb);
        }
    }

    /*
        Using the Pre-order traversal, put the elements into a queue. Now, take the first element out, if the first element is non-null(!n), make it a node. Use the rest of the elements to recursively make its left subtree. And once the left subtree would be formed, and the stack would fold back(due to presence of nulls), build the right subtree with the remaining elements in the queue.
    How would you prove the correctness of your solution?
    */
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList(Arrays.asList(data.split(SPLITTER)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes){
        String str = nodes.remove();
        if(str.equals(NULLSTR)) return null;
        TreeNode curr = new TreeNode(Integer.valueOf(str));
        curr.left = buildTree(nodes);
        curr.right = buildTree(nodes);
        return curr;
    }






    /*  #################################### APPROACH 2 BFS######################################## */


    // Serializes the Binary Tree Using BFS or Level Order Traversal. Uses LinkedList as queue to process the elements level by level(from left to right). We are appending n to signify the null children for an element.
    public String serialize1(TreeNode root) {
        if(root == null) return "";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr==null) {
                sb.append("n,");
                continue;
            }
            sb.append(curr.val+",");
            q.offer(curr.left);
            q.offer(curr.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    /* Since the Serialization was done using the BFS or Level Order Traversal, Deserializing back to a binary tree is little tricky as to what would be the children for a node. So what you do is, You create the first element as the root node and put it into the queue. This is to simulate the order as per level order traversal. Now Starting the for loop from i=1(elements at idx 1 and 2 being the children of the root), pop the element from the queue to be processed next as the Parent. If the element in the string/array is not null(or n) then create that as the left child of the parent. Create i+1 as the right child of the parent. Add left/right to the queue to be processed in level order only if they are not null.
     */
    public TreeNode deserialize1(String data) {
        if(data.equals("")) return null;
        String[] elms = data.split(",");
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(elms[0]));
        q.offer(root);
        for(int i=1; i<elms.length; i++){
            TreeNode parent = q.poll();
            if(!elms[i].equals("n")){
                TreeNode left = new TreeNode(Integer.valueOf(elms[i]));
                parent.left = left;
                q.offer(left);
            }
            if(!elms[++i].equals("n")){
                TreeNode right = new TreeNode(Integer.valueOf(elms[i]));
                parent.right = right;
                q.offer(right);
            }
        }
        return root;
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
}
