package com.ambimmort.app.framework.uitls.timeout;

/**
 * Created by hedingwei on 5/15/15.
 */
public interface ITimeoutDetectSupport {

    public void setStartTime(long timestamp);

    public void setTimeout(long timeout);

    public void timeoutAction(long currentTime, Thread thread, Object args);

    public long getTimeout();

    public long getStartTime();
}
