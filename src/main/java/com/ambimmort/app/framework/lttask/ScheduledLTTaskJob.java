package com.ambimmort.app.framework.lttask;

import com.ambimmort.app.framework.uitls.Application;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hedingwei on 6/21/15.
 */
public class ScheduledLTTaskJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("taskId"));

        try {

            AbstractTask task = null;
            for (LTTaskBean b : ((LTTaskManager) Application.getBean("longTimeTaskRunner")).getRegisteredTasks()) {
                if (b.getTaskId().equals(jobExecutionContext.getJobDetail().getJobDataMap().get("taskId"))) {
                    task = b.getTaskClass().newInstance();
                    task.setId(b.getTaskId());
                    task.getModel().setDescription(b.getDescription());
                    task.getModel().setName(b.getName());
                    ((LTTaskManager) Application.getBean("longTimeTaskRunner")).start(task);
                }
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }



    }
}
