var fs=require('fs');
var db=require('./db');
var crypto = require('crypto');

function md5(str){
    var md5sum = crypto.createHash('md5');
    md5sum.update(str);
    str = md5sum.digest('hex');
    return str;
}
var jstr=String(fs.readFileSync('beijing_2.json'));
var data=eval('('+jstr+')');
var ids={
	f:100001,
	s:1000001
}

var sql_first="insert into hsw_buss.address (level,self_id,parent_id,name) values (1,?,'','北京')";
db.query(sql_first,[md5("1_quanbeijing_北京")]);
for(var i in data){
	obj=data[i];
	console.log("======================================");
	//console.log(obj.first.name+":"+obj.first.value);
	var id=md5(2+"_"+obj.first.name+"_"+obj.first.value);
	if(obj.first.name!='zufang'){

		var sql="insert into hsw_buss.address (level,self_id,parent_id,name) values (2,'"+id+"','"+md5("1_quanbeijing_北京")+"','"+obj.first.value+"')";
		db.query(sql);
	}
	sec=obj.second;
	for(var j in sec){
		var objsec=sec[j];
		console.log("\t"+objsec.name+":"+objsec.value);
		var sql2="insert into hsw_buss.address (level,self_id,parent_id,name) values (3,?,?,?)";
		var opt=[md5("3_"+objsec.name+"_"+objsec.value),id,objsec.value];
		db.query(sql2,opt);
	}
}

