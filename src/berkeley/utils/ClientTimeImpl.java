package berkeley.utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public class ClientTimeImpl extends UnicastRemoteObject implements ClientTime {

    private static final long serialVersionUID = 1L;
    private Client client;

    public Client getClient() {
        return client;
    }

    public ClientTimeImpl(Client client) throws RemoteException {
        this.client = client;
    }

    @Override
    public long getTime() throws RemoteException {
        return client.getTime().toSeconds();
    }

    @Override
    public long getTimeDiff(long serverTimeSeconds) throws RemoteException {
        return client.getTime().toSeconds() - serverTimeSeconds;
    }

    @Override
    public void setTime(long timeInSeconds) throws RemoteException {
        System.out.println("Old value client " + client.getId() + ": " + client.getTime().getValue());
        client.getTime().setValue(LocalTime.ofSecondOfDay(timeInSeconds));
        System.out.println("New value " + client.getId() + ": " + client.getTime().getValue());
    }

}
