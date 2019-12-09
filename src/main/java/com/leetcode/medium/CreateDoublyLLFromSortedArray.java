package com.leetcode.medium;

public class CreateDoublyLLFromSortedArray {

    class Node{
        int val;
        Node left;
        Node right;
        public Node(int val){
            this.val = val;
        }
    }

    Node head;
    Node tail;

    public Node doublyLL(int[] sortArr){
        if (sortArr == null || sortArr.length < 1 ) return null;
        head = new Node(sortArr[0]);
        head.right = recMake(sortArr, 1, head);
        head.left = tail;
        return head;
    }

    private Node recMake(int[] sortArr, int idx, Node left){
        if(idx == sortArr.length) return null;
        Node curr = new Node(sortArr[idx]);
        curr.left = left;
        curr.right = recMake(sortArr, idx+1, curr);
        if(idx == sortArr.length -1) // Tail Node, time to assigh the right pointer of the head.
        {
            tail = curr;
            tail.right = head;
        }
        return curr;
    }

// Less than Ideal structure of the code.
//    public Node doublyLL(int[] sortArr){
//        if (sortArr == null || sortArr.length < 1 ) return null;
//        head = recMake(sortArr, 0, null);
////        head.right =
//        head.left = tail;
//        return head;
//    }
//
//    private Node recMake(int[] sortArr, int idx, Node left){
//        if(idx == sortArr.length) return null;
//        Node curr = new Node(sortArr[idx]);
//        if(idx == 0) // head Node.
//        {
//            head = curr;
//        }
//        curr.left = left;
//        curr.right = recMake(sortArr, idx+1, curr);
//        if(idx == sortArr.length -1) // Tail Node, time to assigh the right pointer of the head.
//        {
//            tail = curr;
//            tail.right = head;
//        }
//        return curr;
//    }


    public void traverseLL(Node head){
        Node curr = head;
        while(curr.right != head){
            System.out.print(curr.val + " -> " );
            curr = curr.right;
        }
        System.out.print(curr.val);
        System.out.println();
    }

    public static void main(String[] args) {
        CreateDoublyLLFromSortedArray doublyLL = new CreateDoublyLLFromSortedArray();
        Node head = doublyLL.doublyLL(new int[]{1,2,3,4,5});
        doublyLL.traverseLL(head);
    }

}
