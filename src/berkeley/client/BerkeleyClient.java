package berkeley.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import berkeley.utils.Client;
import berkeley.utils.ClientTimeImpl;
import berkeley.utils.ServerTime;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class BerkeleyClient {

	public static void main(String[] args) {
		try {
			// inicializado o servico do client
			System.out.println("ServerTimeImpl started");
			
			// buscado o servico do server
			Registry registry = LocateRegistry.getRegistry("localhost", 4500);
			ServerTime st = (ServerTime) registry.lookup("ServerTimeImpl");
			
			List<ClientTimeImpl> clients = new ArrayList<>();
			clients.add(new ClientTimeImpl(new Client(1, "localhost", 4501)));
			clients.add(new ClientTimeImpl(new Client(2, "localhost", 4502)));
			for (ClientTimeImpl c : clients) {
				Registry registryServer = LocateRegistry.createRegistry(c.getClient().getPort());
				registryServer.rebind("ClientTimeImpl" + c.getClient().getId(), c);
				st.registerClient(c.getClient());
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}