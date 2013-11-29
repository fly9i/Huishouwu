var hsw_addr = {
	input : $("<input type='hidden' value='' name ='addr' id='addr'/>"),
	container : null,
	select_id : "",
};
$.fn.addr_select = function() {
	var p = $(this).parent();
	p.find(".addr_selected").removeClass("addr_selected").addClass(
			"addr_unselected");
	$(this).removeClass("addr_unselected").addClass("addr_selected");
	var id = $(this).attr("id");
	hsw_addr.input.val(id);
	hsw_addr.select_id = id;
	var level = p.attr("level");
	if (level == 1) {
		$("#addr2").remove();
		$("#addr3").remove();
	} else if (level == 2) {
		$("#addr3").remove();
	}

	if (level < 3) {
		getAddrByParentId(Number(level) + 1, id);
	}
};
$.fn.genAddr = function(option) {
	var container = $(this);
	hsw_addr.container = container;
	container.append(hsw_addr.input);
	var l = option.level || 1;
	var str = l == 1 ? "<span>城市</span>" : "<span>商圈</span>";
	$.ajax({
		url : hsw_conf.path + "/addr/l/" + l,
		method : "GET",
		async : false,
		success : function(data) {
			var p = $("<p/>").css({
				border : 0,
				margin : 0,
				padding : 0
			}).addClass("addr").attr({
				level : 1,
				id : "addr" + l
			}).html(str);
			for ( var i in data) {
				var a = $("<a/>").attr({
					"id" : data[i].selfId,
					"href" : "javascript:;"
				}).addClass("addr_unselected").html(data[i].name);
				a.on("click", function() {
					$(this).addr_select();
				});
				p.append("&nbsp;&nbsp;");
				p.append(a);

			}
			container.append(p);
		}
	});
};

function getAddrByParentId(level, pid) {
	var str = (level == 1) ? "<span>区域</span>" : "<span>商圈</span>";
	$.ajax({
		url : hsw_conf.path + "/addr/p/" + pid,
		method : "GET",
		async : false,
		success : function(data) {
			var p = $("<p/>").css({
				border : 0,
				margin : 0,
				padding : 0
			}).addClass("addr").attr({
				level : level,
				id : "addr" + level
			}).html(str);
			for ( var i in data) {
				var a = $("<a/>").attr({
					"id" : data[i].selfId,
					"href" : "javascript:;"
				}).addClass("addr_unselected").html(data[i].name);
				a.on("click", function() {
					$(this).addr_select();
				});

				p.append("&nbsp;&nbsp;");
				p.append(a);

			}
			hsw_addr.container.append(p);
		}
	});
}