package com.leetcode.medium;

import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

    /*
    - What kind of values does itertor support. Are null values supported.
    - What happens if the client calls peek() or next() w/o calling the
    hasNext(). Expected behavior should be to return null or throw an exception.
    -
    */

    Iterator<Integer> itr;
    boolean isPeeked;
    Integer peekedVal;


    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        itr = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if(isPeeked)
            return peekedVal;
        if(!itr.hasNext()) throw new java.util.NoSuchElementException();
        isPeeked = true;
        peekedVal = itr.next();
        return peekedVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(isPeeked)
        {
            Integer res = peekedVal;
            isPeeked = false;
            peekedVal = null;
            return res;
        }
        if(!itr.hasNext()) throw new java.util.NoSuchElementException();
        return itr.next();
    }

    @Override
    public boolean hasNext() {
        if(isPeeked || itr.hasNext()) return true;
        return false;
    }


}
