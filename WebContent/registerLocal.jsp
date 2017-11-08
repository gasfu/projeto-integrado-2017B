<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="templates/head.jsp">
	<c:param name="title" value="Cadastre um Local"/>
</c:import>

<c:import url="templates/header.jsp">
	<c:param name="active" value="registerLocal"/>
</c:import>

<main class="register-local">
	<form class="form" app-register-local-action>
		<h2 class="subtitle">Cadastre um Local</h2>
		<div class="alert-wrapper" alert-wrapper></div>	
		<input class="input" type="text" placeholder="nome" name="NAME" />
		<input class="input" type="text" placeholder="endereço" name="ADDRESS" />
		<input class="input" type="text" placeholder="descrição" name="DESCRIPTION" />
		<div class="action">
			<button class="button -default">Cadastrar</button>
		</div>
	</form>
</main>

<c:import url="templates/footer.jsp"/>