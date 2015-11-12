package com.ambimmort.app.framework.controller.lttask.view;

import com.ambimmort.app.framework.controller.lttask.action.CommentBean;
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
@Controller("/f/lttask/manager/start.view.do")
public class Start {



    private ModelAndView start = new ModelAndView("f/lttask/start");



    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/start.view.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("taskId")String taskId) {

        ModelAndView status = new ModelAndView("redirect:status.view.do?taskId="+taskId);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(longTimeTaskRunner.getRunningTask(taskId)!=null){
            return status;
        }else{

            start.addObject("startBean",new CommentBean());
            return start;
        }

    }
}
