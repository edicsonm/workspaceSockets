import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;


public class Cliente {
	
	private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    
	public static void main(String[] args) {
		new Cliente();
	}
	
	public Cliente(){
		try {
            /* Se crea el socket cliente */
            Socket socket = new Socket ("localhost", 1234);
            System.out.println ("conectado");
            /* Se obtiene un stream de lectura para leer tipos simples de java */
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            salidaDatos.writeUTF("Este es un mensaje 1....");
            salidaDatos.writeUTF("Este es un mensaje .2...");
//            salidaDatos.writeUTF("Este es un mensaje ..3..");
//            salidaDatos.writeUTF("Este es un mensaje ...4.");
            
            String mensajeRecibido = entradaDatos.readUTF();
            System.out.println("mensajeRecibido en el cliente: " + mensajeRecibido);
            
            
            
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
