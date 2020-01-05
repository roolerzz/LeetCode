package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

    /*
        - Create a Map of nodes from oldNode to the new Node. This would help you to update the random pointer.
        - Traverse through each node of the original linked list.
            - For each node in the OG list
                - Create a new node with the same value copied over.
                - Put in the map, OGNode : new Node.
                - Update the new node's next pointer to recursive function(OGNODE.next)
                - Update the random pointer of the new Node by finding the newNode corresponding to the random pointer of the OG node points to.
        return head;
    */

    /* Iterative version from https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43488/Java-O(n)-solution */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<Node, Node>();

        // loop 1. copy all the nodes
        Node node = head;
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }

        public Node copyRandomList1(Node head) {
            Map<Node, Node> map = new HashMap<>();
            return createList(head, map);
        }

        private Node createList(Node ogNode, Map<Node, Node> map){
            if(ogNode == null) return null;
            Node newNode = new Node(ogNode.val);
            map.put(ogNode, newNode);
            newNode.next = createList(ogNode.next, map);
            newNode.random = map.getOrDefault(ogNode.random, null);
            return newNode;
        }

        /*
        Idea: https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43491/A-solution-with-constant-space-complexity-O(1)-and-linear-time-complexity-O(N)

        Idea is Instead of using a map to associate the old nodes with the new nodes, while creating the new nodes, interleaves new nodes amongst the old nodes.
        1) Associate each nodes to its copy. copy to points to the og's next node.
        2) In the next pass, update the random pointer of the new nodes.
        3) Remove the list interleaving. Return the pointer to the first newer node.
        */
        public Node copyRandomList2(Node head) {
            if(head == null) return null;
            Node curr = head, next;
            // Step 1:
            while(curr != null) {
                next = curr.next;
                Node copy = new Node(curr.val);
                curr.next = copy;
                copy.next = next;
                curr = next;
            }

            // Step 2:
            Node oldNode = head;
            while(oldNode !=null){
                if(oldNode.random != null)
                    oldNode.next.random = oldNode.random.next;
                oldNode = oldNode.next.next;
            }

            // Step 3:
            Node oldCurr = head;
            Node newHead = head.next;
            Node newCurr = oldCurr.next;
            while(oldCurr != null){
                oldCurr.next = newCurr.next;
                oldCurr = oldCurr.next;
                if(oldCurr != null){
                    newCurr.next = oldCurr.next;
                    newCurr = newCurr.next;
                }
            }
            return newHead;
        }

}
