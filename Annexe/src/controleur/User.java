package controleur;

public class User extends Personne {
	
	private String email, mdp, role;
	
	public User(int iduser, String nom, String prenom, String email, String mdp, String role) {
		super(iduser, nom, prenom);
		this.email = email;
		this.mdp = mdp;
		this.role = role;
	}
	
	public User(String nom, String prenom, String email, String mdp, String role) {
		super(nom, prenom);
		this.email = email;
		this.mdp = mdp;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
