package com.ambimmort.nisp3.controller.f.user.action;

import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.def.IModular.Result;
/**
 * Created by liu on 2015/6/17.
 */
@Controller("/f/user/setstatus.action.do")
public class SetStatus {
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;
    private ModelAndView error = new ModelAndView("/pub/error");
    private ModelAndView success=new ModelAndView("/pub/success");
    private String redirectURL = "/f/um/user/list.view.do";

    @RequestMapping("/f/user/setStatus.action.do")
    public ModelAndView action(HttpServletRequest request,@ModelAttribute("username") String username,@ModelAttribute("areaId") String areaId,@ModelAttribute("flag") Boolean flag){
        String uname = request.getUserPrincipal().getName();

        boolean oldStatus=false;


        UserBean userBean2=null;

        try {
            UserBean userBean=userManagementService.getUser(uname);


            //旧值
            userBean2=userManagementService.getUser(username);
            oldStatus=userBean2.getStatus();
            userBean2.setStatus(oldStatus);

            if(!uname.equals(username)){
                if(userManagementService.hasAuth(uname,areaId)){
                    userManagementService.setStatus(username,flag);
                    success.addObject("message","修改状态成功");
                    success.addObject("redirectURL",redirectURL);
                    //添加成功日志
                    logService.recordUpdatedLogs(
                            Modular.UM,
                           SubModular.UM_USER,
                           Action.UPDATE,
                            Result.TRUE,
                            userManagementService.getUser(username).toString(),
                            userBean2.toString() ,
                            flag,request);
                    return success;
                }else{
                    error.addObject("message","修改状态失败");
                    error.addObject("redirectURL",redirectURL);
                    return error;
                }
            }else{
                error.addObject("message","不能停用当前登录用户");
                error.addObject("redirectURL",redirectURL);
                return error;
            }

        } catch (Exception e) {
            e.printStackTrace();
            error.addObject("message","修改状态失败");
            error.addObject("redirectURL",redirectURL);
            try {
                //添加失败日志
                logService.recordErrorLogs(Modular.UM,SubModular.UM_USER,
                       Action.ADD, Result.FALSE, userBean2.toString()
                        ,e.getMessage(), userBean2, request);
            }catch(Exception e2){
                e2.printStackTrace();
            }
            return error;
        }

    }
}
