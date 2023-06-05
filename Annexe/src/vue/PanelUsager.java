package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Usager;
import controleur.Tableau;
import controleur.User;
import modele.Modele;

public class PanelUsager extends PanelDeBase implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtdateNaissance = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txttel = new JTextField();
	private JTextField txtclasse = new JTextField();
	private JTextField txtville = new JTextField();
	private JTextField txtcp = new JTextField();
	private JComboBox<String> txtsex = new JComboBox<>();
	private JComboBox<String> txtetat= new JComboBox<>();
	private JTextField txtpays = new JTextField();
	
	private JRadioButton radioUsager = new JRadioButton();
	

	private JTable uneTable = null;

	private static Tableau unTableau = null;
	
	// Zone de recherche - par requete like
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher"); 

	public PanelUsager(User unUser) {
		super(Color.gray); // Couleur de fond

		this.panelForm.setLayout(new GridLayout(12, 2));

		this.panelForm.add(new JLabel("Nom : "));
		this.panelForm.add(this.txtNom);

		this.panelForm.add(new JLabel("Prénom : "));
		this.panelForm.add(this.txtPrenom);

		this.panelForm.add(new JLabel("Date de naissance : "));
		this.panelForm.add(this.txtdateNaissance);
		
		this.panelForm.add(new JLabel("Adresse : "));
		this.panelForm.add(this.txtAdresse);

		this.panelForm.add(new JLabel(" Tel : "));
		this.panelForm.add(this.txttel);
		
		this.panelForm.add(new JLabel("Classe : "));
		this.panelForm.add(this.txtclasse);
		
		this.panelForm.add(new JLabel("Ville : "));
		this.panelForm.add(this.txtville);
		
		this.panelForm.add(new JLabel("Code Postal : "));
		this.panelForm.add(this.txtcp);
		
		this.panelForm.add(new JLabel("Sexe : "));
		this.panelForm.add(this.txtsex);
		this.txtsex.addItem("Feminin");
		this.txtsex.addItem("Masculin");
		
		this.panelForm.add(new JLabel("Pays : "));
		this.panelForm.add(this.txtpays);
		
		this.panelForm.add(new JLabel("Etat : "));
		this.panelForm.add(this.txtetat);
		this.txtetat.addItem("En attente");
		this.txtetat.addItem("Valide");
		this.txtetat.addItem("Refusee");
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		
		this.panelForm.setBounds(20,20,280,320);
		this.add(this.panelForm);
	
		if (unUser.getRole().equals("admin")) {
			this.add(this.panelForm);
			
			JLabel lbUsager = new JLabel("Usager");
			lbUsager.setBounds(65, 130, 80, 20); // Dimension du texte
			lbUsager.setForeground(Color.WHITE);
			this.add(lbUsager);
			this.radioUsager.setBounds(40, 130, 20, 20); // Dimension du fonds
			this.add(this.radioUsager);
			
			// Par défaut : on prend le PanelPilote
			this.radioUsager.setSelected(true); // Bouton radio sélectionné
			
			
			//
		} else {
			// Modifier les tailles des tableaux
		}
	
		// Construction du panel Table
		this.panelTable.setBounds(345, 20, 550, 303);
		this.panelTable.setBackground(Color.yellow);
		this.panelTable.setLayout(null);
		String entetes[] = { "ID", "Nom", "Prénom", "DateNaiss", "Adresse", "Téléphone", "Classe", "Ville", "CP", "Sexe", "Pays", "État"};
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees); // Appel du constructeur Tableau
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 60, 550, 280);
		this.panelTable.add(uneScroll);
		
		// Placement de la zone de recherche
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
				if (unUser.getRole().equals("admin")) {
					int nbclic = e.getClickCount();
					if (nbclic == 2) {
						int numLigne = uneTable.getSelectedRow();
						int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supptimer ce pilote ?", "suppression pilore", JOptionPane.YES_NO_OPTION);
						if(retour == 0) {
							int idUsager = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							Modele.deleteUsager(idUsager);
							unTableau.supprimerLigne(numLigne);
							viderChamps();
						}
					} else if(nbclic == 1) {
						int numLigne = uneTable.getSelectedRow();
						String nom = unTableau.getValueAt(numLigne, 1).toString();
						txtNom.setText(nom);

						String prenom = unTableau.getValueAt(numLigne, 2).toString();
						txtPrenom.setText(prenom);

						String dateNaiss = unTableau.getValueAt(numLigne, 3).toString();
						txtdateNaissance.setText(dateNaiss);

						String adresse = unTableau.getValueAt(numLigne, 4).toString();
						txtAdresse.setText(adresse);

						String tel = unTableau.getValueAt(numLigne, 5).toString();
						txttel.setText(tel);
						
						String classe = unTableau.getValueAt(numLigne, 6).toString();
						txtclasse.setText(classe);

						btEnregistrer.setText("Modifier");
						//unTableau
					}
				}
			}
		});

		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.radioUsager.addActionListener(this);
	}

	public Object[][] getLesDonnees(String mot) {
		ArrayList<Usager> lesUsagers = null;
		if (mot.equals("")) {
			lesUsagers = Modele.selectAllUsager();
		} else {
			lesUsagers = Modele.selectLikeUsager(mot);
		}
		Object[][] matrice = new Object[lesUsagers.size()][12];
		int i = 0;
		for (Usager unUsager : lesUsagers) {
			matrice[i][0] = unUsager.getIdpers();
			matrice[i][1] = unUsager.getNom();
			matrice[i][2] = unUsager.getPrenom();
			matrice[i][3] = unUsager.getadresse();
			matrice[i][4] = unUsager.getdateNaiss();
			matrice[i][5] = unUsager.gettel();
			matrice[i][6] = unUsager.getclasse();
			matrice[i][7] = unUsager.getville();
			matrice[i][8] = unUsager.getcp();
			matrice[i][9] = unUsager.getsex();
			matrice[i][10] = unUsager.getpays();
			matrice[i][11] = unUsager.getetat();
			
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtdateNaissance.setText("");
		this.txtAdresse.setText("");
		this.txttel.setText("");
		this.txtclasse.setText("");
		this.txtville.setText("");
		this.btEnregistrer.setText("Enregistrer");
	}
	public Usager saisirUsager () {
		Usager unUsager = null;
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String dateNaiss = this.txtdateNaissance.getText();
		String adresse = this.txtAdresse.getText();
		String tel = this.txttel.getText();
		String classe = this.txtclasse.getText();
		String ville = this.txtville.getText();
		String cp = this.txtcp.getText();
		String sex = this.txtsex.getSelectedItem().toString();
		String pays = this.txtpays.getText();
		String etat = this.txtetat.getSelectedItem().toString();
		unUsager = new Usager ( nom, prenom, dateNaiss,adresse, tel, classe, ville, cp,  sex,  pays,etat);
		
		return unUsager;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Enregistrer")) {
			Usager unUsager = this.saisirUsager();
			Modele.insertUsager(unUsager);
			
			// Récupérer l'ID auto_increment de la BDD
			unUsager = Modele.selectWhereUsager(unUsager.getNom(), unUsager.getPrenom());
			
			// Mettre à jour l'affichage
			Object ligne[] = {unUsager.getIdpers(), unUsager.getnom(), unUsager.getprenom(), unUsager.getdateNaiss(), 
					unUsager.getadresse(), unUsager.gettel(), unUsager.getclasse(), unUsager.getville(),
					unUsager.getcp(),unUsager.getsex(),unUsager.getpays(),unUsager.getetat()};
			unTableau.ajouterLigne(ligne);
			
			JOptionPane.showMessageDialog(this, "Insertion Usager réussie !");
			unTableau.fireTableDataChanged();
			this.viderChamps();
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Usager unUsager = this.saisirUsager();
			int numLigne = this.uneTable.getSelectedRow();
			int idPers = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			unUsager.setIdpers(idPers);
			Modele.updateUsager(unUsager);
			JOptionPane.showMessageDialog(this, "Modification effectuée !");
			Object ligne[] = {unUsager.getIdpers(), unUsager.getnom(), unUsager.getprenom(), unUsager.getdateNaiss(), 
					unUsager.getadresse(), unUsager.gettel(), unUsager.getclasse(), unUsager.getville(),
					unUsager.getcp(),unUsager.getsex(),unUsager.getpays(),unUsager.getetat()};
			unTableau.ajouterLigne(ligne);
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
