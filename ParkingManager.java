import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface ParkingManager extends Remote {
    boolean chercher(Integer numeroPlace) throws RemoteException;
    void reserver(String idReservation, Integer numeroPlace, String nomClient, Date dateReservation) throws RemoteException;
    void annuler(String idReservation) throws RemoteException;

    String getParkingLotStatus()  throws RemoteException;
}
