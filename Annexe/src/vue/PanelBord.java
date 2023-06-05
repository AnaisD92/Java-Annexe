package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Tableau;
import controleur.TableauBord;
import modele.Modele;

public class PanelBord extends PanelDeBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelBord = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelBord() {
		super(Color.lightGray);
		
		this.panelBord.setBounds(10, 10, 727, 303);
		this.panelBord.setBackground(Color.yellow); 
		this.panelBord.setLayout(null);
		
		String entetes[] = {"Salle","Nom", "Prénom", "Reservation", "Heure Debut","Etat"};
		Object donnees[][] = this.getLesDonnees();
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		JScrollPane uneSroll = new JScrollPane(this.uneTable);
		uneSroll.setBounds(10, 10, 707, 280);
		this.panelBord.add(uneSroll);
		
		this.add(this.panelBord);
	}
	
	public Object[][] getLesDonnees() {
	    ArrayList<TableauBord> lesTableaux = Modele.selectAllTableau();
	    Object[][] matrice = new Object[lesTableaux.size()][6];
	    int i = 0;
	    for (TableauBord unTableau : lesTableaux) {
	        matrice[i][0] = unTableau.getSalleLibelle();
	        matrice[i][1] = unTableau.getUsagerNom();
	        matrice[i][2] = unTableau.getUsagerPrenom();
	        matrice[i][3] = unTableau.getDateOccup();
	        matrice[i][4] = unTableau.getHeureDebOccup();
	        matrice[i][5] = unTableau.getReservationEtat();
	        i++;
	    }
	    return matrice;
	}
}
