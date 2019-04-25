package exoDice.interfaces;

public interface IHMUpdateI {
	public void updateScoreDe(int scoreD1, int scoreD2);

	public void resetScoreDe();

	public void updateJ1(String nom, int score);

	public void updateJ2(String nom, int score);

	public void updateTour(int tour);

	public void updateButtonVisibility(boolean visibleButtonJ1, boolean visibleButtonJ2);

	public void updateResultText(String text);

	public void toggleTricheurJ1(boolean checked);

	public void toggleTricheurJ2(boolean checked);
}
