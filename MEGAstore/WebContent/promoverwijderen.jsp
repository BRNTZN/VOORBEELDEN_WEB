<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Promo verwijderen</title>
</head>
<body>
	<form action="promo" method="get">
		Voer de unieke code in om een promo-code te verwijderen: <input
			type="text" name="verwijderpromo" value="${param.verwijderpromo}" />
		<input type="submit" name="verwijderen" value="verwijderen" /> <input
			type="submit" name="terug" value="terug" />
	</form><br>
	<label style="${verwijderpromomeldingstijl}">
		${promoverwijdermelding}</label>
</body>
</html>