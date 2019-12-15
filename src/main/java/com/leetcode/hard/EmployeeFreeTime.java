package com.leetcode.hard;

import sun.jvm.hotspot.utilities.Interval;

import java.util.*;

public class EmployeeFreeTime {

    static class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
        @Override
        public String toString(){
            return start + " - " + end;
        }
    }
    /*
    - Does my i/p exists into the memory.
    - Integer values for start/end.
    - [1,1] not allowed. Positive length.
    - For each employee, check the first busy schedule and last busy schedule to get start/end time. Find Min Start and Max end acrross all emp.
    Approach I:
    - If some interval overlaps any busy interval, that won't be included in the ans. SO we basically have to solve for following:
    given a set of intervals, find all the places there are no intervals.
    - Use events approach. For each event[s,e], we can thing of 2 events, increment the runningEventcount when time = s, and decrement when time=e. We want to know the regions where count ==0.
    */
    public List<Interval> employeeFreeTime1(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        int OPEN =0, CLOSE=1;
        List<int[]> events = new ArrayList<>();
        for(List<Interval> emp : schedule){
            for(Interval busyTime: emp){
                events.add(new int[]{busyTime.start, OPEN});
                events.add(new int[]{busyTime.end, CLOSE});
            }
        }
        Collections.sort(events, (a, b)-> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        int prev = -1, bal = 0;
        for(int[] event: events){
            if(bal == 0 && prev >= 0)
                res.add(new Interval(prev, event[0]));
            bal += event[1]== OPEN ? 1 : -1;
            prev = event[0];
        }
        return res;
    }
    /*
Approach 2(I):
   Don't worry about different employees. Just think of all the intervals collectively. Sort them as per their start times.
   See if 2 consecutive intervals doesn't overlap, that means there is a free time b/w them. Otherwise no, skip the current.
   Time complexity : NlogN where N is the total number of intervals. Worse than approach II.
   Space: O(N) where N is the total number of intervals.
*/
    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        List<Interval> times = new ArrayList<>();
        schedule.forEach(emp -> times.addAll(emp));
        Collections.sort(times, Comparator.comparingInt(a -> a.start));
        Interval prev = times.get(0);
        for(int i = 1 ; i < times.size(); i++){
            Interval next = times.get(i);
            if(prev.end < times.get(i).start)
            {
                res.add(new Interval(prev.end, next.start));
                prev = next;
            }
            else {
                prev = next.end > prev.end ? next : prev;
            }
        }
        return res;
    }

    /* Approach 2(II)
        Same idea as 2(I) but using a priority queue to return the minimum interval by their start times.
    */
    public List<Interval> employeeFreeTime22(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Interval> minPQ = new PriorityQueue<>(Comparator.comparingInt(a-> a.start));
        schedule.forEach(emp -> minPQ.addAll(emp));
        Interval prev = minPQ.poll();
        while(!minPQ.isEmpty()){
            Interval next = minPQ.poll();
            if(prev.end < next.start){
                res.add(new Interval(prev.end, next.start));
                prev = next;
            }
            else
                prev = prev.end < next.end ? next : prev;
        }
        return res;
    }

    /*
    Approach 3: Use merge sort instead of using PQ or fully sorting the intervals as the indivudual employee intervals are already sorted. With that, the time complexity comes down NlogK because the sorting time is reduced.
    */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        List<Interval> unsorted = new ArrayList<>();
        schedule.forEach(emp -> unsorted.addAll(emp));
        System.out.println(unsorted.toString());
        List<Interval> sorted =  mergeSort(schedule, 0, schedule.size()-1);
        System.out.println(sorted.toString());
        // schedule.forEach(emp -> sorted.addAll(emp));
        Interval prev = sorted.get(0);
        for(int i = 1 ; i < sorted.size(); i++){
            Interval next = sorted.get(i);
            if(prev.end < next.start)
            {
                res.add(new Interval(prev.end, next.start));
                prev = next;
            }
            else
                prev = prev.end < next.end ? next: prev;
        }

        return res;
    }

    private List<Interval> mergeSort(List<List<Interval>> schedule, int start, int end){
        if(start == end) return schedule.get(start);
        int mid = start + (end-start)/2;
        System.out.println("Start: " + start + " Mid : " + mid + "End : " + end);
        List<Interval> left = mergeSort(schedule, start, mid);
        List<Interval> right = mergeSort(schedule, mid+1, end);
        return merge(left, right);
    }

    private List<Interval> merge(List<Interval> left, List<Interval> right){
        List<Interval> res = new ArrayList<>();
        System.out.println("Left list to be merged is : " + left.toString());
        System.out.println("Right list to be merged is : " + right.toString());
        int i = 0; int j = 0;
        while(i < left.size() || j < right.size()){
            if(i < left.size() && j < right.size()){
                if(left.get(i).start <= right.get(j).start)
                    res.add(left.get(i++));
                else res.add(right.get(j++));
            }
            else if(i < left.size())
                res.add(left.get(i++));
            else // (j < right.size())
                res.add(right.get(j++));
        }
        System.out.println("Merged list is : " + res.toString());
        return res;
    }

    public static void main(String[] args) {
        EmployeeFreeTime ft = new EmployeeFreeTime();
        List<List<Interval>> input = new ArrayList();

        List<Interval> emp1 = new ArrayList<>();
        emp1.addAll(Arrays.asList(new Interval(1,2), new Interval(5,6)));
        List<Interval> emp2 = new ArrayList<>();
        List<Interval> emp3 = new ArrayList<>();
        emp2.addAll(Arrays.asList(new Interval(1,3)));
        emp3.addAll(Arrays.asList(new Interval(4,10)));
        input.add(emp1);
        input.add(emp2);
        input.add(emp3);
        List<Interval> free = ft.employeeFreeTime(input);
        System.out.println(free.toString());
    }

    /* Approach IV:
        Idea is, to know all the employee's smallest interval(hint priority Queue) sorted by min Start times.
        - Pick the smallest Job ID. At a point in time, where we pop the job from the queue(i.e. an interval happend for some employee), we were either busy(doing some other job) or were free.
        If we were busy doing some other job, then our anchor value(set to last job's .end would be greater than next one's start time) then we don't do anything.
        If we were free(anchor time < start time of the new job), that means our free time ended with the new job start. So we add the interval to the list of free intervals.
        Time Complexity: O(IlogE) where I is the total number of intervals and E is the number of employees. E is the PQ size so takes logE for each insertion.
        Space: O(E) for the PQ containing 1 Job node from each employee.
        */
    public List<Interval> employeeFreeTime4(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        PriorityQueue<Job> pq  = new PriorityQueue<Job>((a, b) -> schedule.get(a.empID).get(a.idx).start-schedule.get(b.empID).get(b.idx).start);
        int anchor = Integer.MAX_VALUE;
        int i = 0;
        for(List<Interval> emp: schedule){
            pq.offer(new Job(i++, 0));
            anchor = Math.min(anchor, emp.get(0).start);
        }
        while(!pq.isEmpty()){
            Job job = pq.poll();
            Interval curr = schedule.get(job.empID).get(job.idx);
            if(anchor < curr.start){
                res.add(new Interval(anchor,curr.start));
            }
            anchor = Math.max(curr.end, anchor);
            if(job.idx++ < schedule.get(job.empID).size())
                pq.offer(job);
        }
        return res;
    }

    class Job {
        int empID; // Which employee(index in the schedule list) does this interval belongs to.
        int idx;  // What is the location of this interval in the list of intervals for this employee. We need this so that we can process the next interval in the sorted list of interval for that employee next.
        Job(int empID, int idx){
            this.empID = empID;
            this.idx = idx;
        }
    }
}
