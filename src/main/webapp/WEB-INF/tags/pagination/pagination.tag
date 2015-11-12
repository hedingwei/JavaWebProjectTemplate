<%@ tag import="java.util.UUID" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="action" required="false" rtexprvalue="true" description="" %>
<%@ attribute name="targetForm" required="false" rtexprvalue="true" description="" %>

<%@ tag pageEncoding="utf-8" %>

<%
    String _ruuid = UUID.randomUUID().toString().replace('-', '_');

%>

    <!--
        如果 action 属性不存在，则提交父节点的 form， 如果action 存在，贼提交本 action 的 form
    -->

<c:if test="${action!=null && !action.equals('')}">

    <form:form id="<%=_ruuid%>" action="${action}" method="get">

    <input type="hidden" name="total" value="${paginationBean.total}"/>
    <input id="<%=_ruuid+"_start"%>" type="hidden" name="start" value="${paginationBean.start}"/>
    <input id="<%=_ruuid+"_interval"%>" type="hidden" name="interval" value="${paginationBean.interval}"/>
    <input id="<%=_ruuid+"_end"%>" type="hidden" name="end" value="${paginationBean.end}"/>
    <input id="<%=_ruuid+"_isClickPagination"%>" type="hidden" name="isClickPagination" value='0'/>

    <ul class="pagination" style="margin: 0px">
        <li>
            <a href="#">
                当前区间 <b style="color: #00625A">${paginationBean.start}
                - ${paginationBean.start+paginationBean.interval}</b>
            </a>
        </li>
        <li>
            <a href="#">
                共 <b style="color: red">${paginationBean.total}</b> 条记录
            </a>
        </li>
        <li id="<%=_ruuid+"_pre"%>">
            <a href="javascript:_submit('pre','${paginationBean.start}','${paginationBean.total}','${paginationBean.interval}','${paginationBean.end}')">
                上一页
            </a>
        </li>
            <%--<li class="active">--%>
            <%--<a type="submit" href="javascript:_submit()">1</a>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<a href="#">2</a>--%>
            <%--</li>--%>
        <li id="<%=_ruuid+"_next"%>">
            <a href="javascript:_submit('next','${paginationBean.start}','${paginationBean.total}','${paginationBean.interval}','${paginationBean.end}')">
                下一页
            </a>
        </li>
    </ul>
    <script type="text/javascript">
        function _submit(type, start, total, interval, end) {
            start = start * 1;
            total = total * 1;
            interval = interval * 1;
            end = end * 1;

            if (type == "pre") {
                if (start <= 0) {
                    alert("到头啦！")
                    return;
                }
                document.getElementById('<%=_ruuid+"_start"%>').value = start - interval;
                document.getElementById('<%=_ruuid+"_end"%>').value = end - interval;
            } else {
                if (end >= total) {
                    alert("到尾啦！")
                    return;
                }
                if (end < total) {
                    document.getElementById('<%=_ruuid+"_start"%>').value = start + interval;
                    document.getElementById('<%=_ruuid+"_end"%>').value = end + interval;
                } else {
                    document.getElementById('<%=_ruuid+"_end"%>').value = total;
                }
            }
            document.getElementById('<%=_ruuid+"_isClickPagination"%>').value = '1';
            document.getElementById('<%=_ruuid%>').submit();
        }

    </script>

    </form:form>

</c:if>

<c:if test="${action==null||action.equals('')}">

    <input type="hidden" name="total" value="${paginationBean.total}"/>
    <input id="<%=_ruuid+"_start"%>" type="hidden" name="start" value="${paginationBean.start}"/>
    <input id="<%=_ruuid+"_interval"%>" type="hidden" name="interval" value="${paginationBean.interval}"/>
    <input id="<%=_ruuid+"_end"%>" type="hidden" name="end" value="${paginationBean.end}"/>
    <input id="<%=_ruuid+"_isClickPagination"%>" type="hidden" name="isClickPagination" value='0'/>

    <ul class="pagination" style="margin: 0px">
        <li>
            <a href="#">
                当前区间 <b style="color: #00625A">${paginationBean.start}
                - ${paginationBean.start+paginationBean.interval}</b>
            </a>
        </li>
        <li>
            <a href="#">
                共 <b style="color: red">${paginationBean.total}</b> 条记录
            </a>
        </li>
        <li id="<%=_ruuid+"_pre"%>">
            <a href="javascript:;" onclick="_submit(this,'pre','${paginationBean.start}','${paginationBean.total}','${paginationBean.interval}','${paginationBean.end}')">
                上一页
            </a>
        </li>
            <%--<li class="active">--%>
            <%--<a type="submit" href="javascript:_submit()">1</a>--%>
            <%--</li>--%>
            <%--<li>--%>
            <%--<a href="#">2</a>--%>
            <%--</li>--%>
        <li id="<%=_ruuid+"_next"%>">
            <a href="javascript:;" onclick="_submit(this,'next','${paginationBean.start}','${paginationBean.total}','${paginationBean.interval}','${paginationBean.end}')">
                下一页
            </a>
        </li>
    </ul>
    <script type="text/javascript">

        function preForm(obj){
            var a , y, f= null;
            a = obj.parentNode;
            if(a){
                if(a.tagName == "FORM" && a.id == "${targetForm}"){
                    console.log(a);
                    return a;
                }else{
                    f = preForm(a);
                }
            }
            return f;
        }

        function _submit(obj, type, start, total, interval, end) {
            start = start * 1;
            total = total * 1;
            interval = interval * 1;
            end = end * 1;

            if (type == "pre") {
                if (start <= 0) {
                    alert("到头啦！")
                    return;
                }
                document.getElementById('<%=_ruuid+"_start"%>').value = start - interval;
                document.getElementById('<%=_ruuid+"_end"%>').value = end - interval;
            } else {
                if (end >= total) {
                    alert("到尾啦！")
                    return;
                }
                if (end < total) {
                    document.getElementById('<%=_ruuid+"_start"%>').value = start + interval;
                    document.getElementById('<%=_ruuid+"_end"%>').value = end + interval;
                } else {
                    document.getElementById('<%=_ruuid+"_end"%>').value = total;
                }
            }

            document.getElementById('<%=_ruuid+"_isClickPagination"%>').value = '1';
            preForm(obj).submit();

        }

    </script>

</c:if>






