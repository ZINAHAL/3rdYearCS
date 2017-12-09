import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
/**
 * 
 */

/**
 * @author zinah
 *
 */
public class GetAPIData {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws Exception {
		
		try
		{
			URL githubUrl = new URL("https://api.githubzinah.com/");
			InputStream input = githubUrl.openStream();
			JSONParser jParser = new JSONParser();
			Object parsedUrlObject = jParser.parse(new InputStreamReader(input));
			JSONObject parsedUrlJSONObject = (JSONObject) parsedUrlObject;
			
			System.out.println(parsedUrlJSONObject.get("user_search_url"));
		}
		catch(ParseException e) { e.printStackTrace(); }
		catch(IOException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
		

	}

}
