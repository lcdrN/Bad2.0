package Table;

import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Comparteur.ComparateurEquipe;
import DB.Database;
import IHM.MainWin;
import Main.Equipe;
import Main.Joueur;


public class TableEquipe extends AbstractTableModel {

	private ArrayList<Equipe> donnees;
	private final String[] titres = {"Classement", "Joueur 1", "Joueur 2", "Score", "Match Joué ?"};
	private MainWin frame;
	
	public TableEquipe(ArrayList<Equipe> donnees, MainWin frame){
		super();
		this.donnees = donnees;
		this.frame = frame;
	}
	

	public int getColumnCount() {
		return this.titres.length;
	}

	
	public int getRowCount() {
		return this.donnees.size();
	}

	public String getColumnName(int columnIndex) {
        return this.titres[columnIndex];
    }
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		 switch(columnIndex){
         case 0:
             return rowIndex+1;
         case 1:
             return this.donnees.get(rowIndex).getJ1().getPrenom() + " " + this.donnees.get(rowIndex).getJ1().getNom();
         case 2:
        	 return this.donnees.get(rowIndex).getJ2().getPrenom() + " " + this.donnees.get(rowIndex).getJ2().getNom();
         case 3:
             return this.donnees.get(rowIndex).getScore();
         case 4:
             return this.donnees.get(rowIndex).isMatchJoue2();
         default:
             return null; //Ne devrait jamais arriver
     }
 }
	
    public void addEquipe(Equipe j) {
        donnees.add(j);
        fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
    }
 
    public void removeEquipe(int rowIndex) {
        this.donnees.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void changeData(){
    	ComparateurEquipe c = new ComparateurEquipe();
    	Collections.sort(this.donnees, c);
    	this.fireTableDataChanged();
    }
    
    public void setDonnees(ArrayList<Equipe> donnees){
    	this.donnees = donnees;
    	this.changeData();
    }
    
    public boolean isCellEditable(int row, int column){
    	if(this.donnees.get(row).isMatchjoue() == true && (column ==1 || column == 2)){
    		return false;
    	}
    	else{
    		return column == 1 || column == 2;
    	}

    }
    
    public void setValueAt(Object value, int row, int col) {
    	if(col == 1){
    		int confirmation = JOptionPane.YES_NO_OPTION;
    		int resultat = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cette équipe ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if(resultat == 0){
    		Joueur joueurN2 = (Joueur) value;
    		Joueur j = this.donnees.get(row).getJ1();
    		int idequipe = this.donnees.get(row).getId();
    		
  		  	this.donnees.get(row).setJ1(joueurN2);
  		  	this.donnees.get(row).setId(this.donnees.get(row).getId());
  		  	
  		  	try {
				this.frame.getTournois().getBd().update("update equipe set idjoueur1="+joueurN2.getId()+" where idequipe="+idequipe+";"
						+ "update equipe set idequipe="+this.donnees.get(row).getId()+" where idequipe="+idequipe+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
  		  	
  		  	for(int i=0;i<this.donnees.size();i++){
  		  		if((this.donnees.get(i).getJ1().equals(joueurN2) && i != row)){
  		  			int idequipe2 = this.donnees.get(i).getId();
  		  			this.donnees.get(i).setJ1(j);
  		  			this.donnees.get(i).setId(this.donnees.get(i).getId());
  		  			try {
						this.frame.getTournois().getBd().update("update equipe set idjoueur1="+j.getId()+" where idequipe="+idequipe2+";"
								+ "update equipe set idequipe="+this.donnees.get(i).getId()+" where idequipe="+idequipe+"");
					} catch (Exception e) {e.printStackTrace();}
  		  		}
  		  		if(this.donnees.get(i).getJ2().equals(joueurN2) && i == row){
  		  			
  		  			int idequipe2 = this.donnees.get(i).getId();
		  			this.donnees.get(i).setJ2(j);
		  			this.donnees.get(i).setId(this.donnees.get(i).getId());
  		  		try {
					this.frame.getTournois().getBd().update("update equipe set idjoueur2="+j.getId()+" where idequipe="+idequipe2+";"
							+ "update equipe set idequipe="+this.donnees.get(i).getId()+" where idequipe="+idequipe+"");
				} catch (Exception e) {e.printStackTrace();}
  		  		}
  		  		if((this.donnees.get(i).getJ2().equals(joueurN2) && i != row)){
  		  			int idequipe2 = this.donnees.get(i).getId();
  		  			this.donnees.get(i).setJ2(j);
  		  			this.donnees.get(i).setId(this.donnees.get(i).getId());
  		  			try {
  		  				this.frame.getTournois().getBd().update("update equipe set idjoueur2="+j.getId()+" where idequipe="+idequipe2+";"
						+ "update equipe set idequipe="+this.donnees.get(i).getId()+" where idequipe="+idequipe+"");
  		  			} catch (Exception e) {e.printStackTrace();}
  		  		}
  		  	}
  		  	try {
				this.frame.getTournois().majIdMatch();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
  		  	
    	}
    	if(col == 2){
    		int confirmation = JOptionPane.YES_NO_OPTION;
    		int resultat = JOptionPane.showConfirmDialog(null, "Voulez-vous modifier cette équipe ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if(resultat == 0){
    		Joueur joueurN2 = (Joueur) value;
    		Joueur j = this.donnees.get(row).getJ2();
    		int idequipe = this.donnees.get(row).getId();
    		
  		  	this.donnees.get(row).setJ2(joueurN2);
  		  	this.donnees.get(row).setId(this.donnees.get(row).getId());
  		  	
  		  	
  		  	try {
				this.frame.getTournois().getBd().update("update equipe set idjoueur2="+joueurN2.getId()+" where idequipe="+idequipe+";"
						+ "update equipe set idequipe="+this.donnees.get(row).getId()+" where idequipe="+idequipe+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
  		  	
  		  	for(int i=0;i<this.donnees.size();i++){
  		  		if((this.donnees.get(i).getJ2().equals(joueurN2) && i != row)){
  		  			int idequipe2 = this.donnees.get(i).getId();
  		  			this.donnees.get(i).setJ2(j);
  		  			this.donnees.get(i).setId(this.donnees.get(i).getId());
  		  			try {
						this.frame.getTournois().getBd().update("update equipe set idjoueur2="+j.getId()+" where idequipe="+idequipe2+";"
								+ "update equipe set idequipe="+this.donnees.get(i).getId()+" where idequipe="+idequipe+"");
					} catch (Exception e) {e.printStackTrace();}
  		  		}
  		  		if(this.donnees.get(i).getJ1().equals(joueurN2) && i == row){
  		  			
  		  			int idequipe2 = this.donnees.get(i).getId();
		  			this.donnees.get(i).setJ1(j);
		  			this.donnees.get(i).setId(this.donnees.get(i).getId());
  		  		try {
					this.frame.getTournois().getBd().update("update equipe set idjoueur1="+j.getId()+" where idequipe="+idequipe2+";"
							+ "update equipe set idequipe="+this.donnees.get(i).getId()+" where idequipe="+idequipe+"");
				} catch (Exception e) {e.printStackTrace();}
  		  		}
  		  		if((this.donnees.get(i).getJ1().equals(joueurN2) && i != row)){
  		  			int idequipe2 = this.donnees.get(i).getId();
  		  			this.donnees.get(i).setJ1(j);
  		  			this.donnees.get(i).setId(this.donnees.get(i).getId());
  		  			try {
  		  				this.frame.getTournois().getBd().update("update equipe set idjoueur1="+j.getId()+" where idequipe="+idequipe2+";"
						+ "update equipe set idequipe="+this.donnees.get(i).getId()+" where idequipe="+idequipe+"");
  		  			} catch (Exception e) {e.printStackTrace();}
  		  		}
  		  	}
  		  try {
			this.frame.getTournois().majIdMatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           }
    	}
        fireTableCellUpdated(row, col);
        this.changeData();
    }



}
