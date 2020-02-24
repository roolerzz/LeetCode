package com.companyspecific.Instacart;

import java.util.*;

public class Top3StringsMaxProperties {

  /*
   * Given a list of strings, containing characters from following characterset, return top 3 strings that have max score
   * w.r.t given properties
   *   CharacterSet:
   *       - small english letters (a-z).
   *       - sign(+, -, =)
   *
   * Priority Order:
   * 1. All letters from english alphabet.
   * 2. All equal Sign
   * 3. all different sign.
   * 4. all different characters.
   * 5. Others.
   *
   * */

  /*
   * - What is my character set looks like.
   * - Does my input List of strings fits into the memory.
   * - What happens in case of a tie.
   * - not enough strings in the input. Return lesser of the size of the input or 3.
   */

  public static void main(String[] args) {
    List<String> inp = Arrays.asList("abc","aaa", "bbb", "ccc","+++","+-=");
      System.out.println(Arrays.asList(top3Strings(inp)));
  }

    public static String[] top3Strings(List<String> input){

        Collections.sort(input, (a,b) -> {
            return score(b) - score(a);
        });
        for(String str : input){
            System.out.println("Score for string " + str + " is " + score(str));

        }
        List<String> res =  new ArrayList<>();
        for(int i=0; i < 3; i++){
            res.add(input.get(i));
        }
        return res.toArray(new String[3]);
    }

    private static int score(String str){

        Set<Character> uniqueLetter = new HashSet<>();
        Set<Character> uniqueSign = new HashSet<>();
        for(char c : str.toCharArray()){
          if(c >='a' && c <= 'z')
          {
              uniqueLetter.add(c);
          }

          else if (c == '+' || c == '=' || c == '-')
          {
              uniqueSign.add(c);
          }

        }

        if(uniqueLetter.size() != 0 && uniqueSign.size()!=0) return 1;
        else if(uniqueSign.size() != 0){
            if(uniqueSign.size() != 1) return 3;
            else return 4;
        }
        else if(uniqueLetter.size() != 0){
            if(uniqueLetter.size() != 1) return 2;
            else return 5;
        }
        // empty string.
        return 0;
    }


}
