package com.ambimmort.nisp3.controller.f.role.view;

import com.ambimmort.nisp3.service.impl.RoleManagementServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by pc on 2015/6/10.
 */
@Controller("/f/role/list.view")
public class List {
    private Log logger = LogFactory.getLog(List.class);

    @Autowired
    private RoleManagementServiceImpl userService;


    @RequestMapping("/f/um/role/list.view.do")
    public ModelAndView action(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/f/um/role/list");
        String uname=request.getUserPrincipal().getName();
        mv.addObject("list",userService.listRoles(uname));


        return mv;
    }

}
