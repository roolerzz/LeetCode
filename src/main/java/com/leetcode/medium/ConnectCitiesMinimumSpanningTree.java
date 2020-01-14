package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectCitiesMinimumSpanningTree {

    public class Connection {
        public String city1, city2;
        public int cost;
        public Connection(String city1, String city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }
    }


    /*
     * - Union find to know if adding the edge creates a cycle, or if they are already connected, before using that connection.
     * - To use the array based union find operation, your objects should map to integers[0,n). Use Hashmap to map string values to integers?
     * - int[] uf data structure.
     * - union(int p, int q) // Connects the groups nodes p and q are part of.
     * - find(int p, int q) // Find if 2 nodes are in the same connected component. Return false if not.
     * - Sort the connections in the ascending order of weights. If the weights are same, sort by String cities name city1 and city2 inorder.
     * */
    public List<Connection> lowestCost(List<Connection> connections) {
        List<Connection> result = new ArrayList<>();
        map =  new HashMap<>();
        connections.sort((a,b)-> {
            if(a.cost != b.cost) return a.cost-b.cost;
            else if (!a.city1.equals(b.city1)) return a.city1.compareTo(b.city1);
            else return a.city2.compareTo(b.city2);
        });

        int id= 0;
        // Adds all the connections.
        for(Connection conn : connections){
            if(!map.containsKey(conn.city1))
                map.put(conn.city1, id++);
            if(!map.containsKey(conn.city2))
                map.put(conn.city2, id++);
        }
        uf = new int[id+1];
        for(int i=0 ; i <= id; i++){
            uf[i] = i;
        }

        // Consider the edges in the order of their weights. Add the edge b/w then unless it creates a cycle.
        for(Connection conn : connections){
            if(!find(conn.city1, conn.city2)){
                union(conn.city1, conn.city2);
                result.add(conn);
            }
        }
        return result;
    }

    private void union(String city1, String city2){
        uf[root(map.get(city1))] = uf[root(map.get(city2))];
    }

    private boolean find(String city1, String city2){
        return root(map.get(city1)) == root(map.get(city2));
    }

    private int root(int p){
        while(p != uf[p])
            p = uf[p];
        return p;
    }

    int[] uf;
    Map<String, Integer> map;

}
