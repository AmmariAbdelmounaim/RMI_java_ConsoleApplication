import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingManagerImpl extends UnicastRemoteObject implements ParkingManager {

    private ConcurrentHashMap<Integer, Reservation> reservations;
    private static final int NUM_SPOTS_PER_ROW = 5;
    private static final int NUM_ROWS = 5;


    protected ParkingManagerImpl() throws RemoteException{
        super();
        reservations = new ConcurrentHashMap<>();
    }

    @Override
    public String getParkingLotStatus() throws RemoteException {
        StringBuilder status = new StringBuilder();
        status.append("   1 2 3 4 5\n"); // header for column numbers

        for (int row = 1; row <= NUM_ROWS; row++) {
            status.append(row).append(" "); // row number

            for (int spot = 1; spot <= NUM_SPOTS_PER_ROW; spot++) {
                int id = row * 10 + spot; // x0 + y = xy

                boolean isReserved = reservations.values().stream()
                        .anyMatch(reservation -> reservation.getIdReservation().equals(String.valueOf(id)));

                status.append(isReserved ? " R" : " E");
            }
            status.append("\n"); // nouvelle ligne après chaque ligne

        }

        return status.toString();
    }
    @Override
    public boolean chercher(Integer numeroPlace) throws RemoteException {
        return !reservations.containsKey(numeroPlace);
    }

    @Override
    public void reserver(String idReservation,Integer numeroPlace, String nomClient, Date dateReservation) throws RemoteException {
        if(!reservations.containsKey(numeroPlace)) {
            reservations.put(numeroPlace, new Reservation(idReservation, numeroPlace, nomClient, dateReservation));
            System.out.println("Reservation successful. Current parking lot status:");
            System.out.println(getParkingLotStatus());  // This line prints out the current parking status.
        } else {
            throw new RemoteException("Place déjà réservée.");
        }
    }

    @Override
    public void annuler(String idReservation) throws RemoteException {
        reservations.entrySet().removeIf(entry -> entry.getValue().getIdReservation().equals(idReservation));
    }
}
