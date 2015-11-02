package Controleur;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import IHM.ChoixTournois;
import IHM.MainWin;
import Main.Tournois;

public class ControleurSelectionTournois implements ActionListener {

		private JComboBox<String> nom;
		private JFrame fen;
		private MainWin frame;
	
	public ControleurSelectionTournois(JComboBox<String> nom, JFrame fen, MainWin frame){
		this.nom = nom;
		this.fen = fen;
		this.frame = frame;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		
			try {
				Tournois t  = new Tournois(this.nom.getSelectedItem().toString(), this.frame.getEquipeBar());
				this.frame.setTournois(t);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			fen.dispose();
		
		

	}

}
