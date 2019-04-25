package exoDice;

import java.util.Random;

public class DeBean {
	protected int valeur;
	protected static final Random RANDOM = new Random();

	// Methods

	public void lancer() {
		valeur = RANDOM.nextInt(6) + 1;
	}

	// Getters + Setters

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
}
