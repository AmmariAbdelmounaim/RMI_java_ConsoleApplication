import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            ParkingManagerImpl obj = new ParkingManagerImpl();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("ParkingManagerService", obj);

            System.out.println("Serveur RMI prêt.");
        } catch (Exception e) {
            System.err.println("Exception serveur RMI: " + e.toString());
            e.printStackTrace();
        }
    }

}
