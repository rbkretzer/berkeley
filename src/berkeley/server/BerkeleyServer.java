package berkeley.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import berkeley.utils.Client;
import berkeley.utils.ClientTime;
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
			ServerTimeImpl server = new ServerTimeImpl();
			Registry registryServer = LocateRegistry.createRegistry(port);
			registryServer.rebind("ServerTimeImpl", server);
			System.out.println("ServerTimeImpl started");

			while (true) {
				long totalDiff = 0l;
				List<Client> clients = new ArrayList<>(server.getClientsConnected());
				ClientTime ct = null;
				for (Client c : clients) {
					ct = getClientPort(c);
					if (ct == null) {
						continue;
					}					
					totalDiff += ct.getTimeDiff(server.getTime().toSeconds());
				}
				long toAdjust = totalDiff / (clients.size() + 1);
				for (Client c : clients) {
					System.out.println("Change time client " + c.getId());
					ct = getClientPort(c);
					if (ct == null) {
						continue;
					}
					
					long resto = ct.getTime() - ct.getTimeDiff(server.getTime().toSeconds());
					ct.setTime(toAdjust + resto);
				}
				if (clients.isEmpty()) {
					continue;
				}
				ct = getClientPort(clients.get(0));
				if (ct == null) {
					return;
				}
				server.setTime(server.getTime().toSeconds() + ct.getTimeDiff(server.getTime().toSeconds()));
				Thread.sleep(5000);
			}
		} catch (Exception ex) {
			System.out.println("Ocorreu o seguinte erro ao rodar o BerkeleyServer: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	private static ClientTime getClientPort(Client c) {
		try {
			Registry registryClient = LocateRegistry.getRegistry(c.getHost(), c.getPort());
			return (ClientTime) registryClient.lookup("ClientTimeImpl" + c.getId());
		} catch (Exception e) {
			System.out.println("Não foi possível se conectar com o client");
			return null;
		}
	}
}
