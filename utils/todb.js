var fs=require('fs');
var db=require('./db');
var jstr=String(fs.readFileSync('hefei.json'));
var data=eval('('+jstr+')');
var ids={
	f:100001,
	s:1000001
}
for(var i in data){
	obj=data[i];
	console.log("======================================");
	//console.log(obj.first.name+":"+obj.first.value);
	var sql="insert into hsw_buss.address (level,self_id,parent_id,name) values (1,'"+obj.first.name+"','','"+obj.first.value+"')";
	
	db.query(sql);

	sec=obj.second;
	for(var j in sec){
		var objsec=sec[j];
		console.log("\t"+objsec.name+":"+objsec.value);
		var sql2="insert into hsw_buss.address (level,self_id,parent_id,name) values (2,?,?,?)";
		var opt=[objsec.name,obj.first.name,objsec.value];
		db.query(sql2,opt);
	}
}