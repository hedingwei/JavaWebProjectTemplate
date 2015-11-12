package com.ambimmort.nisp3.controller.f.role.action;

import com.ambimmort.app.framework.springsecurity.SecurityMetadataSourceHelper;
import com.ambimmort.nisp3.model.ui.f.role.EditRoleBean;
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
@Controller("/f/um/role/edit.action.do")
public class Edit {
    @Autowired
    RoleManagementServiceImpl roleManagementService;
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;
    @RequestMapping("/f/um/role/edit.action.do")
    public ModelAndView action(@Valid @ModelAttribute("formBean") EditRoleBean bean,BindingResult result,HttpServletRequest request) throws Exception {
        ModelAndView success=new ModelAndView("/pub/success");
        ModelAndView fail=new ModelAndView("/f/um/role/edit");

        String uname = request.getUserPrincipal().getName();

               String rolename= bean.getName();
        int  roleid=roleManagementService.getRoleId(rolename);

        try {
            bean.setSelectedFunctionNames(roleManagementService.getRole(roleid).getSelectedFunctionNames());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String oldDetail=null;

        //获取USER的cookie值

        try {

            oldDetail=roleManagementService.getRole(bean.getId()).toString();


            if(result.hasErrors()){

                fail.addObject("formBean",bean);
                fail.addObject("function",bean.getSelectedFunctions());
                fail.addObject("functions", roleManagementService.getAllFunctions(request));
                return fail;
            }
            if (roleManagementService.getRoleName(bean.getId()).equals(bean.getName())) {
                roleManagementService.updateRole(bean);

                success.addObject("message", "编辑成功！");
                success.addObject("redirectURL", "/f/um/role/list.view.do");

                // 修改了Role的权限，需要重新加载
                SecurityMetadataSourceHelper.reloadMetadataSource(request.getServletContext());

                //添加成功日志
                logService.recordUpdatedLogs(Modular.UM,SubModular.UM_ROLE
                        ,Action.UPDATE,Result.TRUE,bean.toString(),oldDetail,bean,request);
                return success;
            }
            if(systemUtilsService.isKeyWord(bean.getName())){
                fail.addObject("msg", "不能使用数据库保留字作为用户名");
                fail.addObject("formBean", bean);
                fail.addObject("functions", roleManagementService.getAllFunctions(request));
                fail.addObject("function", bean.getSelectedFunctions());
                return fail;
            }
            if (roleManagementService.existRole(bean.getName())) {
                roleManagementService.updateRole(bean);
                success.addObject("message", "编辑成功！");
                success.addObject("redirectURL", "/f/um/role/list.view.do");

                // 修改了Role的权限，需要重新加载
                SecurityMetadataSourceHelper.reloadMetadataSource(request.getServletContext());

                //添加成功日志
                logService.recordUpdatedLogs(Modular.UM,SubModular.UM_ROLE
                        , Action.UPDATE,Result.TRUE,bean.toString(),oldDetail,bean,request);
                return success;
            } else {
                fail.addObject("formBean", bean);
                fail.addObject("functions", roleManagementService.getAllFunctions(request));
                fail.addObject("function", bean.getSelectedFunctions());
                fail.addObject("msg", "角色名已存在！");
                return fail;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            roleManagementService.updateRole(bean);
            success.addObject("message", "编辑成功!");
            success.addObject("redirectURL","/f/um/role/edit.view.do");

            // 修改了Role的权限，需要重新加载
            SecurityMetadataSourceHelper.reloadMetadataSource(request.getServletContext());

        } catch (Exception e) {
            fail.addObject("msg", e.getMessage());
            fail.addObject("formBean", bean);
            fail.addObject("functions", roleManagementService.getAllFunctions(request));
            fail.addObject("function", bean.getSelectedFunctions());
            return fail;
        }

        try {
            //添加成功日志
            logService.recordUpdatedLogs( Modular.UM, SubModular.UM_ROLE
                    ,Action.UPDATE, Result.TRUE, bean.toString(),oldDetail, bean,request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }
}
