// JavaScript Document

(function($){ 
	$.fn.menu = function(option){
		var obj = this;
		var def = {
                        obj:'',
			data:''
		}
		
		var data = $.extend(def,option);
		init(data.data,data.obj);
	};
	function init(data,obj){
		$("."+obj.name+"").html("");
		$("."+obj.child.name+"").html("");
		for(var i in data){
			$("."+obj.name+"").append("<option value='"+data[i].name+"'>"+data[i].title+"</option>");
		}
		for(var i in data[0].child){
			$("."+obj.child.name+"").append("<option value='"+data[0].child[i].name+"'>"+data[0].child[i].title+"</option>");
		}
		$("."+obj.name).change(function(){
			var value = $(this).val();
			for(var i in data){
				if(data[i].name == value){
					var datas = data[i].child;
					$("."+obj.child.name+"").html("");
					for(var i in datas){
						$("."+obj.child.name+"").append("<option value='"+datas[i].name+"'>"+datas[i].title+"</option>");
					}
				}
			}
			
		});
	}

})(jQuery); 
