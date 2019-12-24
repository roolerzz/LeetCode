package com.leetcode.medium;

import java.util.List;

// https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

   /*
            342 : 2 -> 4 -> 3
            465 : 5 -> 6 -> 4
                : 7 -> 0 -> 8

   Result   807 : 7 -> 0 -> 8

   - Start with the dummy node. tail = dummy.
   - Init Carry to 0. Start with the heads of the list.
   - while either of temps is not null.
       - Add the numbers in the respective heads and the current carry to get the new sum.(If the temp 1 is not null and temp2 is not null)
       - carry = sum /10;
       - sum = sum %10
       - Insert a new node at the tail. Update tail.
   - return dummy.next as the head.
   Not in place.
   If needed to do inplace :
   - We should the which of the list is the longer one, and there we will update the elements. Still might have to insert a new node at the last.
   */
    /*
        M l1 , N l2.
        O(Max(M,N)) time complexity, and O(Max(M,N)+1) space complexity.

    */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2);
    }

    /*

     /*
     Followup: What if the digits are not stored in the reverse order.
                : h1          t1
          12342 : 1 -> 2 -> 3 -> 4 -> 2 (5)
            465 :           4 -> 6 -> 5           (3)
                :         h2/t2
        In the final list, insertion happens in the head of the list. Recusive function. Which adds the current nodes. But before that if the next nodes are there, it adds those and return the carry from that step.
   Result   807 : 7 -> 0 -> 8
            align t1 and t2 by knowing the length of the lists. T1 = bigger, T2 = smaller. Move T1 x steps forward.
            carry = add(t1, t2);
                carry = add(t1.next, t2.next)
                create a new node(carry + t1.val + t2.val).
                newnode.next = dummy.next;
                dummy.next = newnode.
            if carry is still there, that means it needs to be propogated using T1's values.
            9 -> 9-> 9 -> 1 -> 1
                          9 -> 9
                          0 -> 0(carry 1)
            or
            in place
            9 -> 9-> 9 -> 0 -> 0
    */
    /*
    Or easier soln is to reverse both the lists. Adds them. Return the reversed version of the result.
    */
    public static ListNode addTwoNumbersNonReversed(ListNode l1, ListNode l2) {
        ListNode rl1 = reverse(l1);
        ListNode rl2 = reverse(l2);
        ListNode rl3 = addTwoNumbersHelper(rl1, rl2);
        return reverse(rl3);
    }

    public static ListNode addTwoNumbersHelper(ListNode l1, ListNode l2)
    {
        ListNode temp1 = l1, temp2 = l2;
        int carry = 0;
        ListNode dummy = new ListNode(0), tail = dummy;
        while(temp1 != null || temp2 != null){
            int sum = carry;
            if(temp1 != null){
                sum += temp1.val;
                temp1 = temp1.next;
            }
            if(temp2 != null){
                sum += temp2.val;
                temp2 = temp2.next;
            }
            carry = sum/10;
            sum = sum %10;
            tail.next = new ListNode(sum);
            tail = tail.next;
        }
        if(carry != 0){
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return dummy.next;
    }

    /*
        null<-1 <-2 <- 3  null

    */
    private static ListNode reverse(ListNode head){
        ListNode prev = null, curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode (1);
        insertAtEnd(head, List.of(2, 3));
        ListNode head2 = new ListNode (9);
        insertAtEnd(head2, List.of(8, 7));
        print(addTwoNumbers(head, head2)); // 321 + 789 =    1110
        print(addTwoNumbersNonReversed(head, head2)); // 123 + 987 =
//    head.next = newListNode()
        //
    }

    private static void print(ListNode head){
        while(head != null && head.next != null){
            System.out.print(head.val + " ->");
            head = head.next;
        }
        System.out.println(head.val);
    }

    private static void insertAtEnd(ListNode node, List<Integer> nums){
        while(node != null && node.next != null){
            node = node.next;
        }
        ListNode tail = node;
        for(int num :  nums){
            tail.next = new ListNode(num);
            tail = tail.next;
        }
    }
}
