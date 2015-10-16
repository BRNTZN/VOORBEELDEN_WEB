package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Promo;
import drivers.MySQLDriverUtil;

public class PromoDAL {

	public static List<Promo> getPromoList() {
		List<Promo> promoList = new ArrayList<Promo>();
		Promo promo = null;
		try {
			MySQLDriverUtil.registreerDriver();
			try (Connection conn = MySQLDriverUtil.getConnection("megastore")) {
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("select * from promos");
				while (rs.next()) {
					promo = new Promo();
					promo.setUniekeCode(rs.getString(1));
					promo.setStartdatum(rs.getString(2));
					promo.setEinddatum(rs.getString(3));
					promo.setMinimumAankoopbedrag(rs.getInt(4));
					promo.setKortingpercentage(rs.getDouble(5));
					promoList.add(promo);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return promoList;
	}

	public static void addPromo(Promo promo) {
		try {
			MySQLDriverUtil.registreerDriver();
			try (Connection conn = MySQLDriverUtil.getConnection("megastore")) {
				PreparedStatement addRecord = conn
						.prepareStatement("insert into promos values (?, ?, ?, ?, ?)");
				addRecord.setString(1, promo.getUniekeCode());
				addRecord.setDate(2, Date.valueOf(promo.getStartdatum()));
				addRecord.setDate(3, Date.valueOf(promo.getEinddatum()));
				addRecord.setInt(4, promo.getMinimumAankoopbedrag());
				addRecord.setDouble(5, promo.getKortingpercentage());
				addRecord.executeUpdate();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static void deletePromo(String unieke_code) {
		try {
			MySQLDriverUtil.registreerDriver();
			try (Connection conn = MySQLDriverUtil.getConnection("megastore")) {
				PreparedStatement deleteRecord = conn
						.prepareStatement("delete from promos where unieke_code = ?");
				deleteRecord.setString(1, unieke_code);
				deleteRecord.executeUpdate();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static List<String> getUniekeCodeList() {
		List<String> uniekeCodeList = new ArrayList<String>();
		String uniekeCode = "";
		try {
			MySQLDriverUtil.registreerDriver();
			try (Connection conn = MySQLDriverUtil.getConnection("megastore")) {
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("select unieke_code from promos");
				while (rs.next()) {
					uniekeCode = "";
					uniekeCode = rs.getString(1);
					uniekeCodeList.add(uniekeCode);
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return uniekeCodeList;
	}
}