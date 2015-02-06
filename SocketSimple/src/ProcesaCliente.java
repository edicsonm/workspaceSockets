import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;


public class ProcesaCliente extends Thread{
	
	private Socket socket = null;
	private Clientes clientes = Clientes.getInstance();
	private PrintWriter out;
	private BufferedReader in;
	
	private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
	
	public ProcesaCliente(Socket socket){
		this.socket = socket;
		try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            clientes.getClientes().put(String.valueOf(this.getId()), salidaDatos);
        } catch (IOException ex) {
        	System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
		
		this.start();
	}
	
	public void run(){
		boolean conectado = true;
		String mensajeRecibido;
		while (conectado) {
            try {
                // Lee un mensaje enviado por el cliente
                mensajeRecibido = entradaDatos.readUTF();
                Mensaje mensaje = procesaMensaje(mensajeRecibido);
                System.out.println("Cliente XX existe? " + clientes.getClientes().containsKey(String.valueOf(mensaje.getIDDestino())));
                if(!clientes.getClientes().containsKey(String.valueOf(mensaje.getIDDestino()))){
                		out.println("El destino no existe");
                }else {
//                	PrintWriter printWriter = clientes.getClientes().get(String.valueOf(mensaje.getIDDestino()));
//                	printWriter.println(mensaje.getMensaje());
                	DataOutputStream out = clientes.getClientes().get(String.valueOf(mensaje.getIDDestino()));
                	out.writeUTF(mensaje.getMensaje());
                }
                
                
                // Pone el mensaje recibido en mensajes para que se notifique 
                // a sus observadores que hay un nuevo mensaje.
                System.out.println("Mensaje Recibido: " + mensajeRecibido);
            } catch (IOException ex) {
            	System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
                conectado = false; 
                // Si se ha producido un error al recibir datos del cliente se cierra la conexion con el.
                try {
                    entradaDatos.close();
                    salidaDatos.close();
                } catch (IOException ex2) {
                	System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
            }
        }   
	}
	
//	/*public void run2(){
//		try {
//			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//			clientes.getClientes().put(String.valueOf(this.getId()), out);
//			System.out.println("Registrando el cliente " + this.getId());
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//            	Mensaje mensaje = procesaMensaje(inputLine);
////                System.out.println("Recibido en el servidor: " + inputLine);
//                System.out.println("mensaje.getIDDestino():" + mensaje.getIDDestino()+":");
//                System.out.println("Cliente 10 existe? " + clientes.getClientes().containsKey("10"));
//                System.out.println("Cliente 11 existe? " + clientes.getClientes().containsKey("11"));
//                System.out.println("Cliente 12 existe? " + clientes.getClientes().containsKey("12"));
//                System.out.println("Cliente XX existe? " + clientes.getClientes().containsKey(String.valueOf(mensaje.getIDDestino())));
//                if(!clientes.getClientes().containsKey(String.valueOf(mensaje.getIDDestino()))){
//                		out.println("El destino no existe");
////                		String clave;
////                		Iterator<String> productos = clientes.getClientes().keySet().iterator();
////                	    System.out.println("Hay los siguientes productos:");
////                	    while(productos.hasNext()){
////                	        clave = productos.next();
////                	        System.out.println(clave + " - " + clientes.getClientes().get(clave));
////                	    }
//                }else {
//                	PrintWriter printWriter = clientes.getClientes().get(String.valueOf(mensaje.getIDDestino()));
//                	printWriter.println(mensaje.getMensaje());
//                }
//            }
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}*/
	
	public Mensaje procesaMensaje(String mensaje){
		try {
			String[] partes = mensaje.split(":");
			System.out.println("Parte 0:" + partes[0]+":");
			System.out.println("Parte 1: " + partes[1]+":");
			return new Mensaje(Long.parseLong(partes[0]),partes[1]);
		} catch (java.lang.ArrayIndexOutOfBoundsException ex){
			return new Mensaje(this.getId(),"Su mensaje no traia destinatario, no es posible procesarlo");
		}
	}
	
	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
}
