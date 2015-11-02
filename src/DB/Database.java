package DB;

import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hsqldb.util.SqlTool;

import Exception.ThrowError;
import Main.Tournois;




import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Database {

	/**
	 * @param args
	 * @throws SQLException
	 */

	private Connection conn;
	private String nom;
	
	public Database(String nom){
		this.nom = nom;
	}
	


	public Connection Connexiondb(String nom) throws Exception {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		String tmp ="";
		
		try {
			tmp = "jdbc:hsqldb:file:db/" + nom;
			this.conn = DriverManager.getConnection(tmp, "sa", "");
			return this.conn;
		} catch (SQLException e) {
			ThrowError.doError("Erreur dans la connexion à la base de données \n"+"Veuillez retenter l'opération");
			return null;
		}
	}

	public boolean Connexiontournois() throws Exception {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		try {
			conn = DriverManager.getConnection("jdbc:hsqldb:file:db/tournois",
					"sa", "");
			return true;
		} catch (SQLException e) {
			ThrowError.doError("Erreur dans la connexion à la base de données \n"+"Veuillez retenter l'opération");
			return false;
		}
	}

	public void shutdown() throws Exception {
		Statement st = this.conn.createStatement();
		st.execute("SHUTDOWN COMPACT");
		this.conn.close();
	}

	public Connection getConnetion() {
		return this.conn;
	}

	// Pour select normal
	public ResultSet select(String requete) throws Exception {
		this.Connexiondb(this.nom);
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery(requete);
		shutdown();
		return resultat;
	}

	// Pour select tournoi
	public ResultSet selecttournois(String requete) throws Exception {
		Connexiontournois();
		Statement stmt = conn.createStatement();
		ResultSet resultat = stmt.executeQuery(requete);
		shutdown();
		return resultat;
	}

	// Pour InsÃ©rer
	public void insert(String requete) throws Exception {
		this.Connexiondb(this.nom);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	// Pour mettre Ã  jour
	public void update(String requete) throws Exception {
		this.Connexiondb(this.nom);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	// Pour mettre Ã  jour tournois
	public void updatetournois(String requete) throws Exception {
		Connexiontournois();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	public void tabletournois() throws Exception {
		updatetournois("create table tournois(nom varchar(255) not null, nbterrain int not null, numtour int not null)");
	}

	// Pour supprimer
	public void delete(String requete) throws Exception {
		this.Connexiondb(this.nom);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(requete);
		shutdown();
	}

	public void table() throws Exception {
		this.Connexiondb(this.nom);
		update("create table joueur(idjoueur int primary key, nom varchar(255) not null, prenom varchar(255) not null, age int not null, sexe boolean not null, nouveau boolean not null, score int, Nbmatchjoue int, actif boolean)");
		update("create table equipe(idequipe int primary key, idjoueur1 int not null, idjoueur2 int not null, numerotour int not null, score int, joue boolean)");
		update("create table match(idmatch int primary key, numtour int not null, idequipe1 int not null, idequipe2 int not null, pointequipe1 int not null, pointequipe2 int not null, matchjoue boolean)");
	}							

	public void premier() throws Exception {
		try {
			selecttournois("select * from tournois");
		} catch (Exception e) {
			Connexiontournois();
			tabletournois();
		}

	}
	
	public String[] listeTournois() throws Exception{
		ArrayList<String> s = new ArrayList<String>();
		try{
		ResultSet result = selecttournois("select nom from tournois");
		while(result.next()){
			s.add(result.getString("nom"));
		}
		String[] s2 = new String[s.size()];
		for(int i = 0;i<s.size();i++){
			s2[i] = s.get(i);
		}
		return s2;
		
		}catch (Exception e){
			premier();
			ResultSet result = selecttournois("select nom from tournois");
			while(result.next()){
				s.add(result.getString("nom"));
			}
			String[] s2 = new String[s.size()];
			for(int i = 0;i<s.size();i++){
				s2[i] = s.get(i);
			}
			return s2;
		}
		
	}

	
}
