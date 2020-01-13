package com.leetcode.hard;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-window-substring/
// https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
public class MinimumWindowSubstring {

    /*
    Approach 1:
    Brute force:
    Find all the substrings of length 1:n N^2.
    - For each of the substring, see if it contains all the required characters as the stirng.T. O(N) ops.
    - TOtal runtime would be O(N^3)

    Approach2:
    Improve 1:
    Finding if the subtring (i,j) stillMatches, then we are again calculating if i+x, j stillMatches, to know if thats a smaller substring.
    or to think it the other way around.
    If we have already computed windowSubtring(i,j) in time proportional to O(N), deriving windowSubstring(i, J+1) should not be O(N) ops, should only be O(1), by using the results of (i,j) already computed.
    So for every starting position i(n possiblitieies) in the string, take j from i through N-1 increasing the window. each adddition is a constant time operation. there are total of N additions in the worst case.
    O(N^2) operation.

    Approach 3:
    start = 0, end=0.
    - Keep track of characters needed in a int[] 26 array using T.
    - B/w Start, end , keep track of temp int[26] array only incrementing marking the characters needed in T. Until you find a match keep on incrementing the end.
    - once a match is found, compare and keep if minimum.
    - Increment start. Do this until start is exhausted.
    O(N). Pointers only moving in 1 direction towards the end.
    Incrementing start would atmax decrement the count from the array.
    Incrementing end would either increment the count of one of the matching character or either leave it.
    */
    public String minWindow1(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String minStr="";
        int[] charCountT = countChars(t);
        for(int i=0 ; i< s.length(); i++){
            for(int j=i+1; j <= s.length(); j++){
                if(match(s, i, j, charCountT) && j-i < minLen){ // Check for Off by 1
                    minLen = j-i;
                    minStr = s.substring(i,j);
                }
            }
        }
        return minStr;
    }

    private boolean match(String s, int i, int j, int[] charCountT){
        int[] charCountS = new int[256];
        for(int k = i ; k < j; k++){
            char curr = s.charAt(k);
            if(charCountT[curr] != 0 && charCountT[curr] != charCountS[curr]){
                charCountS[curr]++;
                if(Arrays.equals(charCountT, charCountS)) return true;
            }
        }
        return false;
    }

    private int[] countChars(String t){
        int[] res = new int[256];
        for(int i=0; i < t.length(); i++){
            res[t.charAt(i)]++;
        }
        return res;
    }

    public String minWindow2(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String minStr="";
        int[] charCountT = countChars(t);
        for(int i=0 ; i< s.length(); i++){
            int[] charCountS = new int[256];
            int j=i;
            while(j < s.length()){
                char curr = s.charAt(j++);
                if(charCountT[curr] != 0 && charCountT[curr] != charCountS[curr])
                    charCountS[curr]++;
                if(j-i < minLen && Arrays.equals(charCountS, charCountT)){
                    minLen = j-i;
                    minStr = s.substring(i, j);
                    break;
                }
            }
        }
        return minStr;
    }

    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        int[] charCountT = new int[256];
        for(int i=0; i < t.length(); i++){
            charCountT[t.charAt(i)]++;
        }
        int[] res = {-1,-1};
        int nCharsT = t.length();
        int nCharsS = s.length();
        int start = 0, end = 0;
        while(end < nCharsS){
            while(end < nCharsS && nCharsT > 0){
                if(--charCountT[s.charAt(end++)] >= 0)
                    nCharsT--;
            }
            while(start < end && nCharsT ==0){
                if(end-start < minLen){
                    minLen = end-start;
                    res = new int[]{start, end};
                }
                if(++charCountT[s.charAt(start++)] > 0)
                    nCharsT++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(res[0],res[1]);
    }


  public static void main(String[] args) {
        MinimumWindowSubstring m1 = new MinimumWindowSubstring();
//        String s = "tfsxfpbgrvlgngmrtgotjumbaxosseklkckrrlzljnrytfpolgjxcvycvounteafgkyaxylgleeglwuycfvecvgxmbmfhmmoykoykbgxhndsjzqneorjlzgxctckhqaibqgnrolybqskskxyyqhmqojsjocqeeyhugvlvglwwfjqqipqzwcrmmjvcoogsmopvjaqroontzglivcjtgagslbwbpdwetlbcnrtdhizhokploafvanpnqmpirnmkynmnghrnrqwicltrlaonndbersmihyshnguvplkhdhpgukxillvyqycksoszongdvbyloluusjamvzugmsfesjolahkqwturuuekhyezjgoffilrcswvekgetsurlqjhnpuuoywxfkcmuqexicumocsiupbihkrwclrqkpnrzsdvijuslohaqzknqlxyyouaansmehddzibjuvcfkjhcktrdhobhdxjfnbrriwxkgytatvifgpktlbwsqxzvwiajltdtradxazytugbdxlwimscerbbnldzwwcspexnewnzrvausnsdckfibhkquprrmvjdzgqfdffwahsvxkyzwdhzmtpjjhaxclaettjyyqwbaqwxauyyzfxobpxkyzjakjgfesediaekrchtwhuxnrlqikcgjljvwpbbhfgshmcaomwmtqjijxysjaatnjokzrlgwragddirrefkwqvintazwasjkitlaetyxueazptqtycsrxaetcfpcxaogwbicvgarncqcwixwmpnkpufrzwwzwmhsopvlxxckzumxcmwoblatffmhbiiaxpulgrydoaqquenyqjouvpncwzlaktahkwuqouweumuqqiohtcbotqtqpesbyukiqgbgaxlujfkzpagjfjzyzsqrxksxedfvjidkfogowtqltyuaiubjoraletiiyqfhyjtgzcuvvkhqjdrtzucoldbrymaweffcqbkqdflyruqcyjvzd";
//        String t = "ynrdribdizhqelgfwwid";
      String s = "ADOBECODEBANC";
      String t = "ABC";
        System.out.println(m1.minWindow(s, t));

  }
}
