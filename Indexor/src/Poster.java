import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

public class Poster {
	
	public Poster(){
		
	}
	
	public void postToES(JSONObject obj){
		StringBuffer content = new StringBuffer();
		try {
			String id = obj.getString("id");
			obj.remove("id");
			URL url = new URL("http://137.112.104.135:9201/email/enron/"+id);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setDoOutput(true);
			con.setDoInput (true);
			con.setDoOutput (true);
			con.setUseCaches (false);
			con.setRequestProperty("Content-Type","application/json");   
			con.setRequestProperty("Host", "android.schoolportal.gr");
			con.connect();  
			
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(obj.toString());
		    out.close();  
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			con.disconnect();
			
			System.out.println(content.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
