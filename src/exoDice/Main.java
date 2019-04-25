package exoDice;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DiceGameIHM ihm = new DiceGameIHM();
					Controler controler = new Controler();
					ihm.frame.setVisible(true);
					ihm.setListener(controler);
					controler.setIhm(ihm);
					controler.refreshScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
