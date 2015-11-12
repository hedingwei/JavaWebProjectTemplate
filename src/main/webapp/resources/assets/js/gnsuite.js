/*
*
*
*/
(function($) {
    var settings = {
        containerSelector:"",
        breadcrumbSelector:"",
        targetFrame:"",
        ulClass:"",
        activeClass: "active",
        data:[],
        maps:{}    //存放路径path对应的模块或功能名
    };
    $.nav = {
        initNav:function(option){
            $.extend(settings, option);
            settings.containerSelector.append($.nav.addNode(0,settings.data));

            for(var i in settings.ulClass){
                $("[level="+i+"]").addClass(settings.ulClass[i]);
            }
        },
        addNode: function(level,data) {
            var $ul = $("<ul level='"+level+"'></ul>");
            level++;
            for(var i in data){

                //记录路径path对应的模块或功能名
                settings.maps[data[i].path] = data[i].name;

                var hasChild = typeof(data[i].children)=="undefined" || data[i].children.length <= 0?false:true;

                var $li = $("<li></li>");
                //如果有子节点，则改层的 a 标签点击无效
                data[i].path = hasChild?"javascript:;":data[i].path;

                //定义了父子节点的 a 标签属性
                var _a = "<a href='"+data[i].path+"' target='"+settings.targetFrame+"'><i class='" + data[i].picture + "'></i><span class='menu-text'>" + data[i].name + "</span>";
                if (hasChild) {
                    _a += "<b class='arrow icon-angle-down'></b>";
                }
                _a += "</a>";
                var $a = $(_a);

                //为 a 标签绑定手风琴效果和事件
                $a.click(function(){
                    $(this).next("ul").toggle();
                    $(this).parent().siblings().find("ul").hide();
                    $(this).parent().addClass(settings.activeClass);
                    $(this).parent().siblings().removeClass(settings.activeClass).find(settings.activeClass).removeClass(settings.activeClass);

                    var arr = new Array();
                    $(this).parents("li.active").each(function(){
                        var _a = $(this).find("a:first");
                        arr.push({
                            name: _a.text(),
                            href: _a.attr("href"),
                            target: _a.attr("target")
                        });

                    });

                    $.nav.initBreadcrumb(arr);
                });

                $li.append($a);
                if(hasChild){
                    //递归添加子节点
                    $li.append($.nav.addNode(level,data[i].children));
                }
                $ul.append($li);
            }
            return $ul;
        },
        initBreadcrumb: function(arr){
            settings.breadcrumbSelector.html("");
            for(var i=arr.length-1; i>=0; i--){
                var $li = $("<li class='active'><a target='"+arr[i].target+"' class='text' href='"+arr[i].href+"'>"+arr[i].name+"</a></li>");

                $li.click(function(){
                    if($(this).attr('href') === 'javascript:;'){ return }
                    $(this).nextAll().remove();
                });
                settings.breadcrumbSelector.append($li);
            }
        },


        //仅针对共享检测模块--业务策略管理页面的入口按钮
          appendBreadcrumb1: function(data) {

              console.log('23213123123123');
            console.log(settings.breadcrumbSelector.find("li").last().text());

            settings.breadcrumbSelector.find("li").last().remove();

            var $li = $("<li class='active'><a target='"+settings.targetFrame+"' class='text' href='"+data.href+"'>"+data.name+"</a></li>");
           
            settings.maps[data.href] = data.name;

            $li.click(function(){
                if($(this).attr('href') === 'javascript:;'){ return }
                $(this).nextAll().remove();
            });

           
           /**/
            
            settings.breadcrumbSelector.append($li);
        },

        appendBreadcrumb: function(data) {
            var $li = $("<li class='active'><a target='"+settings.targetFrame+"' class='text' href='"+data.href+"'>"+data.name+"</a></li>");
           
            settings.maps[data.href] = data.name;
            $li.click(function(){
                if($(this).attr('href') === 'javascript:;'){ return }
                $(this).nextAll().remove();
            });
            settings.breadcrumbSelector.append($li);
        },

        removeBreadcrumb: function(data){

            /**
             *  思路：如果点击“返回”时的URL的路径存在于 settings.maps（预先存放路径path对应的模块或功能名的集合）中
             *        则将这个功能名以后的面包屑全部删掉
             */
            console.log('231232132131'+settings.maps);
            var name = settings.maps[data.href];
            console.log("------------------"+name);
            console.log(settings.breadcrumbSelector.find("a:contains('"+name+"')").text());
            settings.breadcrumbSelector.find("a:contains('"+name+"')").parent("li").nextAll().remove();
        },

        //仅针对编辑成功或失败后的跳转页面上的返回按钮
         removeBreadcrumb1: function(data){

            /**
             *  思路：如果点击“返回”时的URL的路径存在于 settings.maps（预先存放路径path对应的模块或功能名的集合）中
             *        则将这个功能名以后的面包屑全部删掉
             */
            settings.breadcrumbSelector.find("li").last().remove();
        }
    };

})(jQuery);