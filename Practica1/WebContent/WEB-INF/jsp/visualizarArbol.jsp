<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Practica 2 - Arbey Villegas Carvajal</title>
<link href="<c:url value="/resources/css/practica1.css" />"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="menu.jsp" />
	<h1>
		Indices para <c:out value="${tipo}"></c:out>
	</h1>
	<p>
		El &aacute;rbol mostrado como xml es recorrido en pre-orden,por lo que la ra&iacute;z se encuentra externamente, el hijo izquierdo se imprime despu&eacute;s y luego el hijo derecho 
	</p>
	<pre class="brush:xml;">
		<c:out value="${xml}"></c:out>
	</pre>
</body>
</html>