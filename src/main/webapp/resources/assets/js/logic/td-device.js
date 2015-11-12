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
    cont += "                    <div class='modal-body step-content' id='modal-step-contents' style='max-height:300px;overflow-y: auto;'>";
    cont += "                        <div class='' id='modal-step1'>";
    cont += "                            <div class='center'>";
    cont += "                                <form class='form-horizontal'>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='local_ip' class='col-sm-3 control-label no-padding-right'>上报IP：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 local_ip ip' id='local_ip' name='local_ip' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='local_port' class='col-sm-3 control-label no-padding-right'>端口：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 local_port number' id='local_port' name='local_port' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='local_user' class='col-sm-3 control-label no-padding-right'>sftp帐号：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 local_user' id='local_user' name='local_user' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='local_pswd' class='col-sm-3 control-label no-padding-right'>sftp密码：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='password' class='col-xs-8 local_pswd' id='local_pswd' name='local_pswd' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='freq' class='col-sm-3 control-label no-padding-right'>上报周期：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <select class='col-xs-8 freq' id='freq' name='freq'>";
    cont += "                                               <option value='0/30 * * * * ?'>每30s执行</option>";
    cont += "                                               <option value='0 0/1 * * * ?'>每1分钟执行</option>";
    cont += "                                               <option value='0 0/5 * * * ?'>每5分钟执行</option>";
    cont += "                                               <option value='0 0 * * * ?'>每1小时执行</option>";
    cont += "                                               <option value='0 0 0 * * ?'>每天0点(24点)执行</option>";
    cont += "                                            </select>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='local_path' class='col-sm-3 control-label no-padding-right'>DPI上传文件路径：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 local_path' id='local_path' name='local_path' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";

    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='up2_ip' class='col-sm-3 control-label no-padding-right'>集团IP：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 up2_ip ip' id='up2_ip' name='up2_ip' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='up2_port' class='col-sm-3 control-label no-padding-right'>端口：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 up2_port number' id='up2_port' name='up2_port' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='up2_user' class='col-sm-3 control-label no-padding-right'>sftp帐号：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='text' class='col-xs-8 up2_user' id='up2_user' name='up2_user' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='up2_pswd' class='col-sm-3 control-label no-padding-right'>sftp密码：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <input type='password' class='col-xs-8 up2_pswd' id='up2_pswd' name='up2_pswd' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='desc' class='col-sm-3 control-label no-padding-right'>描述：</label>";
    cont += "                                        <div class='col-sm-9'>";
    cont += "                                            <textarea class='col-xs-8 desc' id='desc' name='desc' required></textarea>";
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
    $('.modal-body,.comments').slimScroll();
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
        url: "../QueryTddevice",
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
    $('#modal-wizard').modal("show");
    $(".save").attr("onclick", "saveDevice()");
}
//编辑设备信息
var run = 0;
var old_ip, old_user;
function editDevice(obj) {
    addWidonw();
    //给盒子里面的内容填设备信息----待完善
    $("#local_ip").val(obj.local_ip);
    $("#local_port").val(obj.local_port);
    $("#local_user").val(obj.local_user);
    $("#local_pswd").val(obj.local_pswd);
    $("#freq").val(obj.freq);
    $("#local_path").val(obj.local_path);
    $("#up2_ip").val(obj.up2_ip);
    $("#up2_port").val(obj.up2_port);
    $("#up2_user").val(obj.up2_user);
    $("#up2_pswd").val(obj.up2_pswd);
    $("#desc").val(obj.desc);
    run = obj.isRun;
    old_ip = obj.local_ip;
    old_user = obj.local_user;
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
        url: "../DelTddevice",
        data: {local_ip: obj.local_ip, local_user: obj.local_user},
        dataType: "json",
        success: function(data) {
            //obj = data;
            if (data) {
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
    var local_ip = $("#local_ip").val();
    var local_port = $("#local_port").val();
    var local_user = $("#local_user").val();
    var local_pswd = $("#local_pswd").val();
    var freq = $("#freq").val();
    var local_path = $("#local_path").val();
    var up2_ip = $("#up2_ip").val();
    var up2_port = $("#up2_port").val();
    var up2_user = $("#up2_user").val();
    var up2_pswd = $("#up2_pswd").val();
    var desc = $("#desc").val();
    $.ajax({
        type: "POST",
        async: false,
        url: "../UpdateTddevice",
        data: {local_ip: local_ip, local_port: local_port, local_user: local_user, local_pswd: local_pswd, freq: freq, local_path: local_path,
            up2_ip: up2_ip, up2_port: up2_port, up2_user: up2_user, up2_pswd: up2_pswd, desc: desc, old_ip: old_ip, old_user: old_user},
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
    var local_ip = $("#local_ip").val();
    var local_port = $("#local_port").val();
    var local_user = $("#local_user").val();
    var local_pswd = $("#local_pswd").val();
    var freq = $("#freq").val();
    var local_path = $("#local_path").val();
    var up2_ip = $("#up2_ip").val();
    var up2_port = $("#up2_port").val();
    var up2_user = $("#up2_user").val();
    var up2_pswd = $("#up2_pswd").val();
    var desc = $("#desc").val();
    $.ajax({
        type: "POST",
        async: false,
        url: "../AddTddevice",
        data: {local_ip: local_ip, local_port: local_port, local_user: local_user, local_pswd: local_pswd, freq: freq, local_path: local_path,
            up2_ip: up2_ip, up2_port: up2_port, up2_user: up2_user, up2_pswd: up2_pswd, desc: desc},
        dataType: "json",
        success: function(data) {
            //obj = data;
            if (data) {
                alert("添加成功!");
                closeWindow();
                window.location.reload();
            } else {
                alert("添加失败,请检查设备地址或者设备端口是否已经存在!")
            }
        }
    });
}
