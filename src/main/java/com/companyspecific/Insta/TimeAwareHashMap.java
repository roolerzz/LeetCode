package com.companyspecific.Insta;

public class TimeAwareHashMap {

/*
* Problem Statement: Implement a hashmap with time awareness i.e. Key can store multiple values based on the time it was put.
* Operations to support: 1) put(key, val) 2) get(Key) // Get the last inserted value for the key. 3) get(key, timestamp) // Get the val for key which is closest to the given timestamp.
* 4) delete(key) 5) containsKey(key) ...
* When getting can provide no timestamp (get last inserted value), or one matching either the timestamp or the closest value to the given timestamp.
* */


/*
*  Clarifications?
*  - Size restricted or can support unlimited number of k,v pairs.
*  - Any restrictions on the number of values for a single key. If fixed, we can use arrays instead of list.
*  - what kind of keys should the ds support? Generic, Integers??
*  -
* */


/*
* Solution: Our custom hashmap can expose the above public APIs.
* Thoughts: 1) We can use generic Hashmap<Key,Val> to back our D.S which can support, put(key,val) get(key) delete(key) in O(1) time. But it doesn't
* supports storing multiple values in order of their insertion(timestamp) against their keys.
* 2) To deal with that, we can use a hashmap of <Key, LinkedList<Val>> that would support storing multiple values for a key. But in order to get closest value,
* we need to store additional information in ds. That would be my timestamp.
* 3) Use HashMap<Key, LinkedList<int[] or Pair(timestamp, val)>>  where first index stores the timestamp, and 2nd index stores the value. This allows searching for timestamp.
* That would be linear search in the list though.
* 4) Since the timestamp would be always increasing(sorted), we can somehow utilize the binary search to find the closest timestamp.
*   We can use 2 lists, one for timestamp and one for values. If binary search(need to modify) allows searching on list, we can find the index closest to it
*   where to look for. Get the value from the other list using the index.
*
*   If the list doesn't allow binary search, we need to use array, but then take care of re-sizing the array, everytime it becomes 75% full??
*       - [Update: Collections has binarySearch helper which can be applied to lists.]
*       HashMap<Key,DoubleList>
*           DoubleList{
*           List<Integer> timestamp; // Do a binarysearch to find either the timestamp, or the insertion point. If timestamp doesn't exist, B.S returns
*           -ve value which is -> (-(insertion_point)-1). Compare insetionPoint-1 and insertion point value to know which is closer.// Check for bounds in case of last index.
*           List<Integer> values;
*       }>
*   or
*   HashMap<Key,DoubleList>
*       DoubleList{
*           int[] timestamp;
*           int[] values;
*       }
*    With this if the lists become too long, deletion operation could be an O(N) operation. deleting from an array or list.
* 5) How about using this.
*   HashMap<Key, LinkedHashMap<TimeStamp, Value>> // With this, the deletion could be a constant time operation. Linked structure ensures the order of elements is
*   maintains in doubly linked list, but searching isn't O(1), bcoz binary search isn't applicable.
*
*
* */


}
