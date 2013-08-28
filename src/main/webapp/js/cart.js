$(document).ready(function(){
	caculatePrice();
});
function caculatePrice(){
	var price=0;
	$("table td.price").each(function(){
		price+=Number($(this).html());
	});
	$(".total").html(price);
}