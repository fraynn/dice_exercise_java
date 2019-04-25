package exoDice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartieBddUtils {

	private static final String QUERY_SAVE_JOUEUR = "INSERT INTO joueur (nom, score, de_1, de_2, tricheur) VALUES (?, ?, ?, ?, ?);";

	public static void saveJoueur(JoueurBean joueur) throws Exception {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);

			statement = conn.prepareStatement(QUERY_SAVE_JOUEUR, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, joueur.getNom());
			statement.setInt(2, joueur.getScorePartie());
			statement.setInt(3, joueur.getGobelet().getD1().getValeur());
			statement.setInt(4, joueur.getGobelet().getD2().getValeur());
			statement.setBoolean(5, joueur.isTricheur());

			statement.executeUpdate();
			// Returns primary key
			ResultSet key = statement.getGeneratedKeys();
			key.next();
			joueur.setId(key.getLong(1));

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static final String QUERY_SAVE_PARTIE = "INSERT INTO partie (tour, joueur1, joueur2, joueur_courant) VALUES (?, ?, ?, ?);";

	public static void savePartie(PartieBean partie) throws Exception {

		saveJoueur(partie.getJ1());
		saveJoueur(partie.getJ2());

		Connection conn = null;
		PreparedStatement statement = null;

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD);
			statement = conn.prepareStatement(QUERY_SAVE_PARTIE);

			statement.setInt(1, partie.getTour());
			statement.setLong(2, partie.getJ1().getId());
			statement.setLong(3, partie.getJ2().getId());
			statement.setLong(4, partie.getJoueurCourant().getId());

			statement.executeUpdate();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static final String QUERY_LOAD_PARTIE = "SELECT P.tour,P.joueur_courant, P.joueur1 as idJ1, J1.nom AS nomJ1, J1.de_1 as d1J1, J1.de_2 as d2J1, J1.score as scorePartieJ1, J1.tricheur as j1Tricheur, J2.* FROM partie P INNER JOIN joueur J1 on P.joueur1 = J1.id INNER JOIN joueur J2 on P.joueur2 = J2.id ORDER BY P.id DESC LIMIT 1";

	public static PartieBean chargerPartie() throws Exception {
		Connection con = null;
		Statement stmt = null;
		try {
			// Pour travailler avec Tomcat et wamp Rajouter :
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(ConnexionJDBC.URL, ConnexionJDBC.LOGIN, ConnexionJDBC.PASSWORD); // La
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery(QUERY_LOAD_PARTIE);
			if (!rset.next()) {
				throw new Exception("Aucune partie chargée");
			}

			PartieBean partieBean = new PartieBean(rset.getString("nomJ1"), rset.getString("nom"));
			partieBean.setTour(rset.getInt("tour"));
			// joueur 1
			partieBean.getJ1().setId(rset.getLong("idJ1"));
			partieBean.getJ1().getGobelet().getD1().setValeur(rset.getInt("d1j1"));
			partieBean.getJ1().getGobelet().getD2().setValeur(rset.getInt("d2j1"));
			partieBean.getJ1().setScorePartie(rset.getInt("scorePartieJ1"));
			partieBean.getJ1().setTricheur(rset.getBoolean("j1Tricheur"));

			// joueur 2
			partieBean.getJ2().setId(rset.getLong("id"));
			partieBean.getJ2().getGobelet().getD1().setValeur(rset.getInt("de_1"));
			partieBean.getJ2().getGobelet().getD2().setValeur(rset.getInt("de_2"));
			partieBean.getJ2().setScorePartie(rset.getInt("score"));
			partieBean.getJ2().setTricheur(rset.getBoolean("tricheur"));

			// Joueur courant
			long idJoueurCourant = rset.getLong("joueur_courant");
			if (idJoueurCourant == partieBean.getJ1().getId()) {
				partieBean.setJoueurCourant(partieBean.getJ1());
			} else {
				partieBean.setJoueurCourant(partieBean.getJ2());
			}

			return partieBean;
		} finally {
			if (con != null) {// On ferme la connexion
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
