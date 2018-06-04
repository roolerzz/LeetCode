package com.leetcode.easy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WayfairCodilityProblem {
   
    public static void main(String[] args) {
		String input = "photo.jpg, Warsaw, 2013-09-05 14:08:15\r\n" + 
				"john.png, London, 2015-06-20 15:13:22\r\n" + 
				"myFriends.png, Warsaw, 2013-09-05 14:07:13\r\n" + 
				"Eiffel.jpg, Paris, 2015-07-23 08:03:02\r\n" + 
				"pisatower.jpg, Paris, 2015-07-22 23:59:59\r\n" + 
				"BOB.jpg, London, 2015-08-05 00:02:03\r\n" + 
				"notredame.png, Paris, 2015-09-01 12:00:00\r\n" + 
				"me.jpg, Warsaw, 2013-09-06 15:40:22\r\n" + 
				"a.png, Warsaw, 2016-02-13 13:33:50\r\n" + 
				"b.jpg, Warsaw, 2016-01-02 15:12:22\r\n" + 
				"c.jpg, Warsaw, 2016-01-02 14:34:30\r\n" + 
				"d.jpg, Warsaw, 2016-01-02 15:15:01\r\n" + 
				"e.png, Warsaw, 2016-01-02 09:49:09\r\n" + 
				"f.png, Warsaw, 2016-01-02 10:55:32\r\n" + 
				"g.jpg, Warsaw, 2016-02-29 22:13:11";
		String output = solution(input);
		System.out.println(output);
	}
    
    
    static class Photo implements Comparable<Photo>{
    	private int ID;
    	private String name;
    	private String cityName;
    	private Date date;
    	private String finalName; 
    	private String extension;
    	
		public String getExtension() {
			return extension;
		}
		public void setExtension(String extension) {
			this.extension = extension;
		}
		public Photo(int iD, String name,String extension , String cityName, Date date, String finalName) {
			super();
			ID = iD;
			this.name = name;
			this.cityName = cityName;
			this.date = date;
			this.finalName = finalName;
			this.extension = extension;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getID() {
			return ID;
		}
		public void setID(int iD) {
			ID = iD;
		}
		public String getFinalName() {
			return finalName;
		}
		public void setFinalName(String finalName) {
			this.finalName = finalName;
		}
		@Override
		public int compareTo(Photo o) {
			return this.getDate().compareTo(o.getDate());
		}
    }

	private static String solution(String input) {
		String[] photos = input.split("\r\n");
		
		Map<String,List<Photo>> mapping = new HashMap<>();
		
		// 2016-02-29 22:13:11
		List<Photo> initialList = new ArrayList<>();
		// Each photo is categorized into hashmap where key is city and value is list of photos.
		
		organizePhotosByCities(photos, mapping, initialList);
		
		// For each photo in the city list of photos, sort by the Date using comparable. 
		for(List<Photo> list : mapping.values()) {
			Collections.sort(list);
		}
		
		// generate finalOutput.
		//Warsaw02.jpg
		generateFinalOutput(mapping);
		
		// list sorted as per initial ordering.
		Collections.sort(initialList, new Comparator<Photo>() {
			@Override
			public int compare(Photo o1, Photo o2) {
				return o1.getID()-o2.getID();
			}
		});
		
		
		StringBuilder finalOutput = new StringBuilder(""); 
		for(Photo photo : initialList) {
			finalOutput.append(photo.getFinalName());
			finalOutput.append("\r\n");
		}
		// for each list in mapping, get the size of the list and assign the number accordingly.
		return finalOutput.toString();
	}

	private static void generateFinalOutput(Map<String, List<Photo>> mapping) {
		for(List<Photo> list : mapping.values()) {
			// problem
			int listSize = list.size();
			int stringSize = String.valueOf(listSize).length();
			//problem
			int orderID = 1;
			for(Photo photo : list) {
				StringBuilder output = new StringBuilder("");
				String format  = "%0" + stringSize +"d";
				String formattedID = String.format(format, orderID);
				output.append(photo.getCityName());
				output.append(formattedID);
				output.append(".");
				output.append(photo.getExtension());
				photo.setFinalName(output.toString());
				orderID++;
			}
		}
	}

	private static void organizePhotosByCities(String[] photos, Map<String, List<Photo>> mapping,List<Photo> initialList) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 1;
		for(String photo : photos) {
			String[] temp = photo.split(", ");
			// problem
			String[] photodetails= temp[0].split("\\.");
			String photoName = photodetails[0];
			String extension = photodetails[1];
			String cityName = temp[1];
			String dateString = temp[2];
			Date startDate = new Date();
			try {
				startDate = df.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Photo newPhoto = new Photo(i++, photoName, extension, cityName, startDate, "");
			
			if(!mapping.containsKey(cityName)) {
				mapping.put(cityName, new ArrayList<Photo>());
			}
			mapping.get(cityName).add(newPhoto);
			initialList.add(newPhoto);
		}
	}
	
	
	
}