package controleur;

public class TB {
	
	private String nom, prenom, carte, salle, avis;

	public TB(String nom, String prenom, String carte, String salle, String avis) {
		this.nom = nom;
		this.prenom = prenom;
		this.carte = carte;
		this.salle = salle;
		this.avis = avis;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCarte() {
		return carte;
	}

	public void setCarte(String carte) {
		this.carte = carte;
	}

	public String getSalle() {
		return salle;
	}

	public void setSalle(String salle) {
		this.salle = salle;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}


}
