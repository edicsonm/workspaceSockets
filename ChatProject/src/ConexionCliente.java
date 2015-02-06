import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionCliente extends Thread{
	
	private Socket socket;
    private MensajesChat mensajes;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    
	public ConexionCliente(Socket socket) {
		try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
        	System.out.print("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
	}
	
	public void run(){
		boolean isAlive = true;
		String mensajeRecibido;
		
		while (isAlive){
			 try {
				mensajeRecibido = entradaDatos.readUTF();
				System.out.println("mensajeRecibido: " + mensajeRecibido);
			} catch (IOException e) {
				System.out.print("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
				isAlive = false;
                // Si se ha producido un error al recibir datos del cliente se cierra la conexion con el.
                try {
                    entradaDatos.close();
                    salidaDatos.close();
                } catch (IOException ex2) {
                	System.out.print("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
			}
		}
	}

}
