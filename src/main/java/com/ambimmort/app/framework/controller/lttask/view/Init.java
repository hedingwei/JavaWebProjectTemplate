package com.ambimmort.app.framework.controller.lttask.view;

import com.ambimmort.app.framework.lttask.AbstractTask;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/init.view.do")
public class Init {
    private ModelAndView init = new ModelAndView("f/lttask/init");


    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/init.view.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("taskId")String taskId) {

        ModelAndView status = new ModelAndView("redirect:status.view.do?taskId="+taskId);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(longTimeTaskRunner.getRunningTask(taskId)==null){

            if(longTimeTaskRunner.isRegisteredTask(taskId)){
                AbstractTask abstractTask = longTimeTaskRunner.getLastTask(taskId);
                if(abstractTask==null){
                    init.addObject("hasHistoryTask",false);
                }else{

                    init.addObject("hasHistoryTask",true);


                    init.addObject("task_model",abstractTask.getModel());
                }
                init.addObject("isRunning", false);
                init.addObject("isUnRegisteredTask",false);
                return init;
            }else{
                AbstractTask abstractTask = longTimeTaskRunner.getLastTask(taskId);
                if(abstractTask==null){
                    init.addObject("hasHistoryTask",false);
                }else{

                    init.addObject("hasHistoryTask",true);


                    init.addObject("task_model",abstractTask.getModel());
                }
                init.addObject("isRunning", false);
                init.addObject("isUnRegisteredTask",true);
                return init;
            }


        }else{
            return status;

        }




    }
}
