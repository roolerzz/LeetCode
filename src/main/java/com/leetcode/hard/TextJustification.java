package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/text-justification
public class TextJustification {

    List<String> result;
    /*
    The solution is divided into two parts:
    - First one is for counting the max number of words which can fit into one line, i.e. helper() function does
    it and also passes the next index to be traversed in the next turn.
    - Second part serve as a string editor, i.e. addToResult() uses the actual valid words lengths (len) and
    index of start and end (both inclusive) to count the spaces to be added.
        - A slight weird hanlding of lines which has only single word fitting or the last line. In both the cases,
        there would be bunch of spaces being inserted at the end. Also, for the last line, where would be only 1 space
        b/w words, instead of possible multiple spaces for other lines(due to the need for balancing).

      Potential questions to ask to the interviewer?
      - Is it possible that word length could be greater than max width.
      - How to handle invalid input? Say no words in the array or negative or zero value for max width etc.
      -
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        result = new ArrayList<>();
        helper(words, maxWidth, 0);
        return result;
    }

    private void helper(String[] words, int maxWidth, int start){
        if(start >= words.length) return;
        int totalWidth =0; // Includes the length of the words that can fit onto the line plus additional space b/w the words.
        int wordsWidth=0; // length of the words that fits onto the line w/o the extra spaces.
        int next = -1; // This is the index of the word that would start the next line.
        int i=start; // for iteration
        while(totalWidth < maxWidth && i < words.length) {
            totalWidth += words[i].length();
            if(totalWidth > maxWidth){
                next = i;
                break;
            }
            wordsWidth += words[i].length();
            totalWidth++; // Add space after adding the word.
            i++;
        }
        if(next == -1) // In case all the words fit onto the line and there isn't any break
            next = i;
        addToResult(words, maxWidth, start, next-1, wordsWidth);
        helper(words, maxWidth, next);
    }

    private void addToResult(String[] words, int maxWidth, int start, int end, int wordsWidth){
        StringBuilder sb = new StringBuilder();
        int noOfPos = end-start; // Positions to insert space on each line = 1 less than number of words on each line cuz you need to ignore the last word.
        int spacesPerPos =(noOfPos == 0) ? 0 : (maxWidth - wordsWidth)/noOfPos;
        int moreSpaces = (noOfPos == 0) ? 0 : (maxWidth - wordsWidth)%noOfPos;
        boolean lastLine = false;
        if(noOfPos == 0 || end == words.length-1) // For the case of Last line(end = words.length-1)
            // or the line which only has 1 word noOfPos == 0, it needs to handled in a slightly different way.
        {
            lastLine = true;
            spacesPerPos = 1;
        }
        for(int k=start; k <= end; k++){
            sb.append(words[k]);
            if(k == end) break; // Do not add any space after the last word.
            for(int i=0 ; i < spacesPerPos; i++) // For last line, spacesPerPos value = 1 would take care to only add 1 space after each word.
                sb.append(" ");
            // Balancing space, and keeping the extra ones as left as possible.
            if (!lastLine && moreSpaces-- > 0)
                sb.append(" ");
        }

        // If last line or only 1 word on that middle lines, add bunch of spaces at the end.
        if(lastLine){
            int spaceAtEnd = maxWidth-sb.length();
            for(int i=0; i < spaceAtEnd; i++)
                sb.append(" ");
        }
        result.add(sb.toString());
    }

}
