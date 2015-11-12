 // JavaScript Document

(function($){

	function Log(){
		//console.log("create log instance!");
	}
	
/*	Log.prototype.info = function (flag,detail,fn) {
		
		$.post("UserActionLogServlet",{
			client_user:client_user,
			client_ip:client_ip,
			client_cookie:client_cookie,
			action_flag:flag,
			action_detail:detail
		},function(d){
			fn(d);
		});	
	};*/
	
	Log.prototype.info = function (flag,detail,arr,fn) {
		
		$.post("UserActionLogServlet",{
			client_user:client_user,
			client_ip:client_ip,
			client_cookie:client_cookie,
			action_flag:flag,
			action_detail:detail,
			textarea_detail:arr
		},function(d){
			fn(d);
		});	
	};
	
	$._log = new Log();
})(jQuery);



