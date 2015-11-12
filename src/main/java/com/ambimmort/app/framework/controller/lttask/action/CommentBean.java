package com.ambimmort.app.framework.controller.lttask.action;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by hedingwei on 6/20/15.
 */
public class CommentBean {

    @NotEmpty(message = "请填写人工启动该任务的原因")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
