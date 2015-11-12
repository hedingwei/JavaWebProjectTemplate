package com.ambimmort.nisp3.model.ui.f.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by hedingwei on 6/9/15.
 */
public class profileBean {
    @Pattern(regexp="^[a-zA-Z][a-zA-Z0-9_]{3,15}$",
            message = "请填写正确的用户名:长度为4-16的以字母开头，可包含数字、下划线、英文字母的字符串")
    private String username;

    @Pattern(regexp ="(([\\u4E00-\\u9FA5][\\u4E00-\\u9FA5a-zA-Z0-9_]{1,31})|([a-zA-Z][a-zA-Z0-9_]{1,31}))",message = "请填写正确的姓名")
    private String name;

    @Pattern(regexp = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|" +
            "(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|" +
            "\\d{3}|\\d{2}|\\d{1}))$|^\\s*$)" ,
            message = "不是手机号码,3-4位区号,7-8位直播号码,1-4位分机号（如：12345678901、1234-12345678-1234）")
    private String telephone;
    @NotEmpty(message = "不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    @Pattern(regexp = "^([\\u4E00-\\u9FA5]|\\w){2,32}$",message = "不能含特殊字符且长度为2-32")
    private String department;


    private String comment;




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



    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public String toString(){
        return "用户名："+username+"；姓名："+name+"；电话："+telephone+"；email:"+email+
                "；部门："+department+
                "；描述："+comment;
    }

}