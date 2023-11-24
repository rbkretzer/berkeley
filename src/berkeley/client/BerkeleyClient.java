package berkeley.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import berkeley.utils.Client;
import berkeley.utils.ClientTime;
import berkeley.utils.ClientTimeImpl;
import berkeley.utils.ServerTime;
import berkeley.utils.ServerTimeImpl;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class BerkeleyClient {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 4500);
			ServerTime st = (ServerTime) registry.lookup("ServerTimeImpl");
			Registry registryClient = LocateRegistry.getRegistry();
			ClientTime client = new ClientTimeImpl();
			registry.rebind("CTimeImpl", client);
			
			// Clients sao mockados com qualquer horário no dia de hoje
			List<Client> clients = new ArrayList<>();
			clients.add(new Client(1, 4501));
			clients.add(new Client(2, 4502));
			for (Client c : clients) {
				st.registerClient(c);
				System.out.println("ServerTimeImpl started");
			}

			// Map<Client, ServerTime> servers = new HashMap<>();

			// for (Client c : clients) {
			// 	System.out.println("Client time: " + c.getTime());
			// 	Registry registry = LocateRegistry.getRegistry(c.getHost(), c.getPort());
			// 	ServerTime st = (ServerTime) registry.lookup("ServerTimeImpl");
			// 	System.out.println(String.format("Server time %s: %s", c.getHost(), st.getTime().getValue()));
			// 	servers.put(c, st);
			// }


			// // Berkeley
			// long coordinatorSeconds = time.getEpochSeconds();
			// long diffServer = 0;
			// long sum = 0;
			// long timeServer = 0;
			// for (Entry<Client, ServerTime> entry : servers.entrySet()) {
			// 	timeServer = (entry.getValue().getTime().getEpochSeconds());
			// 	diffServer = timeServer - coordinatorSeconds;
			// 	sum += diffServer;
			// }
			// long average = sum / (servers.size() + 1);

			// long resultSeconds = (average + (-1 * diffServer) + timeServer);

			// LocalDateTime resultDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(resultSeconds),
			// 		ZoneOffset.UTC);
			// System.out.println("Result Berkeley: " + resultDateTime);

			// // Set the new date time
			// for (Map.Entry<Client, ServerTime> entry : servers.entrySet()) {
			// 	entry.getValue().setTime(resultSeconds);
			// 	System.out.println(String.format("Server time %s: %s ", entry.getKey().getHost(),
			// 			entry.getValue().getTime().getValue()));
			// }
			// time.setValue(resultDateTime);
			// System.out.println("Client time: " + time);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}