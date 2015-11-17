package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Comparteur.ComparateurJoueurNom;
import Controleur.ControleurCreerEquipe;
import Controleur.ControleurCreerMatch;
import Controleur.ControleurValider;
import DB.Database;
import DB.Export;
import DB.Import;
import Main.Equipe;
import Main.Joueur;
import Main.Match;
import Main.Tournois;
import Table.JTableEquipe;
import Table.JTableJoueur;
import Table.JTableMatch;
import Table.TableEquipe;
import Table.TableJoueur;
import Table.TableMatch;

public class MainWin extends JFrame {
	
	private Tournois tournois;
	
	private TableJoueur tablejoueur;
	private TableEquipe tableEquipe;
	private TableMatch tableMatch;
	private JPanel principal, panelJoueur, panelJoueurTable, choixJoueurTable, terrain;
	private JPanel panelEquipe, panelEquipeTable, choixEquipeTable, choixEquipeTableGauche, choixEquipeTableDroite;
	private JPanel panelMatch, panelMatchTable,choixMatchTable, choixMatchTableGauche, choixMatchTableDroite;
	private JTableMatch tablem;
	private JTableJoueur tablej;
	private JTableEquipe tablee;
	private ToolBar tool;
	private JButton joueurChoixValider, equipeChoixValider, creerequipe, matchChoixValider, creermatch;
	private JLabel joueurChoix, equipeChoix, matchChoix;
	private JComboBox<String> joueurChoixActif, equipeChoixTour, matchChoixTour, modifAN;
	private JProgressBar equipeBar, matchBar;
	private JScrollPane scrollJoueur, scrollTerrain, scrollEquipe, scrollMatch;
	private JTabbedPane onglets;
	
	public MainWin() throws Exception{
		super("Bad 2.0.1");
		
	
		//Autres
		String[] s ={"Ancien", "Nouveau"};
		modifAN = new JComboBox<String>(s);
		//
		
		
		//ToolBar (Gauche Fenêtre)
		tool = new ToolBar(this);
		//
		
		//Centre Fenêtre
		
			//Panel Joueur
		panelJoueur = new JPanel();
		panelJoueur.setLayout(new BorderLayout());
		
		panelJoueurTable = new JPanel();
		panelJoueurTable.setLayout(new BorderLayout());
		
		choixJoueurTable = new JPanel();
		choixJoueurTable.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		joueurChoixValider = new JButton("Valider");
		joueurChoix = new JLabel("Sélectionner : ");
		String[] s2 = {"Actif", "Inactif", "Tous"};
		joueurChoixActif = new JComboBox(s2);
		
		choixJoueurTable.add(joueurChoix);
		choixJoueurTable.add(joueurChoixActif);
		choixJoueurTable.add(joueurChoixValider);
		
		tablejoueur = new TableJoueur(new LinkedList<Joueur>(),  null);
		tablej = new JTableJoueur(tablejoueur);
		tablej.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(modifAN));
		tablej.setFont(new Font("", Font.PLAIN, 15));
		tablej.setRowHeight(25);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		colorRenderer.setBackground(Color.getHSBColor(50, 50, 50));
		for(int x=0;x<tablej.getColumnCount();x++){
	         tablej.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
	        }
		
		
		
		
		
		panelJoueurTable.add(tablej.getTableHeader(), BorderLayout.NORTH);
		scrollJoueur = new JScrollPane(tablej);
		scrollJoueur.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		panelJoueurTable.add(scrollJoueur, BorderLayout.CENTER);
		
		
		panelJoueur.add(panelJoueurTable, BorderLayout.CENTER);
		panelJoueur.add(choixJoueurTable, BorderLayout.NORTH);
		
		
		
		
			//
		
			//Panel Equipes
		panelEquipe = new JPanel();
		panelEquipe.setLayout(new BorderLayout());
		
		panelEquipeTable = new JPanel();
		panelEquipeTable.setLayout(new BorderLayout());
		
		choixEquipeTable = new JPanel();
		choixEquipeTable.setLayout(new BorderLayout());
		
		choixEquipeTableGauche = new JPanel();
		choixEquipeTableGauche.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		choixEquipeTableDroite = new JPanel();
		choixEquipeTableDroite.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		equipeBar = new JProgressBar(0, 100);
		equipeBar.setStringPainted(true);
		equipeBar.setString("Equipe : 0/0");
		creerequipe = new JButton("Créer Paires");
		creerequipe.setEnabled(false);
		creerequipe.addActionListener(new ControleurCreerEquipe(this));
		equipeChoixValider = new JButton("Valider");
		equipeChoix = new JLabel("Tour : ");
		equipeChoixTour = new JComboBox<String>();
		equipeChoixValider.addActionListener(new ControleurValider(0, this, this.equipeChoixTour));
		
		choixEquipeTableGauche.add(equipeBar);
		choixEquipeTableGauche.add(creerequipe);
		choixEquipeTableDroite.add(equipeChoix);
		choixEquipeTableDroite.add(equipeChoixTour);
		choixEquipeTableDroite.add(equipeChoixValider);
		
		choixEquipeTable.add(choixEquipeTableGauche, BorderLayout.CENTER);
		choixEquipeTable.add(choixEquipeTableDroite, BorderLayout.EAST);
		
		tableEquipe = new TableEquipe(new ArrayList<Equipe>(), this);
		setTablee(new JTableEquipe(tableEquipe));
		
		
		
		getTablee().setFont(new Font("", Font.PLAIN, 15));
		
		getTablee().setRowHeight(25);
		
		for(int x=0;x<getTablee().getColumnCount();x++){
	         getTablee().getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
	        }
		
		
		panelEquipeTable.add(getTablee().getTableHeader(), BorderLayout.NORTH);
		scrollEquipe = new JScrollPane(getTablee());
		scrollEquipe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelEquipeTable.add(scrollEquipe, BorderLayout.CENTER);
		
		panelEquipe.add(panelEquipeTable, BorderLayout.CENTER);
		panelEquipe.add(choixEquipeTable, BorderLayout.NORTH);
			//
		
			//Panel Match
		panelMatch = new JPanel();
		panelMatch.setLayout(new BorderLayout());
		
		panelMatchTable = new JPanel();
		panelMatchTable.setLayout(new BorderLayout());
		
		choixMatchTable = new JPanel();
		choixMatchTable.setLayout(new BorderLayout());
		
		choixMatchTableGauche = new JPanel();
		choixMatchTableGauche.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		choixMatchTableDroite = new JPanel();
		choixMatchTableDroite.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		matchBar = new JProgressBar(0, 100);
		matchBar.setStringPainted(true);
		matchBar.setString("Matchs : 0/0");
		creermatch = new JButton("Créer Matchs");
		creermatch.setEnabled(false);
		creermatch.addActionListener(new ControleurCreerMatch(this));
		matchChoixValider = new JButton("Valider");
		matchChoix = new JLabel("Tour : ");
		matchChoixTour = new JComboBox<String>();
		matchChoixValider.addActionListener(new ControleurValider(2, this, this.matchChoixTour));
		
		choixMatchTableGauche.add(matchBar);
		choixMatchTableGauche.add(creermatch);
		choixMatchTableDroite.add(matchChoix);
		choixMatchTableDroite.add(matchChoixTour);
		choixMatchTableDroite.add(matchChoixValider);
		
		choixMatchTable.add(choixMatchTableGauche, BorderLayout.CENTER);
		choixMatchTable.add(choixMatchTableDroite, BorderLayout.EAST);
		
		tableMatch= new TableMatch(new ArrayList<Match>());
		tablem = new JTableMatch(tableMatch);
		
		tablem.setFont(new Font("", Font.PLAIN, 15));
		
		tablem.setRowHeight(25);
		
		for(int x=0;x<tablem.getColumnCount();x++){
	         tablem.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
	        }
		
		
		panelMatchTable.add(tablem.getTableHeader(), BorderLayout.NORTH);
		scrollMatch = new JScrollPane(tablem);
		scrollMatch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelMatchTable.add(scrollMatch, BorderLayout.CENTER);
		
		panelMatch.add(panelMatchTable, BorderLayout.CENTER);
		panelMatch.add(choixMatchTable, BorderLayout.NORTH);
			//
		
			//Panel Terrain
		terrain = new JPanel();
		
		scrollTerrain = new JScrollPane(terrain);
		
		
		
		
			//
		
		
		
		
			//Définition Onglets
		onglets = new JTabbedPane();
		onglets.add("Joueurs", panelJoueur);
		onglets.addTab("Paires", panelEquipe);
		onglets.add("Matchs", panelMatch);
		onglets.add("Terrains", scrollTerrain);
			//
		
		//
		
		
		
		
		//Panel principal(Fenêtre)
		principal = new JPanel();
		principal.setLayout(new BorderLayout());
		principal.add(tool, BorderLayout.WEST);
		principal.add(onglets, BorderLayout.CENTER);
		//
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setSize(new Dimension(1000, 700));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
	}
	
	
	public Tournois getTournois() {
		return tournois;
	}

	public void setTournois(Tournois tournois) throws NumberFormatException, Exception {
		this.tournois = tournois;
		this.tablejoueur.setDonnees(tournois.getJoueurListe());
		
		if(this.tournois.getNbTour() == 0){
			this.tableEquipe.setDonnees(this.tournois.getEquipeListe(0));
			this.tableMatch.setDonnees(this.tournois.getMatchListe(0));
		}
		else{
			this.tableEquipe.setDonnees(this.tournois.getEquipeListe(this.tournois.getNbTour()-1));
			this.tableMatch.setDonnees(this.tournois.getMatchListe(this.tournois.getNbTour()-1));
		}
		
		int tour = this.tournois.getNbTour();
		
		if(tour == 0){
			this.creerequipe.setEnabled(true);
			this.creermatch.setEnabled(false);
		}
		else if(this.tournois.getJoueurs().isEmpty()){
			this.creerequipe.setEnabled(false);
			this.creermatch.setEnabled(false);
		}
		
		else if(!this.tournois.getJoueurs().isEmpty() && this.tournois.getEquipeListe(tour-1).isEmpty()){
			this.creerequipe.setEnabled(true);
			this.creermatch.setEnabled(false);
		}
		else if(!this.tournois.getJoueurs().isEmpty() && !this.tournois.getEquipeListe(tour-1).isEmpty() && this.tournois.getMatchListe(tour-1).isEmpty()){
			this.creerequipe.setEnabled(false);
			this.creermatch.setEnabled(true);
		}
		
		
		this.listeNbTour();
		this.tablejoueur.setBd(this.tournois.getBd());
		
		this.terrain.setLayout(new GridLayout(this.tournois.getNbTerrain()%3, 3));
		
		if(this.tournois.getNbTour() !=0 && !this.tournois.getMatchListe(this.tournois.getNbTour()-1).isEmpty()){
			ArrayList<Match> liste = (ArrayList<Match>) this.getListeMatchNonJoue().clone();
			
			for(int i=0;i<this.tournois.getNbTerrain();i++){
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				Terrain t = new Terrain(i, null , this);
				t.setMatch(liste);
				t.activer();
				p.add(t.getTerrain());
				this.terrain.add(p);
			}
		}
		
		
		
		getTablee().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<Joueur>(this.getListeJoueur())));
		getTablee().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<Joueur>(this.getListeJoueur())));
		
	}
	
	public Joueur[] getListeJoueur(){
		Joueur[] j = new Joueur[this.tournois.getJoueurs().size()];
		LinkedList<Joueur> l = this.tournois.getJoueurs();
		Collections.sort(l, new ComparateurJoueurNom());
		int nb=0;
		for(int i=0;i<j.length;i++){
			if(this.tournois.joueurAJoue(l.get(i)) == true){
			j[nb++] = l.get(i);
			}
		}
		return j;
	}
	
	public void UpdateWin(){
		this.tableEquipe.setDonnees(this.tournois.getEquipeListe(this.tournois.getNbTour()-1));
		this.tableMatch.setDonnees(this.tournois.getMatchListe(this.tournois.getNbTour()-1));
		this.listeNbTour();
		
	}
	
	public ArrayList<Match> getListeMatchNonJoue(){
		ArrayList<Match> l = new ArrayList<Match>();
		for(int i=0;i<this.tournois.getMatchListe(this.tournois.getNbTour()-1).size();i++){
			if(this.tournois.getMatchListe(this.tournois.getNbTour()-1).get(i).isMatchjoue() == false){
				l.add(this.tournois.getMatchListe(this.tournois.getNbTour()-1).get(i));
			}
		}
		return l;
	}


	public TableJoueur getTablejoueur() {
		return tablejoueur;
	}
	
	public TableEquipe getTableEquipe() {
		return tableEquipe;
	}
	
	public TableMatch getTableMatch() {
		return this.tableMatch;
	}
	
	
	public JTabbedPane getOnglets() {
		return onglets;
	}


	public JButton getCreerequipe() {
		return creerequipe;
	}


	public JButton getCreermatch() {
		return creermatch;
	}


	public void setTablejoueur(TableJoueur tablejoueur) {
		this.tablejoueur = tablejoueur;
	}
	
	public void listeNbTour(){
		this.equipeChoixTour.removeAllItems();
		this.matchChoixTour.removeAllItems();
		for(int i=0;i<this.tournois.getNbTour();i++){
			this.equipeChoixTour.addItem(Integer.toString(i+1));
			this.matchChoixTour.addItem(Integer.toString(i+1));
		}
		this.matchChoixTour.updateUI();
		this.equipeChoixTour.updateUI();
		this.matchChoixTour.setSelectedIndex(this.tournois.getNbTour()-1);
		this.equipeChoixTour.setSelectedIndex(this.tournois.getNbTour()-1);
	}

	

	public JProgressBar getEquipeBar() {
		return equipeBar;
	}


	public JProgressBar getMatchBar() {
		return matchBar;
	}
	
	
	public JPanel getTerrain() {
		return terrain;
	}


	public static void main(String[] args) throws Exception {
		
		new MainWin();

	}


	public JTableEquipe getTablee() {
		return tablee;
	}


	public void setTablee(JTableEquipe tablee) {
		this.tablee = tablee;
	}

}
