<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="templates/head.jsp">
	<c:param name="title" value="Cadastre-se no Acesse"/>
</c:import>

<header class="header-page">
	<img class="brand" src="/acesse/public/images/brand.svg">
	<nav class="navigation">
		<a class="link" href="/acesse">entrar</a>
	</nav>
</header>

<main class="signup">
	<form class="form" app-register-action>
		<h1 class="title">Acesse</h1>
		<h2 class="subtitle">Cadastre-se.</h2>	
		<input class="input" type="text" placeholder="nome" name="NAME" />
		<input class="input" type="text" placeholder="email" name="EMAIL" />
		<input class="input -x50" type="password" placeholder="password" name="PASSWORD"/>
		<input class="input -x50" type="password" placeholder="confirme seu password" />
		<div class="action">
			<button class="button -default">Cadastrar</button>
			<p class="eula">
				Ao se cadastrar você concorda com os nossos <a class="link" href="#">
				Termos de Uso</a> e de <a class="link" href="#">Privacidade.</a>
			</p>
		</div>
	</form>
</main>

<c:import url="templates/footer.jsp"/>