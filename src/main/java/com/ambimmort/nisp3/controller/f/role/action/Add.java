package com.ambimmort.nisp3.controller.f.role.action;

import com.ambimmort.app.framework.springsecurity.SecurityMetadataSourceHelper;
import com.ambimmort.nisp3.model.ui.f.role.AddRoleBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.def.IModular.Result;
/**
 * Created by pc on 2015/6/10.
 */
@Controller("/f/um/role/add.action.do")
public class Add {
    @Autowired
    RoleManagementServiceImpl roleManagementService;
    @Autowired
    UserManagementServiceImpl userManagementService;
    @Autowired
    AreaManagementServiceImpl areaManagementService;
    @Autowired
    LogServiceImpl logService;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;


    @RequestMapping("/f/um/role/add.action.do")
    public ModelAndView action(@Valid @ModelAttribute("formBean") AddRoleBean bean,BindingResult result,HttpServletRequest request) throws Exception {
        ModelAndView success = new ModelAndView("/pub/success");
        ModelAndView fail = new ModelAndView("/f/um/role/add");
        String uname = request.getUserPrincipal().getName();
        int  roleid=roleManagementService.getRoleId(bean.getRolename());

        try {
            bean.setSelectedFunctionNames(roleManagementService.getRole(roleid).getSelectedFunctionNames());
        } catch (Exception e) {
            e.printStackTrace();
        }


        UserBean userBean=null;
        try {
            userBean=userManagementService.getUser(uname);

            if (result.hasErrors()) {
                fail.addObject("formBean", bean);
                fail.addObject("functions", roleManagementService.getAllFunctions(request));
                fail.addObject("function", bean.getSelectedFunctions());
                return fail;
            }
            if (roleManagementService.getRoleName(bean.getName())) {
                fail.addObject("msg", "该角色已存在！");
                fail.addObject("formBean", bean);
                fail.addObject("functions", roleManagementService.getAllFunctions(request));
                fail.addObject("function", bean.getSelectedFunctions());
                return fail;
            }
            if(systemUtilsService.isKeyWord(bean.getName())){
                fail.addObject("msg", "不能使用数据库保留字作为用户名");
                fail.addObject("formBean", bean);
                fail.addObject("functions", roleManagementService.getAllFunctions(request));
                fail.addObject("function", bean.getSelectedFunctions());
                return fail;
            }



        } catch (Exception e) {
            try {
                //添加失败日志
                logService.recordErrorLogs(Modular.UM, SubModular.UM_ROLE,
                        Action.ADD,Result.FALSE, bean.toString(), e.getMessage(), bean, request);
                e.printStackTrace();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        try {
            roleManagementService.addRole(bean,request);
        } catch (Exception e) {
            fail.addObject("msg", e.getMessage());
            fail.addObject("formBean", bean);
            fail.addObject("functions", roleManagementService.getAllFunctions(request));
            fail.addObject("function", bean.getSelectedFunctions());
            return fail;
        }
        success.addObject("message", "创建成功！");
        success.addObject("redirectURL", "/f/um/role/list.view.do");

        // 修改了Role的权限，需要重新加载
        SecurityMetadataSourceHelper.reloadMetadataSource(request.getServletContext());

        //添加成功日志
        try {
            logService.recordLogs(Modular.UM, SubModular.UM_ROLE
                    ,Action.ADD, Result.TRUE,bean, bean.toString(), request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }


}
