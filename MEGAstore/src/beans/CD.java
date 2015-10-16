package beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class CD implements Serializable, Comparable<CD> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iD, titel, artiest;

	private BigDecimal prijs;

	public CD() {

	}

	public CD(String iD, String titel, String artiest, BigDecimal prijs) {
		setiD(iD);
		setTitel(titel);
		setArtiest(artiest);
		setPrijs(prijs);
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getArtiest() {
		return artiest;
	}

	public void setArtiest(String artiest) {
		this.artiest = artiest;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public void setPrijs(String prijs) {
		this.prijs = new BigDecimal(prijs);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iD == null) ? 0 : iD.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		if (iD == null) {
			if (other.iD != null)
				return false;
		} else if (!iD.equals(other.iD))
			return false;
		return true;
	}

	@Override
	public int compareTo(CD cd) {
		return getiD().compareTo(cd.getiD());
	}

	@Override
	public String toString() {
		return getiD() + ". " + getTitel() + " | " + getArtiest() + " (€ "
				+ getPrijs() + ")";
	}

}