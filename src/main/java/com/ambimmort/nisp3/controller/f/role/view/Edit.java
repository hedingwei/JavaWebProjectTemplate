package com.ambimmort.nisp3.controller.f.role.view;

import com.ambimmort.nisp3.model.ui.f.role.EditRoleBean;
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
@Controller("/f/um/role/edit.view.do")
public class Edit {

    @Autowired
    RoleManagementServiceImpl roleManagementService;
    @RequestMapping("/f/um/role/edit.view.do")
    public ModelAndView action(@RequestParam("id") int roleId,HttpServletRequest request) throws Exception {
        ModelAndView mv=new ModelAndView("/f/um/role/edit");
        EditRoleBean model=new EditRoleBean();
        RoleBean roleBean=new RoleBean();
        roleBean=roleManagementService.getRole(roleId);
        model.setName(roleBean.getName());
        model.setId(roleBean.getId());
        model.setDescription(roleBean.getDescription());
        mv.addObject("function", roleBean.getSelectedFunctions());
        mv.addObject("formBean",model);
        mv.addObject("functions",roleManagementService.getAllFunctions(request));
        return  mv;
    }
}
