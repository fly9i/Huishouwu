$(function(){
	$(".del_pic").on("click",function(){
		
		$(this).button("loading");
		var id=$(this).attr("pic_id");
		$.ajax({
			type:"GET",
			url:"./delpic",
			async : true,
			data:{"id":id},
			success:(function(but){
				return function(res){
					but.button("reset");
				};
			})($(this))
		});
	});
	
	$("#upload_pic").on("click",function(){
		$("#form-upload").attr({method:"POST"}).submit();
		
	});
});