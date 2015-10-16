<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Promo toevoegen</title>
</head>
<body>
	<form action="promo" method="get">
		Voeg een promo-code toe door deze gegevens in te vullen:<br> <label
			for="unieke_code">Geef een unieke code </label><input type="text"
			name="unieke_code" value="${param.unieke_code}" placeholder="combinatie 9 cijfers en letter" id="unieke_code" /><br>
		<label for="startdatum">Startdatum waarop promo-actie begint </label><input
			type="text" name="startdatum" value="${param.startdatum}"
			placeholder="yyyy-mm-dd" id="startdatum" /><br> <label
			for="einddatum">Einddatum waarop promo-actie vervalt </label><input
			type="text" name="einddatum" value="${param.einddatum}"
			placeholder="yyyy-mm-dd" id="einddatum" /><br> <label
			for="minimum_aankoopbedrag">Het minimum aankoopbedrag om
			korting te krijgen </label><input type="text" name="minimum_aankoopbedrag"
			value="${param.minimum_aankoopbedrag}" placeholder="bv. 100" id="minimum_aankoopbedrag" /><br>
		<label for="kortingspercentage">Het kortingspercentage op het
			aankoopbedrag </label><input type="text" name="kortingspercentage"
			value="${param.kortingspercentage}" placeholder="bv. 12.50" id="kortingspercentage" /><br>
		<input type="submit" name="toevoegen" value="toevoegen" /> <input
			type="submit" name="terug" value="terug" />
	</form>
	<label style="${voegtoepromomeldingstijl}">
		${promotoevoegenmelding}</label>
</body>
</html>