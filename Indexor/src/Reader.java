import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Reader {
	public Reader() {

	}

	public JSONObject readFile(String fileName) {
		ArrayList<String> output = new ArrayList<>();
		String newline = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((newline = bufferedReader.readLine()) != null) {
				output.add(newline);
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> map = parse(output);
		return convertToJSON(map);
	}

	private JSONObject convertToJSON(Map<String, String> map) {
		JSONObject object = new JSONObject();
		try {
			for (String key : map.keySet()) {
				object.put(key, map.get(key));
			}
		} catch (Exception e) {
		}
		return object;
	}

	private Map<String, String> parse(ArrayList<String> strs) {
		Map<String, String> output = new HashMap();
		output.put("body", "");
		for (String s : strs) {
			String[] tokens = s.split(":");
			if (tokens.length == 0) {
				output.put("body", output.get("body") + s + "\n");
			} else {
				switch (tokens[0]) {
				case "Message-ID":
					if (output.containsKey("id")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("id", getContent(tokens, s));
					}
					break;
				case "Date":
					if (output.containsKey("date")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("date", getContent(tokens, s));
					}
					break;
				case "From":
					if (output.containsKey("from")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("from", getContent(tokens, s));
					}
					break;
				case "To":
					if (output.containsKey("to")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("to", getContent(tokens, s));
					}
					break;
				case "Cc":
					if (output.containsKey("cc")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("cc", getContent(tokens, s));
					}
					break;
				case "Bcc":
					if (output.containsKey("bcc")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("bcc", getContent(tokens, s));
					}
					break;
				case "Subject":
					if (output.containsKey("subject")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("subject", getContent(tokens, s));
					}
					break;
				case "Mime-Version":
					break;
				case "Content-Type":
					break;
				case "Content-Transfer-Encoding":
					break;
				case "X-From":
					if (output.containsKey("xFrom")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("xFrom", getContent(tokens, s));
					}
					break;
				case "X-To":
					if (output.containsKey("xTo")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("xTo", getContent(tokens, s));
					}
					break;
				case "X-cc":
					if (output.containsKey("xCc")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("xCc", getContent(tokens, s));
					}
					break;
				case "X-bcc":
					if (output.containsKey("xBcc")) {
						output.put("body", output.get("body") + s + "\n");
					} else {
						output.put("xBcc", getContent(tokens, s));
					}
					break;
				case "X-Folder":
					break;
				case "X-Origin":
					break;
				case "X-FileName":
					break;
				default:
					output.put("body", output.get("body") + s + "\n");
					break;
				}
			}
		}

		return output;

	}

	private String getContent(String[] arr, String s) {
		int i = arr[0].length();
		if (s.length() <= i + 2) {
			return "";
		} else {
			return s.substring(i + 2);
		}
	}

}
