const formRegister = document.querySelector("[app-register-action]");
formRegister.onsubmit = registerAction;

function registerAction(e) {
	e.preventDefault();
	
	const name = e.target.NAME.value;
	const email = e.target.EMAIL.value;
	const password = e.target.PASSWORD.value;
	
	const data = { name, email, password };
	
	console.log(mapToApi(data));
	
	axios.post("/acesse/cadastrar", mapToApi(data)).then((response) => {
		console.log(response);
	});
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