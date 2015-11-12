package com.ambimmort.nisp3.model.ui.f.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hedingwei on 6/9/15.
 */
public class UserBean {
    @NotEmpty(message = "{username.not.empty}")
    private String username;
    @NotEmpty(message = "{name.not.empty}")
    private String name;
    @NotEmpty(message = "{telephone.not.empty}")
    private String telephone;
    @NotEmpty(message = "{email.not.empty}")
    @Email(message = "{email.format.error}")
    private String email;
    @NotEmpty(message = "{area.not.empty}")
    private String areaId;
    @NotEmpty(message = "{role.not.empty}")
    private String roleId;
    @NotEmpty(message = "{department.not.empty}")
    private String department;
    @NotEmpty(message = "{authenticateIPs.not.empty}")
    private String authenticateIP;
    private String roleName;
    private String areaName;
    private String comment;
    private boolean status;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticateIP() {
        return authenticateIP;
    }

    public void setAuthenticateIP(String authenticateIP) {
        this.authenticateIP = authenticateIP;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isStatus() {
        return status;
    }
    public boolean getStatus(){return status;}

    public void setStatus(boolean status) {
        this.status = status;
    }
    public String toString(){
    return "用户名："+username+"；姓名："+name+"；电话："+telephone+"；email:"+email+
            "；区域ID"+areaId+"；角色ID："+roleId+"；部门"+department+
            ";IP范围："+authenticateIP+"；描述："+comment+"；启用状态："+status;
}
}
