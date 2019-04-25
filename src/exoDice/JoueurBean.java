package exoDice;

public class JoueurBean {
	private boolean tricheur;
	private long id;
	private int scorePartie;
	private String nom;
	private GobeletBean gobelet;

	// Constructor
	public JoueurBean(String nom) {
		this.nom = nom;
		gobelet = new GobeletBean(tricheur);
	}

	public JoueurBean() {
	}

	// Methods

	public void lancer() {
		gobelet.lancer();
	}

	public void addOnePoint() {
		scorePartie++;
	}

	// Getters + Setters

	public int getScorePartie() {
		return scorePartie;
	}

	public void setScorePartie(int scorePartie) {
		this.scorePartie = scorePartie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public GobeletBean getGobelet() {
		return gobelet;
	}

	public void setGobelet(GobeletBean gobelet) {
		this.gobelet = gobelet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isTricheur() {
		return tricheur;
	}

	public void setTricheur(boolean tricheur) {
		this.tricheur = tricheur;
		gobelet = new GobeletBean(tricheur);
	}
}
