package com.leetcode.easy;

// https://leetcode.com/problems/verifying-an-alien-dictionary/
public class VerifyingAnAlienDictionary {

  public boolean isAlienSorted(String[] words, String order) {
    if (words == null || words.length == 0 || order.equals("")) return true;
    int[] indexOrder = new int[26];
    for (int i = 0; i < order.length(); i++) {
      indexOrder[order.charAt(i) - 'a'] = i;
    }
    for (int i = 0; i < words.length - 1; i++) {
      if (!isSmaller(words[i], words[i + 1], indexOrder)) return false;
    }
    return true;
  }

  private boolean isSmaller(String s1, String s2, int[] indexOrder) {
    for (int k = 0; k < Math.min(s1.length(), s2.length()); k++) {
      char ci = s1.charAt(k);
      char cj = s2.charAt(k);
      if (ci != cj) return compareChar(ci, cj, indexOrder);
    }
    return s1.length() < s2.length();
  }

  private boolean compareChar(char ci, char cj, int[] indexOrder) {
    // for +ve indexes, this is okay, but if the char is not in the alphabet, it returns -1.
    // Bottleneck. Insted of doing indexOf for every pair of words, which probably can take more
    // time,
    // Keep an array index by order alphabet of size 26, which tells which character is at
    // lower/higher position.
    // return order.indexOf(ci) <= order.indexOf(cj);
    return indexOrder[ci - 'a'] < indexOrder[cj - 'a'];
  }
}