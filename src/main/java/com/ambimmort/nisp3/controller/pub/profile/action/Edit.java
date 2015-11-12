package com.ambimmort.nisp3.controller.pub.profile.action;

/**
 * Created by qinxiaoyao on 2015/7/6.
 */

import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.model.ui.f.user.profileBean;

import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller("/f/profile/edit.action.do")
public class Edit {
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView failed = new ModelAndView("/f/profile/edit");
    private profileBean formBean;
    private String message = "修改成功";
    private String redirectURL = "/f/profile/edit.view.do";
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    private profileBean profileBean;

    @RequestMapping("/f/profile/edit.action.do")
    public ModelAndView action(HttpServletRequest request,
                               @Valid @ModelAttribute("formBean") profileBean formBean ,BindingResult result) {



        ModelAndView error=new ModelAndView("/pub/error");

        String uname = request.getUserPrincipal().getName();

        try {
            if (result.hasErrors()) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+result);
                this.profileBean=formBean;
                failed.addObject("formBean", formBean);
                return failed;
            }


            UserBean ub = userManagementService.getUser(uname);
            ub.setName(formBean.getUsername());
            ub.setEmail(formBean.getEmail());
            ub.setTelephone(formBean.getTelephone());
            ub.setDepartment(formBean.getDepartment());
            ub.setComment(formBean.getComment());
            userManagementService.updateInfo(ub);
            //添加成功日志
            logService.recordUpdatedLogs(IModular.Modular.SYSTEM, IModular.SubModular.SYSTEM_PERSONINFO
                    , IModular.Action.UPDATE, IModular.Result.TRUE,formBean.toString(),ub.toString(), formBean,request);
            success.addObject("message", message);
            success.addObject("redirectURL", redirectURL);
            return success;

        } catch (Exception e) {
            e.printStackTrace();
            error.addObject("message","出现未知错误!");
            error.addObject("redirectURL",redirectURL);

            //添加失败日志
            try {
                logService.recordErrorLogs(IModular.Modular.SYSTEM, IModular.SubModular.SYSTEM_PERSONINFO,
                        IModular.Action.UPDATE, IModular.Result.FALSE, formBean.toString(),e.getMessage(), formBean, request);
            } catch (Exception e1) {
                e1.printStackTrace();
                error.addObject("message","日志添加失败!");
                error.addObject("redirectURL",redirectURL);
                return error;
            }

            return error;
        }
    }
}


