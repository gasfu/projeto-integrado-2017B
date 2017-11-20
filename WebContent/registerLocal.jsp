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
		<input class="input -x50" type="text" placeholder="cep" name="ZIPCODE" get-location-action />
		<input class="input -x75" type="text" placeholder="endereço" name="ADDRESS" disabled />
		<input class="input -x25" type="text" placeholder="número" name="NUMBER" />
		<input class="input" type="text" placeholder="bairro" name="NEIGHBOURHOOD" disabled />
		<input class="input -x50" type="text" placeholder="cidade" name="CITY" disabled />
		<input class="input -x50" type="text" placeholder="estado" name="STATE" disabled />
		<input type="hidden" name="LAT" />
		<input type="hidden" name="LNG" />
		<div class="row">
			<input class="input -x50" type="text" placeholder="nome" name="NAME" />
			<select class="select -x50" placeholder="categoria" name="CATEGORY">
				<option value="">Selecione uma categoria</option>
			</select>
		</div>
		<textarea class="text-area" type="text" placeholder="descrição" name="DESCRIPTION"></textarea>
		<div class="action">
			<button class="button -default">Cadastrar</button>
		</div>
	</form>
</main>

<c:import url="templates/footer.jsp"/>