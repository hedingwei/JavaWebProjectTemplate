package com.ambimmort.app.framework.controller.lttask.action;

/**
 * Created by hedingwei on 6/23/15.
 */
public class TaskLogBean {
    private int id;
    private long starttime;
    private long endtime;
    private String startby;
    private String result;
    private String startComment;
    private String cancelComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public String getStartby() {
        return startby;
    }

    public void setStartby(String startby) {
        this.startby = startby;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStartComment() {
        return startComment;
    }

    public void setStartComment(String startComment) {
        this.startComment = startComment;
    }

    public String getCancelComment() {
        return cancelComment;
    }

    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }
}
