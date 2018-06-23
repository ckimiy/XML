$(document).ready(function() {
	allCodes();
	
	$("#addCode").click(function() {
		saveCode();
	});
});

var list_1 = "list_1";
var list_2 = "list_2";
var list_3 = "list_3";

var objCateURL = "/codebook/objCategory";
var objTypeURL = "/codebook/objType";
var serviceURL = "/codebook/services";

function allCodes() {
	$.ajax({
		url : objCateURL + '/all',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
			alert(data)
		},
		success : function(data) {
			$("#"+list_1).empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						add(element.id, element.category, list_1, objCateURL);
			});
		}
	});
	
	$.ajax({
		url : objTypeURL + '/all',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
			alert(data)
		},
		success : function(data) {
			$("#"+list_2).empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						add(element.id, element.type, list_2, objTypeURL);
			});
		}
	});
	
	$.ajax({
		url : serviceURL + '/all',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
			alert(data)
		},
		success : function(data) {
			$("#"+list_3).empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						add(element.id, element.service, list_3, serviceURL);
			});
		}
	});
}

function add(id, content, listName, url) {
	var newHTML = [];
	newHTML.push("<div class=\"divBack\" id=\""+listName+"-"+id+"\">");
	newHTML.push("<p class=\"textTemplate sameLine\" id=\"con-"+listName+"-"+id+"\">"+content+"</p>");
	newHTML.push("<div class=\"sameLine\"> <input type=\"button\" class=\"btn\" value=\"Izmeni\" id=\"ediBtn-"+listName+"-"+id+"\"> <input type=\"button\" class=\"btn\" value=\"Izbrisi\" id=\"delBtn-"+listName+"-"+id+"\">");
	newHTML.push("</div>");
	newHTML.push("</div>");
	$("#"+listName).append(newHTML.join(""));
	
	
	$("#ediBtn-"+listName+"-"+id).click(function() {
		editCode(id, listName, content);
	});
	
	$("#delBtn-"+listName+"-"+id).click(function() {
		$.ajax({
	  		type: "DELETE",
	  		url: url + "/" +id,
	  		dataType: "text",
	  		success: function(data) {
	  			$("#"+listName+"-"+id).remove();
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	});
}

function editCode(id, listName, content) {
	$("#editFormDiv").remove();
	var newHTML = [];
	newHTML.push("<div class=\"form_el\" id=\"editFormDiv\">");
	newHTML.push("<form action=\"javascript:void(0)\" id=\"editForm\">");
	newHTML.push("<input required type=\"text\" placeholder=\"\" name=\"content\" id=\"editContent\" required value=\""+content+"\"/><br />");
	newHTML.push("<input type=\"submit\" value=\"Izmeni\" id=\"editCode\"/>");
	newHTML.push("<input type=\"submit\" value=\"Zatvori\" id=\"editClose\"/>");
	newHTML.push(" </form>");
	newHTML.push("</div>");
	$("#"+listName+"-"+id).append(newHTML.join(""));
	
	$("#editCode").click(function() {
		saveEditedCode(id, listName);
	});
	
	$("#editClose").click(function() {
		$("#editFormDiv").remove();
	});
}


function saveEditedCode(id, listName) {
	
	var $form = $("#editForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	if(listName == list_1) {
		$.ajax({
	  		type: "PUT",
	  		url: objCateURL+"/"+id,
	  		data: s,
			contentType: "application/json",
			dataType : 'json',
	  		success: function(data) {
	  			$("#con-"+listName+"-"+id).text(data.category);
	  			$("#editFormDiv").remove();
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	} else if(listName == list_2) {
		$.ajax({
	  		type: "PUT",
	  		url: objTypeURL+"/"+id,
	  		data: s,
			contentType: "application/json",
			dataType : 'json',
	  		success: function(data) {
	  			$("#con-"+listName+"-"+id).text(data.type);
	  			$("#editFormDiv").remove();
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	} else {
		$.ajax({
	  		type: "PUT",
	  		url: serviceURL+"/"+id,
	  		data: s,
			contentType: "application/json",
			dataType : 'json',
	  		success: function(data) {
	  			$("#con-"+listName+"-"+id).text(data.service);
	  			$("#editFormDiv").remove();
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	}
}

function saveCode() {
	var selected = $('#selectForm').find(":selected").val();
	console.log(selected);
	
	var $form = $("#addForm");
	var data = getFormData($form);
	var s = JSON.stringify(data);
	if(selected == "katSmestaja") {
		$.ajax({
	  		type: "POST",
	  		url: objCateURL+"/add",
	  		data: s,
			contentType: "application/json",
			dataType : 'json',
	  		success: function(data) {
	  			add(data.id, data.category, list_1, objCateURL);
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	} else if(selected == "tipSmestaja") {
		$.ajax({
	  		type: "POST",
	  		url: objTypeURL+"/add",
	  		data: s,
			contentType: "application/json",
			dataType : 'json',
	  		success: function(data) {
	  			add(data.id, data.type, list_2, objTypeURL);
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	} else {
		$.ajax({
	  		type: "POST",
	  		url: serviceURL+"/add",
	  		data: s,
			contentType: "application/json",
			dataType : 'json',
	  		success: function(data) {
	  			add(data.id, data.service, list_3, serviceURL);
	  		},
	  		error: function(data) {
	  			alert(data);
	  		}
	  	});
	}
}