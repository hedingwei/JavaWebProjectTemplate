package com.ambimmort.nisp3.model.ui.f.area;

/**
 * Created by liu on 2015/6/16.
 */
public class AreaBean {
    private String areaId;
    private String areaName;
    private String pid;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    public String toString(){
        return "区域id："+areaId+"；区域名称："+areaName+"；父区域id："+pid;
    }
}
