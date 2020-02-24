package com.leetcode.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReadingFromSTDIN {

    public static void main3(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] tokens = s.nextLine().split(" ");
        System.out.println(Arrays.asList(tokens));
        s.close();
    }

    public static void main4(String[] args) {
        Scanner s = new Scanner(System.in);
        StringTokenizer st  = new StringTokenizer(s.nextLine());
        List<String> tokens = new ArrayList<>();
        while(st != null && st.hasMoreElements()){
            tokens.add(st.nextToken());
        }
        System.out.println(tokens);
        s.close();
    }
    public static void main5(String[] args) {
        Scanner s = new Scanner(System.in);
        List<String> tokens = new ArrayList<>();
        while(s.hasNext()){
            tokens.add(s.next());
        }
        System.out.println(tokens);
        s.close();
    }

    public static void main6(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> tokens = new ArrayList<>();
        String str = "";
//        System.out.println(tokens);
//        s.close();
        try {
            while((str=br.readLine()) != ""){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//            tokens.add(s.next());
    }


    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            String line = "";
            while(!(line = input.nextLine()).isEmpty()){
                System.out.println(line);
            }
        }

    }



    public static void main2(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        try {
            if (((str = br.readLine()) != null)){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner s = new Scanner(System.in);
        s.nextInt();
        s.next();
        s.nextLine();
        s.hasNextLine();
//            s.next
//        String st = s.next();
//        StringBuilder sb = new StringBuilder();
//        while(s.hasNext()){
//            if(s.hasNextInt()){
//                int res = s.nextInt();
//                sb.append(res);
//                System.out.println("reading int");
//            }
//            else if (s.hasNextDouble()){
//                double res = s.nextDouble();
//                sb.append(res);
//                System.out.println("reading double");
//            }
//            else {
//                sb.append(s.next());
//                System.out.println("reading string");
//            }
//
//        }
//
////        int it = s.nextInt();
//        System.out.println(sb.toString());
////        s.nextDouble();
////        s.nextFloat();
////        s.nextBoolean();
////        s.nextByte();
////        s.nextLong();
//        s.close();



    }
}
