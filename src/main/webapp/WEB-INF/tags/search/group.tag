<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag pageEncoding="utf-8" %>



<%
    {
        String id = UUID.randomUUID().toString().replace('-','_');
//        String m_var = UUID.randomUUID().toString().replace('-','_');

%>
<script type="text/javascript">





    function uuid(){
        return 'xxxxxxxx_xxxx_4xxx_yxxx_xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
    }

    $(document).ready(function(){

        (function($){

            var searchId = $("div[mtag='<%=id%>']").closest("div[mtag=_s_searchbox]").attr("mtagId");
            var _pattern = window[ window[searchId].pattern];
            console.log(_pattern);

            function bind_condition_remove(){
                $('a[mtag="condition_remove"]').click(function(){
                    $(this).parent().parent().remove();
                });
            }

            function bind_condition_add(){

                $('a[mtag="condition_add"]').click(function(){

                    var ans = $(this).parent().parent().parent().parent().parent().parent().parent().parent().parent();

                    var x = $(ans).attr("mtag");
                    var y = ans.find("div[mtag=_s_selections]").attr("id");

                    if(x==y){
                        $(this).attr("data-target","#"+x);
                        $(this).attr("id",uuid());
                        $('#'+x).attr("inv_id",$(this).attr("id"));
                    }

                });
            }





            function bind_group_add(){
                $('a[mtag="group_add"]').click(function(){
                    var groupAdd = $(this).parent();
                    var all = $(groupAdd).parent();
                    if(3<=$("a[mtag=group_remove]").size()){
                        alert("最多支持3个或条件组");
                        return;
                    }

                    var xuuid = uuid();
                    var strVar="";
                    strVar += "<td grouptag=\""+xuuid+"\">";
                    strVar += "            <table class=\"table\" mtag=\"_s_and_table\">";
                    strVar += "                <tbody>";
                    strVar += "";
                    strVar += "                <tr>";
                    strVar += "                    <td><\/td>";
                    strVar += "                    <td><\/td>";
                    strVar += "                    <td><\/td>";
                    strVar += "                    <td><a mtag=\"condition_add\" data-toggle=\"modal\" data-target=\"#<%=id%>\" ><i class=\"fa fa-fw fa-plus-square-o\"><\/i><\/a><\/td>";
                    strVar += "                <\/tr>";
                    strVar += "                <\/tbody>";
                    strVar += "            <\/table>";
                    strVar += "<\/td>";
                    strVar += "<td grouptag=\""+xuuid+"\" style=\"vertical-align:middle\">";
                    strVar += "    <a mtag=\"group_remove\" grouptag=\""+xuuid+"\"><i class=\"fa fa-fw fa-minus-square-o \"><\/i><\/a>";
                    strVar += "<\/td>";

                    $(groupAdd).before(strVar);
                    bind_group_remove();

                    bind_condition_remove();

                    bind_condition_add();




                });
            }

            function bind_group_remove(){
                $('a[mtag="group_remove"]').click(function(){
                    var mtag = $(this).attr("grouptag");
                    $('td[grouptag='+mtag+']').remove();

                });
            }


            bind_condition_add();
            bind_condition_remove();
            bind_group_add();
            bind_group_remove();



            str1 = "";

            for(attr in _pattern){
                str1 +=  "<option value='"+attr+"'>"+_pattern[attr].name+"</option>"
            }


            $("#select_<%=id%>").html(str1);


            $("#confirm_<%=id%>").click(function(){
                var ml = $(this).parent().parent().parent().parent();
                $('#<%=id%>').modal('hide');
                var idx = $('#select_<%=id%> :selected').val();
                var obj = _pattern[idx];
                var inv_id = $(ml).attr("inv_id");  //invoker id
                var mp = $("#"+inv_id).parent().parent();


                var strVar="";
                strVar += "<tr style=\"vertical-align:middle\" mtag=\"_s_condition_row\">";
                strVar += "    <td style=\"vertical-align:middle\" mtag=\"fieldLabel\">";
                strVar += "        "+obj.name;
                strVar += "    <\/td>";
                strVar += "    <td style=\"vertical-align:middle\">";
                strVar += "        <select>";

                obj.operator.forEach(function(entry){
                    strVar += "            <option value='"+entry.name+"'>"+entry.label+"<\/option>";
                });

                strVar += "        <\/select>";
                strVar += "    <\/td >";
                strVar += "    <td style=\"vertical-align:middle\">";
                strVar += "        <input id=\"_t_input_<%=id%>\" type=\"text\" name=\""+idx+"\"\/>";
                strVar += "    <\/td>";
                strVar += "    <td style=\"vertical-align:middle\">";
                strVar += "        <a mtag=\"condition_remove\"><i class=\"fa fa-fw fa-minus-square \"><\/i><\/a>";
                strVar += "    <\/td>";
                strVar += "<\/tr>";




                $(mp).before(strVar);
                if('datetime'==obj["type"]){
                    $("#_t_input_<%=id%>").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
                }

                $('a[mtag="condition_remove"]').click(function(){
                    $(this).parent().parent().remove();
                });

            });


        })(jQuery);



    });




</script>



<div mtag="<%=id%>"  mtagtype="group">
    <div class="modal fade" mtag="_s_selections" id="<%=id%>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">请选择一个字段</h4>
                </div>
                <div class="modal-body">
                    <select id="select_<%=id%>" class="form-control" name="field">
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="confirm_<%=id%>" class="btn btn-primary">确认</button>
                </div>
            </div>
        </div>
    </div>
    <table class="table table-bordered  " mtag="_s_top_table">
        <tr>
            <jsp:doBody />

            <td style="vertical-align:middle">
                <a mtag="group_add"><i class="fa fa-fw fa-plus-square"></i></a>
            </td>

        </tr>
    </table>
</div>



<%
    }
%>