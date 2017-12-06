window.onload = function() {
	$('#register').on('click', register);
	$('#loginButton').on('click', login);
}

function register() {
	var fn = $('#firstname').val();
	var ln = $('#lastname').val();
	var uname = $('#regEmail').val();
	var pass = $('#regPass').val();
	// add password validation and second input confirmation?

	var user = {
		id : 0,
		username : uname,
		password : pass,
		firstName : fn,
		lastName : ln,
		email : uname,
		role : 0
	};
	var userJSON = JSON.stringify(user);

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("added user");
		}
	};

	xhr.open("POST", "register", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(userJSON);
	$('#message').hide();
	//window.location.replace('landing.html');
}

function login(){
	alert("Message");
	
	var username = $('#logEmail').val();
	var password = $('#logPass').val();
	
	var toSend = [username, password];

	
	var json = JSON.stringify(toSend);
	console.log(json);
	
	var xhr = new XMLHttpRequest();
	console.log(xhr.readyState);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			console.log("in xhr callback" + xhr.responseText);
			/*var user = JSON.parse(xhr.responseText);
			$('#message').show();
			if(user == null){
				$('#message').html("Invalid") ;
			}
			else{
				$('#message').html(`Welcome ${user.firstname}`) ;
				loadApp()
				console.log("success!");
				loadHome();
				
			}*/
		}
	};
	
	xhr.open("POST","login", true);
	console.log(xhr.readyState);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	console.log("AFTER HEADER " + xhr.readyState);
	xhr.send(json);
	window.location.replace('landing.html');
}

var $loginMsg = $(".loginMsg"),
	$login = $(".login"),
	$signupMsg = $(".signupMsg"),
	$signup = $(".signup"),
	$frontbox = $(".frontbox");

$("#switch1").on("click", function() {
	$loginMsg.toggleClass("visibility");
	$frontbox.addClass("moving");
	$signupMsg.toggleClass("visibility");

	$signup.toggleClass("hide");
	$login.toggleClass("hide");
});

$("#switch2").on("click", function() {
	$loginMsg.toggleClass("visibility");
	$frontbox.removeClass("moving");
	$signupMsg.toggleClass("visibility");

	$signup.toggleClass("hide");
	$login.toggleClass("hide");
});