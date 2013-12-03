var http = require("http"), $ = require("jquery"), fs = require("fs");

var baseUrl = "http://hf.58.com/";
var option = {
	host : "hf.58.com",
	port : 80,
	path : ""
};
//var allUrl = "http://bj.58.com/zufang/";
var file=process.argv[2];
function getHtml(path, callback) {
	option.path = path;
	http.get(option, function(res) {
		var html = "";
		res.on("data", function(data) {
			html += data;
		}).on("end", function() {
			callback(html);
		}).on("error",function(e){
			console.log(e.message);
		});
	});
}
var time = 0;
var result = [];
var count = 0;

function spideQueue(data) {
	setTimeout(function() {
		console.log("Begin to parse url:"+data.first.path);
		getHtml(data.first.path, function(html) {
			
			data.second = [];
			$(html).find(".subarea a").each(function() {
				var obj = {};
				obj.path = $(this).attr("href");
				obj.name = obj.path.split("/")[1];
				obj.value = $(this).html();
				data.second.push(obj);
			});
			count++;
			if (count == result.length) {
				// console.log(JSON.stringify(result));
				fs.appendFile(file, JSON.stringify(result), function(
						err) {
					if (err)
						console.log(err.message);
				});
			}
		});
	}, 2000 * time);
	time++;
}

getHtml("/zufang/", function(html) {
	$(html).find(".relative dd:eq(0) a").each(function() {
		if ($(this).attr("href").indexOf("javascript")==-1) {
			var obj = {};
			obj.first = {};
			obj.first.path = $(this).attr("href");
			obj.first.name = obj.first.path.split("/")[1];
			obj.first.value = $(this).html();
			result.push(obj);
		}
	});

	for ( var i in result) {
		spideQueue(result[i]);
	}

});