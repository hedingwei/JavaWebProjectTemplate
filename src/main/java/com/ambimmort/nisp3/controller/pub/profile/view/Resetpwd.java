package com.ambimmort.nisp3.controller.pub.profile.view;

import com.ambimmort.nisp3.controller.f.user.action.ResetPassword;
import com.ambimmort.nisp3.model.ui.f.user.ResetPasswordBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.model.ui.f.user.profileBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;

import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2015/7/8.
 */
@Controller("/f/pub/profile/resetpwd.view.do")
public class Resetpwd {
    @Autowired
    UserManagementServiceImpl userManagementService;

    private ModelAndView mv=new ModelAndView("f/resetpwd");
    @RequestMapping("/f/pub/profile/resetpwd.view.do")
    public ModelAndView action(HttpServletRequest request){
        String username=request.getUserPrincipal().getName();
        ResetPasswordBean resetPasswordBean=new ResetPasswordBean();
        resetPasswordBean.setUsername(username);
        System.out.println(resetPasswordBean.getUsername()+"===============================");
        mv.addObject("formBean", resetPasswordBean);
        return mv;

    }

}
