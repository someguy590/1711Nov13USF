// DONT FORGET TO GO TO 
// www.datatables.net 
// FOR TABLE FORMATTING



function loadHome(){
	loadView("getHomeView");
}

function loadProfile(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200)
			document.getElementById('view').innerHTML = xhr.responseText;
			loadProfileInfo();
	};
	
	xhr.open("GET", "getProfileView", true);
	xhr.send();
}
function loadView(page){

	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200)
			document.getElementById('view').innerHTML = xhr.responseText;
	};
	
	xhr.open("GET", page, true);
	xhr.send();
}

function loadProfileInfo() {
	var sessionUser;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			sessionUser = JSON.parse(xhr.responseText);
			$("#name").html(sessionUser.fName);
			return sessionUser;
		}
	};
	xhr.open("GET", "getUserInfo", true);
	xhr.send();
}


window.onload = function() {
	loadHome();
	
	$('#home').on('click',loadHome);
	$('#profile').on('click',loadProfile);
};

