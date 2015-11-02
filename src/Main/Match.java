package Main;

import DB.Database;

public class Match {
	
	private Equipe e1, e2;
	private int id, numtour;
	private int score1, score2;
	private boolean matchjoue;
	
	
	public Match(Equipe e1, Equipe e2, int numtour, Database bd) throws Exception {
		this.e1 = e1;
		this.e2 = e2;
		this.id = this.e1.getId()+this.e2.getId()+numtour;
		this.numtour = numtour;
		this.score1 = 0;
		this.score2 = 0;
		this.matchjoue = false;
		
		bd.insert("insert into Match(idmatch, numtour, idequipe1, idequipe2, pointequipe1, pointequipe2, matchjoue) "
				+ "values("+this.id+", "+this.numtour+", "+this.e1.getId()+", "+this.e2.getId()+", "
						+ "0, 0, false)");
		
		
	}
	
	public Match(int id, Equipe e1, Equipe e2, int numtour, int score1, int score2, boolean matchjoue){
		this.id = id;
		this.e1 = e1;
		this.e2 = e2;
		this.numtour = numtour;
		this.score1 = score1;
		this.score2 = score2;
		this.matchjoue = matchjoue;
	}

	public Equipe getE1() {
		return e1;
	}

	public void setE1(Equipe e1) {
		this.e1 = e1;
	}

	public Equipe getE2() {
		return e2;
	}

	public void setE2(Equipe e2) {
		this.e2 = e2;
	}

	public int getId2(){
		return this.id;
	}
	
	public int getId() {
		return this.e1.getId()+this.e2.getId()+this.numtour;
	}

	public void setId() {
		this.id = this.getId();
	}

	public int getNumtour() {
		return numtour;
	}

	public void setNumtour(int numtour) {
		this.numtour = numtour;
	}

	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	
	public String isMatchJoue2(){
		if(this.matchjoue == true){return "Oui";}
		else{return "Non";}
	}
	
	
	public boolean isMatchjoue() {
		return matchjoue;
	}

	public void setMatchjoue(boolean matchjoue) {
		this.matchjoue = matchjoue;
	}
	
	
	

}
