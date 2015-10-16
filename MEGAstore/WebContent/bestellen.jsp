<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MEGAstore</title>
</head>
<body>

	<img src="megastore.jpg">
	<hr />

	<form action="bestellen">

		<select name="productID">
			<c:forEach items="${productenlijst}" var="product">
				<option value="<c:out value="${product.iD}" />"><c:out
						value="${product.titel}" /> /
					<c:out value="${product.artiest}" /> (&euro;
					<fmt:formatNumber type="number" maxFractionDigits="2"
						minFractionDigits="2" value="${product.prijs}" />)
				</option>
			</c:forEach>
		</select> <input type="text" name="aantal" /> <input type="submit"
			name="submit" value="Toevoegen" />

		<c:if test="${not empty waarschuwing}">
			<hr />
			<h3 style="color: red">${waarschuwing}</h3>
		</c:if>

		<hr />

		<c:if test="${not empty bestellingen}">
			<table>
				<tr>
					<th>Nr</th>
					<th>Titel</th>
					<th>Artist</th>
					<th>Eenheidsprijs</th>
					<th>Aantal</th>
					<th>Prijs</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${bestellingen}" var="bestelling">
					<tr>
						<td><c:out value="${bestelling.productID}" /></td>
						<td><c:out value="${bestelling.titel}" /></td>
						<td><c:out value="${bestelling.artist}" /></td>
						<td>&euro; <fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2"
								value="${bestelling.eenheidsPrijs}" /></td>
						<td><c:choose>
								<c:when test="${!bestelling.update}">
									<c:out value="${bestelling.aantal}" />
								</c:when>
								<c:otherwise>
									<input type="text" name="Update_Value_${bestelling.productID}"
										value="${bestelling.aantal}"
										onkeydown="if (event.keyCode == 13) return false;" />
								</c:otherwise>
							</c:choose></td>
						<td>&euro; <fmt:formatNumber type="number"
								maxFractionDigits="2" minFractionDigits="2"
								value="${bestelling.subtotaal}" /></td>
						<td><c:choose>
								<c:when test="${!bestelling.update}">
									<button type="submit" name="submit"
										value="Pas_Aan_${bestelling.productID}">Aanpassen</button>
								</c:when>
								<c:otherwise>
									<button type="submit" name="submit"
										id="Update_${bestelling.productID}"
										value="Update_${bestelling.productID}">Update</button>
								</c:otherwise>
							</c:choose></td>
						<td><button type="submit" name="submit"
								value="Verwijder_${bestelling.productID}">Verwijderen</button></td>
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
					<td></td>
					<td></td>
				</tr>

			</table>
			<hr />

			<input type="submit" name="submit" value="Bestellen" />

		</c:if>

	</form>
</body>
</html>