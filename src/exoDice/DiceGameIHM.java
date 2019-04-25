package exoDice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exoDice.interfaces.IHMListener;
import exoDice.interfaces.IHMUpdateI;

public class DiceGameIHM implements IHMUpdateI {

	// Graphic components
	JFrame frame;
	private JTextField textFieldScoreJ1;
	private JTextField textFieldScoreJ2;
	private JButton btnLancerJ2;
	private JLabel lblTour;
	private JLabel labelTourValue;
	private JLabel lblJoueur1;
	private JLabel lblScoreJ1;
	private JButton btnLancerJ1;
	private JLabel lblJoueur2;
	private JComponent lblScoreJ2;
	private JLabel lblDe1;
	private JLabel lblDe2;
	private JButton btnRestart;
	private JLabel lblResult;
	private JLabel imageDe1;
	private JLabel imageDe2;
	private JCheckBox cbJ1;
	private JCheckBox cbJ2;

	// Controler
	IHMListener listener;

	public DiceGameIHM() {
		initialize();
	}

	// Methods MAJ Affichage

	@Override
	public void updateScoreDe(int scoreD1, int scoreD2) {
		imageDe1.setIcon(new ImageIcon(DiceGameIHM.class.getResource("/de_" + scoreD1 + ".png")));
		;
		imageDe2.setIcon(new ImageIcon(DiceGameIHM.class.getResource("/de_" + scoreD2 + ".png")));
		;
	}

	@Override
	public void resetScoreDe() {
		imageDe1.setIcon(new ImageIcon(DiceGameIHM.class.getResource("/roll.jpg")));
		imageDe2.setIcon(new ImageIcon(DiceGameIHM.class.getResource("/roll.jpg")));
	}

	@Override
	public void updateJ1(String nom, int score) {
		textFieldScoreJ1.setText("" + score);
		lblJoueur1.setText(nom);
	}

	@Override
	public void updateJ2(String nom, int score) {
		textFieldScoreJ2.setText("" + score);
		lblJoueur2.setText(nom);
	}

	@Override
	public void updateTour(int tour) {
		labelTourValue.setText("" + tour);
	}

	@Override
	public void updateButtonVisibility(boolean visibleButtonJ1, boolean visibleButtonJ2) {
		btnLancerJ1.setEnabled(visibleButtonJ1);
		btnLancerJ2.setEnabled(visibleButtonJ2);
	}

	@Override
	public void updateResultText(String text) {
		lblResult.setText(text);
	}

	@Override
	public void toggleTricheurJ1(boolean checked) {
		cbJ1.setSelected(checked);
	}

	@Override
	public void toggleTricheurJ2(boolean checked) {
		cbJ2.setSelected(checked);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 732, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblTour = new JLabel("Tour : ");
		lblTour.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTour.setBounds(299, 43, 67, 33);
		frame.getContentPane().add(lblTour);

		labelTourValue = new JLabel();
		labelTourValue.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTourValue.setBounds(369, 49, 31, 20);
		frame.getContentPane().add(labelTourValue);

		lblJoueur1 = new JLabel();
		lblJoueur1.setForeground(Color.BLACK);
		lblJoueur1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJoueur1.setBounds(85, 85, 63, 30);
		frame.getContentPane().add(lblJoueur1);

		lblScoreJ1 = new JLabel("Score : ");
		lblScoreJ1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScoreJ1.setBounds(69, 139, 55, 19);
		frame.getContentPane().add(lblScoreJ1);

		textFieldScoreJ1 = new JTextField();
		textFieldScoreJ1.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldScoreJ1.setBounds(127, 140, 31, 20);
		frame.getContentPane().add(textFieldScoreJ1);
		textFieldScoreJ1.setColumns(10);

		btnLancerJ1 = new JButton("Lancer");
		btnLancerJ1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listener.clickOnLancerJ1();
			}
		});
		btnLancerJ1.setBounds(69, 181, 89, 23);
		frame.getContentPane().add(btnLancerJ1);

		lblJoueur2 = new JLabel();
		lblJoueur2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJoueur2.setBounds(574, 85, 63, 30);
		frame.getContentPane().add(lblJoueur2);

		lblScoreJ2 = new JLabel("Score : ");
		lblScoreJ2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScoreJ2.setBounds(558, 140, 63, 17);
		frame.getContentPane().add(lblScoreJ2);

		textFieldScoreJ2 = new JTextField();
		textFieldScoreJ2.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldScoreJ2.setBounds(616, 140, 31, 20);
		frame.getContentPane().add(textFieldScoreJ2);
		textFieldScoreJ2.setColumns(10);

		btnLancerJ2 = new JButton("Lancer");
		btnLancerJ2.setEnabled(false);
		btnLancerJ2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.clickOnLancerJ2();
			}
		});
		btnLancerJ2.setBounds(558, 181, 89, 23);
		frame.getContentPane().add(btnLancerJ2);

		lblDe1 = new JLabel("DE 1");
		lblDe1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDe1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDe1.setBounds(249, 143, 50, 14);
		frame.getContentPane().add(lblDe1);

		imageDe1 = new JLabel();
		imageDe1.setBounds(249, 181, 50, 50);
		frame.getContentPane().add(imageDe1);

		lblDe2 = new JLabel("DE 2");
		lblDe2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDe2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDe2.setBounds(393, 143, 50, 14);
		frame.getContentPane().add(lblDe2);

		imageDe2 = new JLabel();
		imageDe2.setBounds(393, 181, 50, 50);
		frame.getContentPane().add(imageDe2);

		btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.clickOnRestart();
			}
		});
		btnRestart.setBounds(302, 254, 89, 23);
		frame.getContentPane().add(btnRestart);

		lblResult = new JLabel();
		lblResult.setForeground(Color.RED);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblResult.setBounds(249, 95, 194, 37);
		frame.getContentPane().add(lblResult);

		cbJ1 = new JCheckBox("Tricheur");
		cbJ1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.toggleTricheurJ1();
			}
		});
		cbJ1.setBounds(85, 109, 97, 23);
		frame.getContentPane().add(cbJ1);

		cbJ2 = new JCheckBox("Tricheur");
		cbJ2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.toggleTricheurJ2();
			}
		});
		cbJ2.setBounds(574, 111, 77, 23);
		frame.getContentPane().add(cbJ2);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 716, 21);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);

		JMenuItem menuSave = new JMenuItem("Sauvegarder");
		menuSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listener.savePartie();
			}
		});
		mnNewMenu.add(menuSave);

		JMenuItem menuCharger = new JMenuItem("Charger");
		menuCharger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.chargerPartie();
			}
		});
		mnNewMenu.add(menuCharger);

	}

	// Setter
	public void setListener(IHMListener listener) {
		this.listener = listener;
	}

}
