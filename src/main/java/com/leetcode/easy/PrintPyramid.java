package com.leetcode.easy;

public class PrintPyramid {

/*
     pyramid(1)
//       '#'
//   pyramid(2)
//       ' # '
//       '###'
//   pyramid(3)
//       '  #  '    '  #  '
//       ' ### '    ' ### '
//       '#####' // '#####'
//

 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

    public static void main(String[] args) {
//     ArrayList<String> strings = new ArrayList<String>();
//     strings.add("Hello, World!");
//     strings.add("Welcome to CoderPad.");
//     strings.add("This pad is running Java " + Runtime.version().feature());

//     for (String string : strings) {
//       System.out.println(string);
//     }

        printPyramid(1, true);
        System.out.print("\n*************\n");
        printPyramid(2, true);
        System.out.print("\n*************\n");
        printPyramid(5, true);
        System.out.print("\n*************\n");
        printPyramid(10, true);


        System.out.print("\n*************\n");
        printPyramid(1, false);
        System.out.print("\n*************\n");
        printPyramid(2, false);
        System.out.print("\n*************\n");
        printPyramid(5, false);
        System.out.print("\n*************\n");
        printPyramid(10, false);
    }


    /*
      - Given level i
      - Find the (resLen) length of the string(or no of chars) in the final level.
        - Start with currL 1, and currLen=1. For each level incremented, add 2.(level-1 times)
      - Initialize a char array of len res Len. Initialize all the characts to ' '.
      - Set middle character as '#'.
      - Init start, and end as the middle Idx.
      - until start is bigger than 0 and end is lesser than length
        - set '#' at start and end.
        - Print the char array and a new line.
        - dec start and inc end.
      - return

    2nd arg boolean.
      false: same
      true: return pyramid hollowed out.

        #

        #
       ###

           #
          # #
         #   #
        ##   ##

         At each level from level 2 onwards,
         If I have more levels, carve out(reset) b/w idx, start+1, end-1

    */
    public static void printPyramid(int level, boolean hollow){
        int resLen = 1, finalLev=1;
        while(finalLev < level){
            resLen+=2;
            finalLev++;
        }

        char[] levelRep = new char[resLen];
        for(int i=0; i < levelRep.length; i++){
            levelRep[i]=' ';
        }
        // resLen Odd. 1, 3, 5,
        levelRep[resLen/2] = '#';
        printArray(levelRep);
        int start = resLen/2-1, end=resLen/2+1;
        int currLevel=2;
        while(start >=0 && end < resLen){
      /*
          #
         ###


         #
        # #
       #####
      */

            levelRep[start]='#';
            levelRep[end]='#';
            if(hollow){
                if(currLevel != finalLev)
                {
                    levelRep[start+1]=' ';
                    levelRep[end-1]=' ';
                }
                else {
                    fillLastLevel(levelRep, start, end);
                }
                currLevel++;
            }

            System.out.println();
            printArray(levelRep);
            start--;
            end++;
        }
    }
    private static void fillLastLevel(char[] levelRep, int start, int end){
        for(int k=start; k <= end; k++){
            levelRep[k]='#';
        }
    }


    private static void printArray(char[] levelRep){
        for(int i=0; i < levelRep.length; i++){
            System.out.print(levelRep[i]);
        }
    }



/*
Your previous Plain Text content is preserved below:

Welcome to your interviewing.io interview.

Once you and your partner have joined, a voice call will start automatically.

Use the language dropdown near the top right to select the language you would like to use.

You can run code by hitting the 'Run' button near the top left.

Enjoy your interview!
 */

}
