package exoDice;

public class GobeletBean {
	private DeBean d1, d2;

	// Constructor
	public GobeletBean() {
		this(false);
	}

	public GobeletBean(boolean truque) {
		if (truque) {
			d1 = new DePipeBean();
		} else {
			d1 = new DeBean();
		}
		d2 = new DeBean();
	}

	// Methods

	public int getScoreDes() {
		return d1.getValeur() + d2.getValeur();
	}

	public void lancer() {
		d1.lancer();
		d2.lancer();
	}

	// Getters + Setters

	public DeBean getD1() {
		return d1;
	}

	public void setD1(DeBean d1) {
		this.d1 = d1;
	}

	public DeBean getD2() {
		return d2;
	}

	public void setD2(DeBean d2) {
		this.d2 = d2;
	}

}
