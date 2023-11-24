package berkeley.utils;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClientTimeImpl implements ClientTime {

    List<Client> clients = new ArrayList<>();

    @Override
    public long getTimeDiff(long serverTimeSeconds, int clientId) throws RemoteException {
        return this.client.getTime().getEpochSeconds() - serverTimeSeconds;
    }

    @Override
    public void setTime(long timeInSeconds, int clientId) throws RemoteException {
        this.client.getTime().setValue(LocalTime.ofSecondOfDay(timeInSeconds));;
    }
    
}
