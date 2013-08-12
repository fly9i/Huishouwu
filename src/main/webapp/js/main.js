$(document).ready(function(){
	$("#"+tab).addClass("active");
	var fn=function(){
		$("#cover").hide();
		$("#form_reg").hide();
	}
	$("#close").on("click",fn);
	$("#reg_button").on("click",fn);
	$("#reg").on("click",function(){
		$("#cover").show();
		$("#form_reg").show();
	})
});