package com.leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/subdomain-visit-count/
public class SubdomainVisitCount {

    /*
    - CP domain safe to assume valid? Number followed by space followed by domain/sub-domain.
    - Use a hashmap to keep the count of each unique domain.
    - Iterate over the list of cp domains(count paired)
        - Extract count.
        - Extract the subdomain.
        - idx = 0;
        - Do this while substring is not empty.
            - substring = substring(idx);
            - Update the count of the subdomain(substring)in the list.
            - index = substring.indexOf(.)+1;
            - If index == 0, break.
    */
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> results = new ArrayList<>();
        if(cpdomains == null || cpdomains.length == 0) return results;
        Map<String, Integer> domainCount = new HashMap<String, Integer>();
        for(String cpdomain : cpdomains){
            // String[] arr = cpdomain.split(" ");
            int index = cpdomain.indexOf(' ');
            int count = Integer.parseInt(cpdomain.substring(0, index));
            String subStr = cpdomain.substring(index + 1);
            // int count = Integer.valueOf(arr[0]);
            // String subStr = arr[1];
            int idx = 0;
            do {
                subStr = subStr.substring(idx);
                domainCount.put(subStr,domainCount.getOrDefault(subStr, 0) + count);
                idx = subStr.indexOf(".")+1;
            }while(idx != 0);
        }
        for(Map.Entry<String, Integer> entry : domainCount.entrySet()){
            results.add(entry.getValue() + " " + entry.getKey());
        }
        return results;
    }

}
