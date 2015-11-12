package com.ambimmort.app.framework.lttask;

/**
 * Created by hedingwei on 6/16/15.
 */
public class TaskCancelException extends RuntimeException {

    private TaskCancelEvent event = null;

    public TaskCancelException(TaskCancelEvent event){
        super(event.getReason());
        this.event = event;
    }

    public TaskCancelEvent getEvent() {
        return event;
    }
}
