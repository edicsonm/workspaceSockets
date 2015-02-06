import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cliente  {
	
	String hostName = Constant.SERVER_IP;
    int portNumber = Constant.SERVER_PORT;
	
    public Cliente(){
    	String hostName = Constant.SERVER_IP;
        int portNumber = Constant.SERVER_PORT;
        try {
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//            new Escribe(out, in, stdIn).start();
//            new Lee(out, in, stdIn).start();
            String userInput;
            System.out.println("Entra mensaje\n");
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
                System.out.println("Entra mensaje\n");
            }
        } catch (UnknownHostException e) {
        	e.printStackTrace();
            System.out.println("Servidor desconocido " + hostName);
        } catch (IOException e) {
        	e.printStackTrace(); 
        	System.out.println("No se pudo leer de la conexion a " +hostName);
        }
    }
	public static void main(String[] args) throws IOException {
		new Cliente();
    }
	
	class Lee extends Thread {
		PrintWriter out;
        BufferedReader in;
        BufferedReader stdIn;
		
		private Lee(PrintWriter out, BufferedReader in, BufferedReader stdIn){
			this.out = out;
			this.in = in;
			this.stdIn = stdIn;
		}
		
		public void run(){
			try {
				while(true){
		            String inputLine;
		            while ((inputLine = in.readLine()) != null) {
		            	System.out.println("Recibido: " + inputLine);
		            }
					
//					String userInput;
//		            System.out.println("Entra mensaje\n");
//		            while ((userInput = stdIn.readLine()) != null) {
//		                out.println(userInput);
//		                System.out.println("echo: " + in.readLine());
//		                System.out.println("Entra mensaje\n");
//		            }	
				}
			} catch (UnknownHostException e) {
	        	e.printStackTrace();
	            System.out.println("Servidor desconocido " + hostName);
	        } catch (IOException e) {
	        	e.printStackTrace(); 
	        	System.out.println("No se pudo leer de la conexion a " +hostName);
	        }
		}
	}
	
	class Escribe extends Thread {
		PrintWriter out;
        BufferedReader in;
        BufferedReader stdIn;
		
		private Escribe(PrintWriter out, BufferedReader in, BufferedReader stdIn){
			this.out = out;
			this.in = in;
			this.stdIn = stdIn;
		}
		public void run(){
			try {
				while(true){
				 	String userInput;
		            System.out.println("Entra mensaje\n");
		            while ((userInput = stdIn.readLine()) != null) {
		                out.println(userInput);
		                System.out.println("echo: " + in.readLine());
		                System.out.println("Entra mensaje\n");
		            }
				}
			} catch (UnknownHostException e) {
	        	e.printStackTrace();
	            System.out.println("Servidor desconocido " + hostName);
	        } catch (IOException e) {
	        	e.printStackTrace(); 
	        	System.out.println("No se pudo leer de la conexion a " +hostName);
	        }
		}
	}
	
}


