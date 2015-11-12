package com.ambimmort.nisp3.model.ui.f.area;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by qinxiaoyao on 2015/6/15.
 */
public class EditDomainBean {
    private String id;
    @NotEmpty(message = "区域名不能为空")
    @Pattern(regexp = "(([\\u4E00-\\u9FA5][\\u4E00-\\u9FA5a-zA-Z0-9_]{1,31})|([a-zA-Z][a-zA-Z0-9_]{1,31}))",message = "输入不合法")
    private String name;
    private String pid;
    //private String pname;
    private String description;
    @NotEmpty(message = "IP范围不能为空")
    @Pattern(regexp = "^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\/(\\d{1}|[0-2]{1}\\d{1}|3[0-2])$",message = "IP地址格式错误")
    private String iPRange;
    @NotEmpty(message="不能为空")
    @Pattern(regexp = "^([\\u4E00-\\u9FA5]|\\w)*$",message = "输入不合法")
    private String crmCondition;
    private String crmColumn;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getPname() {
        return pname;
    }*/

   /* //public void setPname(String pname) {
        this.pname = pname;
    }*/

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

    public String getiPRange() {
        return iPRange;
    }

    public void setiPRange(String iPRange) {
        this.iPRange = iPRange;
    }

    public String getCrmCondition() {
        return crmCondition;
    }

    public void setCrmCondition(String crmCondition) {
        this.crmCondition = crmCondition;
    }

    public String getCrmColumn() {return crmColumn;}

    public void setCrmColumn(String crmColumn) {this.crmColumn = crmColumn;}

    public String toString(){
        return  "区域ID："+id+ "；区域名称："+name+"；描述："+description+"；IP范围："+iPRange+"；CRM规则："+crmCondition;
    }
}
