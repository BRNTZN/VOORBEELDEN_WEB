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
<c:if test="${not empty lijst }">

<c:forEach items="${lijst }" var="element">${element }</c:forEach><br>

</c:if>

<c:choose>
<c:when test="${not empty lijst }">Er zit iets in de lijst</c:when>
<c:otherwise>Er zit niets in de lijst</c:otherwise>
</c:choose>
<br>
<c:if test="${boolean }">
test = true
</c:if>
</body>
</html>