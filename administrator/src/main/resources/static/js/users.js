$(document).ready(function() {
	allUsers();
});

function allUsers() {
	$.ajax({
		url : '/users/all',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
		},
		success : function(data) {
			$("#users_list").empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						var newHTML = [];
						var id = element.id;
						newHTML.push("<div class=\"divBack\" id=\"user-"+id+"\">");
						newHTML.push("<p class=\"textTemplate sameLine\">"+element.username+" | "+element.email+"</p>");
						newHTML.push("<div class=\"sameLine\"><input type=\"button\" class=\"btn\" value=\"Blokiraj\" id=\"blBtn-"+id+"\"> <input type=\"button\" class=\"btn\" value=\"Aktiviraj\" id=\"acBtn-"+id+"\"> <input type=\"button\" class=\"btn\" value=\"Obrisi\" id=\"delBtn-"+id+"\">");
						newHTML.push("</div>");
						newHTML.push("</div>");
						$("#users_list").append(newHTML.join(""));
						$("#blBtn-"+id).click(function() {
							$.ajax({
						  		type: "POST",
						  		url: "/users/block/"+id,
						  		success: function() {
						  			
						  		},
						  		error: function() {
						  			
						  		}
						  	});
						});
						
						$("#acBtn-"+id).click(function() {
							$.ajax({
						  		type: "POST",
						  		url: "/users/activate/"+id,
						  		success: function() {
						  			
						  		},
						  		error: function() {
						  			
						  		}
						  	});
						});
						
						$("#delBtn-"+id).click(function() {
							$.ajax({
						  		type: "POST",
						  		url: "/users/delete/"+id,
						  		success: function() {
						  			
						  		},
						  		error: function() {
						  			
						  		}
						  	});
						});
			});
		}
	});
}