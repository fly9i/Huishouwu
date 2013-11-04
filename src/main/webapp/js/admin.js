$(function(){
	$(".adminnav").on("click",function(){
		$("#admin_list li").removeClass("active");
		$(this).parent().addClass("active");
		$("#admin_frame").attr({src:$(this).attr("link")});
	});

});