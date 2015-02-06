import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import mensaje.Message;

public class Server extends Thread{
	
	private Socket socket; 
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    ServerSocket servidor = null;
	
    public void run(){
    	int puerto = 1234;
        try {
            servidor = new ServerSocket(puerto);
            while (true) {//Threads are just used to read information.
                System.out.println("Servidor a la espera de conexiones.");
                socket = servidor.accept();
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
                try {
                    entradaDatos = new DataInputStream(socket.getInputStream());
                    salidaDatos = new DataOutputStream(socket.getOutputStream());
                    String messageInput = entradaDatos.readUTF();
                    while(messageInput != null){
                    	System.out.println("mensajeRecibido: " + messageInput);
                    	Message message = processMessage(messageInput);
                    	salidaDatos.writeUTF("Me enviaste esto : " + message.getMessage());
                    	messageInput = entradaDatos.readUTF();
                    }
                } catch (IOException ex) {
                	ex.printStackTrace();
                	System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
                }
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
    
    public Message processMessage(String message){
    	try {
			String[] partes = message.split(":");
			System.out.println("Parte 0:" + partes[0]+":");
			System.out.println("Parte 1: " + partes[1]+":");
			return new Message(Long.parseLong(partes[0]),partes[1]);
		} catch (java.lang.ArrayIndexOutOfBoundsException ex){
			return new Message(this.getId(),"Su mensaje no traia destinatario, no es posible procesarlo");
		}
    }
    
	public static void main(String[] args) {
        new Server().start();
    }

}
