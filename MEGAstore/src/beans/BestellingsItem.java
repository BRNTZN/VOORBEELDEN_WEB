package beans;

public class BestellingsItem {
	
	private String productID;
	private int aantal;
	private String bestellingsID;
	
	public BestellingsItem() {

	}

	public BestellingsItem(String productID, int aantal, String bestellingsID) {
		setProductID(productID);
		setAantal(aantal);
		setBestellingsID(bestellingsID);
	}

	public String getProductID() {
		return productID;
	}
	
	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public int getAantal() {
		return aantal;
	}
	
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	
	public String getBestellingsID() {
		return bestellingsID;
	}
	
	public void setBestellingsID(String bestellingsID) {
		this.bestellingsID = bestellingsID;
	}

	@Override
	public String toString() {
		return "BestellingsItem [productID=" + productID + ", aantal=" + aantal
				+ ", bestellingsID=" + bestellingsID + "]";
	}
	
	
	
}