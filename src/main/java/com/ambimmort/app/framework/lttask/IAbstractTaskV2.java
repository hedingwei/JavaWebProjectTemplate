package com.ambimmort.app.framework.lttask;

import java.util.HashMap;

/**
 * Created by hedingwei on 7/6/15.
 *
 * A task has 4 states, newborn,init, ready, running and stopped. Each task can and can only execute 1 time.
 * when task object created(just after construction), its state was set to newborn. 当init方法开始执行时，状态
 * 会被设置成init，当init方法执行完毕后，状态设置未ready，此时系统可以进入任务步骤循环，当进入循环前，状态设置未running，当
 * 任务循环结束后或中途出现任何异常，状态被设置未stopped。
 *
 */
public interface IAbstractTaskV2 {
    HashMap<String, Object> getExtContext();

    void setExtContext(HashMap<String, Object> extContext);

    TaskModel getModel();

    void setModel(TaskModel model);

    String getActivator();

    void setActivator(String activator);

    long getStopTimestamp();

    void setStopTimestamp(long stopTimestamp);

    void setProgress(int progress);

    String getCancelReason();

    void setCancelReason(String cancelReason);

    String getStatus();

    void setStatus(String status);

    long getStartTimestamp();

    void setStartTimestamp(long startTimestamp);

    long getElapseTime();

    void setElapseTime(long elapseTime);

    long getRemainTime();

    void setRemainTime(long remainTime);

    void setCanceller(String canceller);

    String getCanceller();

    void cancel(String reason);

    void cancel(TaskCancelEvent event);

    void cancel(String reason, String canceller);

    void log(String message);

    void init(TaskModel model) throws Throwable;

    int getSteps() throws Throwable;

    void processStep(int i, TaskModel model) throws Throwable;
}
