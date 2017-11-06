<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="templates/head.jsp">
	<c:param name="title" value="Bem vindos ao Acesse"/>
</c:import>

<header class="header-page">
	<img class="brand" src="/acesse/public/images/brand.svg">
	<nav class="navigation">
		<a class="link" href="/acesse/cadastrar">cadastre-se</a>
	</nav>
</header>

<main class="authenticate">
	<form class="form" action="/visualizar.html">
		<h1 class="title">Acesse</h1>
		<h2 class="subtitle">Faça parte dessa comunidade, e torne o mundo mais acessivel.</h2>
		<input class="input" type="text" placeholder="email" />
		<input class="input" type="password" placeholder="password" />
		<div class="action">
			<button class="button -default">Entrar</button>
			<a class="forgot" >Esqueceu a senha?</a>
		</div>
	</form>
</main>

<c:import url="templates/footer.jsp"/>