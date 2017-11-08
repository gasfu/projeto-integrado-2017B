const formRegister = document.querySelector("[app-register-action]");
if(formRegister) formRegister.onsubmit = registerAction;

const formAuthorize = document.querySelector("[app-authorize-action]");
if(formAuthorize) formAuthorize.onsubmit = authorizeAction;

function authorizeAction(e) {
	e.preventDefault();
	
	const email = e.target.EMAIL.value;
	const password = e.target.PASSWORD.value;

	const data = { email, password };
	
	axios.post("/acesse/authorize", mapToApi(data)).then((response) => {
		if(response.data.status == 200) document.location.href = "/acesse/visualizar";
		console.log(response);
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
		if(response.data.status == 200) document.location.href = "/acesse/visualizar";
	});
}

function createErrors(errors) {
	let html = '<div class="alert -wrong">';
	errors.forEach(error => {
		html += `<div class="error">${error}</div>`;
	});
	html += '</div>';
	
	const alert = document.querySelector("[alert-wrapper]");
	console.log("alert", html);
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