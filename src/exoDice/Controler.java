package exoDice;

import exoDice.interfaces.IHMListener;
import exoDice.interfaces.IHMUpdateI;

public class Controler implements IHMListener {

	IHMUpdateI ihm;

	PartieBean partieBean;

	// Controlleur
	public Controler() {
		partieBean = new PartieBean("Toto", "Tata");
	}

	// Methods

	private void testScore(JoueurBean joueur) {
		if (joueur.getGobelet().getScoreDes() >= PartieBean.SCORE_A_ATEINDRE) {
			joueur.addOnePoint();
		}
	}

	private void manageScoreDe() {
		JoueurBean joueurPrecedent = partieBean.getJoueurCourant() == partieBean.getJ1() ? partieBean.getJ2()
				: partieBean.getJ1();

		if (joueurPrecedent.getGobelet().getScoreDes() > 0 && partieBean.getTour() > 1
				|| partieBean.getJoueurCourant() == partieBean.getJ2()) {
			ihm.updateScoreDe(joueurPrecedent.getGobelet().getD1().getValeur(),
					joueurPrecedent.getGobelet().getD2().getValeur());
		} else {
			ihm.resetScoreDe();
		}
	}

	public void refreshScreen() {
		// Update J1
		ihm.updateJ1(partieBean.getJ1().getNom(), partieBean.getJ1().getScorePartie());
		ihm.toggleTricheurJ1(partieBean.getJ1().isTricheur());
		// Update J2
		ihm.updateJ2(partieBean.getJ2().getNom(), partieBean.getJ2().getScorePartie());
		ihm.toggleTricheurJ2(partieBean.getJ2().isTricheur());
		// Update dés
		manageScoreDe();

		// Update button visibility
		if (partieBean.getJoueurCourant() == partieBean.getJ1()) {
			ihm.updateButtonVisibility(true, false);

		} else if (partieBean.getJoueurCourant() == partieBean.getJ2()) {
			ihm.updateButtonVisibility(false, true);
		}

		// Update tour
		if (!partieBean.isFinished()) {
			ihm.updateResultText(partieBean.getJoueurCourant().getNom() + "'s turn !");
			ihm.updateTour(partieBean.getTour());
		}

		// Game finished
		if (partieBean.isFinished()) {
			ihm.updateButtonVisibility(false, false);
			if (partieBean.getJ1().getScorePartie() > partieBean.getJ2().getScorePartie()) {
				ihm.updateResultText(partieBean.getJ1().getNom() + " WON !");
			} else if (partieBean.getJ2().getScorePartie() > partieBean.getJ1().getScorePartie()) {
				ihm.updateResultText(partieBean.getJ2().getNom() + " WON !");
			} else {
				ihm.updateResultText("It's a tie !");
			}
		}

	}

	@Override
	public void clickOnRestart() {
		partieBean = new PartieBean(partieBean.getJ1().getNom(), partieBean.getJ2().getNom());

		refreshScreen();
	}

	@Override
	public void clickOnLancerJ1() {
		partieBean.getJ1().lancer();
		testScore(partieBean.getJ1());
		partieBean.changerJoueurCourant();

		// Affichage
		refreshScreen();

	}

	@Override
	public void clickOnLancerJ2() {
		partieBean.getJ2().lancer();
		testScore(partieBean.getJ2());
		partieBean.ajouterUnTour();
		partieBean.changerJoueurCourant();

		// Affichage
		refreshScreen();

	}

	@Override
	public void toggleTricheurJ1() {
		partieBean.getJ1().setTricheur(!partieBean.getJ1().isTricheur());

		refreshScreen();
	}

	@Override
	public void toggleTricheurJ2() {
		partieBean.getJ2().setTricheur(!partieBean.getJ2().isTricheur());

		refreshScreen();
	}

	@Override
	public void savePartie() {
		try {
			PartieBddUtils.savePartie(partieBean);
			ihm.updateResultText("Partie sauvegardée");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ihm.updateResultText("Erreur de sauvegarde : " + e.getMessage());
		}
	}

	@Override
	public void chargerPartie() {
		try {
			partieBean = PartieBddUtils.chargerPartie();
			refreshScreen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ihm.updateResultText("Erreur de chargement : " + e.getMessage());
		}

	}

	// Getter & Setter

	public void setIhm(IHMUpdateI ihm) {
		this.ihm = ihm;
	}

}
