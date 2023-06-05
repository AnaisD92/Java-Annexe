package controleur;

public class Salle {
	
	private int idSalle;
	private String libelle, capacite, etat;
	
	public Salle(int idSalle, String libelle, String capacite, String etat) {
		this.idSalle = idSalle;
		this.libelle = libelle;
		this.capacite = capacite;
		this.etat = etat;
	}
	
	public Salle(String libelle, String capacite, String etat) {
		this.idSalle=0;
		this.libelle = libelle;
		this.capacite = capacite;
		this.etat = etat;
	}
	
	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCapacite() {
		return capacite;
	}

	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}


	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
}
