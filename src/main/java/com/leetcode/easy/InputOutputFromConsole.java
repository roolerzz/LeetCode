package com.leetcode.easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputOutputFromConsole {

	public static void main(String[] args) {

		//BufferedReader br2 = new BufferedReader(new FileReader(new File("")));
		
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = "";
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			while((str = br.readLine())!=null) {
				bw.write(str);
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
