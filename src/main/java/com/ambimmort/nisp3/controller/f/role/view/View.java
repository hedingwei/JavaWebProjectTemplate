package com.ambimmort.nisp3.controller.f.role.view;

import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.service.impl.RoleManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZZZZ on 2015/6/16.
 */
@Controller("/f/um/role/view.view.do")
public class View {
    @Autowired
    RoleManagementServiceImpl roleManagementService;


    @RequestMapping("/f/um/role/view.view.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("id") int roleId) throws Exception {
        ModelAndView mv=new ModelAndView("/f/um/role/view");
        RoleBean bean= roleManagementService.getRole(roleId);
        mv.addObject("name",bean.getName());
        mv.addObject("description",bean.getDescription());
        mv.addObject("function",bean.getSelectedFunctions());
        mv.addObject("functions",roleManagementService.getAllFunctionsView(request,roleId));
        return  mv;

    }
}
