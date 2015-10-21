<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- dit is html commentaar (wordt naar de client gestuurd) -->
	<%-- dit is jsp commentaar (wordt niet naar de client gestuurd) --%>
	<%
		// in dit blok mag je java-code schrijven
		// deze code wordt eerst uitgevoerd door de server, het resultaat wordt naar de client gestuurd
	%>
	<%=request.getParameter("woord")%>
</body>
</html>