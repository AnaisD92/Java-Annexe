package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Reservation;
import controleur.Salle;
import controleur.TB;
import controleur.TableauBord;

import controleur.Usager;
import controleur.User;

public class Modele {
	
	//private static Bdd uneBdd = new Bdd("IP PROJET","annexe","anais","anais@2023"); connexion jour de l'epreuve
	private static Bdd uneBdd = new Bdd("localhost","annexe","root","");
	
//------------------------ GESTION USERS-----------------------------------------------------------------------------------------------
	
	public static User selectWhereUser(String email, String mdp) 
	{
		User unUser = null;
		String requete = "SELECT * FROM user WHERE email = '"+email
				+"' and mdp = '" + mdp + "';";
		try  
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);	
			if(unResultat.next())
			{
				//repecter l'ordre
				unUser = new User (
						unResultat.getInt("iduser"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("role")
						);
				
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
			return unUser;
				
	}
	
	
	//----------------------------------INSERTION D'UN USAGER-----------------------------------------------------------------------------
	
	
	public static void updateUsager(Usager unUsager) {
	    String requete = "UPDATE usager SET nom = '"
	            + unUsager.getNom() + "', prenom = '"
	            + unUsager.getPrenom() + "', dateNaiss = '"
	            + unUsager.getdateNaiss() + "', adresse = '"
	            + unUsager.getadresse() + "', tel = '"
	            + unUsager.gettel() + "', classe = '"
	            + unUsager.getclasse() + "', ville = '"
	            + unUsager.getville() + "', cp = '"
	            + unUsager.getcp() + "', sex = '"
	            + unUsager.getsex() + "', pays = '"
	            + unUsager.getpays() + "', etat = '"
	            + unUsager.getetat() + "' WHERE idUsager = " + unUsager.getIdpers() + ";";

	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        unStat.execute(requete);
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requête : " + requete);
	    }
	}

	//----------------------------------INSERTION D'UN USAGER----------------------------------------------------------------------------- 
	
	public static void insertUsager (Usager unUsager)
	{
		String requete ="INSERT INTO usager VALUES (null, '"
				+ unUsager.getnom()+"','" + unUsager.getprenom()+"','"
				+ unUsager.getdateNaiss()+"','"
				+ unUsager.getadresse()+"','" + unUsager.gettel()+"','"
				+ unUsager.getclasse()+"','" + unUsager.getville()+"','"
				+ unUsager.getcp()+"','" + unUsager.getsex()+"','"
				+ unUsager.getpays()+"','"+ unUsager.getetat()+"');";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
			exp.printStackTrace();
		}
	}
	
	//-------------------------------------SELECTION DE TOUS LES USAGERS--------------------------------------------------------------- 
	public static ArrayList<Usager> selectAllUsager() {
		ArrayList<Usager> lesUsagers = new ArrayList<Usager>();
		String requete="SELECT * FROM usager ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des usagers: fetchall en PHP
			while(desResultats.next())//tant qu'il y'a resultat suivant
			{
				//prendre les donnees dans la bdd
				Usager unUsager = new Usager (
						desResultats.getInt("idpers"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("dateNaiss"),
						desResultats.getString("adresse"),						
						desResultats.getString("tel"),
						desResultats.getString("classe"),
						desResultats.getString("ville"),
						desResultats.getString("cp"),
						desResultats.getString("sex"),
						desResultats.getString("pays"),
						desResultats.getString("etat")						
						);
				
				//on ajoute l'usager dans la liste des usagers
				lesUsagers.add(unUsager);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesUsagers;
	}
	
	public static Usager selectWhereUsager(String nom, String prenom) {
		Usager unUsager=null;
		String requete="SELECT * FROM usager where nom='"+nom+"' and prenom='"+prenom+"';";	
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des usagers: fetchall en PHP
			if(desResultats.next())//tant qu'il y'a resultat suivant
			{
				//prendre les donnees dans la bdd
				 unUsager = new Usager (
						desResultats.getInt("idpers"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("dateNaiss"),
						desResultats.getString("adresse"),						
						desResultats.getString("tel"),
						desResultats.getString("classe"),
						desResultats.getString("ville"),
						desResultats.getString("cp"),
						desResultats.getString("sex"),
						desResultats.getString("pays"),
						desResultats.getString("etat")						
						);
				 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return unUsager;
	}
	
	//--------------------------------SELECT LIKE USAGER-------------------------------------------------------------------------------
	
	public static ArrayList<Usager> selectLikeUsager(String mot) 
	{
		ArrayList<Usager> lesUsagers = new ArrayList<Usager>();
		String requete="SELECT * FROM usager where "
		+ "idpers like '%" + mot+ "%' or  "
		+ "nom like '%" + mot+ "%' or  "
		+ "prenom like '%" + mot+ "%' or "
		+ "dateNaiss like '%" + mot+ "%' or "
		+ "adresse like '%" + mot+ "%' or "
		+ "tel like '%" + mot+ "%' or  "
		+ "classe like '%" + mot+ "%' or "
		+ "ville like '%" + mot+ "%' or "
		+ "cp like '%" + mot+ "%' or "
		+ "sex like '%" +  mot+ "%' or "
		+"pays like '%" + mot + "%' or "
		+"etat like '%"+ mot + "%' ;"
		;
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			
			while(desResultats.next())//tant qu'il un resultat suivant
			{
				Usager unUsager = new Usager  (	
						desResultats.getInt("idpers"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("dateNaiss"),
						desResultats.getString("adresse"),						
						desResultats.getString("tel"),
						desResultats.getString("classe"),
						desResultats.getString("ville"),
						desResultats.getString("cp"),
						desResultats.getString("sex"),
						desResultats.getString("pays"),
						desResultats.getString("etat")						
						
						);
				
				
				lesUsagers.add(unUsager);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesUsagers;
	}
	
	//----------------------------------------DELETE USAGER-----------------------------------------------------------------------------------
	
	public static void deleteUsager(int idUsager) {
		String requete="DELETE FROM usager where idUsager = " + idUsager +";"; 
	
	try 
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
	catch(SQLException exp)
	{
		System.out.println("Erreur de requete:" + requete);
	}
	
}
	
//--------------------------------------- INSERTION D'UNE SALLE--------------------------------------------------------------------------------------
	public static void insertSalle (Salle uneSalle)
	{
		String requete ="INSERT INTO salle VALUES (null, '"
				+ uneSalle.getLibelle()+"','" + uneSalle.getCapacite()+"','"
				+ uneSalle.getEtat()+"');";
		
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	}
	//----------------------------------------DELETE SALLE-----------------------------------------------------------------------------------
	
	public static void deleteSalle(int idSalle) {
		String requete="DELETE FROM salle where idSalle = " + idSalle +";"; 
	
	try 
	{
		uneBdd.seConnecter();
		Statement unStat = uneBdd.getMaconnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBdd.seDeconnecter();
	}
	
	catch(SQLException exp)
	{
		System.out.println("Erreur de requete:" + requete);
	}
	
}

//------------------------------------SELECTION DE LA VUE ------------------------------------------------	
	
	public static ArrayList<TB> selectAllTB () {
		ArrayList<TB> lesTBs = new ArrayList<TB>();
		String requete = "SELECT * FROM TB;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				TB unTB = new TB (
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("carte"),
						desResultats.getString("salle"),
						desResultats.getString("avis")
						);
				lesTBs.add(unTB);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesTBs;
	}
	
//------------------------------------ -SELECT ALL SALLE-------------------------------------------------------------
	
	public static ArrayList<Salle> selectAllSalle() {
		ArrayList<Salle> lesSalles = new ArrayList<Salle>();
		String requete="SELECT * FROM salle ;";
		try 
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			
			//extraction des usagers: fetchall en PHP
			while(desResultats.next())//tant qu'il y'a resultat suivant
			{
				//prendre les donnees dans la bdd
				Salle uneSalle = new Salle (
						desResultats.getInt("idSalle"),
						desResultats.getString("libelle"),
						desResultats.getString("capacite"),
						desResultats.getString("etat")						
						);
				
				//on ajoute l'usager dans la liste des usagers
				lesSalles.add(uneSalle);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
	return lesSalles;
	}
	
	
	//------------------------------------ WHERE  SALLE---------------------------------------------------------------------------------------
	public static Salle selectWhereSalle (String libelle, String capacite) 
	{
		Salle uneSalle = null;
		String requete = "SELECT * FROM salle WHERE libelle = '"+libelle
				+"' and capacite = '" + capacite + "';";
		try  
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);	
			if(unResultat.next())
			{
				//repecter l'ordre
				uneSalle = new Salle (
						unResultat.getInt("idSalle"),
						unResultat.getString("libelle"),
						unResultat.getString("capacite"),
						unResultat.getString("etat")
						);
				
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		
		catch(SQLException exp)
		{
			System.out.println("Erreur de requete:" + requete);
		}
			return uneSalle;
				
	}
	
	
	public static void updateSalle (Salle uneSalle) {
		String requete = "UPDATE salle SET libelle = '"
				+ uneSalle.getCapacite() + "', capacite = '" 
				+ uneSalle.getCapacite() + "', etat = '"
				+ uneSalle.getEtat() + "' WHERE idSalle = "+uneSalle.getIdSalle()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaconnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}

// -------------------------------------------LIKE SALLE---------------------------------------------------------------------------
	
	public static ArrayList<Salle> selectLikeSalle(String mot) {
	    ArrayList<Salle> lesSalles = new ArrayList<Salle>();
	    String requete = "SELECT * FROM salle where "
	            + "idSalle like '%" + mot + "%' or "
	            + "libelle like '%" + mot + "%' or "
	            + "capacite like '%" + mot + "%' or "
	            + "etat like '%" + mot + "%';";

	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);

	        while (desResultats.next()) {
	            Salle uneSalle = new Salle(
	                    desResultats.getInt("idSalle"),
	                    desResultats.getString("libelle"),
	                    desResultats.getString("capacite"),
	                    desResultats.getString("etat")
	            );

	            lesSalles.add(uneSalle);
	        }
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete:" + requete);
	    }
	    return lesSalles;
	}

//----------------------  SELECT ALL RESERVATION ---------------------------------------------
	
	
	public static ArrayList<Reservation> selectAllReservations() {
	    ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
	    String requete = "SELECT * FROM reservation;";

	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);

	        while (desResultats.next()) {

	            Reservation uneReservation = new Reservation(
	                    desResultats.getInt("idReservation"),
	                    desResultats.getInt("idUsager"),
	                    desResultats.getInt("idSalle"),
	                    desResultats.getString("dateHeureDeboccup"),
	                    desResultats.getString("heure_fin_occup"),
	                    desResultats.getInt("nbPersonne") // Changé en int
	            );
	            lesReservations.add(uneReservation);
	        }
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requête: " + requete);
	    }
	    return lesReservations;
	}

//----------------------------------LIKE RESERVATION-----------------------------------
	
	public static ArrayList<Reservation> selectLikeReservation(String mot) {
	    ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
	    String requete = "SELECT * FROM reservation WHERE "
	            + "idReservation LIKE '%" + mot + "%' OR "
	            + "idUsager LIKE '%" + mot + "%' OR "
	            + "idSalle LIKE '%" + mot + "%' OR "
	            + "dateHeureDeboccup LIKE '%" + mot + "%' OR "
	            + "heure_fin_occup LIKE '%" + mot + "%' OR "
	            + "nbPersonne LIKE '%" + mot + "%';";

	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);

	        while (desResultats.next()) {
	            Reservation uneReservation = new Reservation(
	                    desResultats.getInt("idReservation"),
	                    desResultats.getInt("idUsager"),
	                    desResultats.getInt("idSalle"),
	                    desResultats.getString("dateHeureDeboccup"),
	                    desResultats.getString("heure_fin_occup"),
	                    desResultats.getInt("nbPersonne") // Changé en int
	            );

	            lesReservations.add(uneReservation);
	        }
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requête: " + requete);
	    }
	    return lesReservations;
	}
	//--------------------------INSERT RESERVATION--------------------------------------------------
	
	public static void insertReservation(Reservation uneReservation) {
	    String requete = "INSERT INTO reservation (idreservation,idUsager, idSalle, dateHeureDeboccup, heure_deb_occup, heure_fin_occup, nbPersonne) VALUES (null, "
	    		
	            + uneReservation.getIdUsager() + ", "
	            + uneReservation.getIdSalle() + ", '"
	            + uneReservation.getDateOccup() + "', '8:00','"
	            + uneReservation.getHeureOccup() + "', '"
	            + uneReservation.getNbPers() + "');";

	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        unStat.execute(requete);
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete: " + requete);
	        exp.printStackTrace();
	    }
	}

//-----------------------------------DELETE RESERVATION------------------------------------------------
	
	public static void deleteReservation(int idReservation) {
	    String requete = "DELETE FROM reservation WHERE idReservation = " + idReservation + ";";

	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        unStat.execute(requete);
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete: " + requete);
	    }
	}

	//-----------------------------------COUNT RESERVATION------------------------------------------------
	public static int countReservation() {
	    int nbReservations = 0;
	    String requete = "SELECT count(*) as nb FROM reservation;";
	    try {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaconnexion().createStatement();
	        ResultSet unResultat = unStat.executeQuery(requete);
	        if (unResultat.next()) {
	            nbReservations = unResultat.getInt("nb");
	        }
	        unStat.close();
	        uneBdd.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requête : " + requete);
	    }
	    return nbReservations;
	}
//-----------------------------------COUNT SALLE------------------------------------------------
		public static int countSalle() {
		    int nbSalles = 0;
		    String requete = "SELECT count(*) as nb FROM salle;";
		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        ResultSet unResultat = unStat.executeQuery(requete);
		        if (unResultat.next()) {
		            nbSalles = unResultat.getInt("nb");
		        }
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur de requête : " + requete);
		    }
		    return nbSalles;
		}
		
//-----------------------------------COUNT USAGER------------------------------------------------
		public static int countUsager() {
		    int nbUsagers = 0;
		    String requete = "SELECT count(*) as nb FROM usager;";
		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        ResultSet unResultat = unStat.executeQuery(requete);
		        if (unResultat.next()) {
		            nbUsagers = unResultat.getInt("nb");
		        }
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur de requête : " + requete);
		    }
		    return nbUsagers;
		}
//-----------------------------------SELECT ALL TABLEAU------------------------------------------------
		public static ArrayList<TableauBord> selectAllTableau() {
		    ArrayList<TableauBord> lesTableaux = new ArrayList<TableauBord>();
		    String requete = "SELECT * FROM tableau;";

		    try {
		        uneBdd.seConnecter();
		        Statement unStat = uneBdd.getMaconnexion().createStatement();
		        ResultSet desResultats = unStat.executeQuery(requete);

		        while (desResultats.next()) {

		        	TableauBord unTableau = new TableauBord(
		                    desResultats.getInt("idReservation"),
		                    desResultats.getString("usager_nom"),
		                    desResultats.getString("usager_prenom"),
		                    desResultats.getString("salle_libelle"),
		                    desResultats.getString("date_occup"),
		                    desResultats.getString("heure_deb_occup"),
		                    desResultats.getString("heure_fin_occup"),
		                    desResultats.getString("reservation_etat")
		            );
		            lesTableaux.add(unTableau);
		        }
		        unStat.close();
		        uneBdd.seDeconnecter();
		    } catch (SQLException exp) {
		        System.out.println("Erreur de requête: " + requete);
		    }
		    return lesTableaux;
		}

		
	}



	


