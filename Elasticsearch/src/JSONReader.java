import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

public class JSONReader {
	
	public JSONReader(){
	}
	
	public Map<String,JSONObject> parse(String input) throws IOException, ParseException{
	    JSONObject object;
		Map<String, JSONObject> map= new HashMap();
		try {
			object = new JSONObject(input);
			JSONArray hits = object.getJSONObject("hits").getJSONArray("hits");
			
			for (int i=0; i<hits.length();i++){
				try {
					map.put(hits.getJSONObject(i).getJSONObject("_source").getString("subject"),
							hits.getJSONObject(i).getJSONObject("_source"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    return map;
	}
	
	public ArrayList<String> parseAggregation(String input) throws IOException, ParseException{
	    JSONObject object;
		ArrayList<String> str= new ArrayList<>();
		try {
			object = new JSONObject(input);
			JSONArray buckets = object.getJSONObject("aggregations")
					.getJSONObject("query_related").getJSONArray("buckets");
			
			for (int i = 1; i < 6;i++){
				try {
					str.add(buckets.getJSONObject(i).getString("key"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    return str;
	}

}
