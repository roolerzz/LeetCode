package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/remove-k-digits/
public class RemoveKDigits {

        /*
            So the below solution of finding the peaks K times can be done in only 1 pass using a stack. This is a greedy approach.
            As long as the sequence is monotonically increasing(the next number is equal or greater), push onto stack.
            - For all elements in the string
                - while you find the smaller number(comparing with the top of the stack if the stack is non-empty), that means, thats your first peak. Remove the top of the stack.Decrement K.
                - Push the current element on the top of the stack.
            - If you got monotonically increasing sequence, and you couldn't remove k digits yet, remove until K is 0.
            - Use a string builder to insert all the popped digits from the stack. Reverse the string in string builder.
            - Handle Leading zeroes in the final solution by using deleteCharAt(idx).
        */
        public String removeKdigits(String num, int k) {
            Deque<Character> stack = new ArrayDeque<>();
            for(char digit : num.toCharArray()){
                while(stack.size() > 0 && k > 0 && stack.peek() > digit){
                    stack.pop();
                    k--;
                }
                stack.push(digit);
            }
            while(k > 0){
                stack.pop();
                k--;
            }
            StringBuilder sb = new StringBuilder();
            boolean leadingZero = true;
            int size = stack.size();
            while(size-- >0){
                char dig = stack.removeLast();
                if(leadingZero && dig =='0') continue;
                leadingZero = false;
                sb.append(dig);
            }
            return sb.toString().equals("") ? "0" : sb.toString();
        }


        // Requires calculating the first peak k time. Total Runtime: O(N*K);
        public String removeKdigitsFindingKPeaks(String num, int k) {
            char[] numChar  = num.toCharArray();
            int len = numChar.length;
            while(k > 0){
                int i=0;
                while(i+1 < len && numChar[i] <= numChar[i+1]) i++;
                // remove digit at i and move left;
                removeAndMoveLeft(numChar, i, len);
                len--;
                k--;
            }
            // Convert the char array back to the string.
            StringBuilder sb = new StringBuilder();
            int i=0;
            while(i < len && numChar[i]=='0') i++; // Removing leading zeroes from the final result;
            for( ;i < len ; i++){
                sb.append(numChar[i]);
            }
            return sb.toString().equals("") ? "0" : sb.toString();
        }

        private void removeAndMoveLeft(char[] numChar, int idx, int len){
            System.out.println("Removing : " + numChar[idx]);
            for(int i=idx; i < len-1; i++){
                numChar[i] = numChar[i+1];
            }
        }

  public static void main(String[] args) {
    RemoveKDigits kd = new RemoveKDigits();
    System.out.println(kd.removeKdigits("1432219", 3));
    System.out.println(kd.removeKdigits("10200", 1));
    System.out.println(kd.removeKdigits("10", 2));
  }
}
