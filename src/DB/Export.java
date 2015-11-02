package DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import Comparteur.ComparteurJoueur;
import Main.Equipe;
import Main.Joueur;
import Main.Tournois;

public class Export {
	
	private Tournois t;
	
	public Export(Tournois t){
		this.t = t;
	}
	
	
	public void Exportdonnees() throws IOException{
		
		FileWriter r = new FileWriter(this.t.getNom()+".csv");
		
		//Joueur
		r.write("Joueurs : \n");
		r.write("Classement;Nom;Prénom;Âge;Sexe;Adhérent;Score;NbMatchJoués \n");
		LinkedList <Joueur> t = (LinkedList<Joueur>) this.t.getJoueurListe().clone();
		Collections.sort(t, new ComparteurJoueur());
		Iterator<Joueur> i = t.iterator();
		int nb=1;
		String v=";";
		while(i.hasNext()){
			Joueur j = i.next();
			r.write(Integer.toString(nb++)+v+j.getNom()+v+j.getPrenom()+v+j.getAge()+v+j.isHomme2()+v+j.isNouveau2()+v+j.getNbPoint()+v+j.getNbMatchJoue()+"\n");
		}
		
		r.write("\n \n Equipes : \n");
		r.write("Numéro Tour;Joueur 1;Joueur 2;Score;Match Joué ?"+"\n");
		
		for(int y=0;y<this.t.getNbTour();y++){
			Iterator<Equipe> equipe = this.t.getEquipeListe(y).iterator();
			while(equipe.hasNext()){
				Equipe e = equipe.next();
				r.write(y +v+ e.getJ1().getNom()+" "+e.getJ1().getPrenom() +v+ e.getJ2().getNom()+" "+e.getJ2().getPrenom() +v+ 
						e.getScore()+v+e.isMatchJoue2()+"\n");
			}
			
			
		}
		
		
		r.close();
		
	}
	
	
	
	

}
