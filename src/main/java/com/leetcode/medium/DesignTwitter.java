package com.leetcode.medium;

import java.util.*;

public class DesignTwitter {

    /*

    Discussion with the interviewer:
    - Can a user follow itself? How to handle that
    - Multi threading in terms of concurrent messages across user. Sequential dealing with timestamp.
    - What to do for follow, unfollow, getNewsFeed, postTweet etc if one of the user doesn't exist.
    - What is the reasonable number of tweets to save for each user.
    -

    - Use an adjacency list to represent to represent user followers.
    - Use a tweetUsermap that stores all the tweets in order(with timestamp).

    - Follow(1,2) -> Add an directed edge in the adj list.
    - Unfollow(1,2) -> remove edge.
    - PostTweet(1,5) -> Add to the tweet user map, tweetID and timestamp.
    - getNewsFeed(uid1) -> get all the followers that uid1 follows and put all their tweets in a min PQ of size(10) and its own tweet as well. Priority should sort itself based on timestamp. Size restriction as we only need to have 10 tweets
        - Return the elements of the minPQ in reverse order. Max time to min time.
    - restrict the tweets remembered by each user to 10.

    */

        class TweetTime {
            int tweetId;
            long timestamp;
            TweetTime(int tweetId, long timestamp){
                this.tweetId = tweetId;
                this.timestamp = timestamp;
            }
            @Override
            public String toString(){
                return "TweetId : " + this.tweetId + " sent at time " + timestamp;
            }

        }
        Map<Integer, Queue<TweetTime>> userTweetMap;
        Map<Integer, Set<Integer>> userFolloweeMap;
        long counter;

        /** Initialize your data structure here. */
        public DesignTwitter() {
            userFolloweeMap = new HashMap<>();
            userTweetMap = new HashMap<>();
            counter = 1;
        }

        private static final int MAX_SIZE = 10;

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            userFolloweeMap.putIfAbsent(userId, new HashSet<>());
            userTweetMap.putIfAbsent(userId, new LinkedList<>());
            Queue<TweetTime> tweets = userTweetMap.get(userId);
            tweets.offer(new TweetTime(tweetId, counter++));
            if(tweets.size() > MAX_SIZE)
                tweets.poll();
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            if(!userFolloweeMap.containsKey(userId)) return new ArrayList<Integer>();
            List<TweetTime> allTweets = new ArrayList<>();
            for(Integer followeeId: userFolloweeMap.getOrDefault(userId, new HashSet<Integer>())){
                allTweets.addAll(userTweetMap.getOrDefault(followeeId, new LinkedList<TweetTime>()));
            }
            allTweets.addAll(userTweetMap.getOrDefault(userId, new LinkedList<TweetTime>()));
            Collections.sort(allTweets, (a, b) -> {
                if(b.timestamp > a.timestamp) return 1;
                else if (b.timestamp < a.timestamp) return -1;
                else return 0;
            });
            List<Integer> res = new ArrayList<>();
            for(int i=0 ; i < 10  && i < allTweets.size(); i++){
                res.add(allTweets.get(i).tweetId);
            }
            return res;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(followerId == followeeId) return;
            userFolloweeMap.putIfAbsent(followerId, new HashSet<>());
            userFolloweeMap.get(followerId).add(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(!userFolloweeMap.containsKey(followerId)) return;
            userFolloweeMap.get(followerId).remove(followeeId);
        }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */


}
