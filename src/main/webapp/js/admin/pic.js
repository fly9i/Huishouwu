$(function() {
	$(".del_pic").on("click", function() {

		$(this).button("loading");
		var id = $(this).attr("pic_id");
		$.ajax({
			type : "GET",
			url : "./delpic",
			async : true,
			data : {
				"id" : id
			},
			success : (function(but) {
				return function(res) {
					but.button("reset");
				};
			})($(this))
		});
	});

	$("#upload_pic").on("click", function() {
		$("#form-upload").attr({
			method : "POST"
		}).submit();

	});

	$("#clear_pic_cache").on("click", function() {
		$("#clear_pic_cache").button("loading");
		$.ajax({
			type : "GET",
			url : hsw_conf.path + "/admin/clearpiccache",
			async : true,
			data : {
				val : value,
				type : 'un'
			},
			success : function(res) {
				res = eval("(" + res + ")");
				if (res.code == 200) {
					result = true;
					$("#clear_pic_cache").button("reset");
				} else {
					result = false;
				}
			}
		});
	});
});