package com.ambimmort.app.framework.lttask;

import org.apache.commons.codec.binary.Base64;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.quartz.JobBuilder.*;

import static org.quartz.TriggerBuilder.*;

/**
 * Created by hedingwei on 6/16/15.
 */

public class LTTaskManager implements Observer {


    private DataSource dataSource;

    private Scheduler scheduler;

    private ExecutorService executorService = null;

    private List<LTTaskBean> registeredTasks = new ArrayList<>();

    private HashMap<String, AbstractTask> runningTasks = new HashMap<String,AbstractTask>();

    public AbstractTask getLastTask(String taskId){

        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement("select * from t_lttask_log WHERE taskid=? ORDER BY starttime DESC LIMIT 1");
            ps.setString(1,taskId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String modelClass = rs.getString("modelclass");
                String content = rs.getString("content");
                String startComment = rs.getString("startComment");

                String cancelComment = rs.getString("cancelComment");

                AbstractTask task = (AbstractTask) Class.forName(modelClass).newInstance();

                task.parse(content);
                task.getContext().put("startComment",startComment);
                task.getContext().put("cancelComment",cancelComment);
                return task;

            }else{
                return null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return  null;

    }

    public AbstractTask getHistoryTaskLog(int logId){

        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement("select * from t_lttask_log WHERE id =?");
            ps.setInt(1,logId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String modelClass = rs.getString("modelclass");
                String content = rs.getString("content");
                String startComment = rs.getString("startComment");

                String cancelComment = rs.getString("cancelComment");

                AbstractTask task = (AbstractTask) Class.forName(modelClass).newInstance();

                task.parse(content);
                task.getContext().put("startComment",startComment);
                task.getContext().put("cancelComment",cancelComment);
                return task;

            }else{
                return null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return  null;

    }


    public LTTaskBean getRegisteredTask(String id){
        for(LTTaskBean b:registeredTasks){
            if(b.getTaskId().equals(id)){
                if(getRunningTask(id)!=null){
                    b.setStatus("执行中");
                    b.setProgress(getRunningTask(id).getProgress());
                }else{
                    b.setStatus("未执行");
                    b.setProgress(0);
                }
                return b;
            }
        }
        return null;
    }

    public void updateRegisteredTaskBeanStatus(){
        for(LTTaskBean b:registeredTasks){
                if(getRunningTask(b.getTaskId())!=null){
                    b.setStatus("执行中");
                    b.setProgress(getRunningTask(b.getTaskId()).getProgress());
                }else{
                    b.setStatus("未执行");
                    b.setProgress(0);
                }
        }
    }

    public LTTaskManager(){
        executorService = Executors.newFixedThreadPool(10);

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            scheduler = schedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }



    public void xinit(){

        for(LTTaskBean taskBean: registeredTasks){
            try {
                //引进作业程序
                JobDetail jobDetail =newJob(ScheduledLTTaskJob.class).withIdentity(taskBean.getTaskId()).usingJobData("taskId", taskBean.getTaskId()).build();

                CronTrigger trigger = newTrigger().withIdentity("trigger-"+taskBean.getTaskId()).withSchedule(CronScheduleBuilder.cronSchedule(taskBean.getCron())).build();

                scheduler.scheduleJob(jobDetail, trigger);
                taskBean.setTriggerKey(trigger.getKey());
                scheduler.start();
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }

        }

    }




    public void start(AbstractTask task){
        start(task,"");
    }

    public void start(AbstractTask task,String startComment){
        if(!runningTasks.containsKey(task.getId())){
            try {
                task.getModel().getContext().put("startComment", Base64.encodeBase64String(startComment.getBytes("utf-8")));
                task.getModel().getContext().put("cancelComment","");
                task.setStartTimestamp(System.currentTimeMillis());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            task.addObserver(this);
            runningTasks.put(task.getId(), task);
            executorService.submit(task);
        }
    }

    public AbstractTask getRunningTask(String key) {
        return runningTasks.get(key);
    }



    private void log(AbstractTask task){
        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO t_lttask_log VALUES (NULL,?,?,?,?,?,?,?,?,?,?)");

            ps.setObject(1, new Date(task.getStartTimestamp()));
            ps.setObject(2, new Date(System.currentTimeMillis()));
            ps.setString(3, task.getActivator());



            if(task.isCancelled()&&task.getCanceller().equals("system")){
                ps.setString(4,"failed");
            }else if(task.isCancelled()&&(!task.getActivator().equals("system"))){
                ps.setString(4,"cancelled");
            }else{
                ps.setString(4,"success");
            }

            ps.setString(5, task.getModel().toString());

            ps.setString(6,task.getClass().getName());

            ps.setString(7,task.getModel().getId());

            ps.setString(8, (String) task.getContext().get("startComment"));

            ps.setString(9, (String) task.getCancelReason());

            ps.setString(10,task.getLttType());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        // arg==-2 代表任务被取消
        if("onFinalized"==arg||(arg instanceof TaskCancelEvent)){
           Iterator<AbstractTask> it = runningTasks.values().iterator();
            AbstractTask ptr = null;
            while(it.hasNext()){
                ptr = it.next();
                if(ptr==o) {
                    it.remove();
                    try {
                        ptr.setStopTimestamp(System.currentTimeMillis());
                        log(ptr);
                    }catch (Throwable t){
                        t.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    public List<LTTaskBean> getRegisteredTasks() {

        return registeredTasks;
    }

    public boolean isRegisteredTask(String taskId){
        return getRegisteredTask(taskId)!=null;
    }

    public void setRegisteredTasks(List<LTTaskBean> registeredTasks) {
        this.registeredTasks = registeredTasks;
    }

    public HashMap<String, AbstractTask> getRunningTasks() {
        return runningTasks;
    }

    public void setRunningTasks(HashMap<String, AbstractTask> runningTasks) {
        this.runningTasks = runningTasks;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
