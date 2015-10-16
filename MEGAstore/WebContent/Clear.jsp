<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DANGERZONE</title>
<style type="text/css">
#danger {
color: #FFFFFF;
background-color: #FF0000; 
font-weight: bold;
font-size: 2000%;
}

#danger:hover{
cursor: pointer;
font-size: 4000%;
}

</style>
</head>
<body>
<h1>BENT U WEL ZEKER?</h1>
<h1>ALLE GEANNULEERDE BESTELLINGEN WORDEN VERWIJDERD!!!</h1>
${melding }
<form action="">
<input type="submit" name="nietzeker" value="neen">
<button type="submit" name="welzeker" value="JA" id="danger">JA</button>
</form>
</body>
</html>