package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * request
		 */
		if (request.getAttribute("req")==null) {
			request.setAttribute("req", 1);
		}else{
			request.setAttribute("req", (int)request.getAttribute("req")+1); 
		}
		/**
		 * session
		 */
		if (request.getSession().getAttribute("ses")==null) {
			request.getSession().setAttribute("ses", 1);
		}else{
			request.getSession().setAttribute("ses", (int)request.getSession().getAttribute("ses")+1); 
		}
		/**
		 * context
		 */
		if (getServletContext().getAttribute("con")==null) {
			getServletContext().setAttribute("con", 1);
		}else{
			getServletContext().setAttribute("con", (int)getServletContext().getAttribute("con")+1); 
		}
		request.getRequestDispatcher("/scope.jsp").forward(request, response);
	}
}
