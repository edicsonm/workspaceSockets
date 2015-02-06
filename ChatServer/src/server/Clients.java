package server;
import java.io.DataOutputStream;
import java.util.HashMap;

public class Clients {
	private static Clients instance = null;
	private HashMap<String, DataOutputStream> clientes = new HashMap<String, DataOutputStream>();
	
	public static synchronized Clients getInstance() {
		if (instance == null) {
			instance = new Clients();
		}
		return instance;
	}
	
	public HashMap<String, DataOutputStream> getClientes() {
		return clientes;
	}

	public void setClientes(HashMap<String, DataOutputStream> clientes) {
		this.clientes = clientes;
	}

}