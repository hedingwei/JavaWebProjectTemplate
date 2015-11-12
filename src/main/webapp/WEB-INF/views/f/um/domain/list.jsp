<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta http-equiv="_lient-Type" _lient="text/html; charset=UTF-8">
    <meta name="viewport" _lient="width=device-width, initial-scale=1.0">
    <!-- basic styles -->
    <link href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-rtl.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/ace-skins.min.css">
    <style>
        .form-actions{margin-top: 0; padding: 0px; margin-bottom: 0; }
        #nestable {max-width: 90%;}
    </style>
</head>
<body>
<div class="main-_liainer" style="min-width:700px;">

    <div class="" style=" margin-right: 0;margin-left: 0;">

        <div class="col-sm-12">
            <div class="dd" id="nestable">
                <ol class="dd-list">
                    <li class="dd-item" data-id="5">
                        <button data-action="collapse" class="collapse" type="button" style="display: block;" onclick='btnhide(this);'>Collapse</button>
                        <button data-action="expand" class="expand" type="button" style="display: none;" onclick='btnhide(this);'>Expand</button>

                        <div class="dd-handle">
                            全局
                            <span class="lighter grey">

                            </span>

                            <div class="pull-right action-buttons">
                                <a class="blue appendBreadcrumb1" href="${pageContext.request.contextPath}/f/um/domain/add.view.do?pid=0&pname=全局&pcode=100000000">
                                    <i class="icon-pencil bigger-130"></i>
                                    添加子区域
                                </a>
                            </div>
                        </div>
                        <ol class="dd-list" id="pli">

                        </ol>
                    </li>
                </ol>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/jquery.slimscroll.min.js"></script>
<!-- <script src="${pageContext.request.contextPath}/resources/assets/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/ace.min.js"></script> -->
<!-- <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.nestable.min.js"></script>
 -->

<script type="text/javascript">

    //将数组转化成标准树形JSON, 参见:http://www.ztree.me/v3/api.php  transformToTreeFormat
    function transformTozTreeFormat(sNodes) {
        var i,l,
            key = "id",
            parentKey = "pid",
            childKey = "children";
        if (!key || key=="" || !sNodes) return [];

        var r = [];
        var tmpMap = [];
        for (i=0, l=sNodes.length; i<l; i++) {
            tmpMap[sNodes[i][key]] = sNodes[i];
        }
        for (i=0, l=sNodes.length; i<l; i++) {
            if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
                if (!tmpMap[sNodes[i][parentKey]][childKey])
                    tmpMap[sNodes[i][parentKey]][childKey] = [];
                tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
            } else {
                r.push(sNodes[i]);
            }
        }
        return r;
    }

    function del(obj){
        var c = confirm("确定要删除该区域吗？");
        if(c){
//            alert($(obj).attr("href"));
            window.location = $(obj).attr("href");
        }
    }

   function btnhide(obj)
    {
       
        /* alert(obj.type);*/
        if($(obj).attr("class")=='collapse')
        {
            
            $(obj).css("display","none");
            $(obj).siblings("button").css("display","block");

            $(obj).siblings("ol").css("display","none");
        }

         if($(obj).attr("class")=='expand')
        {
           
            $(obj).css("display","none");
            $(obj).siblings("button").css("display","block");
            $(obj).siblings("ol").css("display","block");
        }
    }


    jQuery(function($){

        //递归遍历树形 JSON
        function loop(data,pli){

            for(var i in data){
                var hasCild = data[i].children?true:false;

                var _li = '';
                if(hasCild){
                    _li += "<li class='dd-item' data-id='7'>";
                    _li += "<button data-action='collapse' type='button'  class='collapse' style='display: block;' onclick='btnhide(this);'>Collapse</button>";
                     _li += "<button data-action='expand' class='expand' type='button' style='display: none;' onclick='btnhide(this);'>Expand</button>";
                }else{
                    _li += "<li class='dd-item item-green' data-id='7'>";
                }

                _li += "    <div class='dd-handle'> "+data[i].name;
                _li += "        <span class='lighter grey'>";
                _li += "            "+data[i].iPRange;
                _li += "        </span>";
                _li += "        <div class='pull-right action-buttons'>";
                _li += "            <a class='blue appendBreadcrumb1' href='${pageContext.request.contextPath}/f/um/domain/add.view.do?pid="+data[i].id+"&pname="+data[i].name+"&pcode="+data[i].pcode+"'>";
                _li += "                <i class='icon-pencil bigger-130'></i>";
                _li += "                添加子区域";
                _li += "            </a>";
                _li += "";
                _li += "            <a class='blue appendBreadcrumb1' href='${pageContext.request.contextPath}/f/um/domain/edit.view.do?id="+data[i].id+"&pname="+data[i].name+"&pcode="+data[i].pcode+"'>";
                _li += "                <i class='icon-pencil bigger-130'></i>";
                _li += "                编辑";
                _li += "            </a>";
                _li += "";
                _li += "            <a class='red' onclick='del(this);return false' href='${pageContext.request.contextPath}/f/um/domain/delete.action.do?id="+data[i].id+"&pcode="+data[i].pcode+"'>";
                _li += "                <i class='icon-trash bigger-130'></i>";
                _li += "                删除";
                _li += "            </a>";
                _li += "        </div>";
                _li += "    </div>";
                _li += "";
                _li += "</li>";

                var $li = $(_li);
                if(hasCild){
                    var $ol = $("<ol class='dd-list' id='area_"+data[i].id+"'></ol>");
                    $li.append($ol);
                    loop(data[i].children, $ol);
                }
                pli.append($li);
            }
        }

        loop(transformTozTreeFormat(${uiareas}),$("#pli"));

        /*  $('.dd').nestable();*/

        $(".appendBreadcrumb1").each(function(){
            $(this).bind("click",function(){
                var obj = {
                    name: $(this).text(),
                    href: $(this).attr("href")
                }
                top.$.nav.appendBreadcrumb(obj);
            });
        });




    });
</script>
</body>
</html>