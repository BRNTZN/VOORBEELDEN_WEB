package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAL.AssortimentDAL;
import DAL.BestelDAL;
import beans.Bestelling;
import beans.CD;

@WebServlet("/InternServlet")
public class InternServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String link = "InternHome.jsp";

		// -->internhome
		if (request.getParameter("zoek") != null) {
			link = "Zoek.jsp";
		}

		// -->zoek
		if (request.getParameter("sbmtZoek") != null) {
			link = "Zoek.jsp";
			String titel = request.getParameter("titel");
			String artiest = request.getParameter("artiest");
			BigDecimal minprijs = null;
			if (!request.getParameter("minprijs").equals("")) {
				try {
					minprijs = new BigDecimal(request.getParameter("minprijs"));
				} catch (Exception e) {
					request.setAttribute("minprijsmelding",
							"Geef een geldige prijs in, groter dan of gelijk aan nul");
				}
				if (minprijs.compareTo(new BigDecimal(0)) < 0) {
					request.setAttribute("minprijsmelding",
							"Geef een geldige prijs in, groter dan of gelijk aan nul");
				}
			}
			BigDecimal maxprijs = null;
			if (!request.getParameter("maxprijs").equals("")) {
				try {
					minprijs = new BigDecimal(request.getParameter("maxprijs"));
				} catch (Exception e) {
					request.setAttribute("maxprijsmelding",
							"Geef een geldige prijs in, groter dan of gelijk aan nul");
				}
				if (minprijs.compareTo(new BigDecimal(0)) < 0) {
					request.setAttribute("maxprijsmelding",
							"Geef een geldige prijs in, groter dan of gelijk aan nul");
				}
			}
			try {
				List<CD> list = AssortimentDAL.zoekViaAlles(titel, artiest,
						minprijs, maxprijs);
				request.getSession().setAttribute("zoeklist", list);
			} catch (SQLException ex) {
				request.setAttribute("melding",
						"Databank kon niet bereikt worden");
			}
		}

		// -->internhome
		if (request.getParameter("toevoegen") != null) {
			link = "Toevoegen.jsp";
		}

		// -->toevoegen
		if (request.getParameter("sbmtToevoegen") != null) {
			CD cd = new CD();

			if (request.getParameter("artiest").equals("")
					|| request.getParameter("prijs").equals("")
					|| request.getParameter("titel").equals("")) {
				request.setAttribute("veldmelding",
						"Alle velden dienen ingevuld te worden");
				link = "Toevoegen.jsp";
			} else {
				BigDecimal bigD = new BigDecimal(request.getParameter("prijs"));
				if (bigD.compareTo(new BigDecimal(0)) <= 0) {
					request.setAttribute("prijsmelding",
							"Voer een geldig bedrag in groter dan nul");
					link = "Toevoegen.jsp";
				}

				cd.setArtiest(request.getParameter("artiest"));
				cd.setPrijs(request.getParameter("prijs"));
				cd.setTitel(request.getParameter("titel"));
				try {
					AssortimentDAL.addCD(cd);
				} catch (SQLException e) {
					request.setAttribute("melding",
							"Databank kon niet bereikt worden");
					link = "Toevoegen.jsp";
				}
				link = "Confirmatie.jsp";
				request.setAttribute("confirmatie", "toegevoegd");
			}
		}

		// -->Zoek
		if (request.getParameter("wijzig") != null) {
			int id = Integer.parseInt(request.getParameter("wijzig"));
			try {
				request.getSession().setAttribute("cd",
						AssortimentDAL.getCD(id));
				link = "Wijzig.jsp";
			} catch (SQLException e) {
				request.setAttribute("melding",
						"Databank kon niet bereikt worden");
				link = "Zoek.jsp";
			}
		}

		// -->wijzig
		if (request.getParameter("sbmtwijzig") != null) {
			String titel = request.getParameter("titel");
			String artiest = request.getParameter("artiest");
			BigDecimal prijs = new BigDecimal(request.getParameter("prijs"));
			String id = ((CD) request.getSession().getAttribute("cd")).getiD();
			try {
				AssortimentDAL.modifyCD(id, titel, artiest, prijs);
			} catch (SQLException e) {
				request.setAttribute("melding",
						"Databank kon niet bereikt worden");
				link = "Wijzig.jsp";
			}
			link = "Confirmatie.jsp";
			request.setAttribute("confirmatie", "gewijzigd");
			request.getSession().setAttribute("zoeklist", null);
		}

		// --> zoek
		if (request.getParameter("delete") != null) {
			String id = request.getParameter("delete");
			try {
				AssortimentDAL.deleteCD(id);
			} catch (SQLException e) {
				request.setAttribute("melding",
						"Databank kon niet bereikt worden");
				link = "Zoek.jsp";
			}
			link = "Confirmatie.jsp";
			request.setAttribute("confirmatie", "verwijderd");
			request.getSession().setAttribute("zoeklist", null);
		}

		// -->internhome
		if (request.getParameter("annulerenGO") != null) {
			link = "Bestellingen.jsp";
			try {
				List<String> bestelNrs = BestelDAL.getBestelNrsNonGeannuleerd();
				List<Bestelling> bestellijst = new ArrayList<Bestelling>();
				for (String string : bestelNrs) {
					Bestelling tmp = BestelDAL.getBestelling(string);
					bestellijst.add(tmp);
				}
				request.getSession().setAttribute("bestellijst", bestellijst);
			} catch (SQLException ex) {
				request.setAttribute("melding",
						"Databank kon niet bereikt worden");
			}
		}
		// -->bestellingen
		if (request.getParameter("annuleer") != null) {
			try {
				BestelDAL.annuleerBestelling(request.getParameter("annuleer"));
			} catch (SQLException ex) {
				request.setAttribute("melding",
						"Databank kon niet bereikt worden");
			}
			link = "BestellingConfirmatie.jsp";
		}
		
//		-->internhome
		if (request.getParameter("promotoevoegen") != null) {
			link = "promotoevoegen.jsp";
		}
		
//		-->internhome
		if (request.getParameter("promoverwijderen") != null) {
			link = "promoverwijderen.jsp";
		}
		
//		FORWARD ANY
		RequestDispatcher dispatcher = request.getRequestDispatcher(link);
		dispatcher.forward(request, response);
	}
	
}
