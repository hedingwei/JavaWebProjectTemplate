package com.ambimmort.nisp3.model.ui.f.CRM;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by ZZZZ on 2015/6/19.
 */
public class CRMColumnBean {

    private String id;
    @NotEmpty(message ="字段不能为空！" )
    private String field;
    @NotEmpty(message ="字段名不能为空！" )
    private String name;
   // @NotEmpty(message ="字段类型不能为空！" )
    private String fieldType;
    @NotEmpty(message ="字段长度不能为空！" )
    private String lengthValue;

    private String defaultValue;
    private boolean emptyValue;
    private boolean unique;
    private boolean isEmpty;

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }


    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(String lengthValue) {
        this.lengthValue = lengthValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isEmptyValue() {
        return emptyValue;
    }

    public void setEmptyValue(boolean emptyValue) {
        this.emptyValue = emptyValue;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "ID："+id+"；字段："+field+"；字段名："+name+"；字段类型:"+fieldType+
                "；字段长度"+lengthValue+";默认值："+defaultValue+";空值："+emptyValue+";唯一："+unique+";是否为空："+isEmpty;
    }
}
