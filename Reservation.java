import java.util.Date;

public class Reservation {
    private String idReservation;
    private Integer numberPlace;
    private String nomClient;
    private Date dateReservation;

    public Reservation(String idReservation,Integer numberPlace,String nomClient,Date dateReservation){
        this.idReservation = idReservation;
        this.numberPlace = numberPlace;
        this.nomClient = nomClient;
        this.dateReservation = dateReservation;
    }


    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getNumberPlace() {
        return numberPlace;
    }

    public void setNumberPlace(Integer numberPlace) {
        this.numberPlace = numberPlace;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
}
