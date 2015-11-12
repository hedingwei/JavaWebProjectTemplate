package com.ambimmort.app.framework.lttask;

/**
 * Created by hedingwei on 7/6/15.
 * A task has 4 states, newborn,init, ready, running and stopped. Each task can and can only execute 1 time.
 * when task object created(just after construction), its state was set to newborn. 当init方法开始执行时，状态
 * 会被设置成init，当init方法执行完毕后，状态设置未ready，此时系统可以进入任务步骤循环，当进入循环前，状态设置未running，当
 * 任务循环结束后或中途出现任何异常，状态被设置未stopped。
 */
public interface ITask {

    public final static int STATE_NEWBORN = 0;
    public final static int STATE_INIT = 1;
    public final static int STATE_READY = 2;
    public final static int STATE_RUNNING = 3;
    public final static int STATE_STOPPED = 4;

    /**
     * 获取任务的状态
     * @return 当前任务的状态
     */
    public int getState();

    /**
     * 设置任务的状态，任务的状态仅可能是0,1,2,3或4
     * @param state
     */
    public void setState(int state);


    public void init();


    public void process(Object arg);


    public void addTaskListener();



}
