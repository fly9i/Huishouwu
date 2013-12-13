$(function() {
	$("#form_apply").validate({
		onkeyup : false,

		submitHandler : function() {
			$("#apply_corp_button").button("loading");
			$.ajax({
				type : "POST",
				async : true,
				url : hsw_conf.path + "/col/apply",
				data : $('#form_apply').serialize(),
				success : function(res) {
					$("#apply_corp_button").button("reset");
					res = eval("(" + res + ")");
					alert(res.message);
					if (res.code == 200) {
						window.location.reload();
					}
				},
				complete:function(res){
					console.log(res);
					$("#apply_corp_button").button("reset");
				}
			});
		}
	});
});