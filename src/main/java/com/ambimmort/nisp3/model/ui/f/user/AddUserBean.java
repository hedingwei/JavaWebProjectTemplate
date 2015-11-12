package com.ambimmort.nisp3.model.ui.f.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

/**
 * Created by hedingwei on 6/9/15.
 */
public class AddUserBean {

    @Pattern(regexp="^[a-zA-Z][a-zA-Z0-9_]{3,15}$",
            message = "请填写正确的用户名:长度为4-16的以字母开头，可包含数字、下划线、英文字母的字符串")
    private String username;

    @Pattern(regexp ="(([\\u4E00-\\u9FA5][\\u4E00-\\u9FA5a-zA-Z0-9_]{1,31})|([a-zA-Z][a-zA-Z0-9_]{1,31}))",message = "请填写正确的姓名")
    //@Pattern(regexp ="\\p{InCJK_UNIFIED_IDEOGRAPHS}{2,7}|[a-zA-Z]{4,32}",message = "请填写正确的姓名")
    private String name;

    @Pattern(regexp = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{4,16}$",message = "请填写正确格式的密码:长度为4-16字母或者数字.不包含特殊符号")
    private String password;

    //@Pattern(regexp = "(([\\d]{2,7})|([a-zA-Z]{4,16}))",message = "请填写正确的密码")
    @Pattern(regexp = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{4,16}$",message = "请填写正确格式的密码:长度为4-16字母或者数字.不包含特殊符号")
    private String password_validate;

    @Pattern(regexp = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|" +
            "(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|" +
            "\\d{3}|\\d{2}|\\d{1}))$|^\\s*$)" ,
            message = "不是手机号码,3-4位区号,7-8位直播号码,1-4位分机号（如：12345678901、1234-12345678-1234）")
    private String telephone;
    @NotEmpty(message = "不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    @NotEmpty(message = "不能为空")
    private String areaId;
    @NotEmpty(message = "不能为空")
    private String roleId;
    @Pattern(regexp = "^([\\u4E00-\\u9FA5]|\\w){2,32}$",message = "不能含特殊字符且长度为2-32")
    private String department;
    @Pattern(regexp = "^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\/(\\d{1}|[0-2]{1}\\d{1}|([0-9]|[1-9][0-9]|[1][0-9][0-9]|[2][0-5][0-5]))$|^\\s*$" +
            "|^\\s*$",message = "请输入正确格式的IP地址范围（如：192.168.1.1/24）")
    private String authenticateIPs;

    private String comment;

    private boolean enabled;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAuthenticateIPs() {
        return authenticateIPs;
    }

    public void setAuthenticateIPs(String authenticateIPs) {
        this.authenticateIPs = authenticateIPs;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_validate() {
        return password_validate;
    }

    public void setPassword_validate(String password_validate) {
        this.password_validate = password_validate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String toString(){
        return "用户名："+username+"；姓名："+name+"；电话："+telephone+"；email:"+email+
                "；区域ID："+areaId+"；角色ID："+roleId+"；部门："+department+
                        ";IP范围："+authenticateIPs+"；描述："+comment+"；启用状态："+enabled;
    }
}
