package com.ambimmort.nisp3.model.ui.f.logs;

/**
 * Created by qinxiaoyao on 2015/6/18.
 */
public class LogBean {
//    * @param id            id
//    * @param time          日志记录时间
//    * @param username      当前系统操作员
//    * @param area          当前操作区域，无区域绑定的功能则填写："全局"
//    * @param modular       系统模块名
//    * @param submodular    系统子模块名
//    * @param action        操作动作：添加、修改、导入、导出、删除
//    * @param result        执行结果：成功、失败
//    * @param obj           执行对象：一般直接填入表单对象
//    * @param newDetail     新执行细节：一段描述性语言描述当前的执行操作和细节
//    * @param oldDetail     旧执行细节：一段描述性语言描述当前的执行操作和细节   （方便新旧值对比，查看修改内容）
//    * @param reason        执行失败原因
    /**
     * 当前系统操作员
     */
    private int id;
    private String time;
    private String name;
    private String username;
    private String areaid;
    private String areaname;
    private String modular;
    private String submodular;
    private String action;
    private String result;
    private String obj;
    private String newDetail;
    private String oldDetail;
    private String reason;
    private String ip;
    private String cookie;


    /**
     * //枚举是一种类型，用于定义变量，以限制变量的赋值；赋值时通过“枚举名.值”取得枚举中的值
     * 模块UM，CRM
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getSubmodular() {
        return submodular;
    }

    public void setSubmodular(String submodular) {
        this.submodular = submodular;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getNewDetail() {
        return newDetail;
    }

    public void setNewDetail(String newDetail) {
        this.newDetail = newDetail;
    }

    public String getOldDetail() {
        return oldDetail;
    }

    public void setOldDetail(String oldDetail) {
        this.oldDetail = oldDetail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

}
