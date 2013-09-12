function addCheckMethod() {
	$.validator.addMethod("checkuser", function(value, element) {
		var result = false;
		$.ajax({
			type : "GET",
			url : hsw_conf.path + "/user/check",
			async : false,
			data : {
				val : value,
				type : 'un'
			},
			success : function(res) {
				res = eval("(" + res + ")");
				if (res.code == 200) {
					result = true;
				} else {
					result = false;
				}
			}
		});
		return result;
	}, "用户名已被使用");

	$.validator.addMethod("checkmobile", function(value, element) {
		var result = false;
		$.ajax({
			type : "GET",
			url : hsw_conf.path + "/user/check",
			data : {
				val : value,
				type : 'mb'
			},
			async : false,
			success : function(res) {
				res = eval("(" + res + ")");
				if (res.code == 200) {
					result = true;
				} else {
					result = false;
				}
			}
		});
		return result;
	}, "电话号码已被使用");

	$.validator.addMethod("checkemail", function(value, element) {
		var result = false;
		$.ajax({
			type : "GET",
			url : hsw_conf.path + "/user/check",
			data : {
				val : value,
				type : 'em'
			},
			async : false,
			success : function(res) {
				res = eval("(" + res + ")");
				if (res.code == 200) {
					result = true;
				} else {
					result = false;
				}
			}
		});
		return result;
	}, "邮件地址已被使用");
}

$(document).ready(function() {
	addCheckMethod();
	$("#form_register").validateSetup({
		onChange : false,
		onKeyup : false,
		onBlue : true
	});

	$("#form_register").validate({

		submitHandler : function() {
			$.ajax({
				type : "POST",
				async : true,
				url : hsw_conf.path + "/user/add",
				data : $('#form_register').serialize(),
				success : function(res) {
					res = eval("(" + res + ")");
					alert(res.message);
					if (res.code == 200) {
						window.location.reload();
					}
				}
			});
		}
	});
	if ((typeof tab) != 'undefined') {
		$("#" + tab).addClass("active");
	}
	var close = function() {
		$("#cover").hide();
		$("#form_reg").hide();
	};
	$("#close").on("click", close);

	$("#reg").on("click", function() {
		$("#cover").show();
		$("#form_reg").show();
	});

	$("#logout").on("click", function() {
		$.ajax({
			url : hsw_conf.path + "/user/logout",
			async : true,
			type : "GET",
			success : function(res) {
				res = eval("(" + res + ")");
				if (res.code == 200) {
					window.location.reload();
				} else {
					alert(res.message);
				}
			}
		});
	});

	$("#login_btn").on("click", function() {
		$(this).button("loading");
		$.ajax({
			url : hsw_conf.path + "/user/login",
			data : $("#form_login").serialize(),
			type : "POST",
			async : true,
			success : (function(btn) {
				return function(res) {
					btn.button("reset");
					res = eval("(" + res + ")");
					if (res.code == 200) {
						window.location.reload();
					} else {
						alert(res.message);
					}
				};
			})($(this))
		});
	});

});

$.fn.changeDiv = function() {
	return this.each(function() {
		var arr_e = $("li", this);
		arr_e.css({
			position : "absolute"
		});
		arr_e.parent().css({
			margin : "0",
			padding : "0",
			"list-style" : "none",
			overflow : "hidden"
		});

		var active = arr_e.filter(".active").length ? arr_e.filter(".active")
				: arr_e.first();
		var next = active.next().length ? active.next() : arr_e.first();
		active.css({
			"z-index" : 9
		});
		next.css({
			opacity : 0.0,
			"z-index" : 10
		}).addClass('active').animate({
			opacity : 1.0
		}, 1000, function() {
			active.removeClass('active').css({
				"z-index" : 8
			});
		});

	});
};
