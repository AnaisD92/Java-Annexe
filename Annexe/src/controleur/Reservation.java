package controleur;

public class Reservation {

    protected int idreservation;
    protected int idUsager;
    protected int idSalle;
    protected String dateOccup, heureOccup;
    protected int nbPers;

    public Reservation(int idreservation, int idUsager, int idSalle, String dateOccup, String heureOccup, int nbPers) {
        this.idreservation = idreservation;
        this.idUsager = idUsager;
        this.idSalle = idSalle;
        this.dateOccup = dateOccup;
        this.heureOccup = heureOccup;
        this.nbPers = nbPers;
    }

    public Reservation(int idUsager, int idSalle, String dateOccup, String heureOccup, int nombrePersonnes) {
        this.idreservation = 0;
        this.idUsager = idUsager;
        this.idSalle = idSalle;
        this.dateOccup = dateOccup;
        this.heureOccup = heureOccup;
        this.nbPers = nombrePersonnes;
    }

    public int getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(int idUsager) {
        this.idUsager = idUsager;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public String getDateOccup() {
        return dateOccup;
    }

    public void setDateOccup(String dateOccup) {
        this.dateOccup = dateOccup;
    }

    public String getHeureOccup() {
        return heureOccup;
    }

    public void setHeureOccup(String heureOccup) {
        this.heureOccup = heureOccup;
    }

    public int getNbPers() {
        return nbPers;
    }

    public void setNbPers(int nbPers) {
        this.nbPers = nbPers;
    }
}
