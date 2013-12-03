$(function(){
	$(".data_opr").on("click",function(){
		var data=eval("("+$(this).attr("data")+")");
		$(this).button("loading");
		$.ajax({
			type:"GET",
			async:true,
			url:"./orderdel",
			data:{"id":data.orderid},
			success:(function(target){
				return function(res){
					target.button("reset");
					window.location.reload();
				};
			})($(this))
		});
	});
});