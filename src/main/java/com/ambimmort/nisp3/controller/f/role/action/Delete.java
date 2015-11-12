package com.ambimmort.nisp3.controller.f.role.action;

import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.RoleManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
/**
 * Created by hx on 2015/6/16.
 */
@Controller("/f/um/role/delete.action.do")
public class Delete {
    @Autowired
    RoleManagementServiceImpl roleManagementService;
    @Autowired
    UserManagementServiceImpl userManagementService;
    @Autowired
    LogServiceImpl logService;

    private ModelAndView success = new ModelAndView("/pub/success");
    private ModelAndView fail = new ModelAndView("/pub/error");
    private String redirectURL = "/f/um/role/list.view.do";
    @RequestMapping("/f/um/role/delete.action.do")
    public ModelAndView action(@RequestParam("id") int id,HttpServletRequest request)  {

        RoleBean roleBean=new RoleBean();


        try {
            roleBean = roleManagementService.getRole(id);
            //System.out.println("11111111111111111111111111111111111111111111111111111");
            if(roleManagementService.isTied(id)==1) {
                //System.out.println("2222222222222222222222222222222222222222222222");
                fail.addObject("message","已绑定，无法删除！");
                fail.addObject("redirectURL",redirectURL);
                return fail;
            }else {
                roleManagementService.deleteRole(id);
                //System.out.println("3333333333333333333333333333333333333");
                success.addObject("message","删除成功！");
                success.addObject("redirectURL",redirectURL);
            }

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("444444444444444444444444");
            fail.addObject("message",e.getMessage());
            fail.addObject("redirectURL",redirectURL);
            try {
                //添加失败日志
                logService.recordErrorLogs(Modular.UM,SubModular.UM_AREA,
                        Action.DELETE,Result.FALSE, roleBean.toString()
                        , e.getMessage(), roleBean,request);
            }catch (Exception e2){
                e2.printStackTrace();
            }

            return fail;
        }

       /* success.addObject("message", "删除角色成功");
        success.addObject("redirectURL", redirectURL);*/
        try {
            //添加成功日志
            logService.recordLogs(Modular.UM, SubModular.UM_AREA
                    , Action.DELETE, Result.TRUE
                    , roleBean, roleBean.toString(), request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }
}
