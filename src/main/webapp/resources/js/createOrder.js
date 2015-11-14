$(document).ready(function(){
	
	$('#roomNumberOptions').change(function(){
		$.get('/DevCor/getRoomDevices', {
			roomId: $(this).val()
		}, function(responseHTML){
			$('#serialPortOptions').html(responseHTML);
		})
	});
	
	$('input[type=button]').click(function(){
		window.location = "dashboard";
	})
});