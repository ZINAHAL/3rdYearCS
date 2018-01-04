import java.io.*;


import java.net.*;
import java.time.MonthDay;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import javax.tools.JavaFileObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.google.gson.Gson;
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
		
		DataRetrieval.gatherFollowersUsernames("phadej", 1);
		Iterator iterat = DataRetrieval.followers_usernames.iterator();
		while(iterat.hasNext())
		{
			DataRetrieval.countPushEvents((String) iterat.next(), MonthDay.of(12, 10), MonthDay.of(12, 17));
		}
		
		// If things were working out in the above then the following code would print out the data that was gathered (the No. of commits that each user did in a week)
		iterat = DataRetrieval.result.iterator();
		while(iterat.hasNext())
		{
			System.out.println(iterat.next());
		}
		
		//The following code will create a .csv file with the following heading: Commits, Frequency; and will work on the "result" list for the records in the file
		Collections.sort(DataRetrieval.result);
		//Integer[] commits = (Integer[]) DataRetrieval.result.toArray();
		Integer[] commits = DataRetrieval.result.toArray(new Integer[DataRetrieval.result.size()]);
 		
		FileWriter users_weekly_commits_file = new FileWriter("users_weekly_commits.csv");
		users_weekly_commits_file.write("Commits,Frequency");
		users_weekly_commits_file.write("\n");
		
		int i = 0;
		while(i < commits.length)
		{
			users_weekly_commits_file.write(String.valueOf(commits[i]));
			int element = commits[i];
			int frequency = 0;
			while(i < commits.length && commits[i] == element)
			{
				frequency++;
				i++;
			}
			
			users_weekly_commits_file.write(",");
			users_weekly_commits_file.write(String.valueOf(frequency));
			users_weekly_commits_file.write("\n");
		}
		
		users_weekly_commits_file.flush();
		users_weekly_commits_file.close();		
		

		System.out.println("I reached the end!!");
		//*****************************************************************************************
		
//		Gson gson = new Gson();
//		FileWriter users_weekly_commits_file = new FileWriter("users_weekly_commits.json");
//		users_weekly_commits_file.write(gson.toJson(DataRetrieval.result));
//		users_weekly_commits_file.flush();
		
		
		
		
		
		
		
	}

	

}
