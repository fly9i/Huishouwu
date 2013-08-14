function YXM_valided_true() { // 验证码输入正确后的操作
	console.log("correct validate");
}
function YXM_valided_false() {
	console.log("err validate");
}



$(document).ready(function() {

	$("#form_register").validate({
		submitHandler : function() {
			$.ajax({
				type : "POST",
				async : true,
				url : "./user/add",
				data : $('#form_register').serialize(),
				success : function(res) {
					if (res == "ok") {
						alert("注册成功");
						close();
					} else {
						alert(res);
					}
				}
			});
		}
	});
	$("#" + tab).addClass("active");
	var close = function() {
		$("#cover").hide();
		$("#form_reg").hide();
	};
	$("#close").on("click", close);

	$("#reg").on("click", function() {
		$("#cover").show();
		$("#form_reg").show();
	});

	$("#login_btn").on("click", function() {
		$(this).addClass("disabled");
		$.ajax({
			url : "./user/login",
			data : $("#form_login").serialize(),
			type : "POST",
			async : true,
			success : (function(btn) {
				return function(res) {
					btn.removeClass("disabled");
					alert(res.result);
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
