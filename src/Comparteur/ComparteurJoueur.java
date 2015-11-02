package Comparteur;

import java.util.Comparator;
import java.util.Random;

import Main.Joueur;

public class ComparteurJoueur implements Comparator<Joueur> {

	
	public int compare(Joueur j1, Joueur j2) {
		if(j1.getNbPoint() > j2.getNbPoint()){
			return -1;
		}
		if(j1.getNbPoint() < j2.getNbPoint()){
			return 1;
		}
		if(j1.getNbPoint() == j2.getNbPoint()){
			return 0;
		}
		else{
			return 0;
		}
	}

	
}
