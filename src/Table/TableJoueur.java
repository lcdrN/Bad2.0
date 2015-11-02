package Table;

import java.awt.Color;
import java.util.*;

import javax.swing.table.AbstractTableModel;

import Comparteur.ComparteurJoueur;
import DB.Database;
import Main.Joueur;


public class TableJoueur extends AbstractTableModel{

	private LinkedList<Joueur> donnees;
	
	private final String[] titres = {"Classement", "Nom & Prénom", "Age", "Sexe", "Niveau", "Score", "Nb Matchs"};
	private Database bd;

	public TableJoueur(LinkedList donnees, Database bd){
		super();
		this.donnees = donnees;
		this.bd = bd;
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
             return this.donnees.get(rowIndex).getNom() + " " + this.donnees.get(rowIndex).getPrenom();
         case 2:
             return this.donnees.get(rowIndex).getAge();
         case 3:
             return this.donnees.get(rowIndex).isHomme2();
         case 4:
        	 return this.donnees.get(rowIndex).isNouveau2();
         case 5:
        	 return this.donnees.get(rowIndex).getNbPoint();
         case 6:
        	 return this.donnees.get(rowIndex).getNbMatchJoue();
         default:
             return null; //Ne devrait jamais arriver
     }
 }
	
    public void addJoueur(Joueur j) {
        donnees.add(j);
        fireTableRowsInserted(donnees.size() -1, donnees.size() -1);
    }
 
    public void removeJoueur(int rowIndex) {
        this.donnees.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void changeData(){
    	ComparteurJoueur c = new ComparteurJoueur();
    	Collections.sort(this.donnees, c);
    	this.fireTableDataChanged();
    }
    
    public void setDonnees(LinkedList<Joueur> donnees){
    	this.donnees = donnees;
    	this.changeData();
    }
    
    public boolean isCellEditable(int row, int column){
        return column == 4;
    }
    
    public void setValueAt(Object value, int row, int col) {
    	if(col == 4){
    		if(value.equals("Nouveau")){
    			this.donnees.get(row).setNouveau(true);
    			int t = this.donnees.get(row).getId();
    			try {
					this.bd.update("update joueur set nouveau=true where idjoueur="+Integer.toString(t)+"");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else{
    			this.donnees.get(row).setNouveau(false);	
    		}
    	}
        fireTableCellUpdated(row, col);
    }


	public void setBd(Database bd) {
		this.bd = bd;
	}
    
    
    
}
