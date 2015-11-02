package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import DB.Import;
import Exception.ThrowError;
import IHM.AjouterJoueur;
import IHM.ChoixTournois;
import IHM.MainWin;

public class ControleurOuvrirFenetre implements ActionListener {

	private int i;
	private MainWin frame;
	
	public ControleurOuvrirFenetre(int i, MainWin frame){
		this.i = i;
		this.frame = frame;
	}
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(this.i == 0){
			new AjouterJoueur(this.frame);
		}
		if(this.i == 1){
			try {
				new ChoixTournois(this.frame);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(i == 2){
			
			 JFileChooser dialogue = new JFileChooser();
             dialogue.showOpenDialog(null);
             
             Import i = new Import(this.frame);
            
  				try {
					i.ImportDonnee(dialogue.getSelectedFile());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
  				
  			
             this.frame.getTablejoueur().changeData();
             if(this.frame.getTournois().getNbTour() == 0 && !this.frame.getTournois().getJoueurs().isEmpty()){
            	 this.frame.getCreerequipe().setEnabled(true);
            	 this.frame.getCreermatch().setEnabled(false);
             }
		}
		
		
	}

}
