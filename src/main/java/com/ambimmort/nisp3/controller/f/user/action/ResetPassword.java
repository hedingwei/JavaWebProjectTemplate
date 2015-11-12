package com.ambimmort.nisp3.controller.f.user.action;

import com.ambimmort.nisp3.model.ui.f.user.ResetPasswordBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.SystemUtilsServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.def.IModular.Result;

/**
 * Created by liu on 2015/6/12.
 */
@Controller("/f/user/resetPassword.action.do")
public class ResetPassword {
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;
    private String redirectURL = "/f/um/user/list.view.do";

    @RequestMapping("/f/user/resetPassword.action.do")
    public ModelAndView _reset(HttpServletRequest request,@Valid @ModelAttribute("formBean") ResetPasswordBean model, BindingResult result) {
        ModelAndView failed = new ModelAndView("f/um/user/reset");
        ModelAndView success = new ModelAndView("/pub/success");
        ModelAndView error=new ModelAndView("/pub/error");

        String uname = request.getUserPrincipal().getName();

       UserBean oldUserBean=null;

        try {
            UserBean userBean=userManagementService.getUser(uname);

            if (userManagementService.hasAuth(uname, userBean.getAreaId())) {
                if("admin".equals(model.getUsername())){
                    error.addObject("message","不能编辑该用户");
                    error.addObject("redirectURL","/f/um/user/list.view.do");
                    return error;
                }
                if (result.hasErrors()) {
                    failed.addObject("fromBean", model);
                    error.addObject("redirectURL",redirectURL);
                    return failed;
                }
                if (!model.getPassword().equals(model.getPassword_validate())) {
                    result.rejectValue("password", null, "两次密码不相同");
                    error.addObject("redirectURL",redirectURL);
                    return failed;
                } else {
                    userManagementService.updatePassword(model.getUsername(), systemUtilsService.encrypt(model.getPassword()));
                    success.addObject("message", "修改密码成功");
                    success.addObject("redirectURL", redirectURL);
                    //添加成功日志
                    logService.recordUpdatedLogs(Modular.UM,SubModular.UM_USER
                            ,Action.UPDATE,Result.TRUE, model.toString(),userBean.toString(),model, request);
                    return success;
                }
            }else {
                error.addObject("message","你没有操作权限");
                error.addObject("redirectURL",redirectURL);
                error.addObject("redirectURL",redirectURL);
                return  error;
            }

        } catch (Exception e) {
            error.addObject("message","操作失败");
            error.addObject("redirectURL",redirectURL);
            try {
                //添加失败日志
                logService.recordErrorLogs( Modular.UM, SubModular.UM_USER,
                       Action.ADD, Result.FALSE,model.toString()
                        ,e.getMessage(),model,request);
            }catch(Exception e2){
                e2.printStackTrace();
            }
            return  error;
        }
    }
}
