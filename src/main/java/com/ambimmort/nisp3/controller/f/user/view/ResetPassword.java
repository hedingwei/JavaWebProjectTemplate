package com.ambimmort.nisp3.controller.f.user.view;

import com.ambimmort.nisp3.model.ui.f.user.EditUserBean;
import com.ambimmort.nisp3.model.ui.f.user.ResetPasswordBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller("/f/um/user/resetPasswoord.view.do")
public class ResetPassword {
    private Log logger = LogFactory.getLog(Edit.class);
    private ModelAndView view = new ModelAndView("/f/um/user/reset");
    @RequestMapping("/f/um/user/resetPassword.view.do")
    public ModelAndView action(HttpServletRequest request, @RequestParam("username") String username) {
        ModelAndView mv = new ModelAndView("f/um/user/reset");
        ResetPasswordBean resetPasswordBean=new ResetPasswordBean();
        resetPasswordBean.setUsername(username);
        mv.addObject("formBean", resetPasswordBean);
        return mv;
    }

}