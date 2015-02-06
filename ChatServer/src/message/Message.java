package message;

import org.json.simple.JSONObject;

public class Message {
	
	private long IDDestiny;
	private long IDOrigen;
	private String message;
	private String type;
	private JSONObject jsonObjectMessage;
	
	public JSONObject getJsonObjectMessage() {
		return jsonObjectMessage;
	}

	public void setJsonObjectMessage(JSONObject jsonObjectMessage) {
		this.jsonObjectMessage = jsonObjectMessage;
	}

	public Message(long IDDestiny, String message){
		this.IDDestiny = IDDestiny;
		this.message = message;
	}
	
	public Message(long IDDestiny,String type, JSONObject jsonObjectMessage){
		this.IDDestiny = IDDestiny;
		this.type = type;
		this.jsonObjectMessage = jsonObjectMessage;
	}
	
	public Message(JSONObject jsonObjectMessage){
		this.jsonObjectMessage = jsonObjectMessage;
	}
	
	public long getIDDestiny() {
		return IDDestiny;
	}

	public void setIDDestiny(long iDDestiny) {
		IDDestiny = iDDestiny;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getIDOrigen() {
		return IDOrigen;
	}

	public void setIDOrigen(long iDOrigen) {
		IDOrigen = iDOrigen;
	}

}
