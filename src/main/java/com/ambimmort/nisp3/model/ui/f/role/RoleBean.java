package com.ambimmort.nisp3.model.ui.f.role;


/**
 * Created by hedingwei on 6/9/15.
 */
public class RoleBean {

    private int id;

    public String getSelectedFunctionNames() {
        return selectedFunctionNames;
    }

    public void setSelectedFunctionNames(String selectedFunctionNames) {
        this.selectedFunctionNames = selectedFunctionNames;
    }

    private String selectedFunctionNames;
    private String name;
    private String description;
    private String selectedFunctions;
    private int roleId;
    private String newFunctionsJson;
    private String creater;
    private String username;
    private String t_code;

    public String getT_code() {
        return t_code;
    }

    public void setT_code(String t_code) {
        this.t_code = t_code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getNewFunctionsJson() {
        return newFunctionsJson;
    }

    public void setNewFunctionsJson(String newFunctionsJson) {
        this.newFunctionsJson = newFunctionsJson;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelectedFunctions() {
        return selectedFunctions;
    }

    public void setSelectedFunctions(String selectedFunctions) {
        this.selectedFunctions = selectedFunctions;
    }
    public String toString(){
        return "角色名："+name+";备注："+description+";业务权限："+selectedFunctionNames;
    }

}
