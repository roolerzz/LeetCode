package com.leetcode.medium;

import java.util.*;

// https://leetcode.com/problems/reconstruct-itinerary/
public class ReconstructItinerary {
    // Deque<String> route = new LinkedList<>();
    List<String> route = new LinkedList<>();
    Map<String, PriorityQueue<String>> graph = new HashMap<>();

    /*
    To form an itinerary given the tickets("from","to"), one is expected to tell what is the paths/edges a person can visit from a starting vertex. Also, in case of a connected component, the questions is asking paths taking all the tickets(edges) in mind. This basically translates to a finding a euler trail in the directed graph(each vertex can be visited more than once).
    - To find the euler tour(it can only be found if you start the traversal from one of the vertex with odd degrees. If none of the vertex has odd degrees, traversal can be started from any arbitrary vertex).
    - So starting with JFK, just visit only edge exactly once in DFS manner, visiting children first before processing the current node.
    - To make sure you visit each edge exactly once for a given node, just remove the edge before visiting. This could be a remove operation from a queue/linked list if thats what being used to keep track of neighbors(edges).
    */

    public List<String> findItinerary1(List<List<String>> tickets) {
        for(List<String> ticket : tickets)
            graph.computeIfAbsent(ticket.get(0),k -> new PriorityQueue<String>()).add(ticket.get(1));
        visit("JFK");
        return route;
    }

    private void visit(String airport){
        while(graph.containsKey(airport) &&!graph.get(airport).isEmpty())
            visit(graph.get(airport).poll());
        // route.addFirst(airport);
        route.add(0,airport);
    }

    /*
        Iterative:
        - Save the current node onto the stack and start processing its edges. Also make sure to remove those edges before starting to process its children.
        - Once all the neighbors(outbound edges) of the current node are processed, add that to the Stack.
    */
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> ticket : tickets)
            graph.computeIfAbsent(ticket.get(0),k -> new PriorityQueue<String>()).add(ticket.get(1));
        Deque<String> stack = new LinkedList<>();
        Deque<String> result = new ArrayDeque<>();
        stack.push("JFK");
        while(!stack.isEmpty()){
            while(graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty())
                stack.push(graph.get(stack.peek()).poll());
            result.addFirst(stack.pop());
        }
        return new ArrayList<>(result);
    }

}
