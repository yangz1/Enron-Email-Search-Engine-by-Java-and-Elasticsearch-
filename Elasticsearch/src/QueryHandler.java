import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryHandler {
	private JSONReader reader;
	private Map<String, JSONObject> currentMap;
	
	public QueryHandler() {
		this.reader = new JSONReader();
	}
	
	public void setCurrentMap(Map<String, JSONObject> currentMap) {
		this.currentMap = currentMap;
	}
	
	public Map<String, JSONObject> getCurrentMap() {
		return currentMap;
	}
	
	public void aggregate(String q, JTable ta){
		StringBuffer content = new StringBuffer();
		try {
			URL url = new URL("http://137.112.104.135:9201/email/enron/_search?pretty");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput (true);
			con.setDoOutput (true);
			con.setUseCaches (false);
			con.setRequestProperty("Content-Type","application/json");   
			con.setRequestProperty("Host", "android.schoolportal.gr");
			con.connect();  
			
			JSONObject all = new JSONObject();
			JSONObject query = new JSONObject();
			JSONObject agg = new JSONObject();
			JSONObject match = new JSONObject();
			JSONObject body = new JSONObject();
			JSONObject query_related = new JSONObject();
			JSONObject significant_terms = new JSONObject();
			
			significant_terms.put("field", "body");
			significant_terms.put("min_doc_count", 2);
			significant_terms.put("exclude", q);
			query_related.put("significant_terms", significant_terms);
			agg.put("query_related", query_related);
			all.put("aggregations", agg);
			
			match.put("body", q);
			query.put("match", match);
			all.put("query", query);
			
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(all.toString());
		    out.close();  
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			con.disconnect();
			
			ArrayList<String> strings = reader.parseAggregation(content.toString());
			DefaultTableModel model = (DefaultTableModel) ta.getModel(); 
			model.setRowCount(0);
			for(int i = 0; i < 5 ; i++){
				model.addRow(new Object[]{strings.get(i)});
			}
			
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String, JSONObject> keywordsQuery(String q){
		StringBuffer content = new StringBuffer();
		try {
			URL url = new URL("http://137.112.104.135:9201/email/enron/_search?q=\'"+q+"\'&pretty");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			con.disconnect();
			
			currentMap = reader.parse(content.toString());
			return currentMap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Map<String,JSONObject> senderQuery(String q){
		StringBuffer content = new StringBuffer();
		try {
			URL url = new URL("http://137.112.104.135:9201/email/enron/_search?pretty");
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
			
			JSONObject query = new JSONObject();
			JSONObject match = new JSONObject();
			JSONObject xFrom = new JSONObject();
			
			xFrom.put("xFrom", q);
			match.put("match", xFrom);
			query.put("query", match);
			
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(query.toString());
		    out.close();  
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			con.disconnect();
			
			currentMap = reader.parse(content.toString());
			return currentMap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public Map<String,JSONObject> receiverQuery(String q){
		StringBuffer content = new StringBuffer();
		try {
			URL url = new URL("http://137.112.104.135:9201/email/enron/_search?pretty");
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
			
			JSONObject query = new JSONObject();
			JSONObject match = new JSONObject();
			JSONObject XTo = new JSONObject();
			
			XTo.put("xTo", q);
			match.put("match", XTo);
			query.put("query", match);
			
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(query.toString());
		    out.close();  
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			con.disconnect();
			
			currentMap = reader.parse(content.toString());
			return currentMap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public Map<String,JSONObject> advancedQuery(String year,String day,String month,String sub){
		StringBuffer content = new StringBuffer();
		try {
			URL url = new URL("http://137.112.104.135:9201/email/enron/_search?pretty");
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
			
			JSONObject query = new JSONObject();
			JSONObject bool = new JSONObject();
			JSONObject should = new JSONObject();
			JSONArray search = new JSONArray();
			JSONObject match1 = new JSONObject();
			JSONObject match2 = new JSONObject();
			JSONObject match3 = new JSONObject();
			JSONObject date = new JSONObject();
			JSONObject date2 = new JSONObject();
			JSONObject subject = new JSONObject();
			
			if(!day.equals("Day")){
				if(!month.equals("Month")){
					if(!year.equals("")){
						date.put("date", day+" "+month+" "+year);
					}else{
						date.put("date", day+" "+month);
					}
				}else{
					if(!year.equals("")){
						date.put("date", ", "+day);
						date2.put("date2", year);
					}else{
						date.put("date", ", "+day);
					}
				}
			}else{
				if(!month.equals("Month")){
					if(!year.equals("")){
						date.put("date", month+" "+year);
					}else{
						System.out.println("only month");
						date.put("date", month);
					}
				}else{
					if(!year.equals("")){
						date.put("date", year);
					}else{
						return null;
					}
				}
			}
			
			subject.put("subject", sub);
			match1.put("match",date);
			match2.put("match",subject);
			match3.put("match", date2);
			search.put(match1);
			search.put(match2);
			should.put("should",search);
			bool.put("bool", should);
			query.put("query", bool);
			
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		    out.write(query.toString());
		    out.close();  
			
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			con.disconnect();
			
			currentMap = reader.parse(content.toString());
			return currentMap;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
}
