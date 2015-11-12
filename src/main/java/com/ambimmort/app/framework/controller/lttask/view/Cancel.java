package com.ambimmort.app.framework.controller.lttask.view;

import com.ambimmort.app.framework.lttask.LTTaskManager;
import com.ambimmort.app.framework.controller.lttask.action.CommentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/cancel.view.do")
public class Cancel {



    private ModelAndView cancel = new ModelAndView("f/lttask/cancel");



    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/cancel.view.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("taskId")String taskId) {


        ModelAndView init = new ModelAndView("redirect:init.view.do?taskId="+taskId);

        if(longTimeTaskRunner.getRunningTask(taskId)!=null){
            cancel.addObject("startBean", new CommentBean());

            return cancel;

        }else{

            return init;
        }

    }
}
