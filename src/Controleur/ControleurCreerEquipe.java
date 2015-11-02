package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Exception.ThrowError;
import IHM.MainWin;

public class ControleurCreerEquipe implements ActionListener {

	private MainWin frame;
		
	public ControleurCreerEquipe(MainWin frame){
		this.frame = frame;
	}
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			this.frame.getTournois().creerEquipe(0);
			if(this.frame.getTournois().getEquipeListe(this.frame.getTournois().getNbTour()).size() == 0){
				ThrowError.doError("Plus de paires possibles");
				this.frame.getCreerequipe().setEnabled(false);
				this.frame.getCreermatch().setEnabled(false);
			}
			else{
				this.frame.getTournois().setNbTour(this.frame.getTournois().getNbTour()+1);
				this.frame.getTournois().getBd().updatetournois("update tournois set numtour=numtour+1 where nom='"+this.frame.getTournois().getNom()+"'");
				this.frame.getCreerequipe().setEnabled(false);
				this.frame.getCreermatch().setEnabled(true);
			}
		} catch (Exception e) {
			ThrowError.doError("Erreur inconnu : Veulllez Réessayer");
		}
		this.frame.UpdateWin();
		
	}

}
