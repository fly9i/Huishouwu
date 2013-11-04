$(function(){
	$(".article").on("mouseover",function(){
		$(this).css({"background-color":"#FFF68F",cursor:"pointer"});
	}).on("mouseout",function(){
		$(this).css({"background-color":"#FFFFFF"});
	}).on("click",function(){
		window.location.href=$(this).find("a").attr("link");
	});
	
	$.ajax({
		type:"GET",
		url:hsw_conf.path +"/news/all",
		async:true,
		success:function(res){
			//res = eval("(" + res + ")");
			var ulAppend=$(document.createDocumentFragment());
			for(var i in res){
				var n=res[i];
				var li=$("<li/>").css({margin:5});
				var link=$("<a/>").attr({href:hsw_conf.path+"/news/"+n.id}).html(n.title);
				li.append(link);
				ulAppend.append(li);
			}
			$("#news_title").replaceWith(ulAppend);
		}
		
	});
});