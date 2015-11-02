package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JProgressBar;

import Comparteur.ComparateurJoueurMatchJoue;
import DB.Database;

public class Tournois {
	
	private String nom;
	private int nbTour;
	private int nbTerrain;
	private LinkedList<Joueur> joueurs;
	private LinkedList<ArrayList<Equipe>> equipes;
	private LinkedList<ArrayList<Match>> matchs;
	private Connection conn;
	private Database bd;
	private JProgressBar equipe;
	
	
	//Nouveau Tournois
	public Tournois(String nom, int nbterrain, JProgressBar b) throws Exception{
		this.nom = nom;
		this.nbTerrain = nbterrain;
		this.nbTour = 0;
		this.joueurs = new LinkedList<Joueur>();
		this.equipes = new LinkedList<ArrayList<Equipe>>();
		this.equipes.add(new ArrayList<Equipe>());
		this.matchs = new LinkedList<ArrayList<Match>>();
		this.matchs.add(new ArrayList<Match>());
		this.bd = new Database(this.nom);
		this.equipe = b;
		
		this.bd.Connexiontournois();
		this.bd.premier();
		
		ResultSet result = this.bd.selecttournois("select nom from tournois where nom='"+this.nom+"'");
		if(!result.isBeforeFirst()){
			this.bd.updatetournois("insert into tournois(nom, nbterrain, numtour) values('"+this.nom+"', "+this.nbTerrain+", 0)");
			this.setConn(bd.Connexiondb(this.nom));
			this.bd.table();
		}
	}
	
	//Lecture base de données
	public Tournois(String nom, JProgressBar b) throws Exception{
		this.nom = nom;
		this.joueurs = new LinkedList<Joueur>();
		this.equipes = new LinkedList<ArrayList<Equipe>>();
		this.matchs = new LinkedList<ArrayList<Match>>();
		this.equipe = b;
		
		this.bd = new Database(this.nom);
		
		ResultSet result = this.bd.selecttournois("select nbterrain, numtour from tournois where nom='"+this.nom+"'");
		while(result.next()){
			this.nbTour = result.getInt("numtour");
			this.nbTerrain = result.getInt("nbterrain");
		}
		
		for(int i=0;i<=this.nbTour;i++){
			this.equipes.add(new ArrayList<Equipe>());
			this.matchs.add(new ArrayList<Match>());
		}
		
		this.setConn(bd.Connexiondb(this.nom));
		this.getDonnees();
		
		
		this.listeJoueurJoue();
				
	}
	
	public void getDonnees() throws Exception{
		ResultSet result = this.bd.select("select * from joueur");
		while(result.next()){
			this.ajouterJoueur(result.getInt("idjoueur"), result.getString("nom"), result.getString("prenom"), result.getInt("age"),
					result.getBoolean("nouveau"), result.getBoolean("sexe"), new LinkedList<Integer>(), result.getInt("score"),
					result.getInt("nbmatchjoue"), result.getBoolean("actif"));
		}
		
		ResultSet result2 = this.bd.select("select * from equipe");
		while(result2.next()){
			this.ajouterEquipe(result2.getInt("idequipe"), result2.getInt("IDJOUEUR1"), result2.getInt("IDJOUEUR2"), 
					result2.getInt("numerotour"), result2.getInt("score"), result2.getBoolean("joue"));
		}
		
		ResultSet result3 = this.bd.select("select * from match");
		while(result3.next()){
			this.ajouterMatch(result3.getInt("idmatch"), result3.getInt("idequipe1"),  result3.getInt("idequipe2"), result3.getInt("numtour"), 
					 result3.getInt("pointequipe1"),  result3.getInt("pointequipe2"),  result3.getBoolean("matchjoue"));
		}
		
	}
	
	
	//Nouveau joueur
	public void ajouterJoueur(String nom, String prenom, int age, boolean nouveau, boolean homme) throws Exception{
		Joueur j = new Joueur(nom, prenom, age, nouveau, homme);
		this.joueurs.add(j);
		int id=j.getId();
		
		this.bd.insert("insert into joueur(idjoueur, nom, prenom, age, sexe, nouveau, score, nbmatchjoue, actif) VALUES("+id+", '"+nom+"', "
				+ "'"+prenom+"', "+age+", "+homme+", "+nouveau+", 0, 0, TRUE)");
		
		
	}
	
	//Lecture base de données
	public void ajouterJoueur(int id, String nom, String prenom, int age, boolean nouveau, boolean homme, LinkedList<Integer> joueurjoue,
			int nbPoint, int nbMatchJoue, boolean actif){
		Joueur j = new Joueur(id, nom, prenom, age, nouveau, homme, joueurjoue, nbPoint, nbMatchJoue, actif);
		this.joueurs.add(j);
	}
	
	//Nouvelle equipe
	public void ajouterEquipe(Joueur j1, Joueur j2, int numtour) throws Exception{
		Equipe e = new Equipe(j1, j2, numtour);
		
		this.bd.insert("insert into equipe(idequipe, idjoueur1, idjoueur2, numerotour, score, joue) values("+e.getId()+", "+e.getJ1().getId()+", "
				+ ""+e.getJ2().getId()+", "+numtour+", 0, false)");
		
		this.equipes.get(numtour).add(e);
	}
	
	//Lecture equipe base
	public void ajouterEquipe(int id, int j1, int j2, int numtour, int score, boolean joue) throws Exception{
		Joueur joueur1 = this.getJoueur(j1);
		Joueur joueur2 = this.getJoueur(j2);
		Equipe e = new Equipe(joueur1.getId()+joueur2.getId(), joueur1, joueur2, numtour, score, joue);
		this.equipes.get(numtour).add(e);
	}
	
	
	//Nouveau Match
	public void ajouterMatch(Equipe e1, Equipe e2, int numtour) throws Exception{
		Match m = new Match(e1, e2, numtour, this.bd);
		this.matchs.get(this.nbTour-1).add(m);
	}
	
	//Lecture match base de données
	public void ajouterMatch(int id, int ide1, int ide2, int numtour, int score1, int score2, boolean matchjoue){
		
		Equipe e1 = this.getEquipe(ide1, numtour);
		Equipe e2 = this.getEquipe(ide2, numtour);
		
		Match m = new Match(id, e1, e2, numtour, score1, score2, matchjoue);
		this.matchs.get(numtour).add(m);
	}
	
	
	//Définit les joueur déja joué par un joueur
	public void listeJoueurJoue(){

		for(int i=0;i<this.nbTour;i++){
			Iterator<Equipe> t = this.equipes.get(i).iterator();
			while(t.hasNext()){
				Equipe e = t.next();
				e.getJ1().getJoueurJoue().add(e.getJ2().getId());
				e.getJ2().getJoueurJoue().add(e.getJ1().getId());
			}
		}
	}
	
	//Créer les Equipes
	public void creerEquipe(int i) throws Exception{
		if(i == 0){
			this.CreerEquipeNA();
		}
	}
	
	//Créer Les Equipes avec nouveaux - Ancien (Jamais même paires)
	public void CreerEquipeNA() throws Exception{
		
		boolean fin = false;
		
		this.equipes.add(new ArrayList<Equipe>());
		
		LinkedList<Joueur> listej = (LinkedList<Joueur>) this.joueurs.clone();
		Collections.sort(listej, new ComparateurJoueurMatchJoue());
		
		ArrayList<Joueur> liste2 = new ArrayList<Joueur>();
		
		while(fin == false){
			boolean fin2 = false;
			int i = 0;
			
			Joueur j = listej.pollFirst();
			
			while(fin2 == false && i<listej.size()){
				if(!j.getJoueurJoue().contains(listej.get(i).getId()) && j.isNouveau() != listej.get(i).isNouveau() && j != listej.get(i)){
					
					this.ajouterEquipe(j, listej.get(i), this.nbTour);
					
					this.equipe.setString(this.Nbequipes(this.equipes.get(nbTour).size()));
					this.equipe.setValue((this.equipes.get(nbTour).size() / (this.joueurs.size()/2)) *100);
					
					
					j.getJoueurJoue().add(listej.get(i).getId());
					listej.get(i).getJoueurJoue().add(j.getId());
					
					listej.remove(listej.get(i));
					
					
					fin2 = true;
				}
				i++;
			}
			if(fin2 == false){
				liste2.add(j);
			}
			if(listej.isEmpty() == true){
				fin = true;
			}
		}
	}
	
	
	public void creerMatch() throws Exception{
		int nbMatch = this.equipes.get(this.nbTour-1).size()/2;
		
		this.matchs.add(new ArrayList<Match>());
		
		ArrayList<Equipe> equipe = (ArrayList<Equipe>) this.equipes.get(this.nbTour-1).clone();
		Collections.shuffle(equipe);
		int nb =1;
		int y =0;
		
		for(int i=0;i<nbMatch;i++){
			this.ajouterMatch(equipe.get(y++), equipe.get(y++), this.nbTour-1);
		}
		
		
	}
	
	
	
	
	public Joueur getJoueur(int id){
		for(int i=0;i<this.joueurs.size();i++){
			if(this.joueurs.get(i).getId() == id){
				return this.joueurs.get(i);
			}
		}
		return null;
	}
	
	
	public Equipe getEquipe(int id, int numtour){
		for(int i=0;i<this.equipes.get(numtour).size();i++){
			if(this.equipes.get(numtour).get(i).getId() == id){
				return this.equipes.get(numtour).get(i);
			}
		}
		return null;
	}
	
	public Match getMatch(int id, int tour){
		for(int i=0;i<this.matchs.get(tour).size();i++){
			if(this.matchs.get(tour).get(i).getId() == id){
				return this.matchs.get(tour).get(i);
			}
		}
		return null;
	}
	
	public String Nbequipes(int nombre){
		int nb = this.joueurs.size()/2;
		return "Equipe : "+nombre+"/"+nb+"";		
		
	}
	
	public void majIdMatch() throws Exception{
		for(int i=0;i<this.matchs.get(nbTour-1).size();i++){
			String id = Integer.toString(this.matchs.get(nbTour-1).get(i).getId2());
			
			this.matchs.get(nbTour-1).get(i).setId();
			
			if(Integer.parseInt(id) != this.matchs.get(nbTour-1).get(i).getId2()){
				this.bd.update("update match set idequipe1="+this.matchs.get(nbTour-1).get(i).getE1().getId()+" where idmatch="+id+";"
						+ "update match set idequipe2="+this.matchs.get(nbTour-1).get(i).getE2().getId()+" where idmatch="+id+" ");
				this.bd.update("update match set idmatch="+this.matchs.get(nbTour-1).get(i).getId()+" where idmatch="+id+"");
			}
			
			
		}
	}
	
	
	public boolean joueurAJoue(Joueur j){
		if(this.nbTour != 0)
		for(int i=0;i<this.equipes.get(nbTour-1).size();i++){
			if((this.equipes.get(nbTour-1).get(i).getJ1() == j || this.equipes.get(nbTour-1).get(i).getJ2() == j) && this.equipes.get(nbTour-1).get(i).isMatchjoue() == true){
				return false;
			}
		}
		return true;
	}
	
	
	public LinkedList<Joueur> getJoueurListe(){
		return this.joueurs;
	}
	
	public ArrayList<Equipe> getEquipeListe(int i){
		return this.equipes.get(i);
	}
	
	public ArrayList<Match> getMatchListe(int i){
		return this.matchs.get(i);
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public int getNbTerrain() {
		return nbTerrain;
	}

	public void setNbTerrain(int nbTerrain) {
		this.nbTerrain = nbTerrain;
	}

	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(LinkedList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Database getBd() {
		return bd;
	}

	public void setBd(Database bd) {
		this.bd = bd;
	}
	
	

	
	
	
	
	
	

}
