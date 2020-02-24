package com.leetcode.medium;


import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// https://leetcode.com/problems/basic-calculator-ii/
public class BasicCalculatorII {
    /*
    Questions:
    - Negative or positive integers?
    - Would be valid?
    - use of braces for ordering of operations?
    - Only integers and operators and empty spaces.
    - consecutive digits in the string fits the width of integer correct?

    Idea is:
    Start scanning the string for numbers.
    Do until the end of string.
    - If space, skip.
    - else if operator
        - Look for the operator. If the operator on the top of the stack is of lower precedence than this operator, push current operator onto stack.
        - else process current state of stack until the current operator is higher precedence than the top of the stack.
    - else
        - Starting at index i
        - start j at i.
        - increment j until next number found.
        - Push onto number stack.
        - Skip the spaces.

    - Process the remaining ites on the operator stack.
    - return the top element of the num stack.


    */
    public int calculate(String s) {
        Deque<Integer> num = new LinkedList<>();
        Deque<Character> op = new LinkedList<>();
        Map<Character,Integer> prec = new HashMap<>();
        prec.put('-',1);
        prec.put('+',1);
        prec.put('*',3);
        prec.put('/',3);
        int i=0;
        while(i < s.length()){
            if(s.charAt(i) == ' ') {
                // System.out.println("Its a space at index " + i + ". Going to ignore that.");
                i++;
                continue;
            }
            // Its a operator
            else if (prec.containsKey(s.charAt(i))){
                char currOp = s.charAt(i);
                // System.out.println("Its an operator at index " + i + " " +  currOp +". Pusing onto stack");
                if(op.isEmpty() || prec.get(op.peek()) < prec.get(currOp))
                {
                    // System.out.println("Pushing onto operator stack " + currOp);
                    op.push(currOp);
                }
                else {
                    // While the operators on the stack are bigger, pop them out and process.
                    process(op, num, currOp, prec);
                    op.push(currOp);
                }
                i++;
            }
            // Its a number. Find the full number.
            else {
                int j = i;
                while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9')
                    j++;
                int number = Integer.valueOf(s.substring(i,j));
                // System.out.println("Pushing onto number stack: " + number);
                num.push(number);
                i = j;
            }
        }
        // Process if there are remaining increasing order numbers.
        process(op,num,'-', prec);
        return num.pop();
    }

    private void process(Deque<Character> op, Deque<Integer> num, char currOp,  Map<Character,Integer> prec){
        while(num.size() >=2 && !op.isEmpty() && prec.get(op.peek()) >= prec.get(currOp))
        {
            char top = op.pop();
            int second = num.pop();
            int first = num.pop();
            int result = apply(first, second, top);
            num.push(result);
        }
    }

    private int apply(int first, int sec, char top){
        // System.out.println("Applying operator" + top + " on the operand : " + first + " and " + sec);
        switch(top){
            case '-':
                return first-sec;

            case '+':
                return first+sec;

            case '*':
                return first*sec;

            case '/':
                return first/sec;
            default:
                return -1;
        }
    }


}
