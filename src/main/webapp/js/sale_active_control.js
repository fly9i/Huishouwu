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

function getTypeConfig(t_name){
	var frag=$(document.createDocumentFragment());
	$.ajax({
		type : "GET",
		async : true,
		url : "./config/"+t_name,
		success : function(res) {
			/*<label class="radio"><input type="radio" name="bx_desc" checked
			value="150lt"/>150L以下 
		</label> 
		*/
			for (var i in res){
				var label=$("<label/>").addClass("radio");
				var input = $("<input/>").attr({type:"radio",name:"bx_desc",checked:"true",value:res[i].f_id});
				label.append(input).append(res[i].feature);
				frag.append(label);
			}
			
		}
	});
}