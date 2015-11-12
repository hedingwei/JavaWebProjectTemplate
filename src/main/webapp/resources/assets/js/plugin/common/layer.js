 // JavaScript Document

(function($){

	function Layer(){
		//console.log("This is a layer");
	}
	
/*	var layer="";
	layer+="<div class='layer' style='display:none;position:fixed; left:0;top:0; width:100%; height:100%; background:rgba(0,0,0,0.1); z-index:999;'>";
	layer+="	<div style='width:240px; margin:0 auto; margin-top:200px; background:#f4f4f4; border:1px solid #ddd; box-shadow:0px 0px 2px; #1a1a1a; padding:20px;'>";
	layer+="		<p><img src='images/loading.gif' style='width: 100px; height:100px; margin-left:46px;'></p>";
	layer+="		<p style='color:#5B98CC;'>正在加载数据，请耐心等候...</p>";
	layer+="	</div>";
	layer+="</div>";*/
	
	
	Layer.prototype.appear = function () {
		
		$(".layer").show();	
	};
	
	Layer.prototype.disappear = function () {
		$(".layer").hide();	
	};
	
	$.Lay = new Layer();
})(jQuery);



