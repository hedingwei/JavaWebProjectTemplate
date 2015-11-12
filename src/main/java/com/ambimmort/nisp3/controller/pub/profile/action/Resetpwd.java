package com.ambimmort.nisp3.controller.pub.profile.action;

import com.ambimmort.nisp3.model.ui.f.user.ResetPasswordBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.SystemUtilsServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Administrator on 2015/7/10.
 */
@Controller("/f/pub/profile/resetpwd.action.do")
public class Resetpwd {
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView error=new ModelAndView("pub/error");
    private ModelAndView failed = new ModelAndView("/f/resetpwd");
    private String redirectURL = "/f/pub/profile/resetpwd.view.do";
    private ResetPasswordBean resetPasswordBean;
    @Autowired
    UserManagementServiceImpl userManagementService;
    @Autowired
    LogServiceImpl logService;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/f/pub/profile/resetpwd.action.do")
    public ModelAndView action(HttpServletRequest request,@Valid @ModelAttribute("formBean") ResetPasswordBean formBean,BindingResult result){
        try {
            if (result.hasErrors()) {
                return failed;}
            String uname = request.getUserPrincipal().getName();
            UserBean userBean=userManagementService.getUser(uname);
            System.out.println(">>>>>>>>>>>>>>>>>"+uname);
            String pwd=userManagementService.getPwd(uname);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+pwd+";oldpwd:"+formBean.getOldpassword());

            if (!formBean.getPassword().equals(formBean.getPassword_validate())) {
                result.rejectValue("password", null, "两次密码不相同");
                return failed;
            }
            if( bCryptPasswordEncoder.matches(formBean.getOldpassword(),pwd)){
                userManagementService.updatePassword(formBean.getUsername(), systemUtilsService.encrypt(formBean.getPassword()));
                success.addObject("message", "修改密码成功");
                success.addObject("redirectURL", redirectURL);
                //添加成功日志
                logService.recordLogs(IModular.Modular.SYSTEM, IModular.SubModular.SYSTEM_PERSONINFO
                        , IModular.Action.UPDATE, IModular.Result.TRUE, formBean, userBean.getUsername().toString(), request);
                return success;

            }
            else  {
                result.rejectValue("oldpassword",null,"原始密码错误！");
                return failed;

            }
        } catch (Exception e) {
            //添加失败日志
            try {
                logService.recordErrorLogs(IModular.Modular.SYSTEM, IModular.SubModular.SYSTEM_PERSONINFO,
                        IModular.Action.UPDATE, IModular.Result.FALSE, formBean.toString(),e.getMessage(), formBean, request);
            } catch (Exception e1) {
                e1.printStackTrace();
                error.addObject("message","出现未知错误!");
                error.addObject("redirectURL",redirectURL);
                return error;
            }
            e.printStackTrace();
            return failed;
        }


    }
}
