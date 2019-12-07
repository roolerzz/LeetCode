package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAllAnagramsInAString {

    /*
    - How is i/p given, does both the strings fit into the memory?
    - What does my character set looks like, is this just a-z or A-Z or numbers(alphanumerics) etc.
    - Upper case vs lowercase.
    - Can p be bigger than s, else we can do I/P validation.
    - I have to return all the start indices. e.g. aaaa , aa - [0,1,2]
    1. Brute Force approach: Generate all possible anagrams of P(of size m) m!, and then do a substring match(finding the index) in the string S(of size n) m*n,
    Total runtime: O(m*n *m!).
    2. Sort P. and then Generate all the substrings of size m(n-m), from S.
    FOr each of those substrings, sort it and then match it with S.
    All substring of size M  in S = N-M(Also remember their start index.)
    Sorting a the substring: O(MlogM)
    Exact string match: O(M)
    Total: O(M * MLOGM * N). Cubic.
    Put the result onto a HashSet for uniqueness.
    -
    */

    //"cbaebabcd" , "abc"
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92059/O(n)-Sliding-Window-JAVA-Solution-Extremely-Detailed-Explanation
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s == null || p == null || s.length() == 0 || p.length() == 0 || s.length() < p.length())
            return res;
        int[] map = new int[26];
        for(int i = 0 ; i < p.length(); i++)
            map[p.charAt(i)-'a']++;
        int len = p.length(), diff=len;
        int start = 0, end = 0;

        // Setup the window of size 0-p.length()-1
        for(end = 0 ; end < len ; end++){
            char endChar = s.charAt(end);
            map[s.charAt(end)-'a']--;
            if(map[endChar-'a'] >=0)
                diff--;
        }

        if(diff == 0)
            res.add(0);
        // Now once the window is setup, for every next character that gets added to the window, remove the first character form the window and update the counts and diffs accordingly. Once the diff goes to 0 for that round, means we found a match, add that to the list of results.
        while(end < s.length()){
            char startChar = s.charAt(start);
            if(map[startChar-'a'] >= 0)
                diff++;
            map[startChar-'a']++;
            start++;

            char endChar = s.charAt(end);
            map[endChar-'a']--;
            if(map[endChar-'a'] >=0)
                diff--;
            if(diff == 0)
                res.add(start);
            end++;

        }
        return res;
    }


    public List<Integer> findAnagrams5(String s, String p) {
        if(s.length() < p.length()) return new ArrayList<>();
        char[] pchar = p.toCharArray(); // [a,b,c]
        int[] pmap = new int[26];
        countOccurence(p, pmap);
        List<Integer> res = new ArrayList<>();
        int[] smap = new int[26];
        int m = p.length();
        int n = s.length();
        countOccurence(s.substring(0,m),smap);
        if(mapMatch(smap,pmap)) res.add(0);
        for(int i = 1; i <= n-m;i++){
            // Optimze so that substring and counting doesn't repeat. and only a constant time operation for bigger strings.
            update(smap,s,i-1, false);
            update(smap,s,i+m-1, true);
            if(Arrays.equals(smap,pmap))
                res.add(i);
        }
        return res;
    }
    private void update(int[] smap,String s, int i, boolean isAdd){
        if(isAdd)
            smap[s.charAt(i)-'a']++;
        else
            smap[s.charAt(i)-'a']--;
    }

    private void countOccurence(String str, int[] countMap){
        for(int i = 0 ; i < str.length(); i++)
            countMap[str.charAt(i)-'a']++;
    }

    public List<Integer> findAnagrams3(String s, String p) {
        char[] pchar = p.toCharArray();
        int[] pmap = new int[26];
        countOccurence(p, pmap);
        int m = p.length(); // 3
        int n = s.length(); //9
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i <= n-m;i++){
            int[] smap = new int[26];
            countOccurence(s.substring(i,i+m),smap);
            if(mapMatch(smap,pmap))
                res.add(i);
        }
        return res;
    }
    private void countOccurence3(String str, int[] countMap){
        for(int i = 0 ; i < str.length(); i++)
            countMap[str.charAt(i)-'a']++;
    }

    private boolean mapMatch(int[] a, int[] b){
        for(int i = 0 ; i < a.length;i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        char[] pchar = p.toCharArray(); // [a,b,c]
        int m = p.length(); // 3
        int n = s.length(); //9
        Arrays.sort(pchar); //[a,b,c]
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i <= n-m;i++){ // 0 - 6
            char[] schar = s.substring(i, i+m).toCharArray();
            Arrays.sort(schar);
            if(matches(pchar,schar))
                res.add(i);
        }
        return res;
    }

    private boolean matches(char[] a, char[] b){
        if(a.length != b.length) return false;
        for(int i = 0 ; i < a.length;i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }


}
