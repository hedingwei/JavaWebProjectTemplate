package com.ambimmort.app.framework.lttask;

/**
 * Created by hedingwei on 7/5/15.
 */
public class AbstractTaskEvent {

    private AbstractTask task;

    public AbstractTask getTask() {
        return task;
    }

    public void setTask(AbstractTask task) {
        this.task = task;
    }
}
