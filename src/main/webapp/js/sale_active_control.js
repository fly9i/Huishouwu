$(document).ready(function() {
	$(".tab-content li").css({display : "none"});
	$(".tab-content .content_show").css({display:"block"});
	$("#sale_list li a").on("click", function(e) {
		e.preventDefault();
		$(this).tab("show");
		var showid = "#" + $(this).attr("fordiv");
		$(".tab-content .content_show").css({display:"none"});
		$(showid).addClass("content_show").css({"display":"block"});
	});
});

function getTypeConfig(id){
	var frag=$(document.createDocumentFragment());
	$.ajax({
		type : "GET",
		async : true,
		url : "./config/bx",
		success : function(res) {
			res=eval("("+res+")");
			alert(res.message);
			if (res.code == 200) {
				window.location.reload();
			}
		}
	});
}