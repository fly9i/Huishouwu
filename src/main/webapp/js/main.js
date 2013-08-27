




$(document).ready(function() {
	
	$("#form_register").validate({
		submitHandler : function() {
			$.ajax({
				type : "POST",
				async : true,
				url : "./user/add",
				data : $('#form_register').serialize(),
				success : function(res) {
					res=eval("("+res+")");
					alert(res.message);
					if (res.code == 200) {
						window.location.reload();
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
	
	$("#logout").on("click",function(){
		$.ajax({
			url:"user/logout",
			async:true,
			type:"GET",
			success:function(res){
				res=eval("("+res+")");
				if(res.code==200){
					window.location.reload();
				}else{
					alert(res.message);
				}
			}
		});
	});

	$("#login_btn").on("click", function() {
		$(this).button("loading");
		$.ajax({
			url : "./user/login",
			data : $("#form_login").serialize(),
			type : "POST",
			async : true,
			success : (function(btn) {
				return function(res) {
					btn.button("reset");
					res=eval("("+res+")");
					if(res.code==200){
						window.location.reload();
					}else{
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
