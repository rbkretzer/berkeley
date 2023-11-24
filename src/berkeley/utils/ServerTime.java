package berkeley.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public interface ServerTime extends Remote {

    void registerClient(Client client)  throws RemoteException;
    void removeClient(Client client) throws RemoteException;

	Time getTime() throws RemoteException;

	void setTime(long epochSeconds) throws RemoteException;
}