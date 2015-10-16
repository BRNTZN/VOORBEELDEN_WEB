package DAL;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import beans.BestellingsItem;
import beans.CD;

public class AssortimentDAL {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/MEGAstore";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public AssortimentDAL() throws ClassNotFoundException {
		// load the jdbc driver
		Class.forName(JDBC_DRIVER);
	}

	public static List<CD> getCDList() throws SQLException {
		List<CD> cdList = new ArrayList<CD>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM CD");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CD cd = new CD();
				cd.setTitel(rs.getString(2));
				cd.setArtiest(rs.getString(3));
				cd.setPrijs(rs.getString(4));
				cd.setiD(rs.getString(1));
				cdList.add(cd);
			}
		}
		return cdList;
	}

	public static CD getCD(int id) throws SQLException {
		CD cd = new CD();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM CD WHERE nr =?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cd.setiD(rs.getString(1));
			cd.setTitel(rs.getString(2));
			cd.setArtiest(rs.getString(3));
			cd.setPrijs(rs.getString(4));

		}

		return cd;
	}

	public static void addCD(CD cd) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO CD (titel, artiest, prijs) VALUES (?, ?, ?)");
			ps.setString(1, cd.getTitel());
			ps.setString(2, cd.getArtiest());
			ps.setBigDecimal(3, cd.getPrijs());
			ps.executeUpdate();
		}
	}

	public static void deleteCD(String id) throws SQLException {
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM CD WHERE nr=?");
			int idint = Integer.parseInt(id);
			ps.setInt(1, idint);
			ps.executeUpdate();
		}
	}

	public static void modifyCD(String id, String titel, String artiest,
			BigDecimal prijs) throws SQLException {
		System.out.println("in update van cd");
		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE CD SET titel=?, artiest=?, prijs=? WHERE nr=?");
			ps.setString(4, id);
			ps.setString(1, titel);
			ps.setString(2, artiest);
			ps.setBigDecimal(3, prijs);
			ps.executeUpdate();
		}
	}

	public static List<CD> zoekCDViaTitel(String titelstuk) throws SQLException {
		List<CD> list = new ArrayList<CD>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM CD WHERE titel like ?");
			ps.setString(1, "%" + titelstuk + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CD cd = new CD();
				cd.setiD(rs.getString(1));
				cd.setTitel(rs.getString(2));
				cd.setArtiest(rs.getString(3));
				cd.setPrijs(rs.getString(4));
				list.add(cd);
			}

		}
		return list;
	}

	public static List<CD> zoekCDViaArtiest(String artieststuk)
			throws SQLException {
		List<CD> list = new ArrayList<CD>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM CD WHERE artiest like ?");
			ps.setString(1, "%" + artieststuk + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CD cd = new CD();
				cd.setiD(rs.getString(1));
				cd.setTitel(rs.getString(2));
				cd.setArtiest(rs.getString(3));
				cd.setPrijs(rs.getString(4));
				list.add(cd);
			}

		}
		return list;
	}

	public static List<CD> zoekCDViaOndergrens(BigDecimal prijs)
			throws SQLException {
		List<CD> list = new ArrayList<CD>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM CD WHERE prijs >= ?");
			ps.setBigDecimal(1, prijs);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CD cd = new CD();
				cd.setiD(rs.getString(1));
				cd.setTitel(rs.getString(2));
				cd.setArtiest(rs.getString(3));
				cd.setPrijs(rs.getString(4));
				list.add(cd);
			}

		}
		return list;
	}

	public static List<CD> zoekCDViaBovengrens(BigDecimal prijs)
			throws SQLException {
		List<CD> list = new ArrayList<CD>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM CD WHERE prijs <= ?");
			ps.setBigDecimal(1, prijs);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CD cd = new CD();
				cd.setiD(rs.getString(1));
				cd.setTitel(rs.getString(2));
				cd.setArtiest(rs.getString(3));
				cd.setPrijs(rs.getString(4));
				list.add(cd);
			}
			return list;
		}
	}

	public static List<CD> zoekViaAlles(String titel, String artiest,
			BigDecimal minprijs, BigDecimal maxprijs) throws SQLException {
		List<CD> titellist = new ArrayList<CD>();
		List<CD> artiestlist = new ArrayList<CD>();
		List<CD> minprijslist = new ArrayList<CD>();
		List<CD> maxprijslist = new ArrayList<CD>();
		titellist = zoekCDViaTitel(titel);
		artiestlist = zoekCDViaArtiest(artiest);
		minprijslist = zoekCDViaOndergrens(minprijs);
		maxprijslist = zoekCDViaBovengrens(maxprijs);

		Set<CD> s = new TreeSet<CD>();

		s.addAll(titellist);
		s.addAll(artiestlist);
		s.addAll(minprijslist);
		s.addAll(maxprijslist);
		if (!titel.equals("")) {
			s.retainAll(titellist);
		}
		if (!artiest.equals("")) {
			s.retainAll(artiestlist);
		}
		if (minprijs != null) {
			s.retainAll(minprijslist);
		}
		if (maxprijs != null) {
			s.retainAll(maxprijslist);
		}
		return new ArrayList<CD>(s);
	}

	public static List<BestellingsItem> getBestellingsItems(String bestelNr)
			throws SQLException {
		List<BestellingsItem> items = new ArrayList<BestellingsItem>();

		try (Connection conn = DriverManager.getConnection(CONNECTIONSTRING,
				USER, PASSWORD)) {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM bestellingen where bestelnr =?");
			ps.setString(1, bestelNr);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				BestellingsItem tmp = new BestellingsItem(rs.getString(2),
						rs.getInt(3), rs.getString(1));
				items.add(tmp);
			}

			return items;
		}

	}
}
