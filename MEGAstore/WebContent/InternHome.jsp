<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InternHome</title>
</head>
<body>
	<h1>Welcome, interne gebruiker</h1>
	<form action="InternServlet">
		<h3>CD Beheer</h3>
		<br /> ${melding } <input type="submit" name="zoek" value="zoek">
		<input type="submit" name="toevoegen" value="toevoegen"><br />
		<h3>Bestelling Beheer</h3>
		<br /> <input type="submit" name="annulerenGO" value="annuleren"><br />

		<h3>Promo Beheer</h3>
		<br /> <input type="submit" name="promotoevoegen" value="toevoegen">
		<input type="submit" name="promoverwijderen" value="verwijderen"><br />
		<br />
	</form>
</body>
</html>