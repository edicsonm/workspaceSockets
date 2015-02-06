import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static ServerSocket servidor = null;
	public static Socket socket = null;
	public static void main(String args[]) {
	      try {
	    	  servidor = new ServerSocket(Constant.SERVER_PORT, Constant.MAX_CONNECTIONS);
	    	  while (true) {
	    		  System.out.print("Servidor a la espera de conexiones.");
	                socket = servidor.accept();
	                System.out.print("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
	                ConexionCliente cc = new ConexionCliente(socket);
	                cc.start();
	            }
	      } catch(Exception e) {
	         System.out.print("Whoops! It didn't work!\n");
	      } finally{
	            try {
	                socket.close();
	                servidor.close();
	            } catch (IOException ex) {
	            	System.out.print("Error al cerrar el servidor: " + ex.getMessage());
	            }
	        }
	   }

}
