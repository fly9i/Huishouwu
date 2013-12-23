$(function(){
	$(".img_show").each(function(i){
		var img=$(this).find("img");
		$(this).on("click",function(){
			console.log(img.attr("src"));
			window.open(img.attr("src"),"img","menubar=no,location=no,status=no,titlebar=no,top=0,left=0,width=800,height=600");
		});
	});
	
	$("#agree").on("click",function(){
		var colId=$(this).attr("col-id");
		$("#agree").button("loading");
		$.ajax({
			method:"GET",
			url:hsw_conf.path +"/col/op/"+colId,
			data:{op:"agree"},
			success:function(data){
				$("#agree").button("reset");
				var res=eval("("+data+")");
				alert(res.des);
				if(res.code==200){
					window.location.reload();
				}
			}
			
		});
	});
	
	$("#ignore").on("click",function(){
		var colId=$(this).attr("col-id");
		
		$("#ignore").button("loading");
		$.ajax({
			method:"GET",
			url:hsw_conf.path +"/col/op/"+colId,
			data:{op:"ignore"},
			success:function(data){
				$("#ignore").button("reset");
				var res=eval("("+data+")");
				alert(res.des);
				if(res.code==200){
					window.location.reload();
				}
			}
			
		});
	});
});