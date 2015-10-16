<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CD toevoegen</title>
</head>
<body>
<form action="InternServlet">
<input type="text" name="home" value="home"><br/>
titel
<input type="text" name="titel">
artiest
<input type="text" name="artiest">
prijs
<input type="text" name="prijs"> ${prijsmelding }
<input type="submit" name="sbmtToevoegen">
</form>
${melding }
</body>
</html>