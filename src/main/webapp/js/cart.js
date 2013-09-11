$(document).ready(function(){
	caculatePrice();
	$("#finish").on("click",function(){
		window.location.href="./orderpre"
	});
});
function caculatePrice(){
	var price=0;
	$("table td.price").each(function(){
		price+=Number($(this).html());
	});
	$(".total").html(price);
}