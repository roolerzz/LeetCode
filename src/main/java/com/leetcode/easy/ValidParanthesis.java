package com.leetcode.easy;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// https://leetcode.com/problems/valid-parentheses/
public class ValidParanthesis {
    /*
    O(N)
    O(N) space.
    */


    public boolean isValid1(String s) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('}','{');
        map.put(')','(');
        map.put(']','[');
        for(char c : s.toCharArray()){
            if(!map.containsKey(c))
                stack.push(c);
            else if(stack.isEmpty() || stack.peek() != map.get(c)) // Doesn't get matching bracket. Not valid.
                return false;
            else
                stack.pop(); // Remove the matching bracket from the stack.
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for(char c : s.toCharArray()){
            switch(c){
                case '{' :  stack[head++]='}';
                    break;
                case '(' :  stack[head++]=')';
                    break;
                case '[' :  stack[head++]=']';
                    break;
                default:
                    if(head == 0 || stack[--head] != c) return false;
                    break;
            }
        }
        return head==0;
    }

}
