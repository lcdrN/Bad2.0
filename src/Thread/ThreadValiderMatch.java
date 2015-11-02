package Thread;

import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Controleur.ControleurValiderMatch;
import IHM.MainWin;
import IHM.PanelImage;
import Main.Joueur;
import Main.Match;

public class ThreadValiderMatch extends Thread {
	
	
	private MainWin frame;
	private JLabel joueur1equipe1, joueur2equipe1, joueur1equipe2, joueur2equipe2;
	private String scoreequipe1, scoreequipe2;
	private PanelImage terrain;
	private Match m2;
	private ArrayList<Match> match;
	
	
	public ThreadValiderMatch(JLabel joueur1equipe1,
			JLabel joueur2equipe1, JLabel joueur1equipe2,
			JLabel joueur2equipe2, String scoreequipe1,
			String scoreequipe2, PanelImage terrain, Match m2,
			ArrayList<Match> match, MainWin frame2) {
		
		this.joueur1equipe1 = joueur1equipe1;
		this.joueur2equipe1 = joueur2equipe1;
		this.joueur1equipe2 = joueur1equipe2;
		this.joueur2equipe2 = joueur2equipe2;
		this.scoreequipe1 = scoreequipe1;
		this.scoreequipe2 = scoreequipe2;
		this.terrain = terrain;
		this.m2 = m2;
		this.match = match;
		this.frame = frame2;
	}





	public void run(){
		try {
			int nb = this.frame.getTournois().getNbTour()-1;
			Match m5 = this.frame.getTournois().getMatch(this.m2.getId(), this.frame.getTournois().getNbTour()-1);
			
			m5.setScore1(Integer.parseInt(this.scoreequipe1));
			m5.setScore2(Integer.parseInt(this.scoreequipe2));
			m5.setMatchjoue(true);
			
			m5.getE1().setScore(Integer.parseInt(this.scoreequipe1));
			m5.getE2().setScore(Integer.parseInt(this.scoreequipe2));
			m5.getE1().setMatchjoue(true);
			m5.getE2().setMatchjoue(true);
			
			m5.getE1().getJ1().setNbPoint(m5.getE1().getJ1().getNbPoint()+Integer.parseInt(this.scoreequipe1));
			m5.getE1().getJ2().setNbPoint(m5.getE1().getJ2().getNbPoint()+Integer.parseInt(this.scoreequipe1));
			m5.getE2().getJ1().setNbPoint(m5.getE2().getJ1().getNbPoint()+Integer.parseInt(this.scoreequipe2));
			m5.getE2().getJ2().setNbPoint(m5.getE2().getJ2().getNbPoint()+Integer.parseInt(this.scoreequipe2));
			
			m5.getE1().getJ1().setNbMatchJoue(m5.getE1().getJ1().getNbMatchJoue()+1);
			m5.getE1().getJ2().setNbMatchJoue(m5.getE1().getJ2().getNbMatchJoue()+1);
			m5.getE2().getJ1().setNbMatchJoue(m5.getE2().getJ1().getNbMatchJoue()+1);
			m5.getE2().getJ2().setNbMatchJoue(m5.getE2().getJ2().getNbMatchJoue()+1);
			
			
			System.out.println(m5.getScore1() + " : " + m5.getScore2());
			
			this.frame.getTournois().getBd().update("update match set pointequipe1="+m5.getScore1()+" where idmatch="+m5.getId()+";"
					+ "update match set pointequipe2="+m5.getScore2()+" where idmatch="+m5.getId()+";"
					+ "update match set matchjoue="+m5.isMatchjoue()+" where idmatch="+m5.getId()+";"
					+ "update equipe set score="+m5.getScore1()+" where idequipe="+m5.getE1().getId()+";"
					+ "update equipe set score="+m5.getScore2()+" where idequipe="+m5.getE2().getId()+";"
					+ "update equipe set joue="+m5.isMatchjoue()+" where idequipe="+m5.getE1().getId()+";"
					+ "update equipe set joue="+m5.isMatchjoue()+" where idequipe="+m5.getE2().getId()+";"
					+ "update joueur set score=score+"+m5.getScore1()+" where idjoueur="+m5.getE1().getJ1().getId()+";"
					+ "update joueur set score=score+"+m5.getScore1()+" where idjoueur="+m5.getE1().getJ2().getId()+";"
					+ "update joueur set score=score+"+m5.getScore2()+" where idjoueur="+m5.getE2().getJ1().getId()+";"
					+ "update joueur set score=score+"+m5.getScore2()+" where idjoueur="+m5.getE2().getJ2().getId()+";"
					+ "update joueur set nbmatchjoue=nbmatchjoue+1 where idjoueur="+m5.getE1().getJ1().getId()+";"
					+ "update joueur set nbmatchjoue=nbmatchjoue+1 where idjoueur="+m5.getE1().getJ2().getId()+";"
					+ "update joueur set nbmatchjoue=nbmatchjoue+1 where idjoueur="+m5.getE2().getJ1().getId()+";"
					+ "update joueur set nbmatchjoue=nbmatchjoue+1 where idjoueur="+m5.getE2().getJ2().getId()+";");
			
			this.frame.getTableMatch().changeData();
			this.frame.getTableEquipe().changeData();
			this.frame.getTablejoueur().changeData();
			this.frame.getTablee().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JComboBox<Joueur>(this.frame.getListeJoueur())));
			this.frame.getTablee().getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<Joueur>(this.frame.getListeJoueur())));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
