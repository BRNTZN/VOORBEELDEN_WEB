package model;

public class Persoon {
	
	private String naam;
	private int id;
	
	public Persoon(String naam, int id) {
		super();
		this.naam = naam;
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
