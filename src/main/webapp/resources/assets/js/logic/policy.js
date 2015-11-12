//返回上一级
function previous() {
    window.location = "policyDepot-list.jsp?type=" + type;
}
//添加策略动作
function addPolicy() {
//    console.log(type + "/add-policy.html?DepotID=" + DepotID + "&DepotName=" + base64.encode(DepotName) + "&type=" + type);
    window.location = type + "/add-policy.html?DepotID=" + DepotID + "&DepotName=" + base64.encode(DepotName) + "&type=" + type;
}
function editPolicy(policyId) {
    window.location = type + "/edit-policy.html?DepotID=" + DepotID + "&DepotName=" + base64.encode(DepotName) + "&type=" + type + "&id=" + policyId;
//    window.location = type + "/add-policy.html?DepotID=" + DepotID + "&DepotName=" + base64.encode(DepotName) + "&type=" + type;
}
function show_oldPolicy(policyId){
    window.location = type + "/old-policy.html?DepotID=" + DepotID + "&DepotName=" + base64.encode(DepotName) + "&type=" + type + "&id=" + policyId;
}
//删除策略
function delPolicy(id) {
    var flag = window.confirm("是否删除该策略？");
    if (flag == false) {
        return;
    }
    ;
    $.ajax({
        type: "POST",
        async: false,
        url: "../policy/delete",
        data: {type: type, messageNo: id, instance: DepotName},
        dataType: "json",
        success: function(data) {
            if (data.result) {
                alert("删除成功！");
                window.location.reload();
            }
        }
    });
}
function getPolicy() {

}
function getPolicyAll() {
    var obj;
    $.ajax({
        type: "GET",
        async: false,
        url: "../policy/list",
        data: {type: type, instance: base64.encode(DepotName)},
        dataType: "json",
        success: function(data) {
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

function getCommonPolicyURL() {
    var url;
    switch (parseInt(type)) {
        case 0xC4:
            break;
        case 0xC7:
            break;
        case 0xC8:
            url = ["../common/produce_web"];
            break;
        case 0xC9:
            url = ["../common/produce_app"];
            break;
        case 0xCA:
            url = ["../common/produce_ip_bank"];
            break;
        case 0xCF:
            url = ["../common/produce_white_list", "../common/produce_black_domain_list", "../common/produce_black_url_list"];
            break;
    }
    return url;
}
//设备列表
function deviceListBox(policy_id) {
    var url = getCommonPolicyURL()
    if (url) {
        for (var ui in url) {
            $.post(url[ui], {messageNo: policy_id}, function(resp) {
            });
        }
    }
    $.ajax({
        type: "GET",
        async: false,
        url: "../policy/getdevice",
        data: {id: DepotID},
        dataType: "json",
        success: function(data) {
            if (data.length < 1) {
                alert("没有任何设备绑定该库，不能添加到下发队列！");
                return;
            }
            var cont = "";
            for (var i in data) {
                cont += "<div class='checkbox col-xs-4'>";
                cont += "    <label>";
                cont += "        <input id='" + data[i].deviceId + "' type='checkbox' checked class='ace' name='form-field-checkbox'>";
                cont += "        <span class='lbl'>" + data[i].deviceName + "</span>";
                cont += "    </label>";
                cont += "</div>";
            }
            $(".box").html("");
            $(".box").append(cont);
            $('#myModal').modal('show');
            $(".addIssuedList").attr("onclick", "addIssuedList('" + policy_id + "')");
        }
    });

}
//添加到下发队列
function addIssuedList(policy_id) {
    var device_id = [];
    $(".box .ace").each(function() {
        if ($(this).is(":checked")) {
            device_id.push($(this).attr("id"));
        }
    });
    if (device_id.length < 1) {
        alert("至少选着一个设备！");
        return;
    }
    ;
    var json = [];
//    console.log("type:" + type);
//    console.log("instance:" + DepotName);
//    console.log("messageNo:" + policy_id);
//    console.log("policyRepositoryID:" + DepotID);
    for (var i in device_id) {
        var listObj = {
            type: messageTypeToUpperCase(type),
            instance: base64.encode(DepotName),
            messageNo: policy_id,
            policyRepositoryID: DepotID,
            deviceID: device_id[i]
        }
        json.push(listObj)
    }
    $.ajax({
        type: "GET",
        async: false,
        url: "../policy/hand_send_policy_queue",
        data: {json: JSON.stringify(json)},
        dataType: "json",
        success: function(data) {
            if (data.result == "true") {
                alert("已成功添加到下发队列！")
                $('#myModal').modal('hide');
            } else {
                alert("添加失败！")
            }
        }
    });

}

function messageTypeToUpperCase(str) {
    str = str.substring(0, 2) + str.substring(2).toUpperCase();
    return str;
}

function messageTypeToLowerCase(str) {
    str = str.substring(0, 2) + str.substring(2).toLowerCase();
    return str;
}