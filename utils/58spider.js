var http=require("http")
,$=require("jquery")
,fs=require("fs");

var baseUrl="http://bj.58.com/";
var option={
		host:"bj.58.com",
		port:80,
		path:""
};
var allUrl="http://bj.58.com/zufang/";

function getHtml(path,callback){
	option.path=path;
	http.get(option,function(res){
		var html="";
		res.on("data",function(data){
			html+=data;
		}).on("end",function(){
			callback(html);
		});
	});
}
var time=0;
var result=[];
var count=0;

function spideQueue(data){
	setTimeout(function(){
		getHtml(data.first.path,function(html){
			data.second=[];
			$(html).find(".subarea a").each(function(){
				var obj={};
				obj.url=$(this).attr("href");
				obj.name=$(this).html();
				data.second.push(obj);
			});
			count++;
			if(count==result.length){
				//console.log(JSON.stringify(result));
				fs.appendFile("beijing.json",JSON.stringify(result),function(err){
					if(err) console.log(err.message);
				});
			}
		});
	},2000*time);
	time++;
}

getHtml("/zufang/",function(html){
	$(html).find("#filter_quyu dd a").each(function(){
		var obj={};
		obj.first={};
		obj.first.path=$(this).attr("href");
		obj.first.name=$(this).html();
		result.push(obj);
	});
	
	for(var i in result){
		spideQueue(result[i]);
	}
	
});