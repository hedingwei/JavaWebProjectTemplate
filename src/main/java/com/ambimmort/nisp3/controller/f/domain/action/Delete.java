package com.ambimmort.nisp3.controller.f.domain.action;

import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.IAreaManagementService;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import com.ambimmort.nisp3.service.def.IUserManagementService;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qinxiaoyao on 2015/6/16.
 */
@Controller("/f/um/domain/delete.action.do")
public class Delete {
    private Log logger = LogFactory.getLog(Delete.class);
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView error = new ModelAndView("pub/error");
    private String redirectURL = "/f/um/domain/list.view.do";
    @Autowired
    private IAreaManagementService areaMangementService;
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private ILogService logService;


    @RequestMapping("/f/um/domain/delete.action.do")
    public ModelAndView action(HttpServletRequest request, @RequestParam("id") String id) {
        String uname = request.getUserPrincipal().getName();
        DomainBean domainBean=new DomainBean();
        //获取USER的cookie值

        UserBean userBean=null;
        try {
            domainBean=areaMangementService.getArea(id);

            areaMangementService.deleteArea(id);
            System.out.println("DELETE ----------------------------------------------");
            System.out.println(id);

        } catch (Exception e) {
            e.printStackTrace();
            error.addObject("message",e.getMessage());
            error.addObject("redirectURL",redirectURL);
            try {
                //添加失败日志
                logService.recordErrorLogs(Modular.UM, SubModular.UM_AREA,
                        Action.DELETE, Result.FALSE, domainBean.toString()
                        , e.getMessage(), domainBean,request);
            }catch (Exception e2){
                e2.printStackTrace();
            }

            return error;
        }

        success.addObject("message", "删除区域成功");
        success.addObject("redirectURL", redirectURL);
        try {
            //添加成功日志
            logService.recordLogs( Modular.UM, SubModular.UM_AREA
                    , Action.DELETE, Result.TRUE
                    , domainBean, domainBean.toString(),request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }
}
