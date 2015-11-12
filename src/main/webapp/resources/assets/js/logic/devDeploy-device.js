//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
//设备盒子
function addWidonw() {
    closeWindow();
    var cont = '';
    cont += "<div id='modal-wizard' class='modal'>";
    cont += "            <div class='modal-dialog'>";
    cont += "                <div class='modal-content'>";
    cont += "                    <div class='modal-header' data-target='#modal-step-contents'>";
    cont += "                        <h3>设备管理</h3>";
    cont += "                    </div>";
    cont += "                    <div class='modal-body step-content' id='modal-step-contents'>";
    cont += "                        <div class='' id='modal-step1'>";
    cont += "                            <div class='center'>";
    cont += "                                <form class='form-horizontal'>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='name' class='col-sm-3 control-label no-padding-right'>部署站点名：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8' id='name' name='name' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='name' class='col-sm-3 control-label no-padding-right'>设备厂家：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                           <input type='text' class='col-xs-8' id='belong' name='belong' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='Dev_Name' class='col-sm-3 control-label no-padding-right'>设备IP组：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <textarea class='col-xs-8' id='ip' name='ip' required></textarea><span>不同IP间以换行分隔</span>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='name' class='col-sm-3 control-label no-padding-right'>设备编号：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                           <input type='text' class='col-xs-8' id='devno' name='devno' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                </form>";
    cont += "                            </div>";
    cont += "                        </div>";
    cont += "                    </div>";
    cont += "                    <div class='modal-footer wizard-actions'>";
    cont += "                        <button class='btn btn-success btn-sm btn-next save' data-last='完成 '>";
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
    $('#modal-wizard').modal("show");
}
//关闭设备信息盒子
function closeWindow() {
    $('#modal-wizard').modal("hide");
    $('#modal-wizard').remove();
}
function getDeviceAll() {
    var souData;
    $.ajax({
        type: "POST",
        async: false,
        url: "../device_deploy/list",
        data: {},
        dataType: "json",
        success: function(data) {
            //obj = data;
            souData = data;
        }
    });
    return souData;
}
//添加设备动作
function addDevice() {
    addWidonw();
    $("#devno").val("001")
    $('#modal-wizard').modal("show");
    $(".save").attr("onclick", "saveDevice()");
}
//编辑设备信息
var id;
function editDevice(obj) {
    addWidonw();
    //给盒子里面的内容填设备信息----待完善
    $("#ip").val(obj.ip.join("\n"));
    $("#name").val(obj.deploySiteName);
    $("#belong").val(obj.belongTo)
    $("#devno").val(obj.deviceNo)
    id = obj.id;
    $('#modal-wizard').modal("show");
    $(".save").attr("onclick", "updataDevice()");
}
//删除设备
function delDevice(obj) {
    var flag = window.confirm("是否删除该设备？")
    if (flag == false) {
        return
    }
    $.ajax({
        type: "POST",
        async: false,
        url: "../device_deploy/delete",
        data: {id: obj.id},
        dataType: "json",
        success: function(data) {
            //obj = data;
            if (data.result) {
                alert("删除成功!")
            } else {
                alert("删除失败!")
            }
        }
    });
    window.location.reload();
}
//修改设备信息
function updataDevice() {
    var form = $(".form-horizontal").validate();
    if (!form.form()) {
        return;
    }
    //获取设备设置信息
    var ip = $("#ip").val().split("\n");
    var name = $("#name").val();
    var belong = $("#belong").val();
    var devno = $("#devno").val();

    $.ajax({
        type: "POST",
        async: false,
        url: "../device_deploy/edit",
        data: {id: id, deploySiteName: name, belongTo: belong, deviceNo: devno, ip: JSON.stringify(ip)},
        dataType: "json",
        success: function(data) {
            //obj = data;
            if (data) {
                alert("设备信息更新成功!");
                closeWindow();
                window.location.reload();
            } else {
                alert("设备信息更新失败,请检查设备地址或者设备端口是否已经存在!")
            }
        }
    });
}
//保存设备信息
function saveDevice() {
    var form = $(".form-horizontal").validate();
    if (!form.form()) {
        return;
    }
    //获取设备设置信息
    var ip = $("#ip").val().split("\n");
    var name = $("#name").val();
    var belong = $("#belong").val();
    var devno = $("#devno").val();

    $.ajax({
        type: "POST",
        async: false,
        url: "../device_deploy/create",
        data: {ip: JSON.stringify(ip), deploySiteName: name, belongTo: belong, deviceNo: devno},
        dataType: "json",
        success: function(data) {
            //obj = data;
            if (data.result) {
                alert("添加成功!");
                closeWindow();
                window.location.reload();
            } else {
                alert("添加失败,请检查设备地址或者设备端口是否已经存在!")
            }
        }
    });
}