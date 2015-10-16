<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Alle bestellingen:</h1>
	${melding }
	<c:forEach var="items" items="${bestellijst}">
	<c:out value="${items.bestelnr }"></c:out>
	<br/>
	<c:forEach var="item" items="${items.list }">
	<c:out value="${item }" /><br/>
	</c:forEach>
		<button type="submit" name="annuleer" value="${item.bestelnr }">annuleer</button>
		<br />
	</c:forEach>
</body>
</html>