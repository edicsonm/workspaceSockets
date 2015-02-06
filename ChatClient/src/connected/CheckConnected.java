package connected;

import java.io.IOException;

import org.json.simple.JSONObject;

import socket.ClientSocket;

public class CheckConnected extends Thread {
	private boolean execute = true;
	ClientSocket clientSocket;
	
	public CheckConnected(ClientSocket clientSocket ) {
		this.clientSocket = clientSocket;
	}
	
	public void run(){
		try {
			while(execute){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("typeMessage", "getConnected");
				clientSocket.outputData.writeUTF(jsonObject.toString());
				this.sleep(4000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			stopThread();
		}
		
		
	}
	
	public void stopThread(){
		execute = false;
	}

}
