$(function() {
	$("#form_apply").validate({
		onkeyup : false,

		submitHandler : function() {
			$("#apply_corp_button").button("loading");
			$.ajax({
				type : "POST",
				async : true,
				url : hsw_conf.path + "/user/apply",
				data : $('#form_register').serialize(),
				success : function(res) {
					$("#reg_button").button("reset");
					res = eval("(" + res + ")");
					alert(res.message);
					if (res.code == 200) {
						window.location.reload();
					}
				}
			});
		}
	});
});