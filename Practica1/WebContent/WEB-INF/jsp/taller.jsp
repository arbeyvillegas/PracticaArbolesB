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
	<form:form action="insertarTaller" method="post" commandName="taller">
		<table class="tableInsert">
			<caption>Insertar Taller</caption>
			<tr>
				<td>C&oacute;digo</td>
				<td><form:input path="codigo" maxlength="3"/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><form:input path="nombre" maxlength="10"/></td>
			</tr>
			<tr>
				<td>Tel&eacute;fono</td>
				<td><form:input path="telefono" maxlength="7"/></td>
			</tr>
			<tr>
				<td>Direcci&oacute;n</td>
				<td><form:input path="direccion" maxlength="20"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Guardar"/> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>