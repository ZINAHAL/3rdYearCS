import java.io.InputStream;
import java.net.*;

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
		
		URL myUrl = new URL("https://api.github.com/"); 
		HttpURLConnection con = (HttpURLConnection) myUrl.openConnection();
		InputStream in = con.getInputStream();
		
		int i;
		while((i = in.read()) != -1)
		{
			System.out.print((char)i);
			if(i == ',')
			{
				System.out.println();
			}
			
		}

	}

}
