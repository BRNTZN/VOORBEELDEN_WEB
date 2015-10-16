<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="InternServlet">
<input type="text" name="titel" value="${cd.titel }">
<input type="text" name="artiest" value="${cd.artiest }">
<input type="text" name="prijs" value="${cd.prijs }">
<input type="submit" name="sbmtwijzig">
</form>
${melding }
</body>
</html>