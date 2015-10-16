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

import beans.CD;
import DAL.AssortimentDAL;
import DAL.BestelDAL;
import DAO.BestelRow;

/**
 * Servlet implementation class bestellen
 */
@WebServlet("/bestellen")
public class Bestellen extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Bestellen() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nextPage = "/bestellen.jsp";
		String waarschuwing = "";
		List<CD> productenlijst = null;

		BigDecimal totaal = (BigDecimal) request.getSession().getAttribute("totaal");
		totaal = (totaal == null) ? new BigDecimal(0) : totaal;

		List<BestelRow> bestellingen = (List<BestelRow>) request.getSession().getAttribute("bestellingen");
		bestellingen = (bestellingen == null) ? new ArrayList<BestelRow>() : bestellingen;
		
		try {
			productenlijst = AssortimentDAL.getCDList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String submit = request.getParameter("submit");

		if (submit != null) {

			if (submit.equals("Toevoegen")) {
				try {
					int aantal = Integer.parseInt(request
							.getParameter("aantal"));
					if (aantal > 0) {
						String productID = request.getParameter("productID");

						BestelRow bestelling = findBestelRow(bestellingen,
								productID);

						if (bestelling != null) {
							totaal = totaal.subtract(bestelling.getSubtotaal());
							bestelling.setAantal(aantal
									+ bestelling.getAantal());
							totaal = totaal.add(bestelling.getSubtotaal());
						} else {
							for (CD cd : productenlijst) {
								if (cd.getiD().equals(productID)) {
									BestelRow bestellingsItem = new BestelRow(
											aantal, cd);
									totaal = totaal.add(bestellingsItem
											.getSubtotaal());
									bestellingen.add(bestellingsItem);
								}
							}

						}
					} else {
						waarschuwing = "Geef een getal dat groter is dan 0.";
					}
				} catch (NumberFormatException e) {
					waarschuwing = "Gelieve een geheel getal in te voeren.";
				}

			} else if (submit.startsWith("Verwijder_")) {

				String productID = submit.replace("Verwijder_", "");
				BestelRow toRemove = findBestelRow(bestellingen, productID);
				bestellingen.remove(toRemove);
				totaal = totaal.subtract(toRemove.getSubtotaal());

			} else if (submit.startsWith("Pas_Aan_")) {

				String productID = submit.replace("Pas_Aan_", "");
				findBestelRow(bestellingen, productID).setUpdate(true);

			} else if (submit.startsWith("Update_")) {

				String productID = submit.replace("Update_", "");
				BestelRow bestelling = findBestelRow(bestellingen, productID);

				try {
					int nieuwAantal = Integer.parseInt(request
							.getParameter("Update_Value_" + productID));
					if (nieuwAantal > 0) {
						totaal = totaal.subtract(bestelling.getSubtotaal());
						bestelling.setAantal(nieuwAantal);
						bestelling.setUpdate(false);
						totaal = totaal.add(bestelling.getSubtotaal());
					} else if (nieuwAantal == 0) {
						totaal = totaal.subtract(bestelling.getSubtotaal());
						bestellingen.remove(bestelling);
					} else {
						waarschuwing = "Geef een getal dat groter is dan 0.";
					}
				} catch (NumberFormatException e) {
					waarschuwing = "Gelieve een geheel getal in te voeren.";
				}

			} else if (submit.equals("Bestellen")) {
				nextPage = "/overzicht.jsp";
			}

		}

		request.getSession().setAttribute("productenlijst", productenlijst);
		request.getSession().setAttribute("bestellingen", bestellingen);
		request.getSession().setAttribute("waarschuwing", waarschuwing);
		
		try {
			int teller = BestelDAL.getTeller()+1;
			if (teller%10==0) {
				totaal = totaal.multiply(new BigDecimal(0.9));
			}
			if (teller%100==0) {
				totaal = BigDecimal.ZERO;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.getSession().setAttribute("totaal", totaal);

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private BestelRow findBestelRow(List<BestelRow> bestellingen, String id) {

		for (BestelRow bestelling : bestellingen) {
			if (bestelling.getProductID().equals(id)) {
				return bestelling;
			}
		}
		
		return null;

	}

}