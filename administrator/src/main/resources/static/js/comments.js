$(document).ready(function() {
	allComments();
});

function allComments() {
	$.ajax({
		url : '/comment/all',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
		},
		success : function(data) {
			$("#comments_list").empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						var newHTML = [];
						var id = element.id;
						newHTML.push("<div class=\"divBack\" id=\"comment-"+id+"\">");
						newHTML.push("<p class=\"textTemplate sameLine\">"+element.content+"</p>");
						newHTML.push("<div class=\"sameLine\"> <input type=\"button\" class=\"btn\" value=\"Odobri\" id=\"appBtn-"+id+"\"> <input type=\"button\" class=\"btn\" value=\"Izbrisi\" id=\"delBtn-"+id+"\">");
						newHTML.push("</div>");
						newHTML.push("</div>");
						$("#comments_list").append(newHTML.join(""));
						
						
						$("#appBtn-"+id).click(function() {
							$.ajax({
						  		type: "PUT",
						  		url: "/comment/approve/"+id,
						  		dataType: "text",
						  		success: function(data) {
						  			$("#comment-"+id).remove();
						  		},
						  		error: function(data) {
						  			alert(data);
						  		}
						  	});
						});
						
						$("#delBtn-"+id).click(function() {
							$.ajax({
						  		type: "DELETE",
						  		url: "/comment/disapprove/"+id,
						  		dataType: "text",
						  		success: function(data) {
						  			$("#comment-"+id).remove();
						  		},
						  		error: function(data) {
						  			alert(data);
						  		}
						  	});
						});
			});
		}
	});
}