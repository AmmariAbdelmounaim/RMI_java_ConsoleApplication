import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {
    private static ParkingManager stub;
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        try {
            // Se connecter au registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            stub = (ParkingManager) registry.lookup("ParkingManagerService");


            // Menu de l'interface en ligne de commande
            while (true) {
                System.out.println("\nRécupération de l'état actuel du parking\n...");
                String parkingStatus = stub.getParkingLotStatus();
                System.out.println(parkingStatus);
                System.out.println("\n--- Gestion de Parking RMI ---");
                System.out.println("1. Vérifier la disponibilité d'une place");
                System.out.println("2. Réserver une place");
                System.out.println("3. Annuler une réservation");
                System.out.println("4. Quitter");
                System.out.print("Choisissez une option : ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        verifierDisponibilite();
                        break;
                    case 2:
                        effectuerReservation();
                        break;
                    case 3:
                        annulerReservation();
                        break;
                    case 4:
                        System.out.println("Au revoir !");
                        return;
                    default:
                        System.out.println("Option non reconnue, veuillez réessayer.");
                }
            }
        } catch (Exception e) {
            System.err.println("Exception dans l'interface en ligne de commande : " + e.toString());
            e.printStackTrace();
        }
    }


    private static void verifierDisponibilite() throws Exception {
        System.out.print("Entrez le numéro de la place à vérifier : ");
        int numeroPlace = scanner.nextInt();
        boolean isAvailable = stub.chercher(numeroPlace);
        if (isAvailable) {
            System.out.println("La place " + numeroPlace + " est disponible.");
        } else {
            System.out.println("La place " + numeroPlace + " est déjà réservée.");
        }
    }

    private static void effectuerReservation() throws Exception {
        System.out.print("Entrez le numéro de la place à réserver : ");
        int numeroPlace = scanner.nextInt();
        if(stub.chercher(numeroPlace)){
            scanner.nextLine(); // Consommer la nouvelle ligne restante
            System.out.print("Entrez votre nom : ");
            String nomClient = scanner.nextLine();
            String idReservation = String.valueOf(numeroPlace);
            // Ici nous utilisons la date et l'heure actuelle pour la réservation.
            Date dateReservation = new Date();

            try {
                stub.reserver(idReservation, numeroPlace, nomClient, dateReservation);
                System.out.println("Réservation effectuée avec succès pour la place " + numeroPlace + " par " + nomClient + " à la date du " + dateFormat.format(dateReservation));
            } catch (Exception e) {
                System.out.println("Impossible de réserver la place : " + e.getMessage());
            }
        }else {
            System.out.println("La place " + numeroPlace + " est déjà réservée.");
        }



    }

    private static void annulerReservation() throws Exception {
        System.out.print("Entrez le numero de place de la réservation à annuler : ");
        String idReservation = scanner.next();
        stub.annuler(idReservation);
        System.out.println("La réservation a été annulée.");
    }
}
