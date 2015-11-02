package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;

import DB.Export;
import DB.Import;
import Exception.Confirmation;
import Exception.ThrowError;
import IHM.MainWin;

public class ControleurValider implements ActionListener {

	private int i;
	private MainWin frame;
	private JComboBox<String> box;
	private String s;
	
	public ControleurValider(int i, MainWin frame, JComboBox<String> box){
		this.frame= frame;
		this.i = i;
		this.box = box;
	}
	
	public ControleurValider(int i, MainWin frame){
		this.i = i;
		this.frame = frame;
	}
	
	public ControleurValider(int i, MainWin frame, String s){
		this.i = i;
		this.frame = frame;
		this.s = s;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(this.i == 0){
			this.frame.getTableEquipe().setDonnees(this.frame.getTournois().getEquipeListe(this.box.getSelectedIndex()));
		}
		if(this.i == 2){
			this.frame.getTableMatch().setDonnees(this.frame.getTournois().getMatchListe(this.box.getSelectedIndex()));
		}
		
		if(this.i == 1){
			Export e = new Export(this.frame.getTournois());
			try {
				e.Exportdonnees();
				Confirmation.doSay("Exportation réussite !");
			} catch (Exception e1) {
				ThrowError.doError("Veuillez Sélectionner Un Tournoi");
			}
		}
			
	}

}
