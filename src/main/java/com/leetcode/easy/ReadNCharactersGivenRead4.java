package com.leetcode.easy;

//https://leetcode.com/problems/read-n-characters-given-read4
public class ReadNCharactersGivenRead4 {


    class Reader4 {
        // Just a placeholder method w/o implementation to successfully compile the code.
        public int read4(char[] buff){
            return -1;
        }
    }

    /**
     * The read4 API is defined in the parent class Reader4.
     *     int read4(char[] buf);
     */
    public class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {
            int charsReadSoFar=0;
            char[] temp = new char[4];
            while(charsReadSoFar < n){
                int cRead = read4(temp);
                if(cRead==0) break;
                int count = Math.min(cRead, n-charsReadSoFar);
                for(int i = 0; i < count ;i++){
                    buf[charsReadSoFar++] = temp[i];
                }
            }
            return charsReadSoFar;
        }
    }

}
