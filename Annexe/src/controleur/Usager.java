package controleur;

public class Usager extends Personne {
	
	
	private String dateNaiss, adresse,tel,classe,ville,cp,sex, pays, etat;
	
	public Usager(int idUsager, String nom,String prenom,String dateNaiss,
			String adresse,String tel,String classe, 
			String ville,String cp, String sex, String pays,String etat) {
		super(idUsager,nom,prenom);
		this.dateNaiss= dateNaiss;
		this.adresse = adresse;
		this.tel = tel;
		this.classe = classe;
		this.ville = ville;
		this.cp = cp;
		this.sex = sex;
		this.pays = pays;
		this.etat = etat;
	
	}
	
	public Usager(String nom,String prenom,String dateNaiss,
			String adresse,String tel,String classe, 
			String ville,String cp, String sex, String pays,String etat){
		super(nom,prenom);
		this.dateNaiss= dateNaiss;
		this.adresse = adresse;
		this.tel = tel;
		this.classe = classe;
		this.ville = ville;
		this.cp = cp;
		this.sex = sex;
		this.pays = pays;
		this.etat = etat;
	}
	public String getnom() {
		return nom;
	}

	public void setnom(String nom) {
		this.nom = nom;
	}

	public String getprenom() {
		return prenom;
	}

	public void setprenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getdateNaiss() {
		return dateNaiss;
	}

	public void setdateNaiss(String dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getadresse() {
		return adresse;
	}

	public void setadresse(String adresse) {
		this.adresse = adresse;
	}

	public String gettel() {
		return tel;
	}

	public void settel(String tel) {
		this.tel = tel;
	}
	
	public String getclasse() {
		return classe;
	}

	public void setclasse(String classe) {
		this.classe = classe;
	}
	
	public String getville() {
		return ville;
	}

	public void setville(String ville) {
		this.ville = ville;
	}

	public String getcp() {
		return cp;
	}

	public void setcp(String cp) {
		this.cp = cp;
	}

	public String getsex() {
		return sex;
	}

	public void setsex(String sex) {
		this.sex = sex;
	}

	public String getpays() {
		return pays;
	}

	public void setpays(String pays) {
		this.pays = pays;
	}

	public String getetat() {
		return etat;
	}

	public void setetat(String etat) {
		this.etat = etat;
	}
	

}
