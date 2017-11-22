<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:if test="true"> 
  <c:set value="-active" var="show"></c:set>
</c:if> 

<header class="header-page">
	<img class="brand" src="/acesse/public/images/brand.svg">
	<nav class="navigation">
		<a class="link ${param.active == 'show' ? "-active" : ""}"" href="/acesse/locais">locais</a>
		<a class="link ${param.active == 'registerLocal' ? "-active" : ""}" href="/acesse/local/novo">+ novo local</a>
		<a class="link ${param.active == 'myLocals' ? "-active" : ""}" href="/acesse/me/locais">meus locais</a>
		<a class="link" href="/acesse">sair</a>
	</nav>
</header>