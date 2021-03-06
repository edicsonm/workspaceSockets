package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	
	private Socket socket; 
    ServerSocket servidor = null;
	
    public void run(){
    	int puerto = 1234;
        try {
            servidor = new ServerSocket(puerto);
            while (true) {//Threads are just create socket Clients.
                System.out.println("Server waiting for connections.");
                socket = servidor.accept();
                new ProcessClient(socket);
            }
        } catch (IOException ex) {
        	System.out.println("Error: " + ex.getMessage());
        } finally{
            try {
                socket.close();
                servidor.close();
            } catch (IOException ex) {
            	System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
    }
    
	public static void main(String[] args) {
        new Server().start();
    }

}
