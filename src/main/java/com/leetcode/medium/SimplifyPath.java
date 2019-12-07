package com.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/simplify-path/
public class SimplifyPath {

    /*
    - How exactly is my input given? String fits into memory, because there is usually an inherent restriction on file name sizes. Atleast in windows.
    - Possible characterset( A-Z, a-z, 0-0, /, \, ., ..)
    - File names can start with number?

    - Possible soln.
    - Using stack:
        - Starting from i = 0, i< n-1; i = /
           - Start a new indx form j = i+1 <= n j = / or end of array.
           - Find the substring b/w i+1 and j.
           - if "." continue.
           - else if ".." stack.pop(); else continue if stack is empty.
           - else stack.push(substring);
        - i = j;
        - Once we have the stack of directory names, String result = "";
            Until the stack is empty(),
            result = stack.pop() + "/" + result;
        - return result;
        O(N) elements with O(n) space.
        - what happens in case path is empty string? return root?
    */

    /*
    /home/
    /
    //////
    / /(What to do with white space?)
    /a/../b/../c/..
    /a/b/c/
    /a/b//.
    /a/b/..
    ""

    */

    public String simplifyPath(String path) {
        if(path.equals("")) return "/";
        Deque<String> stack = new LinkedList<>();
        // Set<String> skip = new HashSet<>(Arrays.asList("", "..","."));
        for(String dir : path.split("/")){
            if(dir.equals("..") /*&& !stack.isEmpty()*/) /*stack.pop();*/ stack.poll();
                // else if (!skip.contains(dir)) stack.push(dir);
            else if (!dir.equals(".") && !dir.equals("")) stack.push(dir);
        }
        /*String result = "";
        for(String dir : stack)
            result = "/" + dir + result;
        return result.isEmpty() ? "/" : result;
        */

        StringBuilder sb = new StringBuilder();
        if(stack.size()==0) return "/";
        while(stack.size()!=0) sb.append("/").append(stack.pollLast());
        return sb.toString();
    }


}
