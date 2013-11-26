var mysql = require('mysql');
var pool  = mysql.createPool({
  host     : 'rosegun.com',
  user     : 'hsw0911',
  password : 'hsw0912',
});

exports.query=function(sql,option){
	pool.getConnection(function(err, connection) {
		connection.query(sql,option,function(){
			if (err) { 
				connection.rollback(function() {
	        		throw err;
	      		});
	    	}
	    	connection.release();
		});
	});
}