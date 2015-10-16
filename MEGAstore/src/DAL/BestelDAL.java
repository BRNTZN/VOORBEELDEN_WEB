package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.Bestelling;
import beans.BestellingsItem;

public class BestelDAL {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/MEGAstore";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public BestelDAL() throws ClassNotFoundException {
		// load the jdbc driver
		Class.forName(JDBC_DRIVER);
	}

	public static void addBestellingsItem(List<BestellingsItem> list)
			throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			for (BestellingsItem b : list) {
				PreparedStatement addRecord = conn
						.prepareStatement("INSERT INTO bestellingen ( "
								+ "bestelnr, id, aantal ) "
								+ "VALUES ( ?, ?, ? )");

				addRecord.setString(1, b.getBestellingsID());
				addRecord.setString(2, b.getProductID());
				addRecord.setInt(3, b.getAantal());

				addRecord.executeUpdate();
			}
		}
	}

	public static void deleteBestellingsItem(String bestelnr)
			throws SQLException {
		try (Connection connection = DriverManager.getConnection(
				CONNECTIONSTRING, USER, PASSWORD)) {
			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM bestellingen WHERE bestelnr=?");
			ps.setString(1, bestelnr);
			ps.executeUpdate();
		}
	}

	public static List<String> getBestelNrs() throws SQLException {
		List<String> bestelNrs = new ArrayList<String>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT bestelnr FROM bestellingen group by bestelnr");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bestelNrs.add(rs.getString(1));
			}
			return bestelNrs;
		}
	}

	// todo
	public static List<String> getBestelNrsNonGeannuleerd() throws SQLException {

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			List<String> alles = getBestelNrs();
			List<String> annul = new ArrayList<String>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT bestelnr FROM bestellingsoverzicht");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				annul.add(rs.getString(1));
			}

			alles.removeAll(annul);
			return alles;
		}
	}

	public static void annuleerBestelling(String bestelnr) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			System.out.println("ANNULATIE");
			PreparedStatement ps = conn
					.prepareStatement("insert into bestellingsoverzicht (bestelnr, annulatiedatum, toestand) VALUES (?, ?, 'geannuleerd')");
			ps.setString(1, bestelnr);
			LocalDate ld = LocalDate.now();
			java.sql.Date date = Date.valueOf(ld);
			ps.setDate(2, date);
			ps.executeUpdate();
		}
	}

	public static int getTeller() throws SQLException {
		int teller = -1;
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT max(bestelnr) from bestellingen");
			ResultSet rs = ps.executeQuery();
			rs.next();
			teller = rs.getInt(1);
		}
		return teller;
	}

	public static Bestelling getBestelling(String bestelnr) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("select id, aantal, bestelnr from bestellingen where bestelnr = ?");
			ps.setString(1, bestelnr);
			ResultSet rs = ps.executeQuery();
			Bestelling b = new Bestelling();
			b.setBestelnr(bestelnr);
			List<BestellingsItem> list = b.getList();
			while (rs.next()) {
				BestellingsItem tmp = new BestellingsItem(rs.getString(1),
						rs.getInt(2), rs.getString(3));
				list.add(tmp);
			}
			b.setList(list);
			return b;
		}
	}

}
