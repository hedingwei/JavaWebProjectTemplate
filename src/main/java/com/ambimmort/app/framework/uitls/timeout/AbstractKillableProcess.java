package com.ambimmort.app.framework.uitls.timeout;

import java.io.IOException;

/**
 * Created by hedingwei on 5/15/15.
 */
public abstract class AbstractKillableProcess extends Thread implements  ITimeoutDetectSupport, IKillSupport{

    private long startTime;

    private long timeout;



    private int exitValue ;


    @Override
    public synchronized void start() {
        super.start();
        TimeoutDetecter.getInstance().add(this);
    }

    public int getExitValue() {
        return exitValue;
    }

    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    @Override
    public void killedSuccess() {

    }

    @Override
    public void killedFailed(Object args) {

    }

    @Override
    public void setStartTime(long timestamp) {
        this.startTime = timestamp;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public void timeoutAction(long currentTime, Thread thread, Object args) {
        if(kill()){
            killedSuccess();
        }else{
            killedFailed(args);
        }
    }

    @Override
    public long getTimeout() {
        return this.timeout;
    }

    @Override
    public long getStartTime() {
        return this.startTime;
    }
}
