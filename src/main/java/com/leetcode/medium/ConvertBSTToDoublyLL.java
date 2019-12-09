package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list
public class ConvertBSTToDoublyLL {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /*
        Idea is, While doing an inorder traversal we are using an explicit stack to remember the nodes which needs to be
        processd. In In-order traversal, the left subtree gets processed first, then curr Node, then the right subtree.
        So we will use an explicit stack to remember the parent Node, while the left child would be getting processed.
        So, starting from the leftmost(smallest) Node, we keep track of the last(smaller) node processed as a prev pointer,
        and get the curr item to be processed from the top of the stack. As a part of processing the curr node, we update
        Previous's next pointer to the current node and current's left pointer to the prev node. Once current is processed,
        its marked as previous, and its right subtree is marked as current to be iteratively processed with the help of
        the stack.

        Another trick used here is the usage of dummy node, which helps eliminate a lot of if else blocks doing null
        checking, and additional variables to keep track of the first node. Here dummy node  points to the first node.

        At last, the Doubly linked list loop is closed(first points to last and last points to first) by updating
        their pointers with the help of dummy reference.
    */
    public Node treeToDoublyList(Node root){
        if(root == null) return null;
        Deque<Node> stack = new ArrayDeque<>();
        Node dummy = new Node(0), prev = dummy, curr = root;
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            connect(prev, curr);
            prev = curr;
            curr = curr.right;
        }
        connect(prev, dummy.right);
        return dummy.right;
    }
    // Connects the left node a to the right node b.
    private void connect(Node a, Node b){
        a.right = b;
        b.left = a;
    }


}
