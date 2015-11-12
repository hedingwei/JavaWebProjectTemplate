//设备盒子
function showLibraryBox() {
    closeLibraryBox();
    var cont = '';
    cont += "<div id='modal-wizard' class='modal'>";
    cont += "            <div class='modal-dialog'>";
    cont += "                <div class='modal-content'>";
    cont += "                    <div class='modal-header' data-target='#modal-step-contents'>";
    cont += "                        <h3>策略库设置</h3>";
    cont += "                    </div>";
    cont += "                    <div class='modal-body step-content' id='modal-step-contents'>";
    cont += "                        <div class='' id='modal-step1'>";
    cont += "                            <div class='center'>";
    cont += "                                <form class='form-horizontal'>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='Dev_Name' class='col-sm-3 control-label no-padding-right'>策略库名称</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8' id='name' name='1' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                </form>";
    cont += "                            </div>";
    cont += "                        </div>";
    cont += "                    </div>";
    cont += "                    <div class='modal-footer wizard-actions'>";
    cont += "                        <button class='btn btn-success btn-sm btn-next save' data-last='完成 ' onclick='saveLibrary()'>";
    cont += "                            完成";
    cont += "                            <i class='icon-ok'></i>";
    cont += "                        </button>";
    cont += "                        <button class='btn btn-danger btn-sm pull-left' data-dismiss='modal'>";
    cont += "                            <i class='icon-remove'></i>";
    cont += "                            取消";
    cont += "                        </button>";
    cont += "                    </div>";
    cont += "                </div>";
    cont += "            </div>";
    cont += "        </div>";
    $("body").append(cont);
}
//关闭设备信息盒子
function closeLibraryBox() {
    $('#modal-wizard').modal("hide");
    $('#modal-wizard').remove();
}
//添加策略库动作
function addLibrary() {
    showLibraryBox();
    $('#modal-wizard').modal("show");
}
//删除策略库
function delLibrary(obj) {
    var flag = window.confirm("是否删除该策略库？");
    if (flag == false) {
        return
    }
    ;
    $.ajax({
        type: "POST",
        async: false,
        url: "../policy/deletePRI",
        data: {id: obj.policy_warehouse_id, instance: obj.policy_warehouse_name, type: type},
        dataType: "json",
        success: function (data) {
            if (data.result == "true") {
                alert("删除成功！");
//                closeLibraryBox();
                window.location.reload();
            } else {
                alert("删除失败！");
            }
        }
    });
}
//保存策略库信息
function saveLibrary() {
    var form = $("#modal-step1 .form-horizontal").validate();
    if (form.form()) {
        var name = $("#name").val();
        $.ajax({
            type: "POST",
            async: false,
            url: "../policy/create_strategy_instance",
            data: {type: type, instance: name},
            dataType: "json",
            success: function (data) {
                if (data.result == "true") {
                    alert("添加成功！");
                    closeLibraryBox();
                    window.location.reload();
                }else{
                    alert("添加失败！");
                }
            }
        });
    }
}
function getLibrary() {

}
function getLibraryAll() {
    var obj;
    $.ajax({
        type: "GET",
        async: false,
        url: "../policy/get_strategy_all_warehouse",
        data: {MessageType: type},
        dataType: "json",
        success: function (data) {
            obj = data;
        }
    });
    return obj;
}
//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}