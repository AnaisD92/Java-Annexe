package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Salle;
import controleur.Reservation;
import controleur.Usager;
import controleur.Tableau;
import modele.Modele;

public class PanelStats extends PanelDeBase {
	
	private JPanel panelStat = new JPanel();
	
	private JPanel panelReservation = new JPanel();
	private JPanel panelSalle= new JPanel();
	private JPanel panelUsager= new JPanel();

	public PanelStats() {
		super(Color.lightGray); 
		
		this.panelStat.setLayout(new GridLayout(3,1));
		this.panelStat.setBounds(10, 10, 730, 200);
		// this.setBackground(Color.yellow);
		this.setVisible(false);
		
		String entetes[] = { "NB Reservation", "NB 	Salles", "NB Usager"};
		Object matrice [][] = {{Modele.countReservation(), Modele.countSalle(),Modele.countUsager()}};
		Tableau unTableau = new Tableau (entetes, matrice);
		JTable uneTable = new JTable(unTableau);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0; i<uneTable.getColumnCount(); i++) {
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		JScrollPane uneScoll = new JScrollPane(uneTable);
		uneScoll.setBounds(40, 40, 250, 100);
		this.add(this.panelStat);
		this.panelStat.add(uneScoll);
	}

}
