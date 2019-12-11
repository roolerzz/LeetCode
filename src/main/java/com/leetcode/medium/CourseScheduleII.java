package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII {
    /*
    - No of courses.
    - 2D array, where each 1D array of size 2 represent the dependency [a,b] => b needs to be completed before a.
    - List<Integer> order;
    - Map<Integer, List<Integer>> graph;
    - Set<Integer> visited;
    - DFS on the directed graph for all the unvisited vetices. Topological sort.(Keep on adding the list into)
    - No ordering found, return empty array.Cycles in the graph. return empty array.
    First parse I/P, directed graph [a,b]
    - numCourses < 1 return empty array.
    - What happens if pre-requisite array is empty? No edges in the graph. While doing DFS, just add the current element to the stack.
    - convert the final stack/list to int array.

    */

    boolean isCycle = false;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] empty = {};
        if(numCourses < 1)  return empty;
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
//        List<Integer>[] graph = List<Integer>)new Object[numCourses];
        Map<Integer,List<Integer>> graph = new HashMap<>();
        makeGraph(numCourses, prerequisites, graph);
        boolean[] onStack = new boolean[numCourses];
        for(int i = 0 ; i < numCourses; i++){
            if(!visited[i]){
                dfs(graph, visited, onStack, res,i);
            }
        }
        int [] result = new int[res.size()];
        convertResult(res, result);
        return isCycle ? empty : result;
    }

    private void convertResult(List<Integer> lRes, int[] arrRes){
        for(int i = 0 ; i < lRes.size(); i++)
            arrRes[i] = lRes.get(i);
    }
    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] onStack, List<Integer> res, int course){
        visited[course] = true;
        onStack[course] = true;
        for(Integer depCourse : graph.get(course)){
            if(isCycle) {
                break;
            }
            if(!visited[depCourse]) {
                dfs(graph, visited,onStack, res, depCourse);
            }
             else if(onStack[depCourse]){
                 // Cycle.
                    isCycle = true;
             }
        }
        if(!isCycle) {
            res.add(course);
        }
        onStack[course] = false;
    }

    private void makeGraph(int numCourses, int[][] prerequisites, Map<Integer, List<Integer>> graph){
        for(int i = 0 ; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] req : prerequisites){
            if(!graph.containsKey(req[0]))
                continue;
            graph.get(req[0]).add(req[1]);
        }
    }


}
