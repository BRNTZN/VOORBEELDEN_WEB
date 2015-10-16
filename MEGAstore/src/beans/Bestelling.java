package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAL.AssortimentDAL;

public class Bestelling implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bestelnr;
	private List<BestellingsItem> list = new ArrayList<BestellingsItem>();
	
	public Bestelling(){
		
	}
	
	public Bestelling(String bestelnr) throws SQLException {
		setBestelnr(bestelnr);
		try{
			setList(AssortimentDAL.getBestellingsItems(bestelnr));
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
	}
	
	public String getBestelnr() {
		return bestelnr;
	}
	public void setBestelnr(String bestelnr) throws SQLException {
		this.bestelnr=bestelnr;
		try{
		setList(AssortimentDAL.getBestellingsItems(bestelnr));
		} catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	public List<BestellingsItem> getList() {
		return list;
	}
	public void setList(List<BestellingsItem> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getBestelnr());
		s.append("<br/>");
		for (BestellingsItem item : list) {
			s.append(item.getProductID());
			s.append("                   ");
			s.append(item.getAantal());
			s.append("<br/>");
		}
		return s.toString();
	}
	
}
