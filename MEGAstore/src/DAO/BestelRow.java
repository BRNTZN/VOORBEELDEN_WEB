package DAO;

import java.math.BigDecimal;

import beans.CD;

public class BestelRow {

	private boolean update = false;
	private int aantal;
	private BigDecimal eenheidsPrijs;
	private String productID, titel, artist;

	public BestelRow() {

	}

	public BestelRow(int aantal, CD cD) {
		setAantal(aantal);
		setCD(cD);
	}

	public BigDecimal getEenheidsPrijs() {
		return eenheidsPrijs;
	}

	public void setEenheidsPrijs(BigDecimal eenheidsPrijs) {
		this.eenheidsPrijs = eenheidsPrijs;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public BigDecimal getSubtotaal() {
		return eenheidsPrijs.multiply(new BigDecimal(aantal));
	}

	public void setCD(CD cD) {
		setProductID(cD.getiD());
		setTitel(cD.getTitel());
		setArtist(cD.getArtiest());
		setEenheidsPrijs(cD.getPrijs());
	}

}
