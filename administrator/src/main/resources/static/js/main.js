$(document).ready(function() {
	loadHeader();
});

var TOKEN_KEY = "jwtToken";

function getJwtToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function setJwtToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}

function removeJwtToken() {
    localStorage.removeItem(TOKEN_KEY);
}

function createAuthorizationTokenHeader() {
    var token = getJwtToken();
    if (token) {
        return {"Authorization": token};
    } else {
        return {};
    }
}

function loadHeader() {
	$("#header").load("/header.html", function() {
		checkUser();
	});
}

function init() {
	checkUser();
	all();
}

function checkUser() {
	console.log("Usao u proveru.")
	$.ajax({
		url : '/admin/status',
		type : 'GET',
		//dataType : 'json',
		headers: createAuthorizationTokenHeader(),
		success : function(data) {
				console.log("Logovan")
				loggedIn(data);
		},
		error : function() {
			console.log("Nije ulogovan")
			removeJwtToken();
			notLoggedIn();
		}
	});
}

function notLoggedIn() {
	$("#topbar_link_0").empty();
	$("#topbar_link_1").empty();
	$("#topbar_link_3").empty();
	$("#topbar_link_4").empty();
	$("#topbar_link_5").empty();
	$("#topbar_link_2").html("<a href=\"login.html\" id=\"login_link\">Login</a>");
	
}

function loggedIn(email) {
	$("#topbar_link_0").html("<a href=\"users.html\" id=\"users_link\">Korisnici</a>");
	$("#topbar_link_1").html("<a href=\"register.html\" id=\"register_link\">Dodaj agenta</a>");
	$("#topbar_link_2").html("<a href=\"comments.html\" id=\"comments_link\">Komentari</a>");
	$("#topbar_link_3").html("<a href=\"codebook.html\" id=\"codebook_link\">Sifarnik</a>");
	$("#topbar_link_5").html("<a href=\"#\" id=\"profile_link\">"+email+"</a>");
	
	
	$("#topbar_link_4").html("<a href=\"javascript:void(0)\" id=\"logout_link\">Odjava</a>");
	$("#logout_link").click(function(){
		/*$.ajax({
			url : '/admin/logout',
			type : 'POST',
			success : function() {
				alert("Odjavljen.");
				window.location = '/';
			},
			error : function() {
				alert("Odjava ne radi!");
			}
		});
		*/
		doLogout();
	});
}

function doLogout() {
    removeJwtToken();
    window.location = '/';
}

function getFormData($form){
	
	 var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	        indexed_array[n['name']] = n['value'];
	    });

	    return indexed_array;
	
}

