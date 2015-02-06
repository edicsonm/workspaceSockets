import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client {

	public static void main(String args[]) {
	      try {
	         Socket socket = new Socket("localhost", Constant.SERVER_PORT);
	         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	         System.out.print("recibiendo data: '");

	         while (!in.ready()) {}
	         System.out.println(in.readLine());
	         System.out.print("'\n");
	         in.close();
	      }
	      catch(Exception e) {
	         System.out.print("No fue posible conectarse ... ");
	      }
	   }

}
