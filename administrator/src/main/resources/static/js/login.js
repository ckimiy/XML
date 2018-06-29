$(document).ready(function() {
	$("#login").click(function() {
		var $form = $("#loginForm");
		var data = getFormData($form);
		var s = JSON.stringify(data);
		$.ajax({
	  		type: "POST",
	  		url: "/admin/login",
	  		data: s,
			contentType: "application/json",
	  		success: function(data, status, xhr) {
                setJwtToken(xhr.getResponseHeader('Authorization'));
	  			window.location = '/';
	  		},
	  		error: function(data) {
	  			alert("Neuspesno logovanje.");
	  			console.log(data);
	  		}
	  	});
	});
});