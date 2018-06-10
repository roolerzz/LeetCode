package com.leetcode.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteOrder;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
	  System.out.println(ByteOrder.nativeOrder());
   /* Solution solution = new Solution();
    if(args == null || args.length !=2) throw new IllegalArgumentException("Invalid arguments to the program");
    System.out.println(solution.myGrep(args[0], args[1]));*/
 }
  
  private static StringBuilder result =new StringBuilder();
  
  
  private String myGrep(String matchingString, String srcFolder) {
	  File file = new File(srcFolder);
	  // recursively iterate over all the files with the directory.
	  try {
		recursivelyMatch(file,matchingString);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return result.toString();
  }


private void recursivelyMatch(File file, String matchingString) throws IOException {
	if(file.isDirectory()) {
		File[] listOfFiles = file.listFiles();
		for(File eachFile : listOfFiles)
				recursivelyMatch(eachFile,matchingString);
	}else {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String line = null;
		int lineNumber = 1;
		while((line = reader.readLine()) != null) {
			if(line.contains(matchingString)) {
				result.append(file.getAbsolutePath());
				result.append(":");
				result.append(lineNumber);
				result.append("\t");
				result.append(line);
				result.append("\n");
			}
			lineNumber++;
		}
		
	}
}
  
}