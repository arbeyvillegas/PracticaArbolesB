<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Practica 2 - Arbey Villegas Carvajal</title>
<link href="<c:url value="/resources/css/practica1.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<form:form action="consultarPorFiltro" method="post" commandName="filtro">
		<table>
			<caption>Filtro para consultar</caption>
			<tr>
				<td>
					<form:select path="nombre">
					   <form:options items="${filtroList}"/>
					</form:select>
				</td>
				<td><form:input path="valor"/></td>
				<td><input type="submit" value="Consultar"/> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>