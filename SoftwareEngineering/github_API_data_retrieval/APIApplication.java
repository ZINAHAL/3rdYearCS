import java.io.*;


import java.net.*;
import java.time.MonthDay;
import java.util.Iterator;
import java.util.Scanner;

import javax.tools.JavaFileObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * 
 */

/**
 * @author zinah
 *
 */
public class APIApplication {
	
	public static void main(String[] args) throws Exception {
		
		// If things were working out in the DataRetrieval class then the following code would print out the data that was gathered (the No. of commits that each user did in a week)
		DataRetrieval.gatherFollowersUsernames("phadej", 1);
		Iterator iterat = DataRetrieval.followers_usernames.iterator();
		while(iterat.hasNext())
		{
			DataRetrieval.countPushEvents((String) iterat.next(), MonthDay.of(12, 10), MonthDay.of(12, 17));
		}
		
		iterat = DataRetrieval.result.iterator();
		while(iterat.hasNext())
		{
			System.out.println(iterat.next());
		}

		System.out.println("I reached the end!!");
		//*****************************************************************************************
		
		
		
		
	}

	

}