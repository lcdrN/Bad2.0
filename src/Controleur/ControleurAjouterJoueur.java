package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import IHM.MainWin;

public class ControleurAjouterJoueur implements ActionListener {

	private JTextField nom, prenom, age;
	private JComboBox sexe, ancien, niveau;
	private JLabel message;
	private MainWin frame;
	
	
	public ControleurAjouterJoueur(JLabel message, JTextField nom, JTextField prenom, JTextField age,
			JComboBox sexe, JComboBox anciente, MainWin frame){
		this.nom = nom;
		this.message = message;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
		this.ancien = anciente;
		this.frame = frame;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		boolean homme = true;
		boolean nouveau = false;
		
		if(this.sexe.getSelectedIndex() == 1){
			homme = false;
		}
		if(this.ancien.getSelectedIndex() == 0){
			nouveau = true;
		}


		
		try {
			this.frame.getTournois().ajouterJoueur(this.nom.getText(), this.prenom.getText(), Integer.parseInt(this.age.getText()), nouveau, homme);
			this.frame.getTablejoueur().changeData();
		} catch (Exception e1) {
			if(this.frame.getTournois() == null){
				this.message.setText("Sélectionnez Un Tournois");
			}
			else{
				this.message.setText("Erreur");
			}
			this.message.setForeground(Color.RED);
		}
		
	}

}
