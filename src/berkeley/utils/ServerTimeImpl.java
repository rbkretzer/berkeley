package berkeley.utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class ServerTimeImpl extends UnicastRemoteObject implements ServerTime {

	private static final long serialVersionUID = 1L;
	private Time time;
    private List<Client> clientsConnected = new ArrayList<>();

	public ServerTimeImpl() throws RemoteException {
		time = new Time();
		System.out.println("Server time: " + time);
	}

	@Override
	public Time getTime() throws RemoteException {
		return time;
	}

	@Override
	public void setTime(long secondsOfDay) throws RemoteException {
		time.setValue(LocalTime.ofSecondOfDay(secondsOfDay));
		System.out.println("Updated time to: " + time);
	}

    @Override
    public void registerClient(Client client) throws RemoteException {
        boolean added = clientsConnected.add(client);
        System.out.println(added ? "Client added" : "Client not added");
        System.out.println(clientsConnected.size());
    }

    @Override
    public void removeClient(Client client) throws RemoteException {
        boolean removed = clientsConnected.remove(client);
        System.out.println(removed ? "Client removed" : "Client not removed");
        System.out.println(clientsConnected.size());
    }
}
