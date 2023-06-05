package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Annexe;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btQuitter = new JButton("Déconnexion");
	private JButton btProfil = new JButton("Mon Profil");
	private JButton btUsager = new JButton("Usager");
	private JButton btSalle = new JButton("Salle");
	private JButton btReservation = new JButton("Reservation");
	private JButton btStats = new JButton("Statistiques");
	private JButton btBoard = new JButton("Tableau de bord");
	
	/*** Les Panels ***/
	private JPanel panelMenu = new JPanel();
	private JPanel panelProfil = new JPanel();
	private static PanelUsager unPanelUsager ;
	private static PanelSalle unPanelSalle = new PanelSalle();
	private static PanelReservation unPanelReservation = new PanelReservation();
	private static PanelStats unPanelStats = new PanelStats();
	private static PanelBord unPanelBord = new PanelBord();
	
	public VueGenerale (User unUser) {
		
		this.setTitle("Annexe Administration");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(152, 251, 152));
		this.setBounds(100, 100, 950, 500);
		this.setLayout(null);
		
		// Construction du panel menu
		this.panelMenu.setLayout(new GridLayout(1,7));
		this.panelMenu.setBounds(20, 10, 900, 40); 
		this.panelMenu.setBackground(Color.cyan);
		this.panelMenu.add(btProfil);
		this.panelMenu.add(btUsager);
		this.panelMenu.add(btSalle);
		this.panelMenu.add(btReservation);
		this.panelMenu.add(btStats);
		this.panelMenu.add(btBoard);
		this.panelMenu.add(btQuitter);
		this.add(this.panelMenu);
		
		// Construction du panel Profil
		this.panelProfil.setLayout(new GridLayout(4,1));
		this.panelProfil.setBounds(260, 100, 400, 300);
		this.panelProfil.setBackground(Color.yellow);
		this.panelProfil.setVisible(false);
		this.panelProfil.add(new JLabel("Nom de l'user : " + unUser.getNom()));
		this.panelProfil.add(new JLabel("Prénom de l'user : " + unUser.getPrenom()));
		this.panelProfil.add(new JLabel("Email de l'user : " + unUser.getEmail()));
		this.panelProfil.add(new JLabel("Rôle de l'user : " + unUser.getRole()));
		this.add(this.panelProfil);
		
		// Insertion des panels d'administration
		this.unPanelUsager = new PanelUsager(unUser);
		this.add(unPanelUsager);
		this.add(unPanelSalle);
		this.add(unPanelReservation);
		this.add(unPanelStats);
		this.add(unPanelBord);
		
		// Rendre les boutons cliquables
		this.btQuitter.addActionListener(this);
		this.btUsager.addActionListener(this);
		this.btProfil.addActionListener(this);
		this.btSalle.addActionListener(this);
		this.btReservation.addActionListener(this);
		this.btStats.addActionListener(this);
		this.btBoard.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btQuitter) {
			Annexe.fermerVueGenerale();
			Annexe.rendreVisibleVueConnexion(true);
		} else if (e.getSource() == this.btProfil) {
			this.panelProfil.setVisible(true);
			unPanelUsager.setVisible(false);
			unPanelSalle.setVisible(false);
			unPanelUsager.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelReservation.setVisible(false);
		}    else if (e.getSource() == this.btStats) {
			this.panelProfil.setVisible(false);
			unPanelUsager.setVisible(false);
			unPanelSalle.setVisible(false);
			unPanelStats.setVisible(true);
			unPanelBord.setVisible(false);
			unPanelReservation.setVisible(false);
		} else if (e.getSource() == this.btBoard) {
			this.panelProfil.setVisible(false);
			unPanelUsager.setVisible(false);
			unPanelSalle.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(true);
			unPanelReservation.setVisible(false);
		}
		else if (e.getSource() == this.btSalle) {
			this.panelProfil.setVisible(false);
			unPanelUsager.setVisible(false);
			unPanelStats.setVisible(false);
			unPanelBord.setVisible(false);
			unPanelSalle.setVisible(true);
			unPanelReservation.setVisible(false);
		}
		 else if (e.getSource() == this.btUsager) {
				this.panelProfil.setVisible(false);
				unPanelUsager.setVisible(true);
				unPanelSalle.setVisible(false);
				unPanelStats.setVisible(false);
				unPanelBord.setVisible(false);
				unPanelReservation.setVisible(false);
			}
		 else if (e.getSource() == this.btReservation) {
				this.panelProfil.setVisible(false);
				unPanelReservation.setVisible(true);
				unPanelUsager.setVisible(false);
				unPanelSalle.setVisible(false);
				unPanelStats.setVisible(false);
				unPanelBord.setVisible(false);
			}
	}

}
