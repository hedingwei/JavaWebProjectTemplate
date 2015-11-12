package com.ambimmort.nisp3.controller.f.user.view;

import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/um/user/list.view.do")
public class List {
    @Autowired
    private UserManagementServiceImpl userService;

    @RequestMapping("/f/um/user/list.view.do")
    public ModelAndView action(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/f/um/user/list");
        java.util.List list = new ArrayList();
        try {
            String username = request.getUserPrincipal().getName();
            String areaid = userService.getUser(username).getAreaId();
            list = userService.listUsers(areaid);
            mv.addObject("list", list);
            return mv;
        } catch (Exception e) {
            ModelAndView error = new ModelAndView("/pub/error");
            e.printStackTrace();
            error.addObject("message", "未知错误");
            error.addObject("redirectURL", "/f/um/user/list.view.do");
            return error;
        }
    }
}

