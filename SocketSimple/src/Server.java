import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server extends Thread {
	
	public static void main(String[] args) throws IOException {
		Server server = new Server();
//		server.BasicServer();
		server.ThreadServer();
    }
	
	public Server(){}
	
	public void BasicServer() {
		int portNumber = Constant.SERVER_PORT;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
        	System.out.println("Servidor esperando conexiones ....");
            Socket clientSocket = serverSocket.accept();    
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                  
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibido: " + inputLine);
            	out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "  + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
	}
	
	public void ThreadServer(){
		this.start();
	}
	public void run(){
		int portNumber = Constant.SERVER_PORT;
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			while(true){
	        	System.out.println("Servidor esperando conexion multithread....");
	            Socket clientSocket = serverSocket.accept();
	            new ProcesaCliente(clientSocket);
			}
		} catch (IOException e) {
            System.out.println("Exception cuando trato de escuchar conexiones por el puerto "  + portNumber);
            System.out.println(e.getMessage());
        }
	}
	
	
	
}
