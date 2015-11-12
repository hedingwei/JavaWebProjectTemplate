package com.ambimmort.nisp3.controller.f.user.action;

import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanglijuan on 2015/6/10.
 */
@Controller("/f/user/delete.action.do")
public class Delete {
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    private ModelAndView error = new ModelAndView("/pub/error");
    private ModelAndView success = new ModelAndView("/pub/success");
    private String message;
    private String redirectURL = "/f/um/user/list.view.do";

    @RequestMapping("/f/user/delete.action.do")
    public ModelAndView _delete(@RequestParam("username") String username, HttpServletRequest request) {
        String uname = request.getUserPrincipal().getName();

        try {
            //旧值
            UserBean userBean2=userManagementService.getUser(username);
            UserBean userBean1=userManagementService.getUser(uname);

            error.addObject("redirectURL", redirectURL);
            if (userManagementService.hasAuth(uname, userBean2.getAreaId())) {
                if ("admin".equals(username)){
                    error.addObject("message", "不能删除admin");
                    //添加失败日志
                    logService.recordErrorLogs( Modular.UM, SubModular.UM_USER,
                           Action.DELETE,Result.FALSE, userBean1.toString()
                            ,"不能删除admin",userBean2,request);
                    return error;
                }
                if (!uname.equals(username)) {
                    userManagementService.deleteUser(username);
                    success.addObject("message", "删除成功");
                    success.addObject("redirectURL", redirectURL);
                    //添加成功日志
                    logService.recordLogs(Modular.UM,SubModular.UM_USER
                            ,Action.DELETE,Result.TRUE
                            ,userBean2,userBean2.toString(), request);
                    return success;
                } else {
                    error.addObject("message", "不能删除当前登陆用户");
                    //添加失败日志
                    logService.recordErrorLogs(Modular.UM,SubModular.UM_USER,
                            Action.DELETE,Result.FALSE, userManagementService.getUser(uname).toString()
                            ,"不能删除当前登录用户", userManagementService.getUser(username), request);
                    return error;
                }

            } else {
                error.addObject("message", "你没有操作权限");
                //添加失败日志
                logService.recordErrorLogs(Modular.UM,SubModular.UM_USER,
                       Action.DELETE, Result.FALSE, userManagementService.getUser(uname).toString()
                        ,"无操作权限",userBean2,request);
                return error;
            }
        } catch (Exception e) {
            error.addObject("message", "发生未知错误");
            error.addObject("redirectURL", redirectURL);
            try {
                //添加失败日志
                logService.recordErrorLogs(Modular.UM, SubModular.UM_USER,
                        Action.DELETE,Result.FALSE, userManagementService.getUser(uname).toString()
                        ,e.getMessage(), userManagementService.getUser(username),request);
            }catch(Exception e2){
                e2.printStackTrace();
            }

            return error;
        }
    }
}
