var hsw_addr={
		input:$("<input type='hidden' value='' name ='addr' id='addr'/>"),

		select_id:""
};
$.fn.addr_select=function(){
	var p=$(this).parent();
	p.find(".addr_selected").removeClass("addr_selected").addClass("addr_unselected");
	$(this).removeClass("addr_unselected").addClass("addr_selected");
	var id=$(this).attr("id");
	hsw_addr.input.val(id);
	hsw_addr.select_id=id;
	var level=p.attr("level");
	if(level<3){
		getAddrByParentId(Number(level)+1,id);
	}
};
$.fn.genAddr=function(option){
	var container=$(this);
	container.append(hsw_addr.input);
	$.ajax({
		url:hsw_conf.path+"/addr/l/1",
		method:"GET",
		async:false,
		success:function(data){
			var p=$("<p/>").css({border:0,margin:0,padding:0}).addClass("addr").attr({level:1,id:"addr1"});
			var p2=$("<p/>").css({border:0,margin:0,padding:0}).addClass("addr").attr({level:2,id:"addr2"});
			var p3=$("<p/>").css({border:0,margin:0,padding:0}).addClass("addr").attr({level:3,id:"addr3"});
			for(var i in data){
				var a=$("<a/>").attr({"id":data[i].selfId,"href":"javascript:;"}).addClass("addr_unselected").html(data[i].name);
				a.on("click",function(){
					$(this).addr_select();
				});
				p.append("&nbsp;&nbsp;");
				p.append(a);
				
				
			}
			container.append(p);
			container.append(p2);
			container.append(p3);
		}
	});
};


function getAddrByParentId(level,pid){
	$.ajax({
		url:hsw_conf.path+"/addr/p/"+pid,
		method:"GET",
		async:true,
		success:function(data){
			var p=$("<p/>").css({border:0,margin:0,padding:0}).addClass("addr").attr({level:level,id:"addr"+level});
			for(var i in data){
				var a=$("<a/>").attr({"id":data[i].selfId,"href":"javascript:;"}).addClass("addr_unselected").html(data[i].name);
				a.on("click",function(){
					$(this).addr_select();
				});

				p.append("&nbsp;&nbsp;");
				p.append(a);
				
			}
			$("#addr"+level).replaceWith(p);
		}
	});
}