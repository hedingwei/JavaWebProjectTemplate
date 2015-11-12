package com.ambimmort.nisp3.controller.f.user.view;

import com.ambimmort.nisp3.model.ui.f.area.AreaBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.user.AddUserBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller("/f/um/user/add.view.do")
public class Add {
    private ModelAndView view = new ModelAndView("/f/um/user/add");
    private AddUserBean formBean;
    private java.util.List<RoleBean> roles;
    private java.util.List<AreaBean> areas;
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @RequestMapping("/f/um/user/add.view.do")
    public ModelAndView action(HttpServletRequest request) {
        UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
        String uname = request.getUserPrincipal().getName();
        try {
            roles = userManagementService.listRoles();
            areas = userManagementService.listAreas(userManagementService.getUser(uname).getAreaId());
            formBean=new AddUserBean();
            formBean.setEnabled(true);
            view.addObject("formBean", formBean);
            view.addObject("roles", roles);
            view.addObject("areas", areas);
            return view;
        } catch (Exception e) {
            ModelAndView error = new ModelAndView("/pub/error");
            error.addObject("message", "未知错误");
            error.addObject("redirectURL", "/f/um/user/list.view.do");
            return error;
        }

    }
}
