package exoDice;

public class PartieBean {
	public static final int NB_LANCER = 10;
	public static final int SCORE_A_ATEINDRE = 7;
	private JoueurBean j1, j2;
	private int tour;
	private JoueurBean joueurCourant;

	// Constructor
	public PartieBean(String nomJ1, String nomJ2) {
		j1 = new JoueurBean(nomJ1);
		j2 = new JoueurBean(nomJ2);
		joueurCourant = j1;
		tour = 1;
	}

	// Methods
	public void changerJoueurCourant() {
		if (j1 == joueurCourant) {
			joueurCourant = j2;
		} else {
			joueurCourant = j1;
		}
	}

	public boolean isFinished() {
		return tour > NB_LANCER;
	}

	public void ajouterUnTour() {
		tour++;
	}

	// Getters + Setters
	public JoueurBean getJ1() {
		return j1;
	}

	public void setJ1(JoueurBean j1) {
		this.j1 = j1;
	}

	public JoueurBean getJ2() {
		return j2;
	}

	public void setJ2(JoueurBean j2) {
		this.j2 = j2;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public JoueurBean getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(JoueurBean joueurCourant) {
		this.joueurCourant = joueurCourant;
	}
}
