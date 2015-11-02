package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import IHM.MainWin;
import Main.Tournois;

public class ControleurCreerTournois implements ActionListener {

	private JTextField nom, nbTerrain;
	private MainWin frame;
	private JFrame frame2;
	
	
	public ControleurCreerTournois(JTextField nom, JTextField nbTerrain, MainWin fen, JFrame frame2){
		this.nom = nom;
		this.nbTerrain = nbTerrain;
		this.frame = fen;
		this.frame2 = frame2;
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		try {
			Tournois t = new Tournois(this.nom.getText(), Integer.parseInt(this.nbTerrain.getText()), this.frame.getEquipeBar());
			this.frame.setTournois(t);
			this.frame2.dispose();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
