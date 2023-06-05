package controleur;

public class Personne {
	
	protected int idpers;
	protected String nom, prenom;
	
	public Personne(int idpers, String nom, String prenom) {
		this.idpers = idpers;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne(String nom, String prenom) {
		this.idpers = 0;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getIdpers() {
		return idpers;
	}

	public void setIdpers(int idpers) {
		this.idpers = idpers;
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

}
