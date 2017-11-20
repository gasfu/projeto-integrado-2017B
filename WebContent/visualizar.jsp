<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="templates/head.jsp">
	<c:param name="title" value="Encontre locais - Acesse"/>
</c:import>

<c:import url="templates/header.jsp">
	<c:param name="active" value="show" />
</c:import>

<main class="visibility">
	<div class="search">
		<input class="input" type="text" placeholder="Encontre lugares para avaliar" search-locals-input />
		<button class="button"><img class="icon" src="/acesse/public/icons/search.svg"></button>
	</div>
	<div class="list" app-local-list></div>
</main>

<c:import url="templates/modal.jsp"/>
<c:import url="templates/footer.jsp"/>