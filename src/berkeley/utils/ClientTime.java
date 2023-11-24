package berkeley.utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientTime extends Remote {
    long getTimeDiff(long serverTime, int clientId) throws RemoteException;
    void setTime(long timeInSeconds, int clientId) throws RemoteException;
}
