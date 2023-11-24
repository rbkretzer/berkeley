package berkeley.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import berkeley.utils.ServerTime;
import berkeley.utils.ServerTimeImpl;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class BerkeleyServer {
    	public static void main(String[] args) {
		final int port = 4500;

		try {
			ServerTime server = new ServerTimeImpl();
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind("ServerTimeImpl", server);
			System.out.println("ServerTimeImpl started");
		} catch (Exception ex) {
			System.out.println("Ocorreu o seguinte erro ao rodar o BerkeleyServer: " + ex.getMessage());
		}

	}
}
