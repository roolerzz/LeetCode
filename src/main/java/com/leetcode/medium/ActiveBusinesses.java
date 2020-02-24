package com.leetcode.medium;

public class ActiveBusinesses {

    /*
    # Write your MySQL query statement below



 #select o.business_id from
 #(select business_id from (select business_id, event_type,occurences from Events group by business_id, event_type ) as av,
 #(select avg(occurences) as avg_occ, event_type from Events group by event_type) as ev where av.event_type = ev.event_type and av.occurences > ev.avg_occ) as o group by o.business_id having count(o.business_id) > 1;

 select business_id
 from
 events a left join
 ,
 (select event_type, avg(occurences) as av from events group by event_type) as b
 on a.event_type = b.event_type
 where a.occurences > b.av
 group by business_id
 having count(business_id) > 1;

    * */

}
