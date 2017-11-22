const formRegister = document.querySelector("[app-register-action]");
if(formRegister) formRegister.onsubmit = registerAction;

const formAuthorize = document.querySelector("[app-authorize-action]");
if(formAuthorize) formAuthorize.onsubmit = authorizeAction;

const formRegisterLocal = document.querySelector("[app-register-local-action]");
if(formRegisterLocal) formRegisterLocal.onsubmit = registerLocalAction;

const formEvaluation = document.querySelector("[app-register-evaluation-action]");
if(formEvaluation) formEvaluation.onsubmit = formEvaluationAction;

const searchInput = document.querySelector("[search-locals-input]");
if(searchInput) searchInput.onkeyup = searchAction;

const zipcodeInput = document.querySelector("[get-location-action]");
if(zipcodeInput) zipcodeInput.onblur = getLocationAction;

const categoryList = document.querySelector("[app-categories-list]");
if(categoryList) setCategories(categoryList);

window.onload = () => {
	getSession();
};

function getSession() {
	const user_id = window.localStorage.getItem("user_id");
	
	if(!user_id) return;
	
	const data = { user_id };
	axios.post("/acesse/session", mapToApi(data)).then(response => {});
}


function authorizeAction(e) {
	e.preventDefault();
	e.target.classList.remove("-error");
	
	const email = e.target.EMAIL.value;
	const password = e.target.PASSWORD.value;

	const data = { email, password };
	
	axios.post("/acesse/authorize", mapToApi(data)).then((response) => {
		if(response.data.status != 200) return e.target.classList.add("-error");
		window.localStorage.setItem("user_id", response.data.user_id);
		document.location.href = "/acesse/locais";
	});
}

function registerAction(e) {
	e.preventDefault();
	destroyErrors();
	const errors = [];
	
	const name = e.target.NAME.value;
	const email = e.target.EMAIL.value;
	const password = e.target.PASSWORD.value;
	const confirmPassword = e.target.CONFIRM_PASSWORD.value;
	
	if(!name.trim()) errors.push("Erro! Por favor preencha o campo nome.");
	
	if(!email.trim()) errors.push("Erro! Por favor preencha o campo email.");
	
	if(!password) errors.push("Erro! Por favor preencha o campo senha.");
	else if(password != confirmPassword) errors.push("Erro! Senhas não condizem.");
	
	if(!confirmPassword) errors.push("Erro! Por favor preencha o campo confirme sua senha.");
		
	if(errors.length) return createErrors(errors);
	
	const data = { name, email, password };
	
	axios.post("/acesse/cadastrar", mapToApi(data)).then((response) => {
		if(response.data.status == 200) document.location.href = "/acesse/locais";
	});
}


function registerLocalAction(e) {
	e.preventDefault();
	destroyErrors();
	const errors = [];
	
	const name = e.target.NAME.value;
	const description = e.target.DESCRIPTION.value;
	const zipcode = e.target.ZIPCODE.value;
	const address = e.target.ADDRESS.value;
	const neighbourhood = e.target.NEIGHBOURHOOD.value;
	const city = e.target.CITY.value;
	const state = e.target.STATE.value;
	const category = e.target.CATEGORY.value ? e.target.CATEGORY.value : null;
	const number = e.target.NUMBER.value;
	const lat = e.target.LAT.value;
	const lng = e.target.LNG.value;
	
	if(!name.trim()) errors.push("Erro! Por favor preencha o campo nome.");
	
	if(!address.trim()) errors.push("Erro! Por favor preencha o campo endereço.");
	
	if(!description.trim()) errors.push("Erro! Por favor preencha o campo descrição.");
	
	if(!zipcode.trim()) errors.push("Erro! Por favor preencha o campo cep.");
	
//	if(!category.trim()) errors.push("Erro! Por favor selecione uma categoria.");
	
	if(!number.trim()) errors.push("Erro! Por favor preencha o campo número.");
	
	
	if(errors.length) return createErrors(errors);
	
	const data = { name, address, zipcode, description, neighbourhood, city, state, category, lat, lng, number };
	
	axios.post("/acesse/locals", mapToApi(data)).then((response) => {
		if(response.data.status == 200) createSucess("Local cadastrado com sucesso :)");
	});
}


function createErrors(errors) {
	let html = '<div class="alert -wrong">';
	errors.forEach(error => {
		html += `<div class="error">${error}</div>`;
	});
	html += '</div>';
	
	const alert = document.querySelector("[alert-wrapper]");
	alert.innerHTML = html;
}

function createSucess(message) {
	let html = '<div class="alert -success">';
	html += `<div class="message">${message}</div>`;
	html += '</div>';
	
	const alert = document.querySelector("[alert-wrapper]");
	alert.innerHTML = html;
}

function destroyErrors() {
	const alert = document.querySelector("[alert-wrapper]");
	alert.innerHTML = "";
}

function mapToApi(data) {
	const keys = Object.keys(data);
	let map = "";
	
	keys.forEach(key => {
		if(!map) map += `${key}=${data[key]}`;
		else map += `&${key}=${data[key]}`
	});
	
	return map;
} 

function initialSearch() {
	axios.get("/acesse/locals").then((response) => {
		let html;
		
		if(!response.data.locals.length) html = "Nenhum local cadastrado :(";
		else {
			html = response.data.locals.sort(evaluationOrder).map(local => { 
				const { name, city, state, id, lat, lng, average } = local;
				return parseLocals({ name, city, state, id, lat, lng, average});
			}).join("");
		}
		
		const localList = document.querySelector("[app-local-list]");
		if(localList) localList.innerHTML = html;
		loadMaps();
	});
};

function evaluationOrder(x, y) {
	return x.average == y.average ? 0 : x.average > y.average ? -1 : 1;  
}

initialSearch();

function parseLocals({ name, city, state, id, lat, lng, average }) {
	let html = "";
	html += '<div class="location-card">';
		html +=	`<div class="map" id=${id} google-map data-lat=${lat} data-lng=${lng}>`;
		html += '</div>';
		html += `<div class="description" id=${id} onClick="showLocationInfo(this)">`;
			html += `<h3 class="title">${name}</h3>`;
			html += `<h3 class="city">${city}, ${state}</h3>`;
			html += getStars(average);
		html += '</div>';
	html += '</div>';
	return html;
}

function parseMyLocals({ name, city, state, id, lat, lng, average, description }) {
	let html = "";
	html += '<div class="my-local-card">';
		html +=	`<div class="map" id=${id} google-map data-lat=${lat} data-lng=${lng}>`;
		html += '</div>';
		html += `<div class="description" id=${id} onClick="showLocationInfo(this)">`;
			html += "<header class='header'>";
				html += `<h3 class="title">${name}`;
				html += `<span class="city">- ${city}, ${state}</span>`;
				html += "</h3>";
				html += getStars(average);
			html += "</header>"
			html += "<main class='main'>";
				html += `<p class="text">${description.length >= 100 ? description.slice(0, 100) +"..." : description}</p>`
				html += "<div class='actions'>";
					html += `<button onClick="deleteLocal(this)" id=${id}><img src="/acesse/public/icons/trash.svg"</button>`;
					html += `<button><img src="/acesse/public/icons/edit.svg"</button>`;
				html += "</div>";
			html += "</main>";
		html += '</div>';
	html += '</div>';
	return html;
}

function getStars(value = 0, show = true) {
	const stars = getStarsValue(value);
	let html = "";
	html += '<div class="stars">';
		html += `<span class="star interior _${stars[0]}"></span>`;
		html += `<span class="star interior _${stars[1]}"></span>`;
		html += `<span class="star interior _${stars[2]}"></span>`;
		html += `<span class="star interior _${stars[3]}"></span>`;
		html += `<span class="star interior _${stars[4]}"></span>`;
		html += `<span class="value ${show ? "" : "-hide"}">${value.toFixed(1)}</span>`;
	html += '</div>';
	return html;
}

function getStarsValue(value) {
	const stars = [];
	for(let i = 0; i < 5; i++) {
		if(value >= 1) {
			stars.push(10);
			value += -1;
		}
		
		else {
			stars.push(value * 10);
			value += -value;
		}
	}
	
	return stars;
}

function searchAction(e) {
	if(!e.target.value) initialSearch();
	axios.get(`/acesse/search?name=${e.target.value}`).then((response) => {
		
		let html;
		
		if(!response.data.locals.length) html = "Nenhum local encontrado :(";
		else {
			html = response.data.locals.sort(evaluationOrder).map(local => { 
				const { name, city, state, id, lat, lng, average } = local;
				return parseLocals({ name, city, state, id, lat, lng, average });
			}).join("");
		}
		
		document.querySelector("[app-local-list]").innerHTML = html;
		loadMaps();
	});
}

function getLocationAction(e) {
	axios.get(`http://viacep.com.br/ws/${e.target.value}/json/`).then((response) => {
		const { logradouro, bairro, localidade, uf } = response.data;
		formRegisterLocal.ADDRESS.value = logradouro;
		formRegisterLocal.NEIGHBOURHOOD.value = bairro;
		formRegisterLocal.CITY.value = localidade;
		formRegisterLocal.STATE.value = uf;
		getCoordsAction(e);
	});
}

function loadMaps() {
	const maps = document.querySelectorAll("[google-map]");
	
	maps.forEach(map => mapGoogle(map));
}

function mapGoogle(map, control = false) {
	const coords = { lat: +map.dataset.lat, lng: +map.dataset.lng };
	
	let data = {
	  zoom: 18,
	  center: coords,
	  streetViewControl: false,
	  disableDefaultUI: true,
	  zoomControl: false,
	  mapTypeControl: false,
	  scaleControl: false,
	  streetViewControl: false,
	  rotateControl: false,
	  fullscreenControl: false
	};
	
	if(control) data = { zoom: 18, center: coords };
	
	const googleMap = new google.maps.Map(map, data);
	google.maps.event.trigger(map, 'resize');

	new google.maps.Marker({
	  position: coords,
	  map: googleMap
	});

	return googleMap;
}

function getCoordsAction(e) {
	axios.get(`https://maps.googleapis.com/maps/api/geocode/json?address=${e.target.value}&key=AIzaSyC2VvQGklxmEcBkCrmeihlnJbc2Nb1gbLE`).then((response) => {
		if(response.data.results.length) {
			const { lat, lng } = response.data.results[0].geometry.location;
			formRegisterLocal.LAT.value = lat;
			formRegisterLocal.LNG.value = lng;
		}
	});
}

const LOCATION_MODAL_WRAPPER = document.querySelector("[app-location-info]");
if(LOCATION_MODAL_WRAPPER) LOCATION_MODAL_WRAPPER.onclick = hideLocationInfo;

const LOCATION_MODAL = document.querySelector("[app-location-info] > .modal");
if(LOCATION_MODAL) LOCATION_MODAL.onclick = function(e) {
	e.stopPropagation();
};

function hideLocationInfo() {
	LOCATION_MODAL_WRAPPER.scrollTop = 0;
	LOCATION_MODAL_WRAPPER.classList.add("hide");
	
	const evaluationList = document.querySelector("[app-evaluation-list]");
	evaluationList.classList.add("-loading");
	evaluationList.innerHTML = "<img src='/acesse/public/icons/rolling.svg' />";
	
	const body = document.querySelector("body");
	body.classList.remove("-nooverflow");
	
}

const months = ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"];

function showLocationInfo(element) {
	axios.get("/acesse/local?id=" + element.id).then(response => {
		
		const viewName = document.querySelector("[info-local-name]");
		viewName.innerHTML = response.data.name;
		
		const viewCreateAt = document.querySelector("[info-local-create-at]");
		const date = new Date(response.data.create_at);
		viewCreateAt.innerHTML = `${months[date.getMonth()]} ${date.getDate()}, ${date.getFullYear()}`;
		
		const map = document.querySelector("[info-map]");
		
		map.dataset.lat = response.data.lat;
		map.dataset.lng = response.data.lng;
		const googleMap = mapGoogle(map, true);
		
		const viewDescription = document.querySelector("[info-local-description]");
		viewDescription.innerHTML = response.data.description;
		
		const viewLocalId = document.querySelector("[info-local-id]");
		viewLocalId.value = response.data.id;
		
		const viewLocalCategory = document.querySelector("[info-local-category]");
		viewLocalCategory.innerHTML = response.data.category.id ? response.data.category.label : "Indefinido";
		
		const viewLocalCity = document.querySelector("[info-local-city]");
		viewLocalCity.innerHTML = `${response.data.city}, ${response.data.state}`;
		
		LOCATION_MODAL_WRAPPER.classList.remove("hide");
		
		const body = document.querySelector("body");
		body.classList.add("-nooverflow");
		
		google.maps.event.trigger(googleMap, 'resize');
		
		fetchEvaluations(response.data.id);
	});	
}

function getStarsWithLabel(value = 0, label, show = true) {
	const stars = getStarsValue(value);
	let html = "";
	html += '<div class="stars">';
		html += `<span class="label">${label}</span>`;
		html += `<span class="star interior _${stars[0]}"></span>`;
		html += `<span class="star interior _${stars[1]}"></span>`;
		html += `<span class="star interior _${stars[2]}"></span>`;
		html += `<span class="star interior _${stars[3]}"></span>`;
		html += `<span class="star interior _${stars[4]}"></span>`;
		html += `<span class="value ${show ? "" : "-hide"}">${value.toFixed(1)}</span>`;
	html += '</div>';
	return html;
}

function fetchEvaluations(id) {
	const evaluationList = document.querySelector("[app-evaluation-list]");
	evaluationList.classList.add("-loading");
	evaluationList.innerHTML = "<img src='/acesse/public/icons/rolling.svg' />";
	formEvaluation.classList.remove("-hide");
	formEvaluation.classList.remove("-sent");
	
	axios.get("/acesse/local/evaluations?local_id=" + id).then(response => {
		let evaluations = response.data.evaluations;
		let html;
		const viewLocalEvaluations = document.querySelector("[info-local-evaluations]");
		
		if(!evaluations.length) {
			html = "<span>Seja o primeiro a avaliar esse local.</span>";
			
			let evaluations = getStarsWithLabel(0, "Acesso p/ Cadeirantes");
			evaluations += getStarsWithLabel(0, "Sanitário p/ Cadeirantes");
			evaluations += getStarsWithLabel(0, "Instruções Braile<");
			evaluations += getStarsWithLabel(0, "Piso Tátil");
			viewLocalEvaluations.innerHTML = evaluations;
		} 
			
		else {
			const userId = window.localStorage.getItem("user_id");
			if(evaluations.length && evaluations.findIndex(evaluation => evaluation.user.id == userId) != -1)
				formEvaluation.classList.add("-hide");
			
			const wheelchairAccessAvg = evaluations.map(evaluation => evaluation.wheelchair_access_value).reduce((a, b) => parseInt(a) + parseInt(b)) / evaluations.length;	
			const wheelchairWcAvg = evaluations.map(evaluation => evaluation.wheelchair_wc_value).reduce((a, b) => +a + +b) / evaluations.length;
			const braileAvg = evaluations.map(evaluation => evaluation.braile_value).reduce((a, b) => +a + +b) / evaluations.length;
			const tatilFloorAvg = evaluations.map(evaluation => evaluation.tatil_floor_value).reduce((a, b) => +a + +b) / evaluations.length;
			
			let evaluationsHTML = getStarsWithLabel(wheelchairAccessAvg, "Acesso p/ Cadeirantes");
			evaluationsHTML += getStarsWithLabel(wheelchairWcAvg, "Sanitário p/ Cadeirantes");
			evaluationsHTML += getStarsWithLabel(braileAvg, "Instruções Braile<");
			evaluationsHTML += getStarsWithLabel(tatilFloorAvg, "Piso Tátil");
			viewLocalEvaluations.innerHTML = evaluationsHTML;
			
			html = evaluations.map(evaluation => getEvaluationCardHTML(evaluation)).join();
		}		
		
		setTimeout(() => {
			evaluationList.classList.remove("-loading");				
			evaluationList.innerHTML = html;	
		}, 1000);
		
	});
}

function getEvaluationCardHTML(evaluation) {
	let html = "";
	html += "<li class='item'>";
	html += "<div class='figure-wrapper'>";
	html += "<figure class='avatar'>";
	html += "<img class='image' src='https://api.adorable.io/avatars/12' alt='avatar'>";
	html += "</figure>";
	html += "</div>";
	html += "<main class='main'> ";
	html += `<h4 class='name'>${evaluation.user.name} <a href='#' class='username'>username</a></h4>`;
	html += `<p class='comment'>${evaluation.comment ? evaluation.comment : "Sem comentário."}</p>`;
	html += "<div class='date'>";
	html += "<span class='create_at'>2 dias atrás</span>";
	html += getStars(+evaluation.value);
	html += "</div>";
	html += "</main>";
	html += "</li>";
	
	console.log(evaluation.value);
	return html;
}

function formEvaluationAction(e) {
	e.preventDefault();
	e.target.classList.add("-sent");
	
	const data = {
		wheelchair_access_value: e.target.WHEELCHAIR_ACCESS_VALUE.value,
		wheelchair_wc_value: e.target.WHEELCHAIR_WC_VALUE.value,
		tatil_floor_value: e.target.TATIL_FLOOR_VALUE.value,
		braile_value: e.target.BRAILE_VALUE.value,
		comment: e.target.COMMENT.value,
		local_id: e.target.LOCAL_ID.value
	};
	
	axios.post("/acesse/evaluations", mapToApi(data)).then((response) => {
		console.log("in create evaluation", response);
	});
}

const USER_LOCALS_LIST = document.querySelector("[app-user-locals-list]");
fetchUserLocals()

function fetchUserLocals() {
	const user_id = window.localStorage.getItem("user_id");
	if(!user_id) return;
	axios.get("/acesse/user/locals?user_id="+ user_id).then(response => {
		let html;
		
		if(!response.data.locals.length) html = "Nenhum local cadastrado :(";
		else {
			html = response.data.locals.sort(evaluationOrder).map(local => { 
				const { name, city, state, id, lat, lng, average, create_at, description } = local;
				return parseMyLocals({ name, city, state, id, lat, lng, average, create_at, description });
			}).join("");
		}
		
		if(USER_LOCALS_LIST) USER_LOCALS_LIST.innerHTML = html;
		loadMaps();
	});
}

function deleteLocal(element) {
	axios.delete("/acesse/local?id="+element.id).then(response => {
		location.reload();
	});
}

function setCategories(element) {
	axios.get("/acesse/categories").then(response => {
		const html = response.data.categories.map(category => `<option value="${category.id}">${category.label}</option>`).join("");
		element.innerHTML = html + element.innerHTML;
	});
}