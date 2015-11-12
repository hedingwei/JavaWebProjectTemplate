package com.ambimmort.app.framework.controller.lttask.action;

import com.ambimmort.app.framework.lttask.AbstractTask;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/cancel.action.do")
public class Cancel {



    private ModelAndView failed = new ModelAndView("f/lttask/cancel");

    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/cancel.action.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("taskId")String taskId, @Valid @ModelAttribute("startBean") CommentBean commentBean,BindingResult result) throws UnsupportedEncodingException {

        ModelAndView init = new ModelAndView("redirect:init.view.do?taskId="+taskId);
        if(result.hasErrors()){
            failed.addObject("startBean", commentBean);
            return failed;
        }else{
            if(longTimeTaskRunner.getRunningTask(taskId)==null){
                return init;
            }else{
                AbstractTask task = longTimeTaskRunner.getRunningTask(taskId);
                task.setCancelled(
                        commentBean.getMessage(),
                        request.getUserPrincipal().getName()
                );
                task.getContext().put("cancelComment", commentBean.getMessage());
                return init;
            }

        }
    }
}
