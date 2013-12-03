$(function() {
	$("#update_config").on("click", function() {

		$(this).button("loading");
		var id = $(this).attr("tip");
		$.ajax({
			type : "GET",
			url : hsw_conf.path + "/upconfig",
			async : true,
			data : {
				"id" : id
			},
			success : (function(but) {
				return function(res) {
					but.button("reset");
					res = eval("(" + res + ")");
					if (res.code == 400) {
						alert(res.des);
					} else {
						window.location.reload();
					}
				};
			})($(this))
		});
	});

});