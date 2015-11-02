package Table;

import java.util.*;

import javax.swing.table.AbstractTableModel;

import Comparteur.ComparateurEquipe;
import Main.Equipe;
import Main.Match;


public class TableMatch extends AbstractTableModel {

	private ArrayList<Match> donnees;
	
	private final String[] titres = {"Classement", "Equipe 1", "Equipe 2", "Score", "Match Joué ?"};
	
	public TableMatch(ArrayList<Match> donnees){
		super();
		this.donnees = donnees;
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
             return this.donnees.get(rowIndex).getE1().getNomEquipe();
         case 2:
        	 return this.donnees.get(rowIndex).getE2().getNomEquipe();
         case 3:
             return this.donnees.get(rowIndex).getScore1() +" - "+ this.donnees.get(rowIndex).getScore2();
         case 4:
             return this.donnees.get(rowIndex).isMatchJoue2();
         default:
             return null; //Ne devrait jamais arriver
     }
 }
	
    public void addEquipe(Match j) {
        donnees.add(j);
        fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
    }
 
    public void removeMatch(int rowIndex) {
        this.donnees.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void changeData(){
    	this.fireTableDataChanged();
    }
    
    public void setDonnees(ArrayList<Match> donnees){
    	this.donnees = donnees;
    	this.changeData();
    }
    

}
