<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MEGAstore: Overzicht bestelling</title>
</head>
<body>

	<img src="megastore.jpg">
	<hr />
	
	<form action="overzicht" method="get">
		<table>
			<tr>
				<th>Nr</th>
				<th>Titel</th>
				<th>Artist</th>
				<th>Eenheidsprijs</th>
				<th>Aantal</th>
				<th>Prijs</th>
			</tr>
			<c:forEach items="${bestellingen}" var="bestelling">
				<tr>
					<td>${bestelling.productID}</td>
					<td>${bestelling.titel}</td>
					<td>${bestelling.artist}</td>
					<td>&euro; <fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${bestelling.eenheidsPrijs}" /></td>
					<td>${bestelling.aantal}</td>
					<td>&euro; <fmt:formatNumber type="number"
							maxFractionDigits="2" minFractionDigits="2"
							value="${bestelling.subtotaal}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><b>Totaal:</b></td>
				<td>&euro; <fmt:formatNumber type="number"
						maxFractionDigits="2" minFractionDigits="2" value="${totaal}" /></td>
			</tr>
			<c:if test="${not empty korting}">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><i>Korting:</i></td>
				<td>&euro; <fmt:formatNumber type="number"
						maxFractionDigits="2" minFractionDigits="2" value="${korting}" /></td>
			</tr>
			</c:if>
			<c:if test="${not empty teBetalen}">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><b>Te betalen:</b></td>
				<td>&euro; <fmt:formatNumber type="number"
						maxFractionDigits="2" minFractionDigits="2" value="${teBetalen}" /></td>
			</tr>
			</c:if>
		</table>
		<hr />
		Promo code: <input type="text" name="promotext">&nbsp;<input
			type="submit" name="submit" value="Get Promo!">
		<c:if test="${not empty waarschuwing}">
			<h3 style="color: red">${waarschuwing}</h3>
		</c:if>
		<hr />
		<input type="submit" name="submit" value="Wijzigen" /> <input
			type="submit" name="submit" value="Bestelling bevestigen" />

			
	</form>
</body>
</html>