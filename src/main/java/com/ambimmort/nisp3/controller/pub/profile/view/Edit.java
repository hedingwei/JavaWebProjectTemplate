package com.ambimmort.nisp3.controller.pub.profile.view;

import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.model.ui.f.user.profileBean;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qinxiaoyao on 2015/7/6.
 */
@Controller("/f/profile/edit.view.do")
public class Edit {

    @Autowired
    private UserManagementServiceImpl userManagementService;
    private ModelAndView error = new ModelAndView("pub/error");
    private String redirectURL = "/f/profile/edit";

    @RequestMapping("/f/profile/edit.view.do")
    public ModelAndView _profileSetting(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/f/profile/edit");
        //取得当前登录用户username
        String uname = request.getUserPrincipal().getName();
        System.out.println("uname>>>>>>>>>>>>>>>>>>>>>>>>>"+uname);

        try {
            UserBean ub = userManagementService.getUser(uname);
            profileBean formBean=new profileBean();
            formBean.setDepartment(ub.getDepartment());
            formBean.setEmail(ub.getEmail());
            formBean.setName(ub.getName());
            formBean.setTelephone(ub.getTelephone());
            formBean.setUsername(ub.getUsername());
            formBean.setComment(ub.getComment());

            System.out.println("view"+ub.toString());


            mv.addObject("formBean", formBean);
            return mv;
        } catch (Exception e) {
            error.addObject("message",e.getMessage());
            error.addObject("redirectURL",redirectURL);
            e.printStackTrace();
            return error;
        }
    }
}
