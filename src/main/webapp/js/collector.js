$(function() {
	$("#form_apply").validate({
		onkeyup : false,

		submitHandler : function() {
			 $('#form_apply').attr({method:"POST",action:hsw_conf.path + "/col/apply"}).submit();
			 
		}
	});
});