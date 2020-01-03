package com.leetcode.easy;

// https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

// Recursive solution Leetcode.
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val)
        {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else
        {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /*
    - What kind of elements does the list contains? Numbers/Strings?
    Approach 1:
    - Similar to merge subroutine in the merge sort, Start with pointers from the head of both of the lists.
    - Insert a new node at the tail of the resulting list, and copy over the smaller element.

    Approach 2:
    - Not creating new nodes, but adjusting pointers to the existing nodes.
    - dummy node. Tail points to the dummy.
    - Start temp1, and temp2 to be the head of l1/l2.
    - If temp1 is smaller than temp2, point tail.next to temp1. temp1 = temp1.next, tail = tail.next.
    - else tail.next = temp2. temp2 = temp2.next. tail=tail.next.
    - Until tail1 and tail2 is null
    - return dummy.next as the head node of the list.
    */

        public ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0), tail=dummy;
            while(l1 != null && l2 != null){
                if(l1.val <= l2.val)
                    l1 = update(tail, l1);
                else
                    l2 = update(tail, l2);
                tail = tail.next;
            }
            tail.next = (l1 != null) ? l1 : l2;
            return dummy.next;
        }



        private ListNode update(ListNode tail, ListNode l){
            tail.next = l;
            return l.next;
        }

}
