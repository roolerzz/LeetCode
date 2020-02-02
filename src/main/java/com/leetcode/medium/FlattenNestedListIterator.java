package com.leetcode.medium;

import java.util.*;
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 */
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

/*
* Suggested by @Stephanpocchman here -> https://leetcode.com/problems/flatten-nested-list-iterator/discuss/80146/Real-iterator-in-Python-Java-C%2B%2B
* Doesn't puts all the element on the stack eagerly.
* */
class NestedIterator implements Iterator<Integer> {

    /*
    IDEA:
    Another potential solution that avoids copying all of the data in the list onto the stack. We push the list's iterator on the stack to remember which element of the list should be processed.

    */
    Deque<ListIterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        stack.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        if(!hasNext()) throw new java.util.NoSuchElementException();
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext())
                stack.pop();
            else{
                NestedInteger curr = stack.peek().next();
                if(curr.isInteger())
                    // This since the call to the next above incremented the cursor, call to previous takes it back so that next actually retrieves that element. Comparison with current is to make is concise, we could have just returned true in the next line.
                    return stack.peek().previous()==curr;
                stack.push(curr.getList().listIterator());
            }
        }
        return false;
    }
}

public class FlattenNestedListIterator implements Iterator<Integer> {

    /*
    IDEA:
    So we need to start with pointing to the first element in the list, and unfold it multiple levels if its a Nested list. So as I am nesting inside, how do I remember where was I last.
    e.g. I started at the outermost layer @ idx 0, went inside layer 1 idx 0, layer2 idx 0..
    layer3 idx0 which was an integer, processed that and go to the next element? well that would be next index. Once layer 3 is done, we come back to last index we processed on layer to and increment that.
    We can store the NestedLists on a stack and the corresponding indices iterator is on on another. This makes the solution much more complicated to deal.
Instead, what we can do is, just put all the elements of the base layer list starting from end to front on the stack. Element to be processed(@ idx0) will be at the top to be processed at.
Each next element on the top should be processed on the same order, if Integer, just return and pop(), next element is @ the top of the stack ready to be procseed. If the top was instead a nested List, unflatten that individually from back to front in the same way and recurse until the top is an integer.

    */
    Deque<NestedInteger> stack;

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stack = new LinkedList<>();
        ListIterator<NestedInteger> itr = nestedList.listIterator(nestedList.size());
        while(itr.hasPrevious()){
            stack.push(itr.previous());
        }
        // We don't know the list implementation of the backing list, could be arraylist or could be LinkedList. Traversal's worst case time complexity is O(N^2) since there isn't a direct index allowed, and each index accessed takes O(N) in the worst case.
        //     for(int i=nestedList.size()-1; i>=0 ; i--){
        //     stack.push(nestedList.get(i));
        // }
        pointToNextInteger();
    }

    private void pointToNextInteger(){
        while(!stack.isEmpty()){
            NestedInteger top = stack.peek();
            if(top.isInteger())
                break;
            // If top was a nested list, recursively/iteratively unfold it.
            stack.pop();
            List<NestedInteger> topList = top.getList();
            ListIterator<NestedInteger> itr = topList.listIterator(topList.size());
            while(itr.hasPrevious()){
                stack.push(itr.previous());
            }
            // for(int i=list.size()-1; i>=0; i--){
            //     stack.push(list.get(i));
            // }
        }
    }

    @Override
    public Integer next() {
        if(!hasNext()) throw new java.util.NoSuchElementException();
        NestedInteger top = stack.pop();
        pointToNextInteger();
        return top.getInteger();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

}
