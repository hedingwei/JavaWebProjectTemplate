package com.ambimmort.nisp3.model.ui.f.role;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by hedingwei on 6/9/15.
 */
public class EditRoleBean {
    @NotEmpty(message = "角色名不能为空！")
    @Pattern(regexp ="(([\\u4E00-\\u9FA5][\\u4E00-\\u9FA5a-zA-Z0-9_]{1,31})|([a-zA-Z][a-zA-Z0-9_]{1,31}))", message = "角色名格式有误！")
    private String name;
    private String selectedFunctionNames;

    public String getSelectedFunctionNames() {
        return selectedFunctionNames;
    }

    public void setSelectedFunctionNames(String selectedFunctionNames) {
        this.selectedFunctionNames = selectedFunctionNames;
    }

    private String newFunctionsJson;

    public String getNewFunctionsJson() {
        return newFunctionsJson;
    }

    public void setNewFunctionsJson(String newFunctionsJson) {
        this.newFunctionsJson = newFunctionsJson;
    }

    private String description;
    @NotEmpty(message = "权限至少选一个！")
    private String selectedFunctions;
    private int id;
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
