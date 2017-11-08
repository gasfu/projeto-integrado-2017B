<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="templates/head.jsp">
	<c:param name="title" value="Cadastre um Local"/>
</c:import>

<header class="header-page">
	<img class="brand" src="/acesse/public/images/brand.svg">
	<nav class="navigation">
		<a class="link" href="/acesse">entrar</a>
	</nav>
</header>

<main class="registerLocal">
	<form class="form" app-register-action>
		<h1 class="title">Acesse</h1>
		<h2 class="subtitle">Cadastre um Local</h2>
		<div class="alert-wrapper" alert-wrapper></div>	
		<input class="input" type="text" placeholder="nome" name="NOME DO LOCAL" />
		<input class="input" type="text" placeholder="endereço" name="ENDEREÇO" />
		<input class="input" type="text" placeholder="descrição" name="DESCRIÇÃO" />
		<div class="action">
			<button class="button -default">Cadastrar</button>
			<p class="eula">
				Ao cadastrar você concorda com os nossos <a class="link" href="#">
				Termos de Uso</a> e de <a class="link" href="#">Privacidade.</a>
			</p>
		</div>
	</form>
</main>

<c:import url="templates/footer.jsp"/>