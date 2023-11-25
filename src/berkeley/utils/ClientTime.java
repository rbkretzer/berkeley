package berkeley.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Gabriel A. Rodrigues, Leonardo S. Nunes e Rafael B. Kretzer
 *
 */
public interface ClientTime extends Remote {
    long getTime() throws RemoteException;
    long getTimeDiff(long serverTime) throws RemoteException;
    void setTime(long timeInSeconds) throws RemoteException;
}
