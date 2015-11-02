package DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import IHM.MainWin;

public class Import {

	private MainWin frame;

	public Import(MainWin frame){
		this.frame = frame;
	}

	public void ImportDonnee(File fichier) throws NumberFormatException, Exception{
		Scanner sc = new Scanner(fichier);
		sc.next();
		
		while(sc.hasNext()){
			String t = sc.next();
			String[] t2 = t.split(";");
			Boolean nouveau, homme;
			if(t2[5].equals("Nouveau")){nouveau = true;}
			else{nouveau = false;}
			if(t2[4].equals("Homme")){homme=true;}
			else{homme=false;}
			
			this.frame.getTournois().ajouterJoueur(t2[1], t2[2], Integer.parseInt(t2[3]), nouveau, homme);
		}
		
	
		
		
		
	}
	
	
	
	

}
