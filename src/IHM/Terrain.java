package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Controleur.ControleurValiderMatch;
import Main.Match;
import Main.Tournois;

public class Terrain extends JPanel{

	
	private JLabel joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2;
	private int idjoueur1equipe1, idjoueur2equipe1, idjoueur1equipe2, idjoueur2equipe2;
	private JTextField scoreequipe1, scoreequipe2;
	private PanelImage terrain;
	private JButton valider;
	private int numterrain, idmatch;
	private int numero;
	private ArrayList<Match> match;
	private MainWin frame;
	
	
	public Terrain(int numero, ArrayList<Match> match, MainWin frame){
	
			this.frame = frame;
			this.match = match;
			this.numero = numero+1;
			this.terrain = new PanelImage(new ImageIcon(this.getClass().getResource("terrainbleu.png")).getImage());
			joueur1equipe1 = new JLabel();
			joueur2equipe1 = new JLabel();
			joueur1equipe2 = new JLabel();
			joueur2equipe2 = new JLabel();
			JLabel numeruduterrain = new JLabel("<html><b>Terrain N°"+this.numero+"</b></html>");
			Font f = new Font("Arial", Font.PLAIN, 15);
			numeruduterrain.setFont(f);
			numeruduterrain.setForeground(Color.WHITE);
			valider = new JButton("Valider score");
			valider.setToolTipText("Valide et bloque le score d'un match");
			
			scoreequipe1 = new JTextField("0");
			scoreequipe2 = new JTextField("0");

			terrain.setLayout(null);
			
			terrain.setPreferredSize(new Dimension(200, 305));
			terrain.setBorder((new BevelBorder(BevelBorder.RAISED)));
			
			
			
			terrain.add(joueur1equipe1);
			terrain.add(joueur2equipe1);
			terrain.add(joueur1equipe2);
			terrain.add(joueur2equipe2);
			
			scoreequipe1.setBorder((new BevelBorder(BevelBorder.RAISED)));
			scoreequipe2.setBorder((new BevelBorder(BevelBorder.RAISED)));
			
			scoreequipe1.setBounds(85, 120, 30, 20);
			scoreequipe2.setBounds(85, 165, 30, 20);
			
			numeruduterrain.setBounds(60, 0, 100, 40);
			
			terrain.add(numeruduterrain);
			
			joueur1equipe1.setBounds(80, 40, 100, 20);
			joueur2equipe1.setBounds(80, 60, 100, 20);
			joueur1equipe2.setBounds(80, 220, 100, 20);
			joueur2equipe2.setBounds(80, 245, 100, 20);
			valider.setBounds(46, 280, 110, 20);
			
			
		this.terrain.setVisible(true);
			
			
			
	}
	
	public void activer(){
		
		if(!this.match.isEmpty()){
			Match m = this.match.get(0);
			this.match.remove(0);
			joueur1equipe1.setText(m.getE1().getJ1().getNom());
			joueur2equipe1.setText(m.getE1().getJ2().getNom());
			joueur1equipe2.setText(m.getE2().getJ1().getNom());
			joueur2equipe2.setText(m.getE2().getJ2().getNom());
			terrain.add(scoreequipe1);
			terrain.add(scoreequipe2);
			terrain.add(valider);
			this.terrain.img = new ImageIcon(this.getClass().getResource("terrain.jpg")).getImage();
			this.valider.addActionListener(new ControleurValiderMatch(joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2, scoreequipe1, scoreequipe2, terrain, valider, this.match, m, frame));
			
		}
		
	}


	public PanelImage getTerrain() {
		return terrain;
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


	public JButton getValider() {
		return valider;
	}


	public void setValider(JButton valider) {
		this.valider = valider;
	}


	public int getNumterrain() {
		return numterrain;
	}


	public void setNumterrain(int numterrain) {
		this.numterrain = numterrain;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public ArrayList<Match> getMatch() {
		return match;
	}


	public void setMatch(ArrayList<Match> match) {
		this.match = match;
	}


	public void setTerrain(PanelImage terrain) {
		this.terrain = terrain;
	}
	
	
	

}
