<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-wrapper hide" app-location-info>
	<div class="modal">
		<header class="header">
			<figure class="avatar">
				<img class="image" src="https://api.adorable.io/avatars/1" alt="avatar">
			</figure>
			<div class="description">
				<h2 class="title" info-local-name></h2>
				<p class="by">por <a href="#" info-user-username>username</a> em <label info-local-create-at></label>  </p>
			</div>
		</header>
		<div class="map" info-map></div>
		<div class="description">
			<p class="about" info-local-description></p>
			<div class="evaluations" info-local-evaluations></div>
			<ul class="options">
				<li class="item"><img src="/acesse/public/icons/map-pin.svg"/><span info-local-city>Location, SP</span></li>
				<li class="item"><img src="/acesse/public/icons/storage.svg"/><span info-local-category>Categoria</span></li>
			</ul>
		</div>
		<div class="evaluations">
			<h3 class="title">Avaliações</h3>
			
			<ul class="list" app-evaluation-list></ul>
			<form class="form evalue" app-register-evaluation-action>
				<h4 class="title">Deixe sua avaliação, contribuindo para um mundo mais acessivel :)</h4>
				
				<div class="stars">
					<label class="label">Acesso p/ Cadeirantes</label>
					<input class="radio" type="radio" name="WHEELCHAIR_ACCESS_VALUE" id="wheelchair_access_value1" value="1" />
					<label class="star _10 interior" for="wheelchair_access_value1"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_ACCESS_VALUE" id="wheelchair_access_value2" value="2" />
					<label class="star _10 interior" for="wheelchair_access_value2"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_ACCESS_VALUE" id="wheelchair_access_value3" checked value="3" />
					<label class="star _10 interior" for="wheelchair_access_value3"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_ACCESS_VALUE" id="wheelchair_access_value4" value="4" />
					<label class="star _10 interior" for="wheelchair_access_value4"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_ACCESS_VALUE" id="wheelchair_access_value5" value="5" />
					<label class="star _10 interior" for="wheelchair_access_value5"></label>
				</div>
				<div class="stars">
					<label class="label">Sanitário p/ Cadeirantes</label>
					<input class="radio" type="radio" name="WHEELCHAIR_WC_VALUE" id="wheelchair_wc_value1" value="1" />
					<label class="star _10 interior" for="wheelchair_wc_value1"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_WC_VALUE" id="wheelchair_wc_value2" value="2" />
					<label class="star _10 interior" for="wheelchair_wc_value2"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_WC_VALUE" id="wheelchair_wc_value3" checked value="3" />
					<label class="star _10 interior" for="wheelchair_wc_value3"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_WC_VALUE" id="wheelchair_wc_value4" value="4" />
					<label class="star _10 interior" for="wheelchair_wc_value4"></label>
					<input class="radio" type="radio" name="WHEELCHAIR_WC_VALUE" id="wheelchair_wc_value5" value="5" />
					<label class="star _10 interior" for="wheelchair_wc_value5"></label>
				</div>
				<div class="stars">
					<label class="label">Instruções Braile</label>
					<input class="radio" type="radio" name="BRAILE_VALUE" id="braile_value1" value="1" />
					<label class="star _10 interior" for="braile_value1"></label>
					<input class="radio" type="radio" name="BRAILE_VALUE" id="braile_value2" value="2" />
					<label class="star _10 interior" for="braile_value2"></label>
					<input class="radio" type="radio" name="BRAILE_VALUE" id="braile_value3" checked value="3" />
					<label class="star _10 interior" for="braile_value3"></label>
					<input class="radio" type="radio" name="BRAILE_VALUE" id="braile_value4" value="4" />
					<label class="star _10 interior" for="braile_value4"></label>
					<input class="radio" type="radio" name="BRAILE_VALUE" id="braile_value5" value="5" />
					<label class="star _10 interior" for="braile_value5"></label>
				</div>
				<div class="stars">
					<label class="label">Piso Tátil</label>
					<input class="radio" type="radio" name="TATIL_FLOOR_VALUE" id="tatil_floor_value1" value="1" />
					<label class="star _10 interior" for="tatil_floor_value1"></label>
					<input class="radio" type="radio" name="TATIL_FLOOR_VALUE" id="tatil_floor_value2" value="2" />
					<label class="star _10 interior" for="tatil_floor_value2"></label>
					<input class="radio" type="radio" name="TATIL_FLOOR_VALUE" id="tatil_floor_value3" checked value="3" />
					<label class="star _10 interior" for="tatil_floor_value3"></label>
					<input class="radio" type="radio" name="TATIL_FLOOR_VALUE" id="tatil_floor_value4" value="4" />
					<label class="star _10 interior" for="tatil_floor_value4"></label>
					<input class="radio" type="radio" name="TATIL_FLOOR_VALUE" id="tatil_floor_value5" value="5" />
					<label class="star _10 interior" for="tatil_floor_value5"></label>
				</div>			
				
				<input type="hidden" name="LOCAL_ID" info-local-id>
				<textarea class="text-area" name="COMMENT" placeholder="comentário" maxlength="140"></textarea>
				<button class="button" type="submit">Avaliar</button>
			</form>
			
		</div>
		
		<c:import url="templates/copyright.jsp"/>
	</div>
</div>