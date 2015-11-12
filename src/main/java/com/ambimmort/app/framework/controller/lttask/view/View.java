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
@Controller("/f/lttask/manager/task.view.do")
public class View {
    private ModelAndView view = new ModelAndView("f/lttask/view");


    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/task.view.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("logId")int logId) {

            AbstractTask abstractTask = longTimeTaskRunner.getHistoryTaskLog(logId);
            if(abstractTask==null){
                view.addObject("hasHistoryTask", false);
            }else{

                view.addObject("hasHistoryTask", true);


                view.addObject("task_model", abstractTask.getModel());
            }
            view.addObject("isRunning", false);
            return view;





    }
}
