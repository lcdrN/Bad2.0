package Main;

public class Equipe {
	
	private Joueur j1;
	private Joueur j2;
	private int score;
	private int id;
	private int numtour;
	private boolean matchjoue;
	
	//Nouvelle équipe
	public Equipe(Joueur j1, Joueur j2, int numtour){
		this.j1 = j1;
		this.j2 = j2;
		this.numtour = numtour;
		this.score = 0;
		this.id = j1.hashCode()+j2.hashCode();
		this.matchjoue = false;
	}
	
	//Lecture equipe dans BD
	public Equipe(int id, Joueur j1, Joueur j2, int numtour, int score, boolean matchjoue){
		this(j1, j2, numtour);
		this.id = id;
		this.score = score;
		this.matchjoue = matchjoue;
	}

	
	public String getNomEquipe(){
		return this.j1.getNom()+" "+this.j1.getPrenom()+" & "+this.j2.getNom()+" "+this.j2.getPrenom();
	}
	
	
	public int getId(){
		return this.j1.hashCode()+this.j2.hashCode();
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isMatchjoue() {
		return matchjoue;
	}
	
	public String isMatchJoue2(){
		if(this.matchjoue == true){
			return "Oui";
		}
		else{
			return "Non";
		}
	}

	public void setMatchjoue(boolean matchjoue) {
		this.matchjoue = matchjoue;
	}
	
	

}
