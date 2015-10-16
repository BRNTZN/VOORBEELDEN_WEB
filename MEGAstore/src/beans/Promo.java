package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Promo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uniekeCode;
	private String startdatum;
	private String einddatum;
	private int minimumAankoopbedrag;
	private double kortingpercentage;

	public Promo() {

	}

	public Promo(String uniekeCode, String startdatum, String einddatum,
			int minimumAankoopbedrag, double kortingpercentage) {
		super();
		setUniekeCode(uniekeCode);
		setStartdatum(startdatum);
		setEinddatum(einddatum);
		setMinimumAankoopbedrag(minimumAankoopbedrag);
		setKortingpercentage(kortingpercentage);
	}

	public String getUniekeCode() {
		return uniekeCode;
	}

	public void setUniekeCode(String uniekeCode) {
		this.uniekeCode = uniekeCode;
	}

	public String getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(String startdatum) {
		this.startdatum = startdatum;
	}

	public String getEinddatum() {
		return einddatum;
	}

	public void setEinddatum(String einddatum) {
		this.einddatum = einddatum;
	}

	public int getMinimumAankoopbedrag() {
		return minimumAankoopbedrag;
	}

	public void setMinimumAankoopbedrag(int minimumAankoopbedrag) {
		this.minimumAankoopbedrag = minimumAankoopbedrag;
	}

	public double getKortingpercentage() {
		return kortingpercentage;
	}

	public void setKortingpercentage(double kortingpercentage) {
		this.kortingpercentage = kortingpercentage;
	}

	public String getResterendeDagen() {
		LocalDate startdatum = LocalDate.parse(getStartdatum());
		LocalDate einddatum = LocalDate.parse(getEinddatum());
		return "" + startdatum.until(einddatum, ChronoUnit.DAYS);
	}

	public boolean isActive() {
		if ((LocalDate.now().isBefore(LocalDate.parse(getEinddatum())))
				&& (LocalDate.now().isAfter(LocalDate.parse(getStartdatum())))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return String
				.format("De promo-code: %1$s%nbegint op datum %2$s en eindigt op datum %3$s. De promo-code binnen %4$s dagen.%nHet minimum aankoopbedrag moet %5$s zijn en u krijgt dan een kortingspercentage van %6$s.",
						getUniekeCode(), getStartdatum(), getEinddatum(),
						getResterendeDagen(), getMinimumAankoopbedrag(),
						getKortingpercentage());
	}
}