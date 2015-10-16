package DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BestellingsItem;

public class bestelDalTest {

	public static void main(String[] args) throws SQLException {
		List<BestellingsItem> list = new ArrayList<BestellingsItem>();
		BestellingsItem b = new BestellingsItem("13", 100, "50");
		BestellingsItem b2 = new BestellingsItem("pid2", 200, "30");
		list.add(b);
		list.add(b2);
		BestelDAL.addBestellingsItem(list);
		
		

	}

}
