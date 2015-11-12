//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
//设备盒子
function showBox() {
    closeBox();
    var PolicyLibrary = getPolicyLibrary();
    var cont = '';
    cont += "<div id='modal-wizard' class='modal'>";
    cont += "            <div class='modal-dialog'>";
    cont += "                <div class='modal-content'>";
    cont += "                    <div class='modal-header' data-target='#modal-step-contents'>";
    cont += "                        <ul class='wizard-steps'>";
    cont += "                            <li data-target='#modal-step1' class='active'>";
    cont += "                                <span class='step'>1</span>";
    cont += "                                <span class='title'>设备信息设置</span>";
    cont += "                            </li>";
    cont += "                            <li data-target='#modal-step2'>";
    cont += "                                <span class='step'>2</span>";
    cont += "                                <span class='title'>设备绑定库设置</span>";
    cont += "                            </li>";
    cont += "                        </ul>";
    cont += "                    </div>";
    cont += "                    <div class='modal-body step-content' id='modal-step-contents' style='max-height:300px;overflow-y: auto;'>";
    cont += "                        <div class='step-pane active' id='modal-step1'>";
    cont += "                            <div class='center'>";
    cont += "                                <form class='form-horizontal'>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='Dev_Name' class='col-sm-3 control-label no-padding-right' style='float: left;text-align: right;width: 30%;'>设备名称</label>";
    cont += "                                        <div class='col-sm-9' style='float: left;text-align: right;width: 70%;'>";
    cont += "                                            <input type='text' class='col-xs-8 Dev_Name' name='1' id='Dev_Name' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='IP' class='col-sm-3 control-label no-padding-right' style='float: left;text-align: right;width: 30%;'>设备IP地址</label>";
    cont += "                                        <div class='col-sm-9' style='float: left;text-align: right;width: 70%;'>";
    cont += "                                            <input type='text' class='col-xs-8 ip' name='2' id='IP' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
//    cont += "                                    <div class='form-group'>";
//    cont += "                                        <label for='Probe_Type' class='col-sm-3 control-label no-padding-right' style='float: left;text-align: right;width: 25%;'>采集类型</label>";
//    cont += "                                        <div class='col-sm-9' style='float: left;text-align: right;width: 75%;'>";
//    cont += "                                            <select class='col-xs-8' id='Probe_Type'>";
//    cont += "                                                <option value='0'>DPI设备</option>";
//    cont += "                                                <option value='1'>EU设备</option>";
//    cont += "                                            </select>";
//    cont += "                                        </div>";
//    cont += "                                    </div>";
    //cont += "                                    <div id='site-name' class='form-group' style='display:none;'>";
    cont += "                                    <div id='site-name' class='form-group'>";
    cont += "                                        <label for='DeploySiteName' class='col-sm-3 control-label no-padding-right' style='float: left;text-align: right;width: 30%;'>设备部署站点名称</label>";
    cont += "                                        <div class='col-sm-9' style='float: left;text-align: right;width: 70%;'>";
    cont += "                                            <input type='text' class='col-xs-8' name='3' id='DeploySiteName' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div id='site-name' class='form-group'>";
    cont += "                                        <label for='IDC_HouseID' class='col-sm-3 control-label no-padding-right' style='float: left;text-align: right;width: 30%;'>设备部署IDC机房名称</label>";
    cont += "                                        <div class='col-sm-9' style='float: left;text-align: right;width: 70%;'>";
    cont += "                                            <input type='text' class='col-xs-8' name='3' id='IDC_HouseID' required>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                    <div class='form-group'>";
    cont += "                                        <label for='Desc' class='col-sm-3 control-label no-padding-right' style='float: left;text-align: right;width: 30%;'>设备描述</label>";
    cont += "                                        <div class='col-sm-9' style='float: left;text-align: right;width: 70%;'>";
    cont += "                                            <textarea class='col-xs-8' id='Desc'></textarea>";
    cont += "                                        </div>";
    cont += "                                    </div>";
    cont += "                                </form>";
    cont += "                            </div>";
    cont += "                        </div>";
    cont += "                        <div class='step-pane' id='modal-step2'>";
    cont += "                            <div class='center'>";
    cont += "                                <form class='form-horizontal'>";
    //策略库设置
    for (var i in PolicyLibrary) {
        cont += "                                    <div class='form-group'>";
        cont += "                                        <label for='Dev_Name' class='col-sm-5 control-label no-padding-right' style='float: left;text-align: right;width: 41.6666%;'>" + PolicyLibrary[i] + "</label>";
        cont += "                                        <div class='col-sm-7' style='float: left;text-align: right;width: 58.3333%;'>";
        cont += "                                            <select class='col-xs-10' id='" + i + "'>";
        cont += "                                                <option value='0'>不绑定该库</option>";
//        cont += "                                                <option value='1' selected='selected'>默认库</option>";
//        for (var n in PolicyLibrary[i].children) {
//            cont += "                                                <option value='" + PolicyLibrary[i].children[n].id + "'>" + PolicyLibrary[i].children[n].dest + "</option>";
//        }
        cont += "                                            </select>";
        cont += "                                        </div>";
        cont += "                                    </div>";
    }
    cont += "                                </form>";
    cont += "                            </div>";
    cont += "                        </div>";
    cont += "                    </div>";
    cont += "                    <div class='modal-footer wizard-actions'>";
    cont += "                        <button class='btn btn-sm btn-prev'>";
    cont += "                            <i class='icon-arrow-left'></i>";
    cont += "                            上一步";
    cont += "                        </button>";
    cont += "                        <button class='btn btn-success btn-sm btn-next' data-last='完成 '>";
    cont += "                            下一步";
    cont += "                            <i class='icon-arrow-right icon-on-right'></i>";
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
    initPolicyDepot();

    // $("#Probe_Type").change(function () {
    //     var probe_type = $("#Probe_Type option:selected").val();
    //     if (probe_type == "0") {
    //         $("#site-name").hide();
    //         $("#DeploySiteName").val("");
    //     } else {
    //         $("#site-name").show();
    //     }
    // });
}
//关闭设备信息盒子
function closeBox() {
    $('#modal-wizard').modal("hide");
    $('#modal-wizard').remove();
}

// 构建策略下发请求的数据对象, num=0代表全量下发， num=1代表增量下发
function generateStrategyUpdateParams(num) {
    var json = {
        "dpi_id": id,
        "data": []
    };
    for (var i in device_strategy) {
        // 当平台版本为0时或策略为DPI设备状态查询请求策略(0xC4)时，不提交更新;
        if(device_strategy[i]["version"] == 0 || device_strategy[i]["Message_Type"] == "0xC4") {
            continue;
        }
        if (num == 0) {
            dpi_ver = 0;
        } else {
            var dpi_strategy_info = dpi_strategy_ver[device_strategy[i]["Message_Type"]];
            if (dpi_strategy_info && dpi_strategy_info["version"] != -1) {
                dpi_ver = dpi_strategy_info["version"];
            } else {
                dpi_ver = 0;
            }
        }
        
        json.data.push({
            "message_type": device_strategy[i]["Message_Type"],
            "instance": device_strategy[i]["strategy_name"],
            "dpi_edition": dpi_ver
        });
    }
    return json;
}

// 更新策略库信息，flag=0时，代表全量更新，flag=1时，代表增量更新
function updateStrategyRepo(flag) {
    var url, param;
    if (flag == 0) {
        url = "../policy/all_send_policy";
        param = generateStrategyUpdateParams(0);
    } else {
        url = "../policy/add_send_policy";
        param = generateStrategyUpdateParams(1);
    }
    $.ajax({
        method: "POST",
        async: false,
        url: url,
        dataType: 'json',
        data: {json:JSON.stringify(param)},
        success: function (resp) {
            if (resp == "true" || resp == true) {
                alert("策略库信息已更新！");
            } else {
                alert("策略库信息更新失败！");
            }
        }
    });
}
//添加设备动作
function addDevice() {
    showBox();
    $('#modal-wizard .modal-header').ace_wizard({
        save: "saveDevice()"
    });
    $('#modal-wizard').modal("show");
}

// 通用策略信息下发
function sendCommon(id) {
    $.ajax({
        method: "POST",
        async: false,
        url: "../device/sendbasicinfo",
        data: {id: id},
        dataType: "json",
        success: function (resp) {
            if (resp.result === "true") {
                alert("通用策略信息已下发！");
            } else {
                alert("通用策略信息下发失败！");
            }

        }
    });
}

//编辑设备信息
function editDevice(id) {
    showBox();
    //给盒子里面的内容填设备信息----待完善
    var devObj = getDevice(id);
    var result = devObj.result;
    $("#Dev_Name").val(devObj.Dev_Name);
    $("#IP").val(devObj.Dev_IP);
//    $("#Probe_Type").val(devObj.Probe_Type);
    $("#DeploySiteName").val(devObj.DeploySiteName);
    $("#IDC_HouseID").val(devObj.IDC_HouseID);
    $("#Desc").val(devObj.description);
    for (var i in result) {
        $("#" + result[i].Message_Type).val(parseInt(result[i].strategy_id));
    }
    $('#modal-wizard .modal-header').ace_wizard({
        save: "updataDevice('" + id + "')"
    });
    $('#modal-wizard').modal("show");
}
//删除设备
function delDevice(id) {
    var flag = window.confirm("是否删除该设备？")
    if (flag == false) {
        return
    }
    $.ajax({
        type: "POST",
        async: false,
        url: "../device/delete",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            //obj = data;
            if (data.result == "true") {
                alert("删除成功!")
            } else {
                alert("删除失败!")
            }
        }
    });
    window.location.reload();
}
//修改设备信息
function updataDevice(id) {
    //获取设备设置信息
    var Dev_Name = $("#Dev_Name").val();
    var IP = $("#IP").val();
    var Probe_Type = 1;//$("#Probe_Type").val();
    var DeploySiteName = $("#DeploySiteName").val();
    var IDC_HouseID = $("#IDC_HouseID").val();
    var Desc = $("#Desc").val();
    //获取设备绑定策略库信息
    var policyDepot = [];
    $("#modal-step2 select").each(function () {
        //console.log($(this).attr("id"))
        var policyType = $(this).attr("id");
        var policyName = $(this).text();
        var policyDepotId = $(this).val();
        if (policyDepotId != 0 || policyDepotId != "0") {
            policyDepot.push({Dev_Name: Dev_Name, instanceId: policyDepotId})
        }
    });
    $.ajax({
        type: "POST",
        async: false,
        url: "../device/edit",
        data: {id: id, result: JSON.stringify(policyDepot), description: Desc, Dev_Name: Dev_Name, Dev_IP: IP, Probe_Type: Probe_Type, DeploySiteName: DeploySiteName, IDC_HouseID: IDC_HouseID},
        dataType: "json",
        success: function (data) {
            //obj = data;
            if (data.result == "true") {
                alert("设备信息更新成功!");
                closeBox();
                window.location.reload();
            } else {
                alert("设备信息更新失败,请检查设备名或者设备地址是否已经存在!")
            }
        }
    });
}
//保存设备信息
function saveDevice() {
    //获取设备设置信息
    var Dev_Name = $("#Dev_Name").val();
    var IP = $("#IP").val();
    var Probe_Type = 1;//$("#Probe_Type").val();
    var DeploySiteName = $("#DeploySiteName").val();
    var IDC_HouseID = $("#IDC_HouseID").val();
    var Desc = $("#Desc").val();
    //获取设备绑定策略库信息
    var policyDepot = [];
    $("#modal-step2 select").each(function () {
        //console.log($(this).attr("id"))
        var policyType = $(this).attr("id");
        var policyName = $(this).text();
        var policyDepotId = $(this).val();
        if (policyDepotId != 0 || policyDepotId != "0") {
            policyDepot.push({Dev_Name: Dev_Name, instanceId: policyDepotId})
        }
    });
    $.ajax({
        type: "POST",
        async: false,
        url: "../device/create",
        data: {result: JSON.stringify(policyDepot), description: Desc, Dev_Name: Dev_Name, Dev_IP: IP, Probe_Type: Probe_Type, DeploySiteName: DeploySiteName, IDC_HouseID: IDC_HouseID},
        dataType: "json",
        success: function (data) {
            //obj = data;
            if (data.result == "true") {
                alert("添加成功!");
                closeBox();
                window.location.reload();
            } else {
                alert("添加失败,请检查设备名或者设备地址是否已经存在!")
            }
        }
    });
}
//查看设备信息动作
function searchDevice(obj) {
    var name = base64.encode(obj.name);
    var ip = obj.ip;
    var id = obj.id;
    window.location = "device-info.html?id=" + id + "&name=" + name + "&ip=" + ip;
}
//获取指定设备静态信息并设置
function getDeviceStatic(id) {
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/information/device_static",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            function PortTypes(num) {
                num = parseInt(num);
                switch (num)
                {
                    case 1:
                        return "GE光口";
                    case 2:
                        return "GE电口";
                    case 3:
                        return "10G POS";
                    case 4:
                        return "10GE";
                    case 5:
                        return "40G POS";
                    case 6:
                        return "40GE";
                    default:
                        return "100GE";
                }
            }
            $("#SoftwareVersion").text(data.SoftwareVersion);
            if (data.Probe_Type == "0") {
                $("#Probe_Type").text("DPI 设备");
            } else {
                $("#Probe_Type").text("EU 设备");
            }
            $("#DeploySiteName").text(data.DeploySiteName);
            $("#IDC_HouseID").text(data.IDC_HouseID);
            $("#Deploy_Mode").text(data.Deploy_Mode);
            $("#Total_Capability").text(data.Total_Capability);
            $("#SlotNum").text(data.SlotNum);
            $("#PreProcSlotNum").text(data.PreProcSlotNum);
            $("#AnalysisSlotNum").text(data.AnalysisSlotNum);
            $("#GPSlotNum").text(data.GPSlotNum);
//            return;
            var PortsType = data.Ports;
            var cont = '';
            for (var i in PortsType) {
                cont += "<div class='col-sm-6'>";
                cont += "                                            <div class='box'>";
                cont += "                                                <strong style='width:180px;'>端口类型：" + PortTypes(PortsType[i].PortType) + "</strong>";
                cont += "                                                <form class='form-horizontal'>";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-6 control-label no-padding-right' style='padding-top:0px;'>本端口的编号</label>";
                cont += "                                                        <div class='col-sm-6'>";
                cont += "                                                            <label>";
                cont += "                                                                " + PortsType[i].PortNo + "";
                cont += "                                                            </label>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-6 control-label no-padding-right' style='padding-top:0px;'>本端口描述信息</label>";
                cont += "                                                        <div class='col-sm-6'>";
                cont += "                                                            <label>";
                cont += "                                                                " + PortsType[i].PortDescription + "";
                cont += "                                                            </label>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-6 control-label no-padding-right' style='padding-top:0px;'>本端口监控链路编号</label>";
                cont += "                                                        <div class='col-sm-6'>";
                cont += "                                                            <label>";
                cont += "                                                                " + PortsType[i].M_LinkID + "";
                cont += "                                                            </label>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-6 control-label no-padding-right' style='padding-top:0px;'>监控链路描述信息</label>";
                cont += "                                                        <div class='col-sm-6'>";
                cont += "                                                            <label>";
                cont += "                                                                " + PortsType[i].M_LinkDesc + "";
                cont += "                                                            </label>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "                                                </form>";
                cont += "                                            </div>";
                cont += "                                        </div>";
            }
            $(".ports").append(cont)
        }
    });
}
//获取指定设备动态信息
function getDeviceDynamic(id) {
    $.ajax({
        type: "GET",
        async: true,
        url: "../device/information/device_dynamic",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            function bool(num) {
                if (num == "1") {
                    return "已连接"
                }
                return "未连接"
            }

            $("#isClientChannelConnected").text(bool(data.isClientChannelConnected));
            $("#isServerChannelConnected").text(bool(data.isServerChannelConnected));
            $("#device_status").text(data.device_status);
            $("#lastHeartBeatTime").text(data.lastHeartBeatTime);

//            return;
            //cpu 信息
            var cpu = data.Total_CPU;
            var cpus = '';
            for (var i = 0; i < cpu.length; i++) {
                var number = i + 1;
                cpus += "<div class='box'>";
                cpus += "                                                <strong>CPU" + number + "信息</strong>";
                cpus += "                                                <form class='form-horizontal'>";
                cpus += "                                                    <div class='form-group'>";
                cpus += "                                                        <label class='col-sm-3 control-label no-padding-right' style='padding-top:0px;'> CPU 编号 </label>";
                cpus += "                                                        <div class='col-sm-9'>";
                cpus += "                                                            <label>";
                cpus += "                                                                " + cpu[i].CPU_No + "";
                cpus += "                                                            </label>";
                cpus += "                                                        </div>";
                cpus += "                                                    </div>";
                cpus += "                                                    <div class='form-group'>";
                cpus += "                                                        <label class='col-sm-3 control-label no-padding-right' style='padding-top:0px;margin-top:-2px'> CPU 利用率 </label>";
                cpus += "                                                        <div class='col-sm-9'>";
                cpus += "                                                            <div data-percent='" + cpu[i].CPU_Usage + "%' class='progress'>";
                cpus += "                                                                <div style='width:" + cpu[i].CPU_Usage + "%;' class='progress-bar'></div>";
                cpus += "                                                            </div>";
                cpus += "                                                        </div>";
                cpus += "                                                    </div>";
                cpus += "                                                </form>";
                cpus += "                                            </div>";
            }
            //port信息
            var pt = data.Total_Ports;
            var cont = '';
            for (var i = 0; i < pt.length; i++) {
                var number = i + 1;
                cont += "<div class='box'>";
                cont += "                                                <strong>端口" + number + "信息</strong>";
                cont += "                                                <form class='form-horizontal'>";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-3 control-label no-padding-right' style='padding-top:0px;'> 端口编号 </label>";
                cont += "                                                        <div class='col-sm-9'>";
                cont += "                                                            <label>";
                cont += "                                                                " + pt[i].PortNo + "";
                cont += "                                                            </label>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-3 control-label no-padding-right' style='padding-top:0px;'> 端口描述信息 </label>";
                cont += "                                                        <div class='col-sm-9'>";
                cont += "                                                            <label>";
                cont += "                                                                " + pt[i].PortInfo + "";
                cont += "                                                            </label>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "                                                    <div class='form-group'>";
                cont += "                                                        <label class='col-sm-3 control-label no-padding-right' style='padding-top:0px;margin-top:-2px'> 端口利用率 </label>";
                cont += "                                                        <div class='col-sm-9'>";
                cont += "                                                            <div data-percent='" + pt[i].PortUsage + "%' class='progress'>";
                cont += "                                                                <div style='width:" + pt[i].PortUsage + "%;' class='progress-bar'></div>";
                cont += "                                                            </div>";
                cont += "                                                        </div>";
                cont += "                                                    </div>";
                cont += "                                                </form>";
                cont += "                                            </div>";
            }
            $(".cpu").append(cpus);
            $(".pt").append(cont);
        }
    });
}
//获取指定设备绑定库信息
function getDevicePolicyDepot(id) {
    $.ajax({
        type: "GET",
        async: true,
        url: "../device/information/device_strategy",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            device_strategy = data;

            $.ajax({
                type: "GET",
                async: true,
                url: "../policy/synchronization/getReportedVersion",
                data: {dpiId: id},
                dataType: "json",
                success: function (data1) {
                    dpi_strategy_ver = data1;
                    var policyType = getPolicyLibrary();
                    var cont = '';
                    cont += '<div class="widget-box transparent"><div class="widget-body"><div class="widget-main no-padding"><table class="table table-bordered table-striped">';
                    cont += '<thead class="thin-border-bottom"><tr><th></th><th></th><th class="">平台维护版本</th><th class="">DPI上报版本</th></tr></thead><tbody>';
                    for (var i in data) {
                        // 不显示DPI设备状态查询请求策略(0xC4)
                        if (messageTypeToUpperCase(data[i].Message_Type) == "0xC4") {
                            continue;
                        }
                        var type = policyType[messageTypeToUpperCase(data[i].Message_Type)];
                        cont += '<tr><td>' + type + '</td>';
                        cont += '<td>' + data[i].strategy_name + '</td>';
                        cont += '<td >' + data[i].version + '</td>';
                        cont += '<td >' + (data1[data[i].Message_Type].version === "-1" ? "未上报" : data1[data[i].Message_Type].version) + '</td></tr>';
                    }
                    cont += '</tbody></table></div></div></div>';
                    $(".policyDepot").append(cont);
                }});

        }
    });
}
//获取所有设备
function getDeviceAll() {
    var obj;
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/list",
        data: {type:"eu"},
        dataType: "json",
        success: function (data) {
            obj = data;
        }
    });
    return obj;
}
//获取指定设备
function getDevice(id) {
    var obj;
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/get",
        data: {id: id},
        dataType: "json",
        success: function (data) {
            obj = data;
        }
    });
    return obj;
}
//获取所有策略类型
function getPolicyLibrary() {
    var obj;
    $.ajax({
        type: "GET",
        async: false,
        url: "../assets/data/PolicyLibrary.json",
        data: {},
        dataType: "json",
        success: function (data) {
            obj = data;
        }
    });
    return obj;
}
//初始化填充策略类型
function initPolicyDepot() {
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/information/get_strategy",
        data: {},
        dataType: "json",
        success: function (data) {
            //obj = data;
            for (var i in data) {
                //console.log("before: "+data[i].Message_Type +" --> "+messageTypeToUpperCase(data[i].Message_Type));
                $("#" + messageTypeToUpperCase(data[i].Message_Type)).append("<option value='" + data[i].strategy_id + "'>" + data[i].strategy_name + "</option>");
            }
        }
    });
}
function stratDevice(id) {
    var flag = window.confirm("是否开启该设备？")
    if (flag == false) {
        return
    }
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/enable",
        data: {id: id, device_status: 1},
        dataType: "json",
        success: function (data) {
            window.location.reload();
        }
    });
}
function enableDevice(id) {
    var flag = window.confirm("是否关闭该设备？")
    if (flag == false) {
        return
    }
    $.ajax({
        type: "GET",
        async: false,
        url: "../device/enable",
        data: {id: id, device_status: 0},
        dataType: "json",
        success: function (data) {
            window.location.reload();
        }
    });
}

function messageTypeToUpperCase(str){
    str = str.substring(0,2)+str.substring(2).toUpperCase();
    return str;
}

function messageTypeToLowerCase(str) {
    str = str.substring(0,2) + str.substring(2).toLowerCase();
    return str;
}
