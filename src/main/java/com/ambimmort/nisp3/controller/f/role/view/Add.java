package com.ambimmort.nisp3.controller.f.role.view;

import com.ambimmort.nisp3.model.ui.f.role.AddRoleBean;
import com.ambimmort.nisp3.service.impl.RoleManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pc on 2015/6/10.
 */
@Controller("/f/um/role/add.view.do")
public class Add {
    @Autowired
    RoleManagementServiceImpl roleManagementService;

    @RequestMapping("/f/um/role/add.view.do")
    public ModelAndView action(HttpServletRequest request) throws Exception {
        ModelAndView mv=new ModelAndView("/f/um/role/add");
        mv.addObject("formBean",new AddRoleBean());
        mv.addObject("functions",roleManagementService.getAllFunctions(request));
        return mv;
    }
}
