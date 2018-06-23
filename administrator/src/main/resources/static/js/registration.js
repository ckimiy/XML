$(document).ready(function() {
	$("#register").click(function() {
		var $form = $("#registerForm");
		var data = getFormData($form);
		var s = JSON.stringify(data);
		console.log(s);
		$.ajax({
			
			url: "/agent/register",
			type: "POST",
			data: s,
			contentType: "application/json",
			dataType: "text",
			success: function(data){
				alert(data);
				
			},
			error: function(data) {
				alert(data);
			}
		
		});
	});
});