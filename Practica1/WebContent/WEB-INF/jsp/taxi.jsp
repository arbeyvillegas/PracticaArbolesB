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
	<form:form action="insertarTaxi" method="post" commandName="taxi">
		<table class="tableInsert">
			<caption>Insertar taxi</caption>
			<tr>
				<td>N&uacute;mero</td>
				<td><form:input path="numeroTaxi" maxlength="3"/></td>
			</tr>
			<tr>
				<td>Placa</td>
				<td><form:input path="placa" maxlength="7"/></td>
			</tr>
			<tr>
				<td>Modelo</td>
				<td><form:input path="modelo" maxlength="4"/></td>
			</tr>
			<tr>
				<td>Marca</td>
				<td><form:input path="marca" maxlength="20"/></td>
			</tr>
			<tr>
				<td>Propietario</td>
				<td>
					<!-- <form:input path="propietario" maxlength="8"/> -->
					<form:select path="propietario">
					   <form:options items="${identificaciones}" itemLabel="valor" itemValue="codigo"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Conductor</td>
				<td>
					<!--<form:input path="conductor" maxlength="8"/>-->
					<form:select path="conductor">
					   <form:options items="${identificaciones}" itemLabel="valor" itemValue="codigo"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Taller</td>
				<td>
					<!--<form:input path="taller"/>-->
					<form:select path="taller">
					   <form:options items="${talleres}" itemLabel="valor" itemValue="codigo"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Guardar"/> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>