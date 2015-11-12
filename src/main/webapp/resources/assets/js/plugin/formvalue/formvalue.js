// JavaScript Document

function Formvalue(){
	this.init();	
}

Formvalue.prototype.init = function(){
	$(document.body).on("click","input[type='submit']",function(){
		var form = $(this).closest(".minifrom");
		var param = {};
		form.find("input").each(function(){
			if(typeof($(this).attr("field")) != 'undefined'){
				if($(this).attr("type")=="checkbox" ||$(this).attr("type")=="radio" ){
					
					if(typeof(param[$(this).attr("field")])=='undefined'){
						param[$(this).attr("field")] = {
							//content : $(this),
							//value : $(this).attr("checked")=="checked"?true:false
							content : [],
							value : []
						};
					}
					//console.log($(this).attr("checked"))
					param[$(this).attr("field")].content.push($(this));
					if($(this).get(0).checked==true){
						param[$(this).attr("field")].value.push($(this).val());
					}
					
					
					
				}else{
					if(typeof(param[$(this).attr("field")])=='undefined'){
						param[$(this).attr("field")] = {
							//content : $(this),
							//value : $(this).val()
							content : [],
							value : []
						};
					}
					param[$(this).attr("field")].content.push($(this));
					param[$(this).attr("field")].value.push($(this).val());
					
				}
				
			}
			
		});
		
		form.find("textarea").each(function(){
			if($(this).attr("field")!=""||typeOf($(this).attr("field"))!=undefined){
				
				if(typeof(param[$(this).attr("field")])=='undefined'){
					param[$(this).attr("field")] = {
						//content : $(this),
						//value : $(this).val()
						content : [],
						value : []
					};
				}
				param[$(this).attr("field")].content.push($(this));
				param[$(this).attr("field")].value.push($(this).val());
			}
		});
		form.find("select").each(function(){
			if($(this).attr("field")!=""||typeOf($(this).attr("field"))!=undefined){
			
				if(typeof(param[$(this).attr("field")])=='undefined'){
					param[$(this).attr("field")] = {
						//content : $(this),
						//value : $(this).val()
						content : [],
						value : []
					};
				}
				param[$(this).attr("field")].content.push($(this));
				param[$(this).attr("field")].value.push($(this).val());

			}
		});
		eval(form.attr('func')+"(param)");
	});
};

