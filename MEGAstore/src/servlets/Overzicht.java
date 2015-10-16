package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAL.BestelDAL;
import DAL.PromoDAL;
import DAO.BestelRow;
import beans.BestellingsItem;
import beans.Promo;

/**
 * Servlet implementation class Overzicht
 */
@WebServlet("/overzicht")
public class Overzicht extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String waarschuwing = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Overzicht() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int teller = 1;
		String nextPage = "/overzicht.jsp";
		String submit = request.getParameter("submit");
		List<BestelRow> bestellingen = null;

		if (submit != null) {

			if (submit.equals("Wijzigen")) {

				nextPage = "/bestellen";

			} else if (submit.equals("Bestelling bevestigen")) {

				List<BestellingsItem> bestellingsItemList = new ArrayList<BestellingsItem>();
				bestellingen = (List<BestelRow>) request.getSession()
						.getAttribute("bestellingen");
				for (BestelRow bestelRow : bestellingen) {
					bestellingsItemList.add(new BestellingsItem(bestelRow
							.getProductID(), bestelRow.getAantal(), String
							.valueOf(teller)));
				}
				teller++;
				try {
					BestelDAL.addBestellingsItem(bestellingsItemList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				nextPage = "/bevestiging.html";
				

			} else if (submit.equals("Get Promo!")) {

				BigDecimal totaal = (BigDecimal) request.getSession()
						.getAttribute("totaal");
				String promoCode = request.getParameter("promotext");
				Promo promo = getPromo(promoCode, totaal);
				if (promo != null) {
					// korting = new BigDecimal(totaal);
					BigDecimal korting = totaal.multiply(new BigDecimal(promo
							.getKortingpercentage()));
					korting = korting.divide(new BigDecimal(100));
					BigDecimal teBetalen = totaal.subtract(korting);
					
					request.getSession().setAttribute("korting", korting);
					request.getSession().setAttribute("teBetalen", teBetalen);
				}
				request.getSession().setAttribute("waarschuwing", waarschuwing);
			}

		}
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

	private Promo getPromo(String promoCode, BigDecimal totaal) {
		List<Promo> promoLijst = PromoDAL.getPromoList();
		for (Promo promo : promoLijst) {
			if (promo.getUniekeCode().equals(promoCode)) {
				if (promo.isActive()) {
					BigDecimal minimumBedrag = new BigDecimal(
							promo.getMinimumAankoopbedrag());
					if (minimumBedrag.compareTo(totaal) < 0) {
						waarschuwing = "";
						return promo;
					} else {
						waarschuwing = "Deze promotie is slechts geldig voor aankopen van meer dan &euro; "
								+ promo.getMinimumAankoopbedrag();
						return null;
					}
				} else {
					waarschuwing = "Deze promotie is niet geldig op dit moment.";
					return null;
				}
			}
		}
		waarschuwing = "Promotie code niet gevonden";
		return null;
	}

}
