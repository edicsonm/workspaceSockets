package message;

import org.json.simple.JSONObject;

public class Message {
	
	private String type;
	private JSONObject jsonObjectMessage;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public JSONObject getJsonObjectMessage() {
		return jsonObjectMessage;
	}
	public void setJsonObjectMessage(JSONObject jsonObjectMessage) {
		this.jsonObjectMessage = jsonObjectMessage;
	}

}
