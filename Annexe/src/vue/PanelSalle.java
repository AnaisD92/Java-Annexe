package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


import controleur.Salle;
import controleur.Tableau;
import modele.Modele;

public class PanelSalle extends PanelDeBase implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtlibelle= new JTextField();
	private JTextField txtcapacite = new JTextField();
	private JComboBox<String> txtetat = new JComboBox<>();

	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	
	// Zone de recherche - par requete like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher"); 

	public PanelSalle() {
		super(Color.gray);
		
		this.panelForm.setLayout(new GridLayout(12,2));
		
		this.panelForm.add(new JLabel("Libelle  : "));
		this.panelForm.add(this.txtlibelle);
		
		this.panelForm.add(new JLabel("Capacite : "));
		this.panelForm.add(this.txtcapacite);
		
		this.panelForm.add(new JLabel("Etat : "));
		this.panelForm.add(this.txtetat);
		this.txtetat.addItem("Disponible");
		this.txtetat.addItem("Occupee");
		
		

		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		// this.panelForm.setBackground(Color.gray);
		
		this.panelForm.setBounds(20,20,280,320);
		this.add(this.panelForm);
		
		// Construction du panel Table
		this.panelTable.setBounds(345, 20, 550, 303);
		this.panelTable.setBackground(Color.yellow);
		this.panelTable.setLayout(null);
		String entetes[] = {"ID", "Libelle", "Capacite","Etat"};
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau (entetes, donnees); // Appel du constructeur Tableau
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 60, 550, 280);
		this.panelTable.add(uneScroll);
		
		JLabel labelMot = new JLabel("	Filtrer par :");
		this.txtMot.setBounds(100, 20, 100, 20);
		this.panelTable.add(this.txtMot);
		this.btRechercher.setBounds(220, 20, 120, 20);
		this.panelTable.add(this.btRechercher);
		labelMot.setBounds(20, 20, 80, 20);
		this.panelTable.add(labelMot);
		
		this.add(this.panelTable);
		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int nbclic = e.getClickCount();
				if (nbclic == 2) {
					int numLigne = uneTable.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cet salle ?", "Suppression d'une salle", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idsalle = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteSalle(idsalle);
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					
					String libelle = unTableau.getValueAt(numLigne, 1).toString();
					txtlibelle.setText(libelle);
					
					String capacite = unTableau.getValueAt(numLigne, 2).toString();
					txtcapacite.setText(capacite);
					
					String etat = unTableau.getValueAt(numLigne, 3).toString();
					txtcapacite.setText(etat);
					
					
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
	}
	
	public Object [] [] getLesDonnees (String mot) {
		ArrayList<Salle> lesSalles = Modele.selectAllSalle();
		if (mot.equals("")) {
			lesSalles= Modele.selectAllSalle();
		} else {
			lesSalles = Modele.selectLikeSalle(mot);
		}
		Object [] [] matrice = new Object[lesSalles.size()][4];
		int i = 0;
		for (Salle uneSalle: lesSalles) {
			matrice[i][0] = uneSalle.getIdSalle();
			matrice[i][1] = uneSalle.getLibelle();
			matrice[i][2] = uneSalle.getCapacite();
			matrice[i][3] = uneSalle.getEtat();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
		this.txtlibelle.setText("");
		this.txtcapacite.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
	
	public Salle saisirSalle() {
	    Salle uneSalle = null;
	    String libelle = this.txtlibelle.getText();
	    String capacite = this.txtcapacite.getText();
	    String etat = this.txtetat.getSelectedItem().toString();
	    
	    boolean hasError = false;
	    
	    if (libelle.equals("")) {
	        this.txtlibelle.setBackground(Color.red);
	        hasError = true;
	    } else {
	        this.txtlibelle.setBackground(Color.white);
	    }
	    
	    if (capacite.equals("")) {
	        this.txtcapacite.setBackground(Color.red);
	        hasError = true;
	    } else {
	        this.txtcapacite.setBackground(Color.white);
	    }

	    if (etat.equals("")) {
	        this.txtetat.setBackground(Color.red);
	        hasError = true;
	    } else {
	        this.txtetat.setBackground(Color.white);
	    }
	    
	    if (!hasError) {
	        uneSalle = new Salle(libelle, capacite, etat);
	    }
	    
	    return uneSalle;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Enregistrer")) {
			Salle uneSalle = this.saisirSalle();
			if (uneSalle !=null) {
					
				Modele.insertSalle(uneSalle);
				
				// Récupérer l'ID auto_increment de la BDD
				uneSalle = Modele.selectWhereSalle(uneSalle.getLibelle(), uneSalle.getCapacite());
				
				// Mettre à jour l'affichage
				Object ligne[] = {uneSalle.getIdSalle(), uneSalle.getLibelle(), uneSalle.getCapacite(), uneSalle.getEtat()};
				unTableau.ajouterLigne(ligne);
				
				JOptionPane.showMessageDialog(this, "Insertion de la salle réussi !");
				this.viderChamps();
			}else 
			{
				JOptionPane.showMessageDialog(this, "Veuillez verifier les informations de la salle !");
			}
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Salle uneSalle = this.saisirSalle();
			int numLigne = this.uneTable.getSelectedRow();
			int idSalle = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			uneSalle.setIdSalle(idSalle);
			Modele.updateSalle(uneSalle);
			JOptionPane.showMessageDialog(this, "Modification effectuée !");
			Object ligne[] = {uneSalle.getIdSalle(), uneSalle.getLibelle(), uneSalle.getCapacite(), uneSalle.getEtat()};
			unTableau.modifierLigne(numLigne, ligne);
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
	      }else if(e.getSource() == this.btRechercher ) {
		String mot = this.txtMot.getText(); 
		Object donnees [][] = this.getLesDonnees(mot);
		this.unTableau.setDonnees(donnees);
	}
}

}

			
		
