package com.ambimmort.app.framework.uitls.timeout;

/**
 * Created by hedingwei on 5/15/15.
 */
public interface IKillSupport {

    public boolean kill();

    public void killedSuccess();

    public void killedFailed(Object args);

}
