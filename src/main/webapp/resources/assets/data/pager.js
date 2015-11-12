// JavaScript Document
(function($){
	function Pager(options){
		var defaults = {
			container:"",//容器
			operatObj:"",//操作对象
			pagesize:10,//每页记录数
			showPageNum:10//显示多少按钮
			
			};
		this.options = $.extend(true,defaults,options);
		this.obj = $(this.options.container);//容器对象
		this.totalsize;//总记录条数
		this.pagesize = this.options.pagesize;//分页大小
		this.totalpage;//总页数
		this.currentpage =1 ;//当前页
		this.state = 0;//定义上一页状态；
		// //识别容器对象
		// if($("."+this.options.container).length > 0){
		// 	this.obj = $("."+this.options.container);
		// 	this.obj.attr("id",this.options.container);
		// 	}else if ($("#"+this.options.container).length > 0){
		// 		this.obj = $("#"+this.options.container);
		// 		this.obj.addClass("flowpanel");
				
		// 		}else{
		// 		this.obj = $(this.options.container);
		// 		this.obj.addClass("flowpanel");
		// 			};
		//默认初始化结构；
		this.init();
		}
		
	//初始化
	Pager.prototype.init = function(){
			
			this.totalsize = $(this.options.operateObj,this.obj).length;
			console.log(this.totalsize);
			this.totalpage = Math.ceil(this.totalsize/this.pagesize);

			this.pages = $("<ul class='pagination'></ul>");//分页结构对象
			//this.pages.css({width:"100%",height:"20px"});
			this.obj.parent().after(this.pages);
			//加载分页结构
			this.page();
		
		};
		
	//page结构
	Pager.prototype.page = function(){
			var $this = this;
			var homepage = $("<li class= 'homepage'><a href='javascript:;'>首页</a></li>");
			var prepage = $("<li class= 'prepage'><a href='javascript:;'>上一页</a></li>");
			var nextpage = $("<li class= 'nextpage'><a href='javascript:;'>下一页</a></li>");
			var endpage = $("<li class= 'endpage'><a href='javascript:;'>末页</a></li>");
			var cont=$("<li class='info'><a href='javascript:;'>共"+this.totalsize+"条记录，共"+this.totalpage+"页</a></li>");
			// this.pages.append(homepage).append(prepage).append(cont);
			this.pages.append(cont).append(prepage);
			var pageoption = "";
			for(var i = 1;i<=this.totalpage;i++){
				pageoptoin = $("<li><a class='num' href='javascript:;'>"+i+"</a></li>");
				
				this.pages.append(pageoptoin);
				
				}
			// this.pages.append(nextpage).append(endpage);
			this.pages.append(nextpage);
			this.getcurr(this.currentpage);
			
			// 事件
			homepage.click(function(){
				$this.currentpage = 1;
				$this.state = 0;
				$this.getcurr($this.currentpage);
				
				});
			prepage.click(function(){
				$this.currentpage--;
				$this.state = 0;
				$this.getcurr($this.currentpage);
				
				});
			$(".num",this.pages).click(function(){
				$this.currentpage = $(this).html()*1;
				$this.getcurr($this.currentpage);
				
				});
			nextpage.click(function(){
				$this.currentpage++;
				$this.state = 1;
				$this.getcurr($this.currentpage);
				
				});
			endpage.click(function(){
				$this.state = 1;
				$this.currentpage = $this.totalpage;
				$this.getcurr($this.currentpage);
				});
		
		};
	Pager.prototype.getcurr = function(currentpage){
		var currentpage1 =currentpage2 = currentpage;

		$(this.options.operateObj,this.obj).hide();
		$(".num",this.pages).hide().removeClass("show");
		
		//li
		if(currentpage>this.totalpage){currentpage=this.totalpage;}
		if(currentpage<1){currentpage=1;}
		
		var startNum =(currentpage-1)*this.pagesize;
		var totalNum = (currentpage-1)*this.pagesize+this.pagesize;
		for(var i = startNum;i<totalNum;i++){
				$(this.options.operateObj,this.obj).eq(i).show();
			};
			
		//分页选项	
		//当前页样式；
		this.currstate;
		//$(".num",this.pages).eq(currentpage1-1).css({background:"#333",color:"white"}).siblings().css({background:"white",color:"black"});
		$(".num",this.pages).eq(currentpage1-1).parent("li").addClass("active").siblings().removeClass("active");
		currentpage1 = Math.floor(currentpage1/this.options.showPageNum);
		var sNum = currentpage1*this.options.showPageNum;
		var tNum = currentpage1*this.options.showPageNum+this.options.showPageNum;

		for(var i = sNum;i<tNum;i++){
				$(".num",this.pages).eq(i).show();
				$(".num",this.pages).eq(i).addClass("show");
			};
		if(currentpage1>0)
		$(".num",this.pages).eq(currentpage1*this.options.showPageNum-1).show();
		
		};
		
	//转换为插件
	$.pager = function(options){
		return	new Pager(options);
	};
	


})(jQuery);