/**
 * previously app.js
 */

window.onload = function(){
	$('#message').hide();
	$('#login').on('click',login);
	$('#register').on('click', registerView);
	
}

function login(){
//	alert("logging in");
	var username = $('#username').val();
	var password = $('#pass').val();
	
	var toSend = [username, password];

	
	
	var json = JSON.stringify(toSend);
	console.log(json);
	
	var xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			console.log("in xhr callback" + xhr.responseText);
			var user = JSON.parse(xhr.responseText);
			$('#message').show();
			if(user == null){
				$('#message').html("Invalid user") ;
			}
			else if(user.id == 0){
				$('#message').html( "Invalid password");
			}
			else{
				$('#message').html(`Welcome ${user.firstname}`) ;
				loadApp()
				console.log("success!");
				loadHome();
				
			}
		}
	};
	
	xhr.open("POST","login", true);
	console.log(xhr.readyState);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	console.log("AFTER HEADER " + xhr.readyState);
	xhr.send(json);
	

}

function registerView(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
		//	window.location.replace(xhr.responseText);
		document.getElementById('view').innerHTML = xhr.responseText;
		$('#message').hide();
		$('#register').on('click', register);
		$('#username').blur(validateEmail);
		$('#register').attr("disabled",true);
		}
	}	
	xhr.open("GET", "register.view" , true);
	xhr.send();
}


function loadApp(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
		//	window.location.replace(xhr.responseText);
		document.getElementById('view').innerHTML = xhr.responseText;
		$('#home').on('click',loadHome);
		$('#profile').on('click', loadProfile);
		}
	}	
	xhr.open("GET", "app.view" , true);
	xhr.send();
}





//function onblur that notifies the user of whether or not their email address is already in use 
function validateEmail(){
	$('#register').attr("disabled",false);
	$('#message').hide();
	var username = $('#username').val();
	
	var toSend = [username, ""];

	
	var json = JSON.stringify(toSend);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			var user = JSON.parse(xhr.responseText);
			console.log("USER: " + user);
			if(user != null){
				$('#message').show();
				$('#message').html("Username Already in use! Please try another") ;
				$('#register').attr("disabled",true);
			}
			else{
				$('#register').attr("disabled",false);
				$('#message').hide();
			}
		}
	};
	
	xhr.open("POST","login", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send(json);
}

function register(){
	var fn = $('#fn').val();
	var ln = $('#ln').val();
	var uname = $('#username').val();
	var pass = $('#pass').val();
	// add password validation and second input confirmation?

	var user = {
			id: 0,
			firstname: fn,
			lastname: ln, 
			email: uname, 
			password: pass
	};
	
	var userJSON = JSON.stringify(user);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			console.log("added user");
		}
	};
	
	xhr.open("POST","register", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send(userJSON);
	$('#message').hide();
	alert("Success! Please login using your credentials");
	window.location.replace('landing.html');
}


/**
 * main page of the application. thinking about creating a pseudo 
 * 	"admin" role based on a userame and password of admin for demo 
 * 	purposes 
 */


function loadHome(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('appview').innerHTML = xhr.responseText;				
		}
	}	
	xhr.open("GET", "home.view" , true);
	xhr.send();
}

function loadProfile(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "profile.view" , true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('appview').innerHTML = xhr.responseText;//partials/profile.html	
			loadProfileView();
			
			console.log("TESTING IF WE ARE GETTING THE USER" + test);
		
		}
	}
}

function loadProfileView(){
	var xhr = new XMLHttpRequest();
	xhr.open("GET","getUserInfo", true);
	xhr.send();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log(xhr.responseText);
			var sessionUser = JSON.parse(xhr.responseText);
			$("#name").html(sessionUser.firstname);
		}
	}
	
	
}








