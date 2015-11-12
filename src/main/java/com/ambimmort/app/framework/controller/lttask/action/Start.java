package com.ambimmort.app.framework.controller.lttask.action;

import com.ambimmort.app.framework.lttask.AbstractTask;
import com.ambimmort.app.framework.lttask.LTTaskBean;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/start.action.do")
public class Start {


    private ModelAndView failed = new ModelAndView("f/lttask/start");

    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/start.action.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("taskId")String taskId, @Valid @ModelAttribute("startBean") CommentBean commentBean,BindingResult result) throws IllegalAccessException, InstantiationException {
        ModelAndView init = new ModelAndView("redirect:init.view.do?taskId="+taskId);


        if(result.hasErrors()){
            failed.addObject("startBean", commentBean);
            return failed;
        }else{
            if(longTimeTaskRunner.getRunningTask(taskId)==null){

                if(longTimeTaskRunner.isRegisteredTask(taskId)){
                    LTTaskBean bean = longTimeTaskRunner.getRegisteredTask(taskId);
                    AbstractTask task = bean.getTaskClass().newInstance();
                    task.setId(bean.getTaskId());
                    task.getModel().setName(bean.getName());
                    task.getModel().setDescription(bean.getDescription());
                    task.setActivator(request.getUserPrincipal().getName());
                    longTimeTaskRunner.start(task,commentBean.getMessage());
                    init.addObject("isUnRegisteredTask",false);
                    return init;
                }else{

                    init.addObject("isUnRegisteredTask",true);
                    init.addObject("_taskId",taskId);
                    return init;
                }
            }
            return init;
        }

    }
}
