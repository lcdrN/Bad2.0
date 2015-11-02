package Main;

import java.util.LinkedList;

public class Joueur {
	
	private int id;
	private String nom;
	private String prenom;
	private int age;
	private boolean nouveau;
	private boolean homme;
	private LinkedList<Integer> joueurJoue;
	private int nbPoint;
	private int nbMatchJoue;
	private boolean actif;
	
	//Constructeur nouveau joueur
	public Joueur(String nom, String prenom, int age, boolean nouveau, boolean homme){
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.nouveau = nouveau;
		this.homme = homme;
		this.id = this.hashCode();
		this.joueurJoue = new LinkedList<Integer>();
		this.nbMatchJoue = 0;
		this.nbPoint = 0;
		this.actif = true;
	}
	
	//Constructeur Joueur lecture base de données
	public Joueur(int id, String nom, String prenom, int age, boolean nouveau, boolean homme, LinkedList<Integer> joueurjoue,
			int nbPoint, int nbMatchJoue, boolean actif){
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.nouveau = nouveau;
		this.homme = homme;
		this.joueurJoue = joueurjoue;
		this.nbMatchJoue = nbMatchJoue;
		this.nbPoint = nbPoint;
		this.actif = actif;
		
		//Ajouter joueurs a la liste
	}
	
	public String toString(){
		return this.prenom + " " + this.nom;
	}
	
	
	public int hashCode(){
		return this.nom.hashCode() + this.prenom.hashCode();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isNouveau() {
		return nouveau;
	}
	
	public String isNouveau2(){
		if(this.nouveau == true){
			return "Nouveau";
		}
		else{
			return "Ancien";
		}
	}
	
	

	public void setNouveau(boolean nouveau) {
		this.nouveau = nouveau;
	}

	public boolean isHomme() {
		return homme;
	}
	
	public String isHomme2(){
		if(this.homme == true){
			return "Homme";
		}
		else{
			return "Femme";
		}
	}
	

	public void setHomme(boolean homme) {
		this.homme = homme;
	}

	public LinkedList<Integer> getJoueurJoue() {
		return joueurJoue;
	}

	public void setJoueurJoue(LinkedList<Integer> joueurJoue) {
		this.joueurJoue = joueurJoue;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public void setNbPoint(int nbPoint) {
		this.nbPoint = nbPoint;
	}

	public int getNbMatchJoue() {
		return nbMatchJoue;
	}

	public void setNbMatchJoue(int nbMatchJoue) {
		this.nbMatchJoue = nbMatchJoue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
