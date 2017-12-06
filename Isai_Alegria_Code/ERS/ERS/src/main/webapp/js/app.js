/**
 * javascript function to change views of page
 */


window.onload = function(){
	
	loadHome();
	$('#home').on('click',loadHome); //button on navbar
	$('#profile').on('click',loadProfile); //button on navbar
	$('#logout').on('click',logOut);
	
}

var user = {}


function logOut(){

	window.location.replace("login.html");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
//				document.getElementById('view').innerHTML = xhr.responseText;
//				$('#addRequest').on('click',loadAddRequest);
//				$('#viewRequests').on('click',loadViewTickets);
				console.log("in callback for logout")
			}
	}
	xhr.open("GET","logOut",true);
	xhr.send();
}

function loadHome(){
	
	//loadView("getHomeView");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				document.getElementById('view').innerHTML = xhr.responseText;
				$('#addRequest').on('click',loadAddRequest);
				$('#viewRequests').on('click',loadViewTickets);
			}
	}
	xhr.open("GET","getHomeView",true);
	xhr.send();
	
}

function loadProfile(){
	
	//send ajax request to some type of getUserInfo Servlet
	//store user as object and access here
	//loadView("getProfileView");
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			loadProfileInfo();
		}
		
	}

	xhr.open("GET","getProfileView",true);
	xhr.send();
	
}

function loadProfileInfo(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			//console.log(xhr.responseText);
			sessionUser = JSON.parse(xhr.responseText);
			//console.log('session user: ' + sessionUser.firstName);
			$('#name').html(sessionUser.firstName);
			$('#fname').html(sessionUser.firstName);
			$('#lname').html(sessionUser.lastName);
			$('#uname').html(sessionUser.username);
			$('#email').html(sessionUser.email);
			$('#uid').html(sessionUser.userID);
			$('#uroleid').html(sessionUser.userRoleID);
			return sessionUser;
			
		}	
	}
	
	xhr.open("GET","getUserInfo",true);
	xhr.send();
}


function loadAddRequest(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
		}
		
	}

	xhr.open("GET","getAddRequestView",true);
	xhr.send();
	
}

function loadViewTickets(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
		}
		
	}

	xhr.open("GET","getViewTicketsView",true);
	xhr.send();
	
}