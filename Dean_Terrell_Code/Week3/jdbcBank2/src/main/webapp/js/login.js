

function login() {
	var username = $('#username').val();
	var password = $('#pass').val();
	var toSend = [username, password];
	/*var user = {
		name: username,
		password: password
	};

	//var tx = JSON.stringify(user);
	console.log(tx) */
	var json = JSON.stringify(toSend);

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200)
			console.log("in xhr callback");
	};

	//login refers to login servlet
	xhr.open("POST", "login", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(json);

	window.location.replace("app.html");
	loadHome();
}

function register() {
	window.location.replace("register.html"); // On call will switch to register page
	var username = $('#username').val();
	var password = $('#pass').val();
	var toSend = [username, password];
	/*var user = {
			name: username,
			password: password
		};

		//var tx = JSON.stringify(user);
		console.log(tx) */
	var json = JSON.stringify(toSend);

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200)
			console.log("in xhr callback");
	};

	//login refers to login servlet
	xhr.open("POST", "register", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(json);
	window.location.replace("login.html");
}

window.onload = function() {
	$('#log').on('click',login);
	$('#reg').on('click',register);
};