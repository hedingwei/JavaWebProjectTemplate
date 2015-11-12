package com.ambimmort.nisp3.model.ui.f.CRM;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ShiYun on 2015/6/24 0024.
 */
public class CRMConfigBean {

    private String id;

    @NotEmpty(message = "上传接口IP不能为空！")
    private String up_ip;

    @NotNull(message = "上传接口端口不能为空！")
    private int up_port;

    @NotEmpty(message = "用户名不能为空！")
    private String up_username;

    @NotEmpty(message = "密码不能为空！")
    private String up_password;

    @NotEmpty(message = "文件路径不能为空！")
    private String filepath;

    @NotEmpty(message = "周期表达式不能为空！")
    private String quartz;

    @NotEmpty(message = "分隔符不能为空！")
    private String crm_separator;

    @NotEmpty(message = "协议类型不能为空!")
    private String protocol_type;

    private int isRun;

    private String map;

    public String getUp_username() {
        return up_username;
    }

    public void setUp_username(String up_username) {
        this.up_username = up_username;
    }

    public String getUp_password() {
        return up_password;
    }

    public void setUp_password(String up_password) {
        this.up_password = up_password;
    }

    public int getIsRun() {
        return isRun;
    }

    public void setIsRun(int isRun) {
        this.isRun = isRun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUp_ip() {
        return up_ip;
    }

    public void setUp_ip(String up_ip) {
        this.up_ip = up_ip;
    }

    public int getUp_port() {
        return up_port;
    }

    public void setUp_port(int up_port) {
        this.up_port = up_port;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getQuartz() {
        return quartz;
    }

    public void setQuartz(String quartz) {
        this.quartz = quartz;
    }

    public String getCrm_separator() {
        return crm_separator;
    }

    public void setCrm_separator(String crm_separator) {
        this.crm_separator = crm_separator;
    }

    public String getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(String protocol_type) {
        this.protocol_type = protocol_type;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
