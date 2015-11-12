<%@ tag import="java.util.UUID" %>
<%@ tag import="com.ambimmort.app.framework.uitls.Application" %>
<%@ tag import="com.ambimmort.app.framework.lttask.LTTaskManager" %>
<%@ tag import="com.ambimmort.app.framework.lttask.AbstractTask" %>
<%@ tag import="net.sf.json.JSONArray" %>
<%@ tag import="net.sf.json.JSONObject" %>
<%@ tag import="org.apache.commons.codec.binary.Base64" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags-search" tagdir="/WEB-INF/tags/search" %>

<%@ tag pageEncoding="utf-8" %>

<%@ attribute name="id" required="true" rtexprvalue="true" %>
<%@ attribute name="sclass" required="false" rtexprvalue="true" %>
<%@ attribute name="stype" required="false" rtexprvalue="true" %>
<%@ attribute name="pattern" required="true" rtexprvalue="true" %>

<%@ attribute name="visible" required="false" rtexprvalue="true"  %>

<script src="${pageContext.request.contextPath}/resources/adminlte/js/plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/adminlte/js/plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/adminlte/js/plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>


<%
    {
        String m_var = UUID.randomUUID().toString().replace('-','_');


%>


<c:if test="${visible==null}">
    <c:if test="${sclass==null}">
        <i class="fa fa-fw fa-windows"></i><a id="${id}" href="#" style="${style}" data-toggle="modal" data-target="#_s_model_div_<%=m_var%>">设定查询条件</a>
    </c:if>

    <c:if test="${sclass!=null}">
        </i><a id="${id}" href="#" class="${sclass}" style="${style}"  data-toggle="modal" data-target="#_s_model_div_<%=m_var%>">设定查询条件</a>
    </c:if>
</c:if>


<c:if test="${(visible!=null)&&(visible==false)}">
    <c:if test="${sclass==null}">
        <i class="fa fa-fw fa-windows"></i><a id="${id}" href="#" style="display: none;" data-toggle="modal" data-target="#_s_model_div_<%=m_var%>">设定查询条件</a>
    </c:if>

    <c:if test="${sclass!=null}">
        </i><a id="${id}" href="#" class="${sclass}" style="display: none;"  data-toggle="modal" data-target="#_s_model_div_<%=m_var%>">设定查询条件</a>
    </c:if>
</c:if>





<div class="modal fade " mtagId="${id}" mtagSId="<%=m_var%>" mtag="_s_searchbox" id="_s_model_div_<%=m_var%>" tabindex="-1" role="dialog" aria-labelledby="id_search_model_${id}">
    <div class="modal-dialog" role="document" style="width: auto;max-height: 100%;height: auto">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="id_search_model_${id}">多纬度查询条件设定</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-info">
                    提示：您可以通过<i class="fa fa-fw fa-plus-square-o"></i>来添加条件组，通过<i class="fa fa-fw fa-plus-square-o"></i>来添加条件。一个条件组中的每个条件之间是且关系，而条件组和条件组之间是或关系。

                </div>
                <jsp:doBody/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="btn_search_confirm_${id}" class="btn btn-primary">确认</button>
            </div>
        </div>
    </div>
</div>

<script>

    (function($){

        var searchObj = {};
        window['${id}'] = searchObj;
        searchObj.matrix = {};
        searchObj.pattern="${pattern}";


        function collectAndTableData(index0,element){

            var pattern = searchObj.pattern;

            var list = [];
            $(element).find("tr[mtag=_s_condition_row]").each(function(index1,element){
                var obj={
                    col:index0,
                    row:index1,
                    field:$(element).find("input").attr("name"),
                    value:$(element).find("input").val(),
                    fieldLabel:$(element).find("td[mtag=fieldLabel]").html(),
                    operator:$(element).find("select :selected").val(),
                    operatorLabel:$(element).find("select :selected").text(),
                    type:window[pattern][$(element).find("input").attr("name")].type
                };
                list.push(obj);
            });
            return list;
        }

        function gd(list){
            var str = "";
            list.forEach(function(element){
                str = str + "("+element.fieldLabel+" "+element.operatorLabel+" "+element.value +") 并且 ";
            });
            str = str.substring(0,str.length-3);

            return str;
        }

        function mkdescription(matrix){
            $('div[mtag=search_dscription_${id}]').html("");
            <%--$('div[mtag=search_dscription_${id}]').append("<br/>");--%>
            var count = 0;
            matrix.forEach(function(element){
                var str = "<p>"+ gd(element) +"</p> <p mtag='_s_tmp_dsc_"+count+"'>或</p>";
                $('div[mtag=search_dscription_${id}]').append(str);
                count+=1;
            });
            $('p[mtag=_s_tmp_dsc_'+(count-1)+']').remove();
        }

        $('#btn_search_confirm_${id}').click(function(){
            tt();
            $('div[mtag=_s_searchbox]').modal('hide');

        });

        function tt(){
            var topTable = $('#btn_search_confirm_${id}').parent().parent();
            var matrix = [];
            $(topTable).find("table[mtag=_s_top_table]").find("table[mtag=_s_and_table]").each(function(index,element){
                var list = collectAndTableData(index,element);
                matrix.push(list);
            });
            window['s_matrix_${id}']=matrix;


            searchObj.matrix = matrix;


            function gd(list){
                var str = "";
                list.forEach(function(element){
                    str = str + "("+element.fieldLabel+" "+element.operatorLabel+" "+element.value +") 并且 ";
                });
                str = str.substring(0,str.length-3);

                return str;
            }

            function gd_string(list){
                var str = "";
                list.forEach(function(element){
                    str = str + "("+element.fieldLabel+" "+element.operatorLabel+" "+element.value +") 并且 ";
                });
                str = str.substring(0,str.length-3);

                return str;
            }

            searchObj.describe_string=function(){
                matrix = this.matrix;
                var message = "";
                var count = 0;
                matrix.forEach(function(element){
                    var str = "("+ gd_string(element) +") 或 ";
                    message+=str;
                    count+=1;
                });

                message = message.substring(0,message.length-2);

                return message;
            }


            function gd_sql(list){
                var str = "";
                var tmp=
                        list.forEach(function(element){

                            if(element.type=='string'){
                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" = '"+element.value +"') AND";
                                }else if(element.operator=='ne'){
                                    str = str + " ("+element.field+" <> '"+element.value +"') AND";
                                }else if(element.operator=='startWith'){
                                    str = str + " ("+element.field+" LIKE '"+element.value +"%') AND";
                                }else if(element.operator=='endWith'){
                                    str = str + " ("+element.field+" LIKE '%"+element.value +"') AND";
                                }else if(element.operator=='contains'){
                                    str = str + " ("+element.field+" LIKE '%"+element.value +"%') AND";
                                }else if(element.operator=='ge'){
                                    str = str + " ("+element.field+" >= '"+element.value +"') AND";
                                }else if(element.operator=='g'){
                                    str = str + " ("+element.field+" > '"+element.value +"') AND";
                                }else if(element.operator=='le'){
                                    str = str + " ("+element.field+" <= '"+element.value +"') AND";
                                }else if(element.operator=='l'){
                                    str = str + " ("+element.field+" < '"+element.value +"') AND";
                                }

                            }else if(element.type=='timestamp' ||element.type=='number'  ){

                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" = "+element.value +") AND";
                                }else if(element.operator=='ne'){
                                    str = str + " ("+element.field+" <> "+element.value +") AND";
                                }else if(element.operator=='ge'){
                                    str = str + " ("+element.field+" >= "+element.value +") AND";
                                }else if(element.operator=='le'){
                                    str = str + " ("+element.field+" <= "+element.value +") AND";
                                }else if(element.operator=='g'){
                                    str = str + " ("+element.field+" > "+element.value +") AND";
                                }else if(element.operator=='l'){
                                    str = str + " ("+element.field+" > "+element.value +") AND";
                                }
                            }else if(element.type=='datetime'){

                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" = '"+element.value +"') AND";
                                }else if(element.operator=='ne'){
                                    str = str + " ("+element.field+" <> '"+element.value +"') AND";
                                }else if(element.operator=='ge'){
                                    str = str + " ("+element.field+" >= '"+element.value +"') AND";
                                }else if(element.operator=='le'){
                                    str = str + " ("+element.field+" <= '"+element.value +"') AND";
                                }else if(element.operator=='g'){
                                    str = str + " ("+element.field+" > '"+element.value +"') AND";
                                }else if(element.operator=='l'){
                                    str = str + " ("+element.field+" > '"+element.value +"') AND";
                                }
                            }
                        });
                str = str.substring(0,str.length-4);
                return str;
            }


            function gd_lucene(list){
                var str = "";
                var tmp=
                        list.forEach(function(element){

//                            console.log(element);

                            if(element.type=='string'){
                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" : '"+element.value +"') AND";
                                }else if(element.operator=='ne'){
                                    str = str + " (NOT "+element.field+" : '"+element.value +"') AND";
                                }else if(element.operator=='startWith'){
                                    str = str + " ("+element.field+" : '"+element.value +"*') AND";
                                }else if(element.operator=='endWith'){
                                    str = str + " ("+element.field+" : '*"+element.value +"') AND";
                                }else if(element.operator=='contains'){
                                    str = str + " ("+element.field+" : '*"+element.value +"*') AND";
                                }
                            }else if(element.type=='number'  ){

                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" : "+element.value +") AND";
                                }else if(element.operator=='ne'){
                                    str = str + " (NOT "+element.field+" : "+element.value +") AND";
                                }else if(element.operator=='ge'){
                                    str = str + " ("+element.field+" : ["+element.value +" TO *]) AND";
                                }else if(element.operator=='le'){
                                    str = str + " ("+element.field+" : [* TO "+element.value +"]) AND";
                                }
                            }else if(element.type=='date'){

                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" : "+element.value +") AND";
                                }else if(element.operator=='ne'){
                                    str = str + " (NOT "+element.field+" : "+element.value +") AND";
                                }else if(element.operator=='ge'){
                                    str = str + " ("+element.field+" : ["+element.value +" TO *]) AND";
                                }else if(element.operator=='le'){
                                    str = str + " ("+element.field+" : [* TO "+element.value +"]) AND";
                                }
                            }else if(element.type=='datetime'){

                                if(element.operator=='e'){
                                    str = str + " ("+element.field+" : "+element.value +") AND";
                                }else if(element.operator=='ne'){
                                    str = str + " (NOT "+element.field+" : "+element.value +") AND";
                                }else if(element.operator=='ge'){
                                    str = str + " ("+element.field+" : ["+element.value +" TO *]) AND";
                                }else if(element.operator=='le'){
                                    str = str + " ("+element.field+" : [* TO "+element.value +"]) AND";
                                }
                            }

                        });
                str = str.substring(0,str.length-4);
                return str;
            }



            searchObj.getMatrix = function(){
                return this.matrix;
            };

            searchObj.describe_human=function(){
                matrix = this.matrix;
                var message = "";
                var count = 0;
                matrix.forEach(function(element){
                    var str = "<p>"+ gd(element) +"</p> <p mtag='_s_tmp_dsc_"+count+"'>或</p>";
                    message+=str;
                    count+=1;
                });

                return message;
            }

            searchObj.describe_sql=function(){
                matrix = this.matrix;
                var message = "";
                var count = 0;
                matrix.forEach(function(element){
                    var str = " ("+ gd_sql(element) +") OR";
                    message+=str;
                    count+=1;
                });
                message = message.substring(0,message.length-3);
                return message;
            }

            searchObj.describe_lucene=function(){
                matrix = this.matrix;
                var message = "";
                var count = 0;
                matrix.forEach(function(element){
                    var str = " ("+ gd_lucene(element) +") OR";
                    message+=str;
                    count+=1;
                });
                message = message.substring(0,message.length-3);
                return message;
            }

            if($('div[mtag=search_dscription_${id}]').attr("id") == undefined){
//
//                console.log(1);
            }else{
//                console.log(2);
                mkdescription(matrix);
            }
            console.log(searchObj);
            console.log(searchObj.describe_sql());

            console.log(searchObj.describe_lucene());
        }

        searchObj.init = tt;


    })(jQuery);
    //
    $(document).ready(function(){
        window['${id}'].init();
    });
</script>




<%
    }
%>