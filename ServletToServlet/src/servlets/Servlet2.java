package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet stap2</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Servlet stap2 at " + request.getContextPath() + "</h1>");
		out.println("Naam: " + request.getParameter("txtNaam"));
		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
