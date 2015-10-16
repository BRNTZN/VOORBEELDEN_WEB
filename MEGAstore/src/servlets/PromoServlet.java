package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAL.PromoDAL;
import beans.Promo;

/**
 * Servlet implementation class Promo
 */
@WebServlet("/promo")
public class PromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PromoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("verwijderpromomeldingstijl",
				"display:none;");

		request.getSession().setAttribute("voegtoepromomeldingstijl",
				"display:none;");

		if (request.getParameter("toevoegen") != null) {
			request.getSession().setAttribute("voegtoepromomeldingstijl",
					"display:inline;");
			if (request.getParameter("unieke_code").matches("[A-Z_0-9]{9}")
					&& request.getParameter("minimum_aankoopbedrag").matches(
							"[0-9]*")
					&& request.getParameter("kortingspercentage").matches(
							"[0-9]{1,2}[.][0-9]{1,2}|100|100[.]0{1,2}|[0-9]{1,2}")
					&& Date.valueOf(request.getParameter("startdatum")).before(
							Date.valueOf(request.getParameter("einddatum"))) == true) {
				// if
				// (request.getParameter("unieke_code").matches("[A-Z_0-9]{9}")
				// && request.getParameter("minimum_aankoopbedrag").matches(
				// "[0-9]*")
				// && request.getParameter("kortingspercentage").matches(
				// "[0-9]{1,2}[.][0-9]{1,2}|100|100[.]0{1,2}")) {
				// if
				// (Date.valueOf(request.getParameter("startdatum")).before(Date.valueOf(request.getParameter("einddatum")))
				// == true) {
				PromoDAL.addPromo(new Promo(
						request.getParameter("unieke_code"), request
								.getParameter("startdatum"), request
								.getParameter("einddatum"),
						Integer.parseInt(request
								.getParameter("minimum_aankoopbedrag")), Double
								.parseDouble(request
										.getParameter("kortingspercentage"))));
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/promotoegevoegdbevestiging.html");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/promotoevoegen.jsp");
				dispatcher.forward(request, response);
			}
		}
		// } else {
		// RequestDispatcher dispatcher = request
		// .getRequestDispatcher("/promotoevoegen.jsp");
		// dispatcher.forward(request, response);
		// }
		// } else {
		// RequestDispatcher dispatcher = request
		// .getRequestDispatcher("/promotoevoegen.jsp");
		// dispatcher.forward(request, response);
		// }
		// }

		if (request.getParameter("verwijderen") != null) {
			request.getSession().setAttribute("verwijderpromomeldingstijl",
					"display:inline;");
			if (!request.getParameter("verwijderpromo").equals("")) {
				if (request.getParameter("verwijderpromo").matches(
						"[A-Z_0-9]{9}")) {
					if (PromoDAL.getUniekeCodeList().contains(
							request.getParameter("verwijderpromo"))) {
						PromoDAL.deletePromo(request
								.getParameter("verwijderpromo"));
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/promoverwijderdbevestiging.html");
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("/promoverwijderen.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/promoverwijderen.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/promoverwijderen.jsp");
				dispatcher.forward(request, response);
			}
		}

		if (request.getParameter("terug") != null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/InternHome.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
