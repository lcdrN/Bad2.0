package Comparteur;

import java.util.Comparator;

import Main.Equipe;


public class ComparateurEquipe implements Comparator<Equipe> {

	
	public int compare(Equipe e1, Equipe e2) {
		if(e1.getScore() > e2.getScore()){
			return -1;
		}
		if(e1.getScore() < e2.getScore()){
			return 1;
		}
		if(e1.getScore() == e2.getScore()){
			return 0;
		}
		else{
			return 0;
		}
	}

	
}
