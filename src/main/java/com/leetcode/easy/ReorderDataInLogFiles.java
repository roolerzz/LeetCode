package com.leetcode.easy;

import java.util.Arrays;

// https://leetcode.com/problems/reorder-data-in-log-files/
public class ReorderDataInLogFiles {
    /*
        - Sort an array of string based on certain rules.
            - For each string, separate using the delimiter space.
            - get the first element as identifier.
            - Compare the first character of the second(1) elemment on the array.
                - if c1 is a digit and c2 is a letter(a-z) c2 is smaller. Vice versa otherwise.
                - if c1 and c2 are both digit. Lexicographically compare using all the elements in the array starting 1. If tie, use IDs.
                - if c1 and c2 are both characters same as above.
    */
    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) return new String[]{};
        Arrays.sort(
                logs,
                (s1, s2) -> {
                    int idx1 = s1.indexOf(" ") + 1;
                    int idx2 = s2.indexOf(" ") + 1;
                    char c1 = s1.charAt(idx1);
                    char c2 = s2.charAt(idx2);

                    boolean isDigit1 = Character.isDigit(c1);
                    boolean isDigit2 = Character.isDigit(c2);
                    if (!isDigit1 && !isDigit2) {
                        int temp = s1.substring(idx1).compareTo(s2.substring(idx2));
                        if (temp != 0) return temp;
                        else return s1.compareTo(s2);
                    }
                    return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
//
//                    if (Character.isDigit(c1) && Character.isLetter(c2)) return 1;
//                    else if (Character.isDigit(c2) && Character.isLetter(c1)) return -1;
//                    else if (Character.isLetter(c2) && Character.isLetter(c1)) {
//                        String tail1 = s1.substring(idx1);
//                        String tail2 = s2.substring(idx2);
//                        int temp = tail1.compareTo(tail2);
//                        if (temp != 0) return temp;
//                        else return s1.compareTo(s2);
//                    }
//                    else return 0;
                });
        return logs;
    }

    // Slightly Slower but concise.
    public String[] reorderLogFiles1(String[] logs) {
        if(logs == null || logs.length == 0) return new String[]{};
        Arrays.sort(
                logs,
                (log1, log2) -> {
                    String[] split1 = log1.split(" ", 2);
                    String[] split2 = log2.split(" ", 2);
                    boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                    boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
                    if (!isDigit1 && !isDigit2) {
                        int temp = split1[1].compareTo(split2[1]);
                        if (temp != 0) return temp;
                        else return split1[0].compareTo(split2[0]);
                    }
                    return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
                });
        return logs;
    }

    public static void main(String[] args) {
        //
        ReorderDataInLogFiles d1 = new ReorderDataInLogFiles();
        String[] input = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] output = d1.reorderLogFiles(input);
        System.out.println("Output is : ");
        for(String s: output)
            System.out.print(s + ", ");
    }
}
