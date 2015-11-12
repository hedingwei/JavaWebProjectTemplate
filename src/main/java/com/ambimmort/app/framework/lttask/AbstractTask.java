package com.ambimmort.app.framework.lttask;

import com.ambimmort.app.framework.uitls.Application;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Created by hedingwei on 6/16/15.
 */
public abstract class AbstractTask extends Observable implements Runnable,Serializable {

    private TaskModel model = new TaskModel();

    private String tmpDir = null;

    private File tempWorkingDir = null;

    private int i = 0;

    private HashMap<String,Object> extContext = new HashMap<>();

    public HashMap<String, Object> getExtContext() {
        return extContext;
    }

    public void setExtContext(HashMap<String, Object> extContext) {
        this.extContext = extContext;
    }

    public void setI(int i){
        this.i = i;
    }

    public TaskModel getModel() {
        return model;
    }

    public void setModel(TaskModel model) {
        this.model = model;
    }

    public String getActivator() {
        return this.model.getActivator();
    }

    public void setActivator(String activator) {
        this.model.setActivator(activator);
    }

    public long getStopTimestamp() {
        return this.model.getStopTime();
    }

    public void setStopTimestamp(long stopTimestamp) {
        this.model.setStopTime(stopTimestamp);
    }

    public void setProgress(int progress) {
        this.model.setProgress(progress);
    }

    public String getCancelReason() {
        return this.model.getCancelReason();
    }

    public void setCancelReason(String cancelReason) {
        this.model.setCancelReason(cancelReason);
    }

    public void setContext(JSONObject context) {
        this.model.setContext(context);
    }

    public boolean isCancelled() {
        return this.model.isCancelled();
    }

    public boolean isSuccess(){
        return this.model.getStatus().equals("success");
    }

    public int getI() {
        return i;
    }

    protected void reportProgressByStep(int stepIndex){

        if(getModel().getContext().containsKey("totalSteps")){

            int totalSteps = (Integer)getModel().getContext().get("totalSteps");
            getModel().setProgress((int)Math.floor((stepIndex + 0.0d) / totalSteps * 100));
            System.out.println(getClass().getName()+" reported by step : with totalSteps "+totalSteps);
        }else{
            System.out.println("reported by step : without totalSteps ");
        }
    }


    protected void reportProgress(int percent){
        getModel().setProgress(percent);
    }







    public String getLttType(){
        if(getModel().getContext().containsKey("lttType")){
            return (String) getModel().getContext().get("lttType");
        }else{
            return "";
        }
    }

    public void setLttType(String lttType){
        getModel().getContext().put("lttType",lttType);
    }

    public void parse(String jsonModel){
        TaskModel model = (TaskModel) JSONObject.toBean(JSONObject.fromObject(jsonModel),TaskModel.class);
        setModel(model);
    }

    public File getTempWorkingDir() {
        return tempWorkingDir;
    }

    public void setTempWorkingDir(File tempWorkingDir) {
        this.tempWorkingDir = tempWorkingDir;
    }

    public List<String> getLogs(){
        return model.getLogs();
    }



    public void setCancelled(String reason) {
        setCancelled(reason,"system");

    }

    public void setCancelled(String reason, String canceller) {
        this.model.setStatus("cancelled");
        this.model.setCancelled(true);
        this.model.setCanceller(canceller);
        try {
            this.model.setCancelReason(Base64.encodeBase64String(reason.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("cancelled reason:"+this.model.getCancelReason());

    }

    public String getStatus() {
        return model.getStatus();
    }

    public void setStatus(String status) {
        this.setStatus(status);
    }

    public int getProgress() {
        return model.getProgress();
    }

    public String getId() {
        return model.getId();
    }

    public void setId(String id) {
        this.model.setId(id);
    }

    public Map getContext() {
        return model.getContext();
    }

    public long getStartTimestamp() {
        return model.getStartTime();
    }

    public void setStartTimestamp(long startTimestamp) {
        this.model.setStartTime(startTimestamp);
    }

    public long getElapseTime() {
        return this.model.getElapseTime();
    }

    public void setElapseTime(long elapseTime) {
        this.model.setElapseTime(elapseTime);
    }

    public long getRemainTime() {
        return model.getRemainTime();
    }

    public void setRemainTime(long remainTime) {
        this.model.setRemainTime(remainTime);
    }

    public void setCanceller(String canceller){
        this.model.setCanceller(canceller);
    }

    public String getCanceller(){
        return this.model.getCanceller();
    }

    public void cancel(String reason) {
        cancel(reason,"system");
    }

    public void cancel(TaskCancelEvent event){
        setCancelled(event.getReason());
        setCanceller("system");
        throw new TaskCancelException(event);
    }

    public void cancel(String reason, String canceller) {
        setCancelled(reason);
        setCanceller("system");
        TaskCancelEvent event = new TaskCancelEvent();
        event.setCancelledByParent(true);
        event.setCancelledBySubtask(false);
        event.setCanceller("system");
        event.setCancelSubtaskId(getId());
        event.setReason(reason);
        throw new TaskCancelException(event);
    }

    public void log(String message){
        try {
            this.model.getLogs().add(Base64.encodeBase64String(message.getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void init(final TaskModel model) throws Throwable{}

    public abstract int getSteps() throws Throwable;

    public abstract void processStep(final int i, final TaskModel model) throws Throwable;

    public void onFinished(final TaskModel model) throws Throwable{}

    public void onCancelled(final int i, final TaskModel model, String reason) throws Throwable{}

    public void onError(final int i, final TaskModel model, Throwable throwable){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos,true,"utf-8");
            throwable.printStackTrace(ps);
            ps.close();
            log(new String(baos.toByteArray(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void onInitError(final TaskModel model, Throwable throwable){

    }

    public void onFinalize(final TaskModel model) throws Throwable{

    }


    public void _doUpdateStatus(){
        try {
            doTimeEstimate(model,_index,_totalSteps);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private int _totalSteps = 0;
    private int _index = 0;
    public void run() {


        /**
         *  更新model的开始时间
         */
        model.setStartTime(System.currentTimeMillis());

        initTmpWorkingDir();

        int totalSteps = 0;


        /**
         *  任务的初始化阶段。
         *  1.如果因cancel(String reason)或cancel(String reason,String canceller)
         *  方法调用而产生TaskCancelException，则设置model的isCancel标记为true，代表任务被取消，使得
         *  后面的步骤循环不执行；
         *  2.若在init阶段捕获了任何其他Throwable，则触发onInitError方法进行处理，子类可以重写该方法。
         */
        try {
            init(model);
        }catch (TaskCancelException be){
            model.setStatus("cancelled");
            model.setCancelled(true);
            try {
                model.setCancelReason(Base64.encodeBase64String(be.getMessage().getBytes("utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers(be.getEvent());
        }catch (Throwable throwable){
            if(throwable instanceof StepZeroException){
                cancel("步长为0异常，请检查"+getClass().getName()+"的getSteps方法");
            }
            onInitError(model,throwable);
            cancel(throwable.getMessage());
        }


        double lengthRate = 0;

        if(!model.isCancelled()){

            /**
             * 通过子类实现的getSteps()获得总循环次数。
             * 注意，这里定义totalSteps变量的原因时只让getSteps()方法调用一次
             * 避免子类实现的getSteps()耗时太长。
             */
            try {
                totalSteps = getSteps();
                _totalSteps = totalSteps;
                model.getContext().put("totalSteps",totalSteps);
                if(totalSteps==0){
                    cancel("步长为0异常，请检查"+getClass().getName()+"的getSteps方法");
                }

            }catch (TaskCancelException be){
                model.setStatus("cancelled");
                model.setCancelled(true);
                try {
                    model.setCancelReason(Base64.encodeBase64String(be.getMessage().getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                setChanged();
                notifyObservers(be.getEvent());
            }catch (Throwable throwable){
                throwable.printStackTrace();
                onInitError(model,throwable);
            }




            /**
             * 步骤循环处理阶段。
             *
             * 1.在Init阶段和步骤循环处理阶段之间有一个衔接，即，如果在Init阶段被cancel，则该阶段直接掠过。
             *   并由onCancelled方法处理，此时onCancelled方法的第一个参数i为-1。
             *
             *
             *
             */

            lengthRate = 100.0 / totalSteps;



            for ( i = 0; i < totalSteps; i++) {
                _index = i;
                this.i = i;
                /**
                 * 如果任务被取消
                 */
                if (model.isCancelled()) {
                    try {
                        model.setStatus("cancelled");
                        onCancelled(i, model, model.getCancelReason());
                    } catch (Throwable throwable) {

                        throwable.printStackTrace();
                    }
                    setChanged();
                    TaskCancelEvent event = new TaskCancelEvent();
                    event.setReason(model.getCancelReason());
                    event.setCancelSubtaskId(getId());
                    event.setCanceller(model.getCanceller());
                    event.setCancelledByParent(true);
                    event.setCancelledBySubtask(false);
                    notifyObservers(event);
                    break;
                }

                /**
                 * 如果任务还没有被取消，则调用processStep方法处理第i步。然后进行时间估计。
                 * 时间估计要统计已经经过多长时间和还需要多少时间完成。
                 * 在此过程中，如果捕获了TaskCancelException则进行cancel逻辑的处理。
                 */
                try {
                    model.getContext().put("cindex",i);
                    processStep(i, model);
                    reportProgressByStep(i + 1);
                    doTimeEstimate(model,i,totalSteps);
                } catch (TaskCancelException tce) {
                    try {
                        this.model.setProgress((int) ((i + 1) * lengthRate));
                        model.setCancelled(true);
                        model.setCancelReason(tce.getMessage());
                        model.setStatus("cancelled");
                        onCancelled(i, model, model.getCancelReason());
                        setChanged();
                        TaskCancelEvent event = tce.getEvent();
                        event.setReason(model.getCancelReason());

                        event.setCancelSubtaskId(getId());
                        event.setCanceller(model.getCanceller());

                        notifyObservers(event);
                        break;
                    } catch (Throwable throwable) {

                        throwable.printStackTrace();
                    }

                    break;
                } catch (Throwable throwable) {
                    onError(i, model, throwable);
                }

                this.model.setProgress((int) ((i + 1) * lengthRate));

            }
            try {
                onFinished(model);
                setChanged();
                notifyObservers("onFinished");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }else{
            try {
                onCancelled(-1, model, model.getCancelReason());
                model.setStatus("cancelled");
                setChanged();
                TaskCancelEvent event = new TaskCancelEvent();
                event.setReason(model.getCancelReason());
                event.setCancelSubtaskId(getId());
                event.setCanceller(model.getCanceller());
                event.setCancelledByParent(true);
                event.setCancelledBySubtask(false);
                notifyObservers(event);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        try {
            cleanTmpWorkingDir();
            onFinalize(model);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        model.setStopTime(System.currentTimeMillis());
        model.setStatus("success");
        setChanged();
        notifyObservers("onFinalized");

    }

    protected void doTimeEstimate(TaskModel taskModel,int i, int totalSteps) throws Throwable{
        taskModel.setElapseTime(System.currentTimeMillis()-taskModel.getStartTime());
        taskModel.setRemainTime((long)((double)taskModel.getElapseTime()/i)*(totalSteps-i));
    }

    protected void doTimeEstimate(int i, int totalSteps) throws Throwable{
        getModel().setElapseTime(System.currentTimeMillis() - getModel().getStartTime());
        getModel().setRemainTime((long) ((double) getModel().getElapseTime() / i) * (totalSteps - i));
    }


    public void initTmpWorkingDir(){
        File lttaskTmpDir = new File(Application.getTmpDir(),"lttasks");
        lttaskTmpDir.mkdirs();
        tmpDir = UUID.randomUUID().toString();
        tempWorkingDir = new File(lttaskTmpDir,tmpDir);
        tempWorkingDir.mkdirs();
    }

    public void cleanTmpWorkingDir(){
        try {
            FileUtils.cleanDirectory(tempWorkingDir);
            FileUtils.deleteDirectory(tempWorkingDir);
        } catch (IOException e) {
            log(e.getMessage());
        }
    }

}
