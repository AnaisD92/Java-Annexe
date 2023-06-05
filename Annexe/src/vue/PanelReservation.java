package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Reservation;
import controleur.Salle;
import controleur.Tableau;
import controleur.Usager;
import modele.Modele;

public class PanelReservation extends PanelDeBase implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panelForm = new JPanel();
    private JPanel panelTable = new JPanel();
    private JButton btEnregistrer = new JButton("Enregistrer");
    private JButton btAnnuler = new JButton("Annuler");
    private JTextField txtDateOccup = new JTextField();
    private JTextField txtHeureOccup = new JTextField();
    private JTextField txtNbPers = new JTextField();

    private JTable uneTable = null;

    private static Tableau unTableau = null;
    

    private JComboBox<String> comboUsager = new JComboBox<String>();
    private JComboBox<String> comboSalle = new JComboBox<String>();
    
    private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher"); 


    public PanelReservation() {
        super(Color.gray);

        this.panelForm.setLayout(new GridLayout(7, 2));

        this.panelForm.add(new JLabel("Date d'occupation : "));
        this.panelForm.add(this.txtDateOccup);

        this.panelForm.add(new JLabel("Heure d'occupation : "));
        this.panelForm.add(this.txtHeureOccup);

        this.panelForm.add(new JLabel("Nombre de personnes : "));
        this.panelForm.add(this.txtNbPers);
        
        this.panelForm.add(new JLabel("Usager : "));
        this.panelForm.add(this.comboUsager);

        this.panelForm.add(new JLabel("Salle : "));
        this.panelForm.add(this.comboSalle);
        
        ArrayList<Usager> lesUsagers = Modele.selectAllUsager();
        for (Usager unUsager : lesUsagers) {
            this.comboUsager.addItem(unUsager.getIdpers() +"-"+unUsager.getnom());
        }

        ArrayList<Salle> lesSalles = Modele.selectAllSalle();
        for (Salle uneSalle : lesSalles) {
            this.comboSalle.addItem(uneSalle.getIdSalle()+"-"+uneSalle.getLibelle()+"-"+uneSalle.getCapacite());
        }

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btEnregistrer);

        this.panelForm.setBounds(20, 20, 280, 180);
        this.add(this.panelForm);

        // Construction du panel Table
        this.panelTable.setBounds(345, 20, 550, 303);
        this.panelTable.setBackground(Color.yellow);
        this.panelTable.setLayout(null);
        String entetes[] = {"ID", "ID Usager", "ID Salle", "Date d'occupation", "Heure d'occupation", "Nombre de personnes"};
        Object donnees[][] = this.getLesDonnees("");
        unTableau = new Tableau(entetes, donnees);
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

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btEnregistrer.addActionListener(this);
    	this.btRechercher.addActionListener(this);
    }

    public Object[][] getLesDonnees(String mot) {
    	ArrayList<Reservation> lesReservations = null;
    	
    	if (mot.equals("")) {
    		lesReservations = Modele.selectAllReservations();
    	}else {
			lesReservations = Modele.selectLikeReservation(mot);
		}
        Object[][] matrice = new Object[lesReservations.size()][6];
        int i = 0;
        for (Reservation uneReservation : lesReservations) {
            matrice[i][0] = uneReservation.getIdreservation();
            matrice[i][1] = uneReservation.getIdUsager();
            matrice[i][2] = uneReservation.getIdSalle();
            matrice[i][3] = uneReservation.getDateOccup();
            matrice[i][4] = uneReservation.getHeureOccup();
            matrice[i][5] = uneReservation.getNbPers();
            i++;
        }
        return matrice;
    }

    public void viderChamps() {
        this.txtDateOccup.setText("");
        this.txtHeureOccup.setText("");
        this.txtNbPers.setText("");
    }

    public Reservation saisirReservation() {
        Reservation uneReservation = null;
        String dateOccup = this.txtDateOccup.getText();
        String heureOccup = this.txtHeureOccup.getText();
        String nbPers = this.txtNbPers.getText();

        if (!dateOccup.isEmpty() && !heureOccup.isEmpty() && !nbPers.isEmpty()) {
        	String tab[] = this.comboUsager.getSelectedItem().toString().split("-");
    		int idUsager = Integer.parseInt(tab[0]);
    		 tab = this.comboSalle.getSelectedItem().toString().split("-");
    		int idSalle = Integer.parseInt(tab[0]);
    		
            int nombrePersonnes = Integer.parseInt(nbPers);

            uneReservation = new Reservation(idUsager, idSalle, dateOccup, heureOccup, nombrePersonnes);
            
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return uneReservation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.viderChamps();
        } else if (e.getSource() == this.btEnregistrer) {
            Reservation uneReservation = this.saisirReservation();
            if (uneReservation != null) {
                Modele.insertReservation(uneReservation);

                // Mettre à jour l'affichage
                Object ligne[] = {uneReservation.getIdreservation(), uneReservation.getIdUsager(), uneReservation.getIdSalle(),
                        uneReservation.getDateOccup(), uneReservation.getHeureOccup(), uneReservation.getNbPers()};
                unTableau.ajouterLigne(ligne);

                JOptionPane.showMessageDialog(this, "Insertion de la réservation réussie !");
                unTableau.fireTableDataChanged();
                this.viderChamps();
            }
        } else if (e.getSource() == this.btRechercher) {
            String mot = this.txtMot.getText();
            Object donnees[][] = this.getLesDonnees(mot);
            this.unTableau.setDonnees(donnees);
        }
    }

}

    
    