package com.ambimmort.app.framework.uitls.timeout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hedingwei on 5/15/15.
 */
public class TimeoutDetecter {

    private static TimeoutDetecter instance = null;

    private List<ITimeoutDetectSupport> targets = new ArrayList<>();

    public static TimeoutDetecter getInstance(){
        if(instance==null){
            instance = new TimeoutDetecter();
        }
        return instance;
    }

    public void start(){
        while(true){
            try {
                Thread.sleep(500);

                detect();

            }catch (Throwable t){
                t.printStackTrace();
            }
        }
    }


    public synchronized void add(ITimeoutDetectSupport target){
        if(!targets.contains(target)){
            targets.add(target);
            target.setStartTime(System.currentTimeMillis());
        }
    }

    public synchronized void remove(ITimeoutDetectSupport target){
        if(targets.contains(target)){
            targets.remove(target);
        }
    }

    private synchronized void detect(){
        long ct = System.currentTimeMillis();
        Iterator<ITimeoutDetectSupport> it = targets.iterator();
        ITimeoutDetectSupport target = null;
        while(it.hasNext()){
            target = it.next();
            if((ct-target.getStartTime())>=target.getTimeout()){
                target.timeoutAction(ct,null,this);
            }
            it.remove();
        }
    }

}
