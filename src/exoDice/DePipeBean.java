package exoDice;

public class DePipeBean extends DeBean {
	@Override
	public void lancer() {
		if (RANDOM.nextBoolean()) {
			valeur = 6;
		} else {
			super.lancer();
		}
	}

}
