package com.ambimmort.nisp3.model.ui.f.user;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by liu on 2015/6/16.
 */
public class ResetPasswordBean {
    private String username;
    @Pattern(regexp = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{4,16}$",message = "请填写正确格式的密码:长度为4-16字母或者数字.不包含特殊符号")
    private String password;
    @Pattern(regexp = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{4,16}$",message = "请填写正确格式的密码:长度为4-16字母或者数字.不包含特殊符号")
    private String password_validate;
    @Pattern(regexp = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{4,16}$",message = "请填写正确格式的密码:长度为4-16字母或者数字.不包含特殊符号")
    private String oldpassword;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getPassword_validate() {
        return password_validate;
    }

    public void setPassword_validate(String password_validate) {
        this.password_validate = password_validate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String toString(){
        return "用户名："+username;
    }


}
