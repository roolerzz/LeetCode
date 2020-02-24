package com.test;

public class Test {

  public static void main(String[] args) {
    //
      System.out.println(findHostName("http://leetcode.com/contest"));
  }

    private static String findHostName(String startUrl){
        // int start = startUrl.indexOf("http://");
        // int end = startUrl.indexOf(".com");
        System.out.println("Finding hostname for "+ startUrl);
        int end = startUrl.indexOf(".com");
        String FQDN = startUrl.substring(7, end);
        System.out.println("FQDN is " + FQDN);
        String[] chunks = FQDN.split("\\.");
        System.out.println("length of chunk array " + chunks.length);
        // String lastChunk
        return chunks[chunks.length-1 > 0 ? chunks.length-1 : 0] + ".com";
    }
}
