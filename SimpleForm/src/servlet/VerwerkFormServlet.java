package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/frmServlet")
public class VerwerkFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		toonRequestParameters(request, response);
		out.println("</body>");
		out.println("</html>");
	}

	private void toonRequestParameters(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Enumeration<String> e = request.getParameterNames();
		String name = e.nextElement();
		String naamValue = request.getParameter(name);
		name = e.nextElement();
		String geslachtValue = request.getParameter(name);
		name = e.nextElement();
		String cursusValue = request.getParameter(name);
		out.print("Dag " + aanspreking(geslachtValue) + " " + naamValue + ", u koos voor de cursus " + cursusValue);
	}

	private String aanspreking(String geslacht) {
		if (geslacht.equalsIgnoreCase("v")) {
			return "mevrouw";
		} else {
			return "meneer";
		}
	}

}
