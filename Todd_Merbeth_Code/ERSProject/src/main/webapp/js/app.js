window.onload = function() {
	loadLoginView();
	$('#logout').on('click', logout);
	hideNav();
}

// //////////////////////////////////////////////////// Login
// ///////////////////////////////////////////////////

function loadLoginView(){	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADLOGINVIEW " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			$('#outcome').hide();
			$('#username').on('input',function(e){
				$('#outcome').hide();
			});
			$('#password').on('input',function(e){
				$('#outcome').hide();
			});
			$('#login').on('click', login);
			$('#register').on('click', loadRegisterView);
		}
	}
	xhr.open("GET", "login.view", true);
	xhr.send();
}
function login(){
	console.log("LOGGING IN");
	var username = $('#username').val();
	var password = $('#password').val();
	var json1 = [username, password];
	var json = JSON.stringify(json1);
	
	// AJAX stuff
	var xhr = new XMLHttpRequest();
	// ReadyState = 0, Client has been created. open() not called yet.
	xhr.onreadystatechange = function(){  // this will happen last, is a
											// callback
		console.log("ATTEMPINGLOGIN " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("in xhr callback");
			var user = JSON.parse(xhr.responseText);
			$('#outcome').show();
			if(user == null){
				$('#outcome').html("Invalid username or password. Please try again.");
			}
			else{
				if(user.role == 2){
					loadManagerHome();
					showManagerNav();
				}
				else {
					loadEmployeeHome();
					showEmployeeNav();
					}
			}
		}
	};
	xhr.open("POST", "login", true);	// what to do, what servlet address (the
										// @WebServlet("/login") in this case),
										// asynchronous request
	xhr.setRequestHeader("Content-type", "application/x-www.form-urlencoded");
	xhr.send(json);
}

// //////////////////////////////////////////////////// Register
// ///////////////////////////////////////////////////

function loadRegisterView(){	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADREGISTERVIEW " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			$('#umessage').hide();
			$('#emessage').hide();
			$('#emptymessage').hide();
			$('#registerRegister').on('click', register);
			$('#registerCancel').on('click', loadLoginView);
			$('#username').blur(checkUsername);
			$('#email').blur(checkEmail);
// $('#fn').on('input', checkAllFilled());
// $('#ln').on('input', checkAllFilled());
// $('#username').on('input', checkAllFilled());
// $('#password').on('input', checkAllFilled());
// $('#email').on('input', checkAllFilled());
// $('#register').attr("disabled",true);
		}
	}
	xhr.open("GET", "register.view", true);
	xhr.send();
}
function checkUsername(){
	var username = $('#username').val();
	var json = JSON.stringify(username);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			var exist = xhr.responseText;
			console.log(exist);
			if(exist.length==4){
				$('#umessage').html(`Username: '${username}' is already taken! Please try another.`);
				$('#umessage').show();
			}
			else {
				$('#umessage').hide();
				}
		}
	};
	
	xhr.open("POST","checkUsername", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send(json);
}
function checkEmail(){
	var email = $('#email').val();
	var json = JSON.stringify(email);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status==200){
			var exist = xhr.responseText;
			console.log(exist);
			if(exist.length==4){
				$('#emessage').html(`Email: '${email}' is already in use for an account! Please try another or log into your account.`);
				$('#emessage').show();
			}
			else {
				$('#emessage').hide();
				}
		}
	};
	
	xhr.open("POST","checkEmail", true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send(json);
}
// function checkAllFilled() {
// var username = $('#username').val();
// var pass = $('#pass').val();
// var fn = $('#fn').val();
// var ln = $('#ln').val();
// var e = $('#email').val();
//	
// if (username.length == 0 || username == '' || p.length == 0 || pass == '' ||
// fn == '' || ln == '' || e == ''){
// $('#register').attr("disabled",true);
// }
// else {
// $('#register').attr("disabled",false);
// }
//	
// }
function checkFilled() {
	var username = $('#username').val();
	var pass = $('#pass').val();
	var fn = $('#fn').val();
	var ln = $('#ln').val();
	var e = $('#email').val();
	
	if (username.length == 0 || username == ''){
		return false;
	}
	if (pass.length == 0 || pass == ''){
		return false;
	}
	if (fn.length == 0 || fn == ''){
		return false;
	}
	if (ln.length == 0 || ln == ''){
		return false;
	}
	if (e.length == 0 || e == ''){
		return false;
	}
	return true;
}
function register(){
	console.log(checkFilled());
	if (checkFilled() == false) {
		$('#emptymessage').show();
	}
	else {
		$('#emptymessage').hide();
		var username = $('#username').val();
		var pass = $('#pass').val();
		var fn = $('#fn').val();
		var ln = $('#ln').val();
		var e = $('#email').val();
		var r = 1;
		if ($('#role').is(":checked")){
			r = 2;
		}
		console.log(r);
		var user = {
				id: 0,
				username: username,
				password: pass,
				firstname: fn,
				lastname: ln, 
				email: e,
				role: r,
				roleStr: ""
		};
		
		var userJSON = JSON.stringify(user);
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			console.log("ATTEMPTINGREGISTER " + xhr.readyState);
			if(xhr.readyState == 4 && xhr.status==200){
				var user = JSON.parse(xhr.responseText);
				if(user == null){
					alert("Error creating account");
				}
				else{
					console.log("added new user");
					$('#message').hide();
					alert("Success! Please log in to your new account");
					loadLoginView();
					// window.location.replace('login.html');
					
				}
			}
		};
	
		xhr.open("POST","register", true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send(userJSON);
	}
}

// /////////////////////////////////////////////////// Employee
// //////////////////////////////////////////////////

function loadEmployeeHome(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	console.log("LOADEMPHOMEVIEW " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			loadEmployeeReimbursements();
		}
	}
	xhr.open("GET", "employeeHome.view", true);
	xhr.send();
}

function loadEmployeeReimbursements(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADEMPREIMBS " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var reimbursements = JSON.parse(xhr.responseText);
			$("#userReimbsBody tr").remove();
			for (var i=0; i<reimbursements.length; i++){
				if(reimbursements[i].description == null){
					reimbursements[i].description = "";
				}
				var line = 
						`<tr>
							<td> ${reimbursements[i].id} </td>
							<td> $${reimbursements[i].amount} </td>
							<td> ${reimbursements[i].submitted} </td>
							<td> ${reimbursements[i].description} </td>
							<td> ${reimbursements[i].typeStr} </td>
							<td> ${reimbursements[i].statusStr}</td>
						</tr>`;
				$('#userReimbsBody').append(line);
			}
			$(document).ready(function(){
			    $('#userReimbs').DataTable();
			});
		}
	}
	xhr.open("GET", "employeeReimbursements", true);
	xhr.send();	
}

function loadEmployeeProfile(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	console.log("LOADEMPPROFILEVIEW " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			loadUserInfo();
		}
	}
	xhr.open("GET", "employeeProfile.view", true);
	xhr.send();
}

function loadUserInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADUSERINFO " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var user = JSON.parse(xhr.responseText);
			var passlength = user.password.length;
			var pass = "";
			for(let i = 0; i < user.password.length; i++){
				pass = pass.concat("*")
			}
			document.getElementById('userInfo').innerHTML = `
			<div id="uInfoInner" style="border:solid; border-width: 1px;border-radius:25px; padding:8px; border-color:bg-primary; background-color: #eee; margin:auto; width:50%; min-width:320px; max-width:450px;">
					<div id="first" style="width: 100%; clear:both; height:28px; margin-top:5px; margin-left:6px; margin-bottom:10px;">
					<div id="firstnameText" style="float:left;">First name: ${user.firstname} </div> 
					<button id="editFirst" class="btn btn-link btn-sm" style="font-size:10px; color:red; padding-bottom:6px;">Edit</button>
					<label for="newFirstname" id="fnlabel" style="float:left; margin-bottom:0;">First name: </label> 
					<input type="text" class="form-control" id="newFirstname" placeholder="${user.firstname}" style="width: 200px; font-size:18px; padding: 0 0 0 4px;">
					</div> 
					
					<div id="last" style="width: 100%; clear:both; height:28px; margin-left:6px; margin-bottom:10px;">
					<div id="lastnameText" style="float:left;">Last name: ${user.lastname}</div> 
					<button id="editLast" class="btn btn-link btn-sm" style="font-size:10px; color:red; padding-bottom:6px;">Edit</button>
					<label for="newLastname" id="lnlabel" style="float:left; margin-bottom:0; clear: both;">Last name: </label> 
					<input type="text" class="form-control" id="newLastname" placeholder="${user.lastname}" style="width: 200px; font-size:18px; padding: 0 0 0 4px;">
					</div>
					
					<div id="email" style="width: 100%; clear:both; height:28px; margin-left:6px; margin-bottom:10px;">
					<div id="emailText" style="float:left;">Email: ${user.email} </div> 
					<button id="editEmail" class="btn btn-link btn-sm" style="font-size:10px; color:red; padding-bottom:6px;">Edit</button>
					<label for="newEmail" id="emaillabel" style="float:left; margin-bottom:0; clear: both;" >Email: </label> 
					<input type="text" class="form-control" id="newEmail" placeholder="${user.email}" style="width: 200px; font-size:18px; padding: 0 0 0 4px;">
					</div> 
					
					<div id="roleText" style="float:left; width: 100%; clear:both; height:28px; margin-left:6px; margin-bottom:10px;">Role: ${user.roleStr} </div>
					
					<div id="username" style="width: 100%; clear:both; height:28px; margin-left:6px; margin-bottom:10px;">
					<div id="usernameText" style="float:left;">Username: ${user.username} </div>
					<button id="editUsername" class="btn btn-link btn-sm" style="font-size:10px; color:red; padding-bottom:6px;">Edit</button>
					<label for="newUsername" id="usernamelabel" style="float:left; margin-bottom:0; clear: both; ">Username: </label> 
					<input type="text" class="form-control" id="newUsername" placeholder="${user.username}" style="width: 200px; font-size:18px; padding: 0 0 0 4px;">
					</div> 
					
					<div id="password" style="width: 100%; clear:both; height:28px; margin-left:6px; margin-bottom:5px;">
					<div id="passwordText" style="float:left; padding-right: 8px;"> Password: ${pass} </div>
					<button id="showPassword" class="btn btn-default btn-sm" style="font-size:10px; color:bg-primary; padding-bottom:6px; width: 40px; float:left;">Show</button>
					<button id="hidePassword" class="btn btn-default btn-sm" style="font-size:10px; color:bg-primary; padding-bottom:6px; width: 40px; float:left;">Hide</button>
					<button id="editPassword" class="btn btn-link btn-sm" style="font-size:10px; color:red; padding-bottom:6px;">Edit</button>
					<label for="newPassword" id="passwordlabel" style="float:left; margin-bottom:0; clear: both;">Password: </label> 
					<input type="password" class="form-control" id="newPassword" style="width: 200px; font-size:18px; padding: 0 0 0 4px;">
					</div>
			</div>`;
			$('#hidePassword').hide();
			
			// Firstname stuff
			$('#newFirstname').hide();
			$('#fnlabel').hide();
			$('#editFirst').click(function() {
				$('#firstnameText').hide();
				$('#editFirst').hide();
				$('#newFirstname').show();
				$('#fnlabel').show();
			});
			$('#newFirstname').keyup(function(event) {
			    if (event.keyCode === 13) {
			    	if($('#newFirstname').val != ''){
			    		updateUser("firstname", $('#newFirstname').val());
			    	}
			    	$('#firstnameText').show();
			    	$('#editFirst').show();
			    	$('#newFirstname').hide();
			    	$('#fnlabel').hide();
			    }
			});
			// Lastname stuff
			$('#newLastname').hide();
			$('#lnlabel').hide();
			$('#editLast').click(function() {
				$('#lastnameText').hide();
				$('#editLast').hide();
				$('#newLastname').show();
				$('#lnlabel').show();
			});
			$('#newLastname').keyup(function(event) {
			    if (event.keyCode === 13) {
			    	if($('#newLastname').val != null){
			    		updateUser("lastname", $('#newLastname').val());
			    	}
			    	$('#lastnameText').show();
					$('#editLast').show();
					$('#newLastname').hide();
					$('#lnlabel').hide();
			    }
			});
			// Email stuff
			$('#newEmail').hide();
			$('#emaillabel').hide();
			$('#editEmail').click(function() {
				$('#emailText').hide();
				$('#editEmail').hide();
				$('#newEmail').show();
				$('#emaillabel').show();
			});
			$('#newEmail').keyup(function(event) {
			    if (event.keyCode === 13) {
			    	if($('#newEmail').val != null){
			    		updateUser("email", $('#newEmail').val());
			    	}
			    	$('#emailText').show();
					$('#editEmail').show();
					$('#newEmail').hide();
					$('#emaillabel').hide();
			    }
			});
			// Username
			$('#newUsername').hide();
			$('#usernamelabel').hide();
			$('#editUsername').click(function() {
				$('#usernameText').hide();
				$('#editUsername').hide();
				$('#newUsername').show();
				$('#usernamelabel').show();
			});
			$('#newUsername').keyup(function(event) {
			    if (event.keyCode === 13) {
			    	if($('#newUsername').val != null){
			    		updateUser("username", $('#newUsername').val());
			    	}
			    	$('#usernameText').show();
					$('#editUsername').show();
					$('#newUsername').hide();
					$('#usernamelabel').hide();
			    }
			});
			// Password stuff
			$('#newPassword').hide();
			$('#passwordlabel').hide();
			$('#editPassword').click(function() {
				$('#passwordText').hide();
				$('#editPassword').hide();
				$('#newPassword').show();
				$('#passwordlabel').show();
				$('#showPassword').hide();
				$('#hidePassword').hide();
			});
			$('#newPassword').keyup(function(event) {
			    if (event.keyCode === 13) {
			    	if($('#newPassword').val != null){
			    		updateUser("password", $('#newPassword').val());
			    	}
			    	$('#passwordText').show();
					$('#editPassword').show();
					$('#newPassword').hide();
					$('#passwordlabel').hide();
					$('#hidePassword').hide();
					$('#showPassword').show();
			    }
			});
			$("#showPassword").click(function() {
				document.getElementById('passwordText').innerHTML = `Password: ${user.password}`;
				$('#hidePassword').show();
				$('#showPassword').hide();
			});
			$("#hidePassword").click(function() {
				document.getElementById('passwordText').innerHTML = `Password: ${pass}`;
				$('#showPassword').show();
				$('#hidePassword').hide();
			});
		}
	}
	xhr.open("GET", "employeeInfo", true);
	xhr.send();	
}

function loadNewRequest(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADNEWREQUEST " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			$('#errmessage').hide();
			loadRTypeInfo()
			$('#createRequest').on('click', sendRequest);
		}
	}
	xhr.open("GET", "empNewRequest.view", true);
	xhr.send();
}
function loadRTypeInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADREQUESTINFO " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var rTypes = JSON.parse(xhr.responseText);
			var str = '<label for="reqType">Type:  </label><select id="reqType"><option disabled selected value> -- select an option -- </option>';
			for (let i = 0; i < rTypes.length; i++){
				var line = `<option value="${rTypes[i].r_id}">${rTypes[i].r_type}</option>`;
				str = str.concat(line);
			}
			str = str.concat("</select>");
			document.getElementById('reqType').innerHTML = str;
		}
	}
	xhr.open("GET", "reimbursementTypes", true);
	xhr.send();	
}
function sendRequest() {
	var amountInput = $('#reqAmount').val();
	var type = $('#reqType option:selected').val();
	var desc = $('#reqDescription').val();	
// if(amount == "" || type == undefined){
// $('#errmessage').show();
// }
// else {
		var reimbursement = {
				id : 0,
				amount : amountInput,
				submitted : "",
				resolved : "",
				description : desc,
				receipt : null,
				author : 0,
				resolver : 0,
				status : 0,
				type : type,
				authorStr : "",
				resolverStr : "",
				statusStr : "",
				typeStr : ""
		};
		
		var reimbJSON = JSON.stringify(reimbursement);
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			console.log("ATTEMPTINGNEWREQUEST " + xhr.readyState);
			if(xhr.readyState == 4 && xhr.status==200){
				var reimbursement = JSON.parse(xhr.responseText);
				if(reimbursement == null){
					alert("Error creating request");
				}
				else{
					alert("Success! New reimbursement request has been added!");
					loadEmployeeHome();
				}
			}
		};
	
		xhr.open("POST","newReimbursement", true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send(reimbJSON);
// }
}
// ////////////////////////////////////////////////// Manager
// //////////////////////////////////////////////////
function loadManagerHome(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	console.log("LOADMANHOMEVIEW " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById('view').innerHTML = xhr.responseText;
			$('#updateError').hide();
			$('#changeReimbursementContainer').hide();
			$('#sLabel').hide();
			loadAllReimbursements();
			loadRStatusOptions();
		}
	}
	xhr.open("GET", "managerHome.view", true);
	xhr.send();
}

function loadAllReimbursements(){
	$("#allReimbsBody tr").remove(); 
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
	console.log("LOADALLREIMBS " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var reimbursements = JSON.parse(xhr.responseText);
			for (var i=0; i<reimbursements.length; i++){
				if(reimbursements[i].resolved == null){
					reimbursements[i].resolved = "";
				}
				if(reimbursements[i].description == null){
					reimbursements[i].description = "";
				}
				if(reimbursements[i].resolverStr == null){
					reimbursements[i].resolverStr = "";
				}
				var line = 
						`<tr>
							<td> ${reimbursements[i].id} </td>
							<td> $${reimbursements[i].amount} </td>
							<td> ${reimbursements[i].submitted} </td>
							<td> ${reimbursements[i].resolved} </td>
							<td> ${reimbursements[i].description} </td>
							<td> ${reimbursements[i].authorStr} </td>
							<td> ${reimbursements[i].resolverStr} </td>
							<td> ${reimbursements[i].typeStr} </td>
							<td> ${reimbursements[i].statusStr}</td>
						</tr>`;
				$('#allReimbsBody').append(line);
			}
			$(document).ready(function(){
			    $('#allReimbs').DataTable();
			});
			addTableClicks(reimbursements);
		}
	}
	xhr.open("GET", "loadAllReimbursements", true);
	xhr.send();
}
function loadRStatusOptions(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOADREQUESTINFO " + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var status = JSON.parse(xhr.responseText);
			console.log(status);
			var str = '<label for="statusSelect">Change Status to: </label><select id="statusSelect"><option disabled selected value> -- select an option -- </option>';
			for (let i = 1; i < status.length; i++){
				var line = `<option value="${status[i].id}">${status[i].status}</option>`;
				str = str.concat(line);
			}
			str = str.concat("</select>");
			document.getElementById('reqStatus').innerHTML = str;
			$('#reqStatus').hide();
			$('#updateStatus').hide();
			$('#updateStatus').on('click', updateReimbursement);
		}
	}
	xhr.open("GET", "reimbursementStatus", true);
	xhr.send();	
}
function addTableClicks(reimbursements) {
    $("#allReimbs tr").click(function() {
    	$('#changeReimbursementContainer').show();
    	$('#sLabel').show();
    	document.getElementById('selected').innerHTML = this.getElementsByTagName("td")[0].innerHTML;
    	$('#reqStatus').show();
    	$('#updateStatus').show();
    });
}
function updateReimbursement(){
	var index = document.getElementById('selected').innerHTML;
	var status = $('#reqStatus option:selected').val();
	if(status == ""){
		$('#updateError').show();
	}
	else {
		var arr = [index, status];
		var arrJSON = JSON.stringify(arr);
		
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			console.log("ATTEMPTINGUPDATE " + xhr.readyState);
			if(xhr.readyState == 4 && xhr.status==200){
				var result = JSON.parse(xhr.responseText);
				console.log(result);
				if (result == null){
					alert(`Error updating reimbursement status`);
				}
				else {
					alert(`Status for reimbursement request #${result.id} has been changed to:${result.statusStr}`);
					loadManagerHome();
				}
			}
		};
		xhr.open("POST","updateReimbursement", true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send(arrJSON);
	}
}
// ////////////////////////////////////////////////// Profile
// ///////////////////////////////////////////////////

function updateUser(command, value){
	var userUpdate = [command, value];
	var uuJSON = JSON.stringify(userUpdate);
	console.log(userUpdate);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("CHANGEUSER" + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			var result = JSON.parse(xhr.responseText);
			console.log(result);
			if (result == null){
				alert(`Error updating profile`);
			}
			else {
				alert(`Profile has been updated!`);
				loadUserInfo();
			}
		}
	}
	xhr.open("POST", "updateUser", true);
	xhr.send(uuJSON);
}

// ////////////////////////////////////////////////// Logout
// ///////////////////////////////////////////////////

function logout(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log("LOGOUT" + xhr.readyState);
		if(xhr.readyState == 4 && xhr.status == 200){
			hideNav();
			loadLoginView();
			alert("You have been logged out. Have a nice day!");
		}
	}
	xhr.open("GET", "logout", true);
	xhr.send();
}

// ////////////////////////////////////////////////// Nav
// //////////////////////////////////////////////////////

function showManagerNav(){
	$('#mhome').show();
	$('#mprofile').show();
	$('#logout').show()
	$('#mhome').on('click', loadManagerHome);
	$('#mprofile').on('click', loadEmployeeProfile);
}

function showEmployeeNav(){
	$('#ehome').show();
	$('#eprofile').show();
	$('#enewreq').show();
	$('#logout').show()
	$('#ehome').on('click', loadEmployeeHome);
	$('#eprofile').on('click', loadEmployeeProfile);
	$('#enewreq').on('click', loadNewRequest);
}

function hideNav(){
	$('#mhome').hide();
	$('#ehome').hide();
	$('#mprofile').hide();
	$('#eprofile').hide();
	$('#enewreq').hide();
	$('#logout').hide()
}

// ********** Left todo **********
// Update user info
// Styling pretty much all pages
// Get rid of "null" in tables
// Keep user session when refresh/back
// Delete reimbursement (optional)
// Manager approves new managers (optional)
// ERS nav button sends to an informational page (optional)
// Reimbursement receipt (optional)







