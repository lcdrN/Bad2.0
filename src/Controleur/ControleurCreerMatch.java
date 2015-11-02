package Controleur;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import IHM.MainWin;
import IHM.Terrain;
import Main.Match;

public class ControleurCreerMatch implements ActionListener {
	
	private MainWin frame;
	
	public ControleurCreerMatch(MainWin frame){
		this.frame = frame;
	}
	
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			this.frame.getTournois().creerMatch();
			this.frame.getTerrain().removeAll();
			
			ArrayList<Match> liste =(ArrayList<Match>) this.frame.getTournois().getMatchListe(this.frame.getTournois().getNbTour()-1).clone();
			
			for(int i=0;i<this.frame.getTournois().getNbTerrain();i++){
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				Terrain t = new Terrain(i, null, this.frame);
				t.setMatch(liste);
				t.activer();
				p.add(t.getTerrain());
				this.frame.getTerrain().add(p);
				}
			
			this.frame.getTerrain().setVisible(true);
			
			this.frame.repaint();
			Dimension d = this.frame.getSize();
			this.frame.setSize(d.width+1, d.height+1);
			
			this.frame.getCreermatch().setEnabled(false);
			this.frame.getCreerequipe().setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.frame.UpdateWin();
		
	}

}
