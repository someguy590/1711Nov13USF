/**
 * 
 */

window.onload = function(){
	$('#login').on('click', login);
	$('#register').on('click', register);
}

function login(){
//	alert("logging in");
	var username = $('#username').val();
	var password = $('#pass').val();
	var toSend = [username, password];
//	var user = {
//			name: username,
//			password: password
//	};
//	
//	var tx = JSON.stringify(user);
	var json = JSON.stringify(toSend);
	console.log(json);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("ready state: " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("in xhr callback");
		}
	}
	xhr.open("POST", "login" ,true);
	// sets the content type of the request
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(json);
}

function register(){
	alert("registering");
}
