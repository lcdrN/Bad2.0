package Comparteur;

import java.util.Comparator;

import Main.Joueur;

public class ComparateurJoueurMatchJoue implements Comparator<Joueur> {

	
	public int compare(Joueur j1, Joueur j2) {
		if(j1.getNbMatchJoue() > j2.getNbMatchJoue()){
			return 1;
		}
		if(j1.getNbMatchJoue() < j2.getNbMatchJoue()){
			return -1;
		}
		if(j1.getNbMatchJoue() == j2.getNbMatchJoue()){
			return 0;
		}
		else{
			return 0;
		}
	}

	
}
