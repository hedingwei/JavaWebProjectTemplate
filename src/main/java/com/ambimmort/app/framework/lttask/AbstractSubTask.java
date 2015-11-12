package com.ambimmort.app.framework.lttask;

import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by hedingwei on 6/17/15.
 */
public abstract class AbstractSubTask extends AbstractTask implements Serializable,Observer {

    private AbstractMSTLTTask parentTask;

    public AbstractMSTLTTask getParentTask() {
        return parentTask;
    }

    public void setParentTask(AbstractMSTLTTask parentTask) {
        this.parentTask = parentTask;
        setTempWorkingDir(parentTask.getTempWorkingDir());
    }

    @Override
    protected void reportProgress(int percent) {
        super.reportProgress(percent);
        int c = parentTask.getCurrentStepInAll();
        int t = parentTask.getTotalSteps();
        try {
            int mt = getSteps();
            double r = mt/(t+0.0);
            double tal = (c)/(t+0.0) + (percent/100.0*r) ;
//            System.out.println((int)(tal*100));
            parentTask.setProgress((int)(tal*100));

            parentTask.getModel().setElapseTime(System.currentTimeMillis() - parentTask.getModel().getStartTime());
            long time = (long)((1- tal)*(parentTask.getModel().getElapseTime()/tal));
            parentTask.getModel().setRemainTime(time);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }




    }

    @Override
    public void update(Observable o, Object arg) {
       if(arg instanceof TaskCancelEvent){

           TaskCancelEvent event = (TaskCancelEvent) arg;
           if(event.isCancelledByParent()){

               if(getModel().getProgress()==100){

               }else if(getModel().getProgress()<100&&getModel().getProgress()>0){
                   getModel().setCancelled(true);
                   try {
                       getModel().setCancelReason(Base64.encodeBase64String(event.getReason().getBytes("utf-8")));
                   } catch (UnsupportedEncodingException e) {
                       e.printStackTrace();
                   }
                   getModel().setCanceller(event.getCanceller());
                   getModel().setStatus("cancelled");
               }else{

               }

           }else if(event.isCancelledBySubtask()){
               if(event.getCancelSubtaskId().equals(getId())){
                   getModel().setCancelled(true);
                   try {
                       getModel().setCancelReason(Base64.encodeBase64String(event.getReason().getBytes("utf-8")));
                   } catch (UnsupportedEncodingException e) {
                       e.printStackTrace();
                   }
                   getModel().setCanceller(event.getCanceller());
                   getModel().setStatus("cancelled");

                   System.out.println(event.getCancelSubtaskId()+"\t"+getId());
               }

           }



       }
    }

    @Override
    public void cancel(TaskCancelEvent event) {
        setCancelled(event.getReason());
        event.setCancelledBySubtask(true);
        event.setCancelledByParent(false);
        event.setCancelSubtaskId(getId());
        throw new TaskCancelException(event);
    }


    @Override
    public void cancel(String reason, String canceller) {
        setCancelled(reason);
        setCanceller(canceller);
        TaskCancelEvent event = new TaskCancelEvent();
        event.setCancelledBySubtask(true);
        event.setCancelledByParent(false);
        event.setCancelSubtaskId(getId());
        event.setReason(reason);
        event.setCanceller(canceller);
        throw new TaskCancelException(event);
    }


}
