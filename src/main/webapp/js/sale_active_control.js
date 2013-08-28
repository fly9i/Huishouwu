function addMovie(e) {
	// this.on("click",function(e){

	var addto = $(e.target).attr("addto");
	var form = $("#form_" + addto)
	var addtoNode = "a[fordiv=" + addto + "]";
	var jpg = $(addtoNode).find("img").attr("src");
	var img = $("<img/>").css({
		"z-index" : 999,
		border : 0,
		position : 'absolute',
		width : 40,
		height : 40,
		left : e.pageX,
		top : e.pageY
	}).attr("src", jpg);
	$("body").append(img);
	var target = $(addtoNode).offset();
	img.animate({
		left : target.left,
		top : target.top - 40
	}, 500, null, function() {
		img.animate({
			top : target.top
		}, 500, null, function() {
			img.remove();
			var cc = $(".count_" + addto);
			var addCount = form.find("input[name=count]").val();
			if (cc.length) {
				cc.html(Number(cc.html()) + Number(addCount));
			} else {
				$(addtoNode).append(
						$("<span class='badge badge-success count_" + addto
								+ "'>" + addCount + "</span>"));
			}
		});

	});

	// });
};
$(document).ready(function() {
	// $(".add").addMovie();
	$(document).on("click", ".add", function(e){
		addCart($(this).parents("form"));
		addMovie(e);
		
	});
	$(document).on("click",".finish",function(){
		window.location.href="cart";
	});
	$(".tab-content li").css({
		display : "none"
	});
	$(".tab-content .content_show").css({
		display : "block"
	});
	getTypeConfig($(".tab-content .content_show").attr("id"));
	$(document).on("click", "#sale_list li a", function(e) {
		e.preventDefault();
		$(this).tab("show");
		var showid = "#" + $(this).attr("fordiv");
		getTypeConfig($(this).attr("fordiv"));
		$(".tab-content .content_show").css({
			display : "none"
		});
		$(showid).addClass("content_show").css({
			"display" : "block"
		});
	});
});

function addCart(form,callback,e){
	$.ajax({
		type:"POST",
		url:"cart/add",
		async:false,
		data:form.serialize(),
		success:function(res){
			res=eval("("+res+")");
			if(res.code!=200){
				alert(res.des);
			}
			//callback(e);
		}
	});
	
}

function getTypeConfig(t_name) {
	var frag = $(document.createDocumentFragment());
	if ($("#form_" + t_name + " .form_container div").length > 0) {
		return;
	}
	$("#form_" + t_name + " .form_container").html("");
	$
			.ajax({
				type : "GET",
				async : false,
				url : "./config/" + t_name,
				beforeSend : function(xhr) {
					
					$("#form_" + t_name + " .form_container").append(
							$("<span/>").html("正在加载......"));
				},
				success : function(res) {
					$("#form_" + t_name + " .form_container").html("");
					/*
					 * <label class="radio"><input type="radio" name="bx_desc"
					 * checked value="150lt"/>150L以下 </label>
					 */
					var label_des = $("<label/>").addClass("control-label")
							.html("描述");
					var div_control1 = $("<div/>").addClass("control-group");
					var div_control2 = $("<div/>").addClass("control-group");
					var div_control3 = $("<div/>").addClass("control-group");
					var div_control_label = $("<div/>").addClass("controls");
					for ( var i in res) {
						var label = $("<label/>").addClass("radio");
						var input = $("<input/>").attr({
							type : "radio",
							name :"desc",
							checked : "true",
							value : res[i].f_id
						});
						label.append(input).append(res[i].feature);
						div_control_label.append(label);
					}
					div_control1.append(label_des).append(div_control_label);
					var addBut = $("<input/>").addClass("btn controls add")
							.attr({
								type : "button",
								addto : res[0].t_name,
								value : "添加" + res[0].des
							});
					var finalBtn = $("<input/>").addClass("btn finish")
					.attr({
						type : "button",
						value : "进入回收车结算"
					});
					div_control3.append(addBut).append("&nbsp;&nbsp;").append(finalBtn);

					var label_count = $("<label/>").addClass("control-label")
							.attr({
								"for" : "count"
							}).html("数量");
					var count_controls = $("<div/>").addClass("controls")
							.append($("<input/>").attr({
								type : "text",
								name : "count",
								value : "1",
								id : "count",
								placeholder : "数量"
							}));
					div_control2.append(label_count).append(count_controls);

					frag.append(div_control1).append(div_control2).append(
							div_control3);
					$("#form_" + res[0].t_name + " .form_container").append(
							frag);

				}
			});
}