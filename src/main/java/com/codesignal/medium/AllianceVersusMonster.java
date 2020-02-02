package com.codesignal.medium;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AllianceVersusMonster {

    /*
    - Idea is to use a greedy approach in the sense that always send your best warrior(with max health followed by max damage point.). This needs to be dynamic. For this we can use a max PQ. Store a pair of {healthpoint, attachdamage}
    - At each round, peek the top element of MaxPQ. deduct the healthpoint of the monster. if < 0 return the elements on the PQ.
    - else pop the top of the queue. Reduce its health by attachDamage of monster. If health is > 0 push it back onth the maxPQ.

*/

    private class HealthAttack{
        int health, attackDmg, idx;
        HealthAttack(int health, int attackDmg, int idx){
            this.health = health;
            this.attackDmg = attackDmg;
            this.idx = idx;
        }
    }

/*

    Space: O(N) N is number of warriors.
    Time: O(N) building a max PQ, O(Size Of Monster Health * log(N)) THis is when the monster keep getting hit by the same warrior(warrior has to be continuously removed and added back to the queue. Additions costs O(logN) each time and can be done a max of O(Monster Health) times).
*/

    int allianceVersusMonster2(int[] healthPoints, int[] attackDamage) {
        PriorityQueue<HealthAttack> maxPQ = new PriorityQueue<>((a,b) -> {
            if(a.health != b.health) return b.health-a.health;
            return b.attackDmg-a.attackDmg;
        });

        for(int i=1; i< healthPoints.length; i++){
            maxPQ.offer(new HealthAttack(healthPoints[i], attackDamage[i], i));
        }

        while(!maxPQ.isEmpty()){
            HealthAttack curr = maxPQ.poll();
            System.out.println("Attacking monster whose health is " + healthPoints[0] + " by a warrior number " + curr.idx +" with health : "+ curr.health + " and doing damage: " + curr.attackDmg) ;
            healthPoints[0] -= curr.attackDmg;
            if(healthPoints[0] <=0) {
                System.out.println("Monster eliminated.");
                maxPQ.offer(curr); // Putting it back.
                break;

            }
            // HealthAttack curr = maxPQ.poll();
            System.out.println("Monster hitting back the warrior: "+ curr.idx + " whose current health is " + curr.health + " with a damage : " + attackDamage[0] + ". New health is: " + (curr.health-attackDamage[0]));
            curr.health -= attackDamage[0];
            if(curr.health <= 0)
            {
                System.out.println("Warrior number : "  + curr.idx + " dying.");
                continue;
            }
            maxPQ.offer(curr);
        }
        System.out.println("Elements in the queue are : " + maxPQ.size());
        return maxPQ.size();
    }
/*
healthPoints: [110, 30, 50]
attackDamage: [12, 11, 20]

healthPoints: [8, 6, 2]
attackDamage: [12, 11, 20]


*/


    public static int allianceVersusMonster(int[] healthPoints, int[] attackDamage) {
        int monsterHealth = healthPoints[0];
        int monsterAttackDamage = attackDamage[0];
        ArrayList<Warrior> arrayList = new ArrayList<>();
        for (int i = 1; i < attackDamage.length; i++) {
            arrayList.add(new Warrior(attackDamage[i], healthPoints[i], i));
        }
        int playersDeathStamina = 0;
        for (Warrior myPlayer : arrayList) {
            playersDeathStamina += (myPlayer.getPlayerStamina(monsterAttackDamage) * myPlayer.attackDamage);
            myPlayer.health = myPlayer.powerLeft(monsterAttackDamage);
        }
        int remainingPowerMonster = monsterHealth-playersDeathStamina;
        PriorityQueue<Warrior> maxPQ = new PriorityQueue<Warrior>((a,b)-> {
            if(a.attackDamage != b.attackDamage) return b.attackDamage-a.attackDamage;
            return b.health-a.health;
        });

        // BUG:Only add the players to the list, who still has some health left for the last round. Cases where health%monsterattack ==0; maxPQ.addAll(arrayList);
        for(int i=0; i<arrayList.size(); i++){
            if(arrayList.get(i).health >= 0)
                maxPQ.add(arrayList.get(i));
        }
        if(remainingPowerMonster > 0 ){
            while(!maxPQ.isEmpty()){
                Warrior curr = maxPQ.peek();
                if(curr.health ==0) {
                    maxPQ.poll();
                    continue;
                }
                remainingPowerMonster -= curr.attackDamage;
                if(remainingPowerMonster <= 0)
                    return maxPQ.size();
                maxPQ.poll();
            }
        }
        return maxPQ.size();
    }

    // My Players Model
    static class Warrior {
        public int attackDamage;
        public int health;
        public int idx;

        public Warrior(int attackDamage, int health, int idx) {
            this.attackDamage = attackDamage;
            this.health = health;
            this.idx = idx;
        }

        public int powerLeft(int attackDamage) {
            int temp = health;
            while(temp-attackDamage >0)
                temp -= attackDamage;
            return temp;
        }

        public int getPlayerStamina(int attackDamage) {
            return health / attackDamage;
        }
    }
    //Test Cases Available at CodeFights
    public static void main(String[] args) {
//        System.out.println("Players Left: " + allianceVersusMonster(new int[]{110, 30, 50}, new int[]{12, 11, 20}));       //Expected Result 2
        System.out.println("Players Left: " + allianceVersusMonster(new int[]{4, 10, 10, 10}, new int[]{10, 1, 1, 1}));    //Expected Result 0
//        System.out.println("Players Left: " + allianceVersusMonster(new int[]{10, 3, 3, 3}, new int[]{2, 1, 5, 1}));       //Expected Result 3
//        System.out.println("Players Left: " + allianceVersusMonster(new int[]{2000000000, 2000000000}, new int[]{1, 1}));  //Expected Result 1
//        System.out.println("Players Left: " + allianceVersusMonster(new int[]{11, 4, 4, 4}, new int[]{1, 1, 1, 1}));       //Expected Result 2
//        System.out.println("Players Left: " + allianceVersusMonster(new int[]{10, 4, 4, 4}, new int[]{1, 1, 1, 1}));       //Expected Result 2
    }
}
