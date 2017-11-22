<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="templates/head.jsp">
	<c:param name="title" value="Cadastre um Local"/>
</c:import>

<c:import url="templates/header.jsp">
	<c:param name="active" value="myLocals"/>
</c:import>

<main class="my-locals">
	<h1 class="title">Meus Locais</h1>
	<div class="list" app-user-locals-list></div>	
</main>

<c:import url="templates/footer.jsp"/>