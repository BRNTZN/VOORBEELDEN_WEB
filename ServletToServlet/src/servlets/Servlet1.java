package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (request.getParameter("btnVerzenden") != null) {
			//response.sendRedirect(response.encodeRedirectURL("Servlet2?txtNaam="
					//+ request.getParameter("txtNaam")));
			/**
			 * zelfde als:
			 */
			RequestDispatcher dispatcher = request.getRequestDispatcher("Servlet2");
			dispatcher.forward(request, response);
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet1</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Servlet1 at " + request.getContextPath()
				+ "</h1>");
		out.println("<form action='Servlet1' method='get'>");
		out.println("Naam: <input type='text' name='txtNaam' /><br />");
		out.println("<input type='submit' name='btnVerzenden' value='verzenden' />");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
