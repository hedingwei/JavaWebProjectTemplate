package com.ambimmort.nisp3.controller.f.user.action;

import com.ambimmort.nisp3.model.ui.f.area.AreaBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.user.AddUserBean;

import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.SystemUtilsServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/user/add.action.do")
public class Add {
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView failed = new ModelAndView("f/um/user/add");
    private String redirectURL = "/f/um/user/list.view.do";
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;
    private java.util.List<RoleBean> roles ;
    private java.util.List<AreaBean> areas;



    @RequestMapping("/f/um/user/add.action.do")
    public ModelAndView action(HttpServletRequest request, @Valid @ModelAttribute("formBean") AddUserBean formBean, BindingResult result) {

        System.out.println(formBean.toString());
        try {
            System.out.println("---"+request.getSession().getAttribute("userBean"));
            UserBean userBean=(UserBean)request.getSession().getAttribute("userBean");
            String uname=userBean.getName();

            roles = userManagementService.listRoles();
            areas = userManagementService.listAreas(userBean.getAreaId());
            System.out.println(areas);
            System.out.println(roles);
            System.out.println("------dsfsdf");
            if (userManagementService.hasAuth(uname, formBean.getAreaId())) {
                if (result.hasErrors()) {
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    return failed;
                }
                if(systemUtilsService.isKeyWord(formBean.getUsername())){
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    result.rejectValue("username", null, "不能使用数据库保留字作为用户名");
                    return failed;
                }
                if (userManagementService.existUser(formBean.getUsername())) {
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    result.rejectValue("username", null, "用户名已存在");
                    return failed;
                }
                if (!formBean.getPassword().equals(formBean.getPassword_validate())) {
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    result.rejectValue("password", null, "两次密码不一致");
                    return failed;
                }
                formBean.setPassword(systemUtilsService.encrypt(formBean.getPassword()));
                userManagementService.addUser(formBean);
                success.addObject("message", "添加用户成功");

                //添加成功日志
                logService.recordLogs(IModular.Modular.UM, IModular.SubModular.UM_USER
                        , IModular.Action.ADD, IModular.Result.TRUE,formBean,formBean.toString(),request);

                success.addObject("redirectURL", redirectURL);
                return success;
            } else {
                failed.addObject("formBean", formBean);
                failed.addObject("roles", roles);
                failed.addObject("areas", areas);
                return failed;
            }
        }catch(Exception e){
            failed.addObject("formBean", formBean);
            failed.addObject("roles", roles);
            failed.addObject("areas", areas);
            try {
                //添加失败日志
                logService.recordErrorLogs(IModular.Modular.UM, IModular.SubModular.UM_USER,
                        IModular.Action.ADD, IModular.Result.FALSE, formBean.toString(),e.getMessage(), formBean, request);
            }catch(Exception e2){
                e2.printStackTrace();
            }

            return failed;
        }
    }
}
