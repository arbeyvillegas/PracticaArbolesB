<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Practica 1 - Arbey Villegas Carvajal</title>
<link href="<c:url value="/resources/css/practica1.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<table border="1" style="min-width: 500px;">
		<caption>Listado de taxis</caption>
		<tr>
			<th>N&uacute;mero taxi</th>
			<th>Placa</th>
			<th>Propietario</th>
			<th>Conductor</th>
		<tr>
		<c:forEach var="listValue" items="${list}">
			<tr>
				<td class="numero">
					<c:out value="${listValue.getNumeroTaxi()}"/>
				</td>
				<td >
					<c:out value="${listValue.getPlaca()}"/>
				</td>
				<td>
					<c:out value="${listValue.getNombrePropietario()}"/>
				</td>
				<td>
					<c:out value="${listValue.getNombreConductor()}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h1><c:out value="${message}"></c:out></h1>
</body>
</html>