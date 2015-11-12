package com.ambimmort.app.framework.lttask;

import org.quartz.TriggerKey;

/**
 * Created by hedingwei on 6/21/15.
 */
public class LTTaskBean {

    private String taskId;

    private String cron;

    private Class<? extends AbstractTask> taskClass;

    private String name;

    private String description;

    private int progress;

    private String status;

    private String lastStatus;

    private long lastStartTime;

    private long lastElapseTimes;

    private long lastSuccessStartTime;

    private long lastSuccessElapseTimes;

    private long totalTimes;

    private long nextScheduledTime;

    private TriggerKey triggerKey;

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public long getLastElapseTimes() {
        return lastElapseTimes;
    }

    public void setLastElapseTimes(long lastElapseTimes) {
        this.lastElapseTimes = lastElapseTimes;
    }

    public long getLastSuccessStartTime() {
        return lastSuccessStartTime;
    }

    public void setLastSuccessStartTime(long lastSuccessStartTime) {
        this.lastSuccessStartTime = lastSuccessStartTime;
    }

    public long getLastSuccessElapseTimes() {
        return lastSuccessElapseTimes;
    }

    public void setLastSuccessElapseTimes(long lastSuccessElapseTimes) {
        this.lastSuccessElapseTimes = lastSuccessElapseTimes;
    }

    public TriggerKey getTriggerKey() {
        return triggerKey;
    }

    public void setTriggerKey(TriggerKey triggerKey) {
        this.triggerKey = triggerKey;
    }

    public long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }



    public long getNextScheduledTime() {
        return nextScheduledTime;
    }

    public void setNextScheduledTime(long nextScheduledTime) {
        this.nextScheduledTime = nextScheduledTime;
    }

    public long getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(long totalTimes) {
        this.totalTimes = totalTimes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public Class<? extends AbstractTask> getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(Class<? extends AbstractTask> taskClass) {
        this.taskClass = taskClass;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
