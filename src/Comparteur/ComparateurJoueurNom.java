package Comparteur;

import java.util.Comparator;

import Main.Joueur;



public class ComparateurJoueurNom implements Comparator<Joueur> {

	
	public int compare(Joueur j1, Joueur j2) {
		if((j1.getPrenom() + " " + j1.getNom()).compareTo(j2.getPrenom() + " " + j2.getNom()) > 0){
			return 1;
		}
		
		if((j1.getPrenom() + " " + j1.getNom()).compareTo(j2.getPrenom() + " " + j2.getNom()) < 0){
			return -1;
		}
		
		if((j1.getPrenom() + " " + j1.getNom()).compareTo(j2.getPrenom() + " " + j2.getNom()) == 0){
			return 0;
		}
		return 0;
	}

	
}
