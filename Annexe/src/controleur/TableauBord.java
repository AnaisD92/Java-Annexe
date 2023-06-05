package controleur;

public class TableauBord{

	private int idReservation;
    private String usagerNom;
    private String usagerPrenom;
    private String salleLibelle;
    private String dateOccup;
    private String heureDebOccup;
    private String heureFinOccup;
    private String reservationEtat;

    	public TableauBord(int idReservation, String usagerNom, String usagerPrenom, String salleLibelle, String dateOccup, String heureDebOccup, String heureFinOccup, String reservationEtat) {
        this.idReservation = idReservation;
        this.usagerNom = usagerNom;
        this.usagerPrenom = usagerPrenom;
        this.salleLibelle = salleLibelle;
        this.dateOccup = dateOccup;
        this.heureDebOccup = heureDebOccup;
        this.heureFinOccup = heureFinOccup;
        this.reservationEtat = reservationEtat;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getUsagerNom() {
        return usagerNom;
    }

    public void setUsagerNom(String usagerNom) {
        this.usagerNom = usagerNom;
    }

    public String getUsagerPrenom() {
        return usagerPrenom;
    }

    public void setUsagerPrenom(String usagerPrenom) {
        this.usagerPrenom = usagerPrenom;
    }

    public String getSalleLibelle() {
        return salleLibelle;
    }

    public void setSalleLibelle(String salleLibelle) {
        this.salleLibelle = salleLibelle;
    }

    public String getDateOccup() {
        return dateOccup;
    }

    public void setDateOccup(String dateOccup) {
        this.dateOccup = dateOccup;
    }

    public String getHeureDebOccup() {
        return heureDebOccup;
    }

    public void setHeureDebOccup(String heureDebOccup) {
        this.heureDebOccup = heureDebOccup;
    }

    public String getHeureFinOccup() {
        return heureFinOccup;
    }

    public void setHeureFinOccup(String heureFinOccup) {
        this.heureFinOccup = heureFinOccup;
    }

    public String getReservationEtat() {
        return reservationEtat;
    }

    public void setReservationEtat(String reservationEtat) {
        this.reservationEtat = reservationEtat;
    }
}






