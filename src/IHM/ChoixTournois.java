package IHM;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.*;

import Controleur.ControleurCreerTournois;
import Controleur.ControleurSelectionTournois;
import DB.Database;
import Main.Tournois;

public class ChoixTournois extends JFrame {
	
	private MainWin frame;
	private Database data;
	

	public ChoixTournois(MainWin frame) throws Exception{
		super("Les Tournois");
		
		this.frame = frame;
		this.data = new Database("");
		
		//Panel haut
		JPanel haut = new JPanel();
		JPanel haut2 = new JPanel();
		haut2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		haut.setLayout(new FlowLayout(FlowLayout.LEFT));
		haut2.setBorder(BorderFactory. createTitledBorder("Liste des Tournois"));
		JComboBox liste = new JComboBox(data.listeTournois());
		JLabel texte = new JLabel("Sélectionner un Tournoi :       ");
		JButton select = new JButton("Sélectionner");
		select.setToolTipText("Charge le tournoi sélectioné");
		
		//
		
		//Panel bas
		JPanel bas = new JPanel();
		JPanel bas2 = new JPanel();
		bas2.setBorder(BorderFactory. createTitledBorder("Créer un Tournoi"));
		bas.setLayout(new GridLayout(2, 2));
		bas2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel nomtournois = new JLabel("Nom du tournoi : ");
		JTextField nom = new JTextField();
		JLabel nbterrains = new JLabel("Nombre de terrains :    ");
		JTextField terrain = new JTextField();
		nom.setPreferredSize(new Dimension(100, 20));
		terrain.setPreferredSize(new Dimension(100, 20));
		JButton confirmer = new JButton("Confirmer");
		confirmer.setToolTipText("Créer un nouveau tournoi");
		bas.add(nomtournois);
		bas.add(nom);
		bas.add(nbterrains);
		bas.add(terrain);
		bas2.add(bas);
		bas2.add(confirmer);
		
		
		//
		
		if (liste.getItemCount() == 0){
			haut2.add(new JLabel("Votre base ne contient aucun tournoi"));
			}
			else{
			haut.add(texte);
			haut.add(liste);
			haut2.add(haut);
			haut2.add(select);
			}
		
		
		
		
		
		//Panel principal
		JPanel principal = new JPanel();
		principal.setLayout(new GridLayout(2, 1));
		principal.add(haut2);
		principal.add(bas2);
		//
		
		//controleur
		confirmer.addActionListener(new ControleurCreerTournois(nom, terrain, this.frame, this));
		select.addActionListener(new ControleurSelectionTournois(liste, this, this.frame));
		//
		
		
		
		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
}
