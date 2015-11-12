package com.ambimmort.nisp3.controller.f.user.view;

import com.ambimmort.nisp3.model.ui.f.user.EditUserBean;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller("/f/um/user/edit.view.do")
public class Edit {
    private ModelAndView view = new ModelAndView("/f/um/user/edit");
    private EditUserBean formBean = new EditUserBean();
    @Autowired
    private UserManagementServiceImpl userManagementService;

   
    @RequestMapping("/f/um/user/edit.view.do")
    public ModelAndView action(HttpServletRequest request, @RequestParam("username") String username) {
        ModelAndView mv = new ModelAndView("f/um/user/edit");
        String uname = request.getUserPrincipal().getName();
        try {
            if (userManagementService.hasAuth(uname,userManagementService.getUser(username).getAreaId())){
                EditUserBean editUserBean=userManagementService.getEditUser(username);
                System.out.println(editUserBean.getName()+editUserBean.getName());

                mv.addObject("formBean",editUserBean);

                mv.addObject("oldDetail",editUserBean.toString());
                mv.addObject("roles",userManagementService.listRoles());
                mv.addObject("areas",userManagementService.listAreas(userManagementService.getUser(uname).getAreaId()));
                return mv;
            }else{
                ModelAndView modelAndView=new ModelAndView("/pub/error");
                modelAndView.addObject("message","你没有操作权限");
                modelAndView.addObject("redirectURL","/f/um/user/list.view.do");
                return modelAndView;
            }

        } catch (Exception e) {
            ModelAndView error = new ModelAndView("/pub/error");
            error.addObject("message", "未知错误");
            error.addObject("redirectURL", "/f/um/user/list.view.do");
            return error;
        }
    }
}