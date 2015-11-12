package com.ambimmort.app.framework.controller.lttask.view;

import com.ambimmort.app.framework.lttask.AbstractTask;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import com.ambimmort.app.framework.lttask.AbstractMSTLTTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/status.view.do")
public class Status {
    private ModelAndView success = new ModelAndView("f/lttask/status");






    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/status.view.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("taskId")String taskId) {

        ModelAndView init = new ModelAndView("redirect:init.view.do?taskId="+taskId);

        AbstractTask task = (AbstractTask) longTimeTaskRunner.getRunningTask(taskId);
        if(task!=null){
            if(task.getProgress()!=100){
                success.addObject("isRunning",true);

                init.addObject("task_model",task.getModel());

            }else{
                success.addObject("isRunning",false);

                init.addObject("task_model",task.getModel());

            }
        }else{
            return init;
        }

        success.addObject("task_model",task.getModel());
        return success;

    }
}
