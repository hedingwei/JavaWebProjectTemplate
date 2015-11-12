package com.ambimmort.nisp3.controller.f.user.action;

import com.ambimmort.nisp3.model.ui.f.area.AreaBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.user.EditUserBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.SystemUtilsServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by pc on 2015/6/10.
 */
@Controller("/f/user/edit.action.do")
public class Edit {
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView failed = new ModelAndView("f/um/user/edit");
    private EditUserBean formBean;
    private String message = "修改成功";
    private String redirectURL = "/f/um/user/list.view.do";
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;
    private java.util.List<RoleBean> roles = null;
    private java.util.List<AreaBean> areas = null;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;


    @RequestMapping("/f/user/edit.action.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("username")String username,
                               @Valid @ModelAttribute("formBean") EditUserBean formBean ,BindingResult result) {
        ModelAndView error=new ModelAndView("/pub/error");

        String uname = request.getUserPrincipal().getName();

        String oldDetail=null;
        try {
            UserBean userBean=userManagementService.getUser(username);
            oldDetail=userBean.toString();
            UserBean userBean1=userManagementService.getUser(uname);



            roles = userManagementService.listRoles();
            areas = userManagementService.listAreas(userBean1.getAreaId());
            if(userManagementService.hasAuth(uname,formBean.getAreaId())){
                if("admin".equals(formBean.getUsername())){
                    error.addObject("message","不能编辑该用户");
                    error.addObject("redirectURL","/f/um/user/list.view.do");
                    return error;
                }
                if (result.hasErrors()) {
                    this.formBean = formBean;
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    return failed;
                }
                if (userManagementService.existUser(formBean.getUsername())&&!username.equals(formBean.getUsername())) {
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    result.rejectValue("username", null, "该用户名已存在");
                    return failed;
                }
                if(systemUtilsService.isKeyWord(formBean.getUsername())){
                    failed.addObject("formBean", formBean);
                    failed.addObject("roles", roles);
                    failed.addObject("areas", areas);
                    result.rejectValue("username", null, "不能使用数据库保留字作为用户名");
                    return failed;
                }
                userManagementService.updateUser(formBean);
                success.addObject("message", message);
                success.addObject("redirectURL", redirectURL);

                //添加成功日志
                logService.recordUpdatedLogs(Modular.UM, SubModular.UM_USER
                        , Action.UPDATE, Result.TRUE,formBean.toString(),oldDetail, formBean,request);
                return success;
            }else {
                failed.addObject("formBean", formBean);
                failed.addObject("roles", roles);
                failed.addObject("areas", areas);
                return failed;
            }

        } catch (Exception e) {
            error.addObject("message","出现未知错误");
            error.addObject("redirectURL","/f/um/user/list.view.do");
            try {
                //添加失败日志
                logService.recordErrorLogs(Modular.UM,SubModular.UM_USER,
                        Action.UPDATE,Result.FALSE, formBean.toString(),e.getMessage(), formBean, request);
            }catch(Exception e2){
                e2.printStackTrace();
            }

            return error;
        }
    }
}

