<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<form:form action="insertarIdentificacion" method="post" commandName="identificacion">
		<table class="tableInsert">
			<caption>Insertar identificaci&oacute;n</caption>
			<tr>
				<td>C&eacute;dula</td>
				<td><form:input path="cedula" maxlength="8"/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><form:input path="nombre" maxlength="30"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Guardar"/> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>