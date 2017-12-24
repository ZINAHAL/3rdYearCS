import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class DataRetrieval {
	
	/*-----------------------PART1-------------------
	 * GOAL
	 * - To collect the number of commits that each user does in week
	 * - Store the collected data in a JSON file 
	 * - Draw a bar chart of collected data
	 * 
	 * APPROACH TAKEN
	 * - Collected all the followers' usernames of the given username and stored it in a list. In this case the github username that I will start with is ..... 
	 * - Then counted the No. of push events that each follower did within the range of the given dates. The result is then stored in a list 
	 * - Some followers can be following other followers in the list. This leads to repetition in the data being collected. However this is prevented by having the method 
	 *   "isUsernameSeenBefore"
	 *   
	 * -----------------------PART1-------------------
	 */
	
	public static List<String> followers_usernames = new ArrayList<String>();
	public static List<Integer> result = new ArrayList<Integer>();
	private static List<String> seen_usernames = new ArrayList<String>(); 
	
	
	
	
	public static void gatherFollowersUsernames(String parent_username, int recursive_no) throws Exception {
		
		if(recursive_no == 0)
		{
			return;
		}
		
		followers_usernames.add(parent_username);
		String followers_url_path = "https://api.github.com/users/" + parent_username + "/followers?page=";
		URL followers_URL_obj;
		HttpsURLConnection request;
		JsonParser json_parser;
		int page_number = 1;
		
		System.out.println("inside gatherFollowersUsernames"); //***********************************
		
		while(true)
		{
			followers_URL_obj = new URL(followers_url_path + page_number);
			request = (HttpsURLConnection) followers_URL_obj.openConnection();
			request.setRequestMethod("GET");
			request.connect();
			if(request.getResponseCode() != HttpsURLConnection.HTTP_OK)
			{
				break;
			}
			
			json_parser = new JsonParser();
			JsonElement parsed_url = json_parser.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonArray parsed_url_array = parsed_url.getAsJsonArray();
			
			for(JsonElement element: parsed_url_array)
			{
				JsonObject follower = element.getAsJsonObject();
				String name = follower.get("login").getAsString();
				System.out.println("username:" + name); //***************************************
				followers_usernames.add(name);
				gatherFollowersUsernames(name, recursive_no-1);
			}
			page_number++;
		}
		request.disconnect();
	}
	
	
	public static void countPushEvents(String username, MonthDay from_date, MonthDay to_date) throws Exception {
		
		System.out.println("im inside countPushEvents"); //***********************
		
		if(isUsernameSeenBefore(username))
		{
			return;
		}
		
		System.out.println("im at the top"); //************************************
		
		String event_url_path = "https://api.github.com/users/" + username + "/events?page=";
		URL event_URL_obj;
		HttpsURLConnection request;
		JsonParser json_parser;
		int push_counter = 0;
		int page_number = 1;
		
		
		while(true)
		{
			event_URL_obj = new URL(event_url_path + page_number);
			request = (HttpsURLConnection) event_URL_obj.openConnection();
			request.setRequestMethod("GET");
		    request.connect();
		    
		    System.out.println("resp code = " + request.getResponseCode()); //*********************************
		    
		    if(request.getResponseCode() != HttpsURLConnection.HTTP_OK)
		    {
		    	break;
		    }
		    
			json_parser = new JsonParser();
			JsonElement parsed_url = json_parser.parse(new InputStreamReader((InputStream) request.getContent()));
			JsonArray parsed_url_array = parsed_url.getAsJsonArray();
			
			System.out.println("inside while and outside for loop"); //*****************************************
			
			for(JsonElement element: parsed_url_array)
			{
				System.out.println("inside for loop"); //*************************
				
				JsonObject follower_events = element.getAsJsonObject();
				if(follower_events.get("type").getAsString().equals("PushEvent")) 
				{
					System.out.println("inside if stat"); //****************************
					String creation_date = follower_events.get("created_at").getAsString();
					int day = getDay(creation_date);
					int month = getMonth(creation_date);
					if((month >= from_date.getMonthValue()) && (month <= to_date.getMonthValue()) && (day >= from_date.getDayOfMonth()) && (day <= to_date.getDayOfMonth()))
					{
						push_counter++;
					}
				}
			}
			page_number++;
		}
		result.add(push_counter);
		request.disconnect();
		
		System.out.println("reached the end of countPushEvents"); //********************************************
		
	}
	
	
	private static int getDay(String date) {
		
		String str_num = date.substring(8, 9);
		char x = date.charAt(9);
		if(x >= '0' && x <= '9')
		{
			str_num = str_num + x;
		} 
		
		return Integer.parseInt(str_num);
	}
	
	
	private static int getMonth(String date) {
		
		String str_num = date.substring(5, 6);
		char x = date.charAt(6);
		if(x >= '0' && x <= '9')
		{
			str_num = str_num + x;
		} 
		
		return Integer.parseInt(str_num);
	}
	
	
	private static boolean isUsernameSeenBefore(String username) {
		
		Iterator iterat = seen_usernames.iterator();
		while(iterat.hasNext())
		{
			if(iterat.next().equals(username))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*PROBLEMS
	 * I believe the above code works.
	 * THE REASONS:
	 * - I seem to be exceeding the API rate limit of 60 requests per hour  
	 * - The HTTP connection is not kept alive
	 * 
	 */
	
		
}
