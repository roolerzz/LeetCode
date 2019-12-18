package com.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;


//https://leetcode.com/problems/asteroid-collision
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        int[] empty = {};
        if(asteroids == null || asteroids.length < 2) return empty;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int ast : asteroids){
            collision: {
                while(!stack.isEmpty() && ast < 0 && stack.peek() > 0){
                    if(stack.peek() < -ast){
                        stack.pop(); continue;
                    }
                    else if(stack.peek() == -ast)
                        stack.pop();
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] res = new int[stack.size()];
        int k = stack.size()-1;
        while(k >=0){
            res[k--] = stack.pop();
        }
        return res;
    }

    // Using linked list (Deque) to simulate stack so that operations are still order 1 but the elements doesn't need to be reversed??
    public int[] asteroidCollision2(int[] asteroids) {
        int[] empty = {};
        if(asteroids == null || asteroids.length < 2) return empty;
        Deque<Integer> stack = new LinkedList<>();
        for(int ast : asteroids){
            collision: {
                while(!stack.isEmpty() && ast < 0 && stack.getLast() > 0){
                    if(stack.getLast() < -ast){
                        stack.removeLast(); continue;
                    }
                    else if(stack.getLast() == -ast)
                        stack.removeLast();
                    break collision;
                }
                stack.add(ast);
            }
        }

//        int[] res = new int[stack.size()];
//        int k = stack.size()-1;
//        while(k >=0){
//            res[k--] = stack.pop();
//        }
        return stack.stream().mapToInt(i->i).toArray();
//                toArray(new Integer[stack.size()]);
    }


    /*
    - Array of integers represeting size of astroids, and the direction they are moving in. +ve - right, -ve -left.
    - Small <> Large small explodes.
    - Same size - both explodes.
    - same direction- they never meet.
    - Max 10000 astroids, size in range -1000,1000
    [5,10,-5] => [5, 10]
    [8,-8] => []
    [10,2,-5] [10]
    [-2,-1,1,2] =? [-2,-1,1,2]

    */
}
