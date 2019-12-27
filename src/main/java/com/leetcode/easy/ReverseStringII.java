package com.leetcode.easy;

// https://leetcode.com/problems/reverse-string-ii/
public class ReverseStringII {
    /*
        - String of length n.
        - For every 2k characters, reverse the first K.
        - If Less than K characters left, reverse them all.
        - If <2k and >=K chars left, reverse the first K and leave the rest.
        "abcdefg" k = 2
        characters left = length of string = 7.
        Start = 0 , end=6
        Null string - return null. O(N)
        Convert the string to a char array.
        while(start < end)
            int charsLeft = end-start+1;
            reverse(start , Math.min(start+k-1, end));
            // if  charsLeft >= 2K,
            //     reverse from (start, start + k -1)
            //     // charsLeft -= 2k;
            //     // start = start+2k;
            // else if charsLeft >=k
            //     reverse(start, start+k-1)
            //     // start = start + 2k;
            // else
            //     reverse(start, end);
            start = start + 2k;
            // charsLeft = Math.Max(0, charsLeft-2K);
        Return the string representation of the char array.
    */

        /*
             "abcdefghik" k=2                  start   end
             [b, a, c, d, e, f, g ]         0       6     r(0, 1)
             [b, a, c, d, f, e, g ]         4       6     r(4, 5)
             [b, a, c, d, f, e, g ]         8       6

        */
        public String reverseStr(String s, int k) {
            if(s == null || k < 1) return s;
            int start = 0, end = s.length()-1;
            char[] str = s.toCharArray();
            while(start < end){
                reverse(str, start, Math.min(start+k-1, end));
                start += 2*k;
            }
            return new String(str);
        }

        private void reverse(char[] str, int start, int end){
            while(start < end){
                char temp = str[start];
                str[start] = str[end];
                str[end]=temp;
                start++;
                end--;
            }
        }

    // Much more concise solution from the LC.
    public String reverseStrII(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }



}
