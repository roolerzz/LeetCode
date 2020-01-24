package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/strobogrammatic-number/
public class StrobogrammaticNumber {
        /*
            - 2, 3, 4, 5, 7(if present in the number non-strobogramatic)
            - 8, 6, 9, 1 and it reads the same after reversing and inverting the digits, then its a stobogrammatic number.
            -
            -
        */
        public boolean isStrobogrammatic2(String num) {
            Map<Character, Character> reverseMap = new HashMap<>();
            reverseMap.put('6','9');
            reverseMap.put('9','6');
            reverseMap.put('0','0');
            reverseMap.put('8','8');
            reverseMap.put('1','1');
            StringBuilder sb = new StringBuilder();
            for(int i=num.length()-1; i>=0; i--){
                char curr = num.charAt(i);
                if(!reverseMap.containsKey(curr)) return false;
                sb.append(reverseMap.get(curr));
            }
            return sb.toString().equals(num);
        }

        // Do a 2 pointer comparison instead of making a string builder in memory. O(1) O(1).
        public boolean isStrobogrammatic(String num) {
            Map<Character, Character> map = new HashMap<Character, Character>();
            map.put('6', '9');
            map.put('9', '6');
            map.put('0', '0');
            map.put('1', '1');
            map.put('8', '8');

            int l = 0, r = num.length() - 1;
            while (l <= r) {
                if (!map.containsKey(num.charAt(l))) return false;
                if (map.get(num.charAt(l)) != num.charAt(r))
                    return false;
                l++;
                r--;
            }

            return true;
        }
}
