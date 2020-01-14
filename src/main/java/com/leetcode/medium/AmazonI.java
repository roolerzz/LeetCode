package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AmazonI {

    /*
        - Given list of Strings of competitors.
        - int noOfCompetitors
        - int topNcompetitors(Max number of competitors amazon wants to know)
        - int numReviews: total no of reviews identified by crawler.
        - List<String> review : List of strings. whee each string represents a review.

        Notes:
        - topNCompetitors could be greater than competitors actually discussed in reviews. Return only the one dicscusse
        - Only 1 occurence of a competitor in a single review.
        - same count competitors should be sorted alphabetically.

        Question?
        - Can same review have multiple competitors mentioned?
        - What happens for topN is greater than numCompetitors.
        - safe to assume numCompetitors same as size of list of competitors?
        - safe to assume numReview same as size of list of reviews?
    */
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public ArrayList<String> topNCompetitors(int numCompetitors,
                                             int topNCompetitors,
                                             List<String> competitors,
                                             int numReviews,
                                             List<String> reviews)
    {
        ArrayList<String> result = new ArrayList<>();
        if(numCompetitors < 1 || topNCompetitors < 1 || competitors == null || competitors.size() < 1 || reviews == null || reviews.size() < 1)
            return result;
        // TODO Validation??
        // Max Priority Queue, which keeps the elements added to the priority sorted first by their count(in Descending order).
        // Inn case of tie, competitorName is used to sort alphabetically.
        Queue<CompetitorCount> maxPQ = new PriorityQueue<>((a, b) -> {
            if(a.count != b.count) return b.count-a.count;
            return a.compName.compareTo(b.compName);
        });
        // Using an integer array to keep track of the total count of the occurences of competitor name in the review.
        int[] count = new int[numCompetitors];
        // Keeping double guard on reviewID incase numReviews isn't in sync with the reviews list size.
        for(int reviewID=0 ; reviewID < numReviews && reviewID < reviews.size() ; reviewID++){
            // // Keeping double guard on compID incase numCompetitors isn't in sync with the competitors list size.
            for(int compID=0; compID < numCompetitors && compID < competitors.size(); compID++){
                if(reviews.get(reviewID).contains(competitors.get(compID))){
                    count[compID]++;
                }
            }
        }

        int competitorsToAdd = 0;
        for(int compID=0 ; compID < count.length; compID++)
            if(count[compID] != 0) // That means competitor at that index has been mentioned onto the reivews.
            {
                maxPQ.offer(new CompetitorCount(compID, competitors.get(compID), count[compID]));
                competitorsToAdd++;
            }

        competitorsToAdd = Math.min(competitorsToAdd, topNCompetitors);
        // Add the required competitors to the result list.
        while(competitorsToAdd-- >0){
            CompetitorCount node = maxPQ.poll();
            result.add(node.compName);
        }
        return result;
    }
    // METHOD SIGNATURE ENDS

    private class CompetitorCount{
        int idx;
        String compName;
        int count;
        CompetitorCount(int idx, String compName, int count){
            this.idx = idx;
            this.compName = compName;
            this.count = count;
        }
    }

  public static void main(String[] args) {

  }
}
