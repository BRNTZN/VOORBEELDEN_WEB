package listeners;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import DAL.AssortimentDAL;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener, HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public Listener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {

		String promoverwijdermelding = "Gelieve een correcte promo-code in te voeren";
		sce.getServletContext().setAttribute("promoverwijdermelding", promoverwijdermelding);
		
		String promotoevoegenmelding = "Gelieve het formaat om een promo-code toe te voegen correct in te vullen";
		sce.getServletContext().setAttribute("promotoevoegenmelding", promotoevoegenmelding);
		
		
		if (sce.getServletContext().getAttribute("AssortimentDAL") == null) {
			try {
				sce.getServletContext().setAttribute("AssortimentDAL",
						AssortimentDAL.getCDList());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("verwijderpromomeldingstijl",
				"display:none;");

		se.getSession().setAttribute("voegtoepromomeldingstijl",
				"display:none;");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

}
