package com.ambimmort.app.framework.lttask;

/**
 * Created by hedingwei on 7/6/15.
 */
public interface ITaskListener {

    public void onNewBorn(ITask task);

    public void onNewBornError(ITask task);

    public void onInit(ITask task);

    public void onInitError(ITask task);

    public void onCancel(ITask task);

    public void onStopped(ITask task);

    public void onProcessed(ITask task);



}
