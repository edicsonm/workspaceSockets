import java.io.DataOutputStream;
import java.util.HashMap;


public class Clientes {
	private static Clientes instance = null;
	private HashMap<String, DataOutputStream> clientes = new HashMap<String, DataOutputStream>();
	
	public static synchronized Clientes getInstance() {
		if (instance == null) {
			instance = new Clientes();
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
