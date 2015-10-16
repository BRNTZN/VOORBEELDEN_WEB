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
<form action="InternServlet">
titel
<input type="text" name="titel"><br/>
artiest
<input type="text" name="artiest"><br/>
minprijs
<input type="text" name="minprijs">${minprijsmelding }<br/>
maxprijs
<input type="text" name="maxprijs">${maxprijsmelding }<br/>
<input type="submit" name="sbmtZoek"><br/>
${melding }
<br>

	<c:forEach var="item" items="${zoeklist}">
		<c:out value="${item }" /> <button type="submit" name="wijzig" value="${item.iD }">wijzig</button><button type="submit" name="delete" value="${item.iD }">delete</button><br/>
	</c:forEach>
</form>	
</body>
</html>