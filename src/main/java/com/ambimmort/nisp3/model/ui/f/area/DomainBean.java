package com.ambimmort.nisp3.model.ui.f.area;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by qinxiaoyao on 2015/6/15.
 */
public class DomainBean {
    private String id;
    @NotEmpty(message = "区域名不能为空")
    @Pattern(regexp ="(([\\u4E00-\\u9FA5][\\u4E00-\\u9FA5a-zA-Z0-9_]{1,31})|([a-zA-Z][a-zA-Z0-9_]{1,31}))",message = "请输入正确格式的区域名，长度为4-32")
    private String name;
    private String pid;
    private String description;

    @NotEmpty(message = "IP范围不能为空")
    @Pattern(regexp = "^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\/(\\d{1}|[0-2]{1}\\d{1}|([0-9]|[1-9][0-9]|[1][0-9][0-9]|[2][0-5][0-5]))$|^\\s*$",
            message = "请输入正确格式的IP地址范围（如：192.168.1.1/24）")
    private String iPRange;

    @NotEmpty(message = "crm规则不能为空")
    @Pattern(regexp = "^([\\u4E00-\\u9FA5]|\\w)*$",message = "请输入正确格式的条件（不含特殊字符）")
    private String crmCondition;
    private String crmColumn;
    private String pname;
    private long pcode;

    public long getPcode() {
        return pcode;
    }

    public void setPcode(long pcode) {
        this.pcode = pcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    public String toString(){
        return  "ID："+id+ "；区域名称："+name+"；描述："+description+"；父区域id: "+pid+"；父区域："+pname+"；IP范围："+iPRange+"；CRM规则："+crmCondition;
    }
}
