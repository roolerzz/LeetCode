package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter {

    Map<String, Integer> timer;
    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        timer = new HashMap<>();
    }

    /*
    - Can there be multiple messages at the same timestamp, whether same key or different key.
    -
    */

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int time = timer.getOrDefault(message, -1); // -1 to see if the message doesn't exist.
        if(time == -1 || time <= timestamp-10){
            timer.put(message,timestamp);
            return true;
        }
        //
        return false;
    }

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

}
