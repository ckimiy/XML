$(document).ready(function() {
	allUsers();
});

function allUsers() {
	$.ajax({
		url : '/users/all',
		type : 'GET',
		dataType : 'json',
		headers: createAuthorizationTokenHeader(),
		error : function(data) {
		},
		success : function(data) {
			$("#users_list").empty(); // cistimo, ako je bilo vec neceg
			$("#blocked_list").empty();
			
			$.each(data, function(index, element) {
				add(element);
			});
		}
	});
}

function add(element) {
	var newHTML = [];
	var id = element.id;
	newHTML.push("<div class=\"divBack\" id=\"user-"+id+"\">");
	newHTML.push("<p class=\"textTemplate sameLine\">"+element.username+" | "+element.email+"</p>");
	newHTML.push("<div class=\"sameLine\"> ");
	if(element.blocked) newHTML.push("<input type=\"button\" class=\"btn\" value=\"Aktiviraj\" id=\"acBtn-"+id+"\"> ");
	else newHTML.push("<input type=\"button\" class=\"btn\" value=\"Blokiraj\" id=\"blBtn-"+id+"\"> ");
	
	newHTML.push("<input type=\"button\" class=\"btn\" value=\"Obrisi\" id=\"delBtn-"+id+"\">");
	
	newHTML.push("</div>");
	newHTML.push("</div>");
	
	if(element.blocked){
		$("#blocked_list").append(newHTML.join(""));
		$("#acBtn-"+id).click(function() {
			$.ajax({
		  		type: "PUT",
		  		url: "/users/activate/"+id,
		  		dataType : 'json',
		  		headers: createAuthorizationTokenHeader(),
		  		success: function(data) {
		  			$("#user-"+id).remove();
		  			add(data);
		  		},
		  		error: function(data) {
		  			alert(data);
		  		}
		  	});
		});
	} else {
		$("#users_list").append(newHTML.join(""));
		$("#blBtn-"+id).click(function() {
			$.ajax({
		  		type: "PUT",
		  		url: "/users/block/"+id,
		  		dataType : 'json',
		  		headers: createAuthorizationTokenHeader(),
		  		success: function(data) {
		  			$("#user-"+id).remove();
		  			add(data);
		  		},
		  		error: function(data) {
		  			alert(data);
		  		}
		  	});
		});
	}
	
	
	$("#delBtn-"+id).click(function() {
		$.ajax({
	  		type: "DELETE",
	  		url: "/users/"+id,
	  		headers: createAuthorizationTokenHeader(),
	  		success: function(data) {
	  			$("#user-"+id).remove();
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	});
}