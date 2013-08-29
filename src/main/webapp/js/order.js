$(document).ready(function(){
	$("#finish").on("click",function(){
		var confirm=window.confirm("确认提交订单？");
		if(confirm){
			document.getElementById("order_form").submit();
		}
	});
});
