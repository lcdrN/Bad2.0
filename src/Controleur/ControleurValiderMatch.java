package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import IHM.MainWin;
import IHM.PanelImage;
import IHM.Terrain;
import Main.Match;
import Thread.ThreadValiderMatch;

public class ControleurValiderMatch implements ActionListener {

	private JLabel joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2;
	private JTextField scoreequipe1, scoreequipe2;
	private PanelImage terrain;
	private JButton valider;
	private ArrayList<Match> match;
	private Match m2;
	private MainWin frame;
	


	public ControleurValiderMatch(JLabel joueur1equipe1, JLabel joueur2equipe1,
			JLabel joueur1equipe2, JLabel joueur2equipe2,
			JTextField scoreequipe1, JTextField scoreequipe2,
			PanelImage terrain, JButton valider, ArrayList<Match> match, Match m, MainWin frame) {
		
		this.frame = frame;
		this.joueur1equipe1 = joueur1equipe1;
		this.joueur2equipe1 = joueur2equipe1;
		this.joueur1equipe2 = joueur1equipe2;
		this.joueur2equipe2 = joueur2equipe2;
		this.scoreequipe1 = scoreequipe1;
		this.scoreequipe2 = scoreequipe2;
		this.terrain = terrain;
		this.valider = valider;
		this.match = match;
		this.m2 = m;
	}






	public void actionPerformed(ActionEvent arg0) {
		
		//thread
		ThreadValiderMatch t = new ThreadValiderMatch(joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2
				, scoreequipe1.getText(), scoreequipe2.getText(), terrain, m2, match, this.frame);
		t.start();
		
		
		
		if(!this.match.isEmpty()){
			
			this.m2 = this.match.get(0);
			this.match.remove(0);
			
			joueur1equipe1.setText(m2.getE1().getJ1().getNom());
			joueur2equipe1.setText(m2.getE1().getJ2().getNom());
			joueur1equipe2.setText(m2.getE2().getJ1().getNom());
			joueur2equipe2.setText(m2.getE2().getJ2().getNom());
			this.scoreequipe1.setText("0");
			this.scoreequipe2.setText("0");
//			terrain.add(this.scoreequipe1);
//			terrain.add(this.scoreequipe2);
			terrain.add(this.valider);
			this.terrain.img = new ImageIcon(this.getClass().getResource("terrain.jpg")).getImage();
		}
		else{
			this.joueur1equipe1.setText("");
			this.joueur2equipe1.setText("");
			this.joueur1equipe2.setText("");
			this.joueur2equipe2.setText("");
			this.scoreequipe1.setVisible(false);
			this.scoreequipe2.setVisible(false);
			valider.setVisible(false);
			this.terrain.img = new ImageIcon(this.getClass().getResource("terrainbleu.png")).getImage();
			this.terrain.repaint();
		}
		
		
	}






	public JLabel getJoueur1equipe1() {
		return joueur1equipe1;
	}






	public void setJoueur1equipe1(JLabel joueur1equipe1) {
		this.joueur1equipe1 = joueur1equipe1;
	}






	public JLabel getJoueur2equipe1() {
		return joueur2equipe1;
	}






	public void setJoueur2equipe1(JLabel joueur2equipe1) {
		this.joueur2equipe1 = joueur2equipe1;
	}






	public JLabel getJoueur1equipe2() {
		return joueur1equipe2;
	}






	public void setJoueur1equipe2(JLabel joueur1equipe2) {
		this.joueur1equipe2 = joueur1equipe2;
	}






	public JLabel getJoueur2equipe2() {
		return joueur2equipe2;
	}






	public void setJoueur2equipe2(JLabel joueur2equipe2) {
		this.joueur2equipe2 = joueur2equipe2;
	}






	public JTextField getScoreequipe1() {
		return scoreequipe1;
	}






	public void setScoreequipe1(JTextField scoreequipe1) {
		this.scoreequipe1 = scoreequipe1;
	}






	public JTextField getScoreequipe2() {
		return scoreequipe2;
	}






	public void setScoreequipe2(JTextField scoreequipe2) {
		this.scoreequipe2 = scoreequipe2;
	}






	public PanelImage getTerrain() {
		return terrain;
	}






	public void setTerrain(PanelImage terrain) {
		this.terrain = terrain;
	}






	public JButton getValider() {
		return valider;
	}






	public void setValider(JButton valider) {
		this.valider = valider;
	}






	public ArrayList<Match> getMatch() {
		return match;
	}






	public void setMatch(ArrayList<Match> match) {
		this.match = match;
	}






	public Match getM2() {
		return m2;
	}






	public void setM2(Match m2) {
		this.m2 = m2;
	}
	
	

}
