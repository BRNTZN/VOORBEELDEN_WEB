package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("setcookie") != null) {
			Cookie cookie = new Cookie("LearningJava", "Cookies!");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			out.println("<html><body><h1>Cookie Set...</h1>");
		} else {
			out.println("<html><body>");
			Cookie[] cookies = request.getCookies();
			if (cookies == null || cookies.length == 0)
				out.println("<h1>No cookies found...</h1>");
			else {
				for (int i = 0; i < cookies.length; i++) {
					out.println("<h1>Name: " + cookies[i].getName() + "<br/>"
							+ "Value: " + cookies[i].getValue() + "</h1>");
				}
			}
			out.println("<p><a href='" + request.getRequestURI()
					+ "?setcookie=true'>"
					+ "Reset the Learning Java cookie.</a>");
		}
		out.println("</body></html>");
		out.close();
	}

}
