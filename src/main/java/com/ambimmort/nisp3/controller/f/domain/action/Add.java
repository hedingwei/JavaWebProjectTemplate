package com.ambimmort.nisp3.controller.f.domain.action;

import com.ambimmort.nisp3.model.ui.f.area.AddDomainBean;
import com.ambimmort.nisp3.service.def.*;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by qinxiaoyao on 2015/6/16.
 */

@Controller("/f/um/domain/add.action.do")
public class Add {

    private Log logger = LogFactory.getLog(Add.class);
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView failed = new ModelAndView("pub/error");
    private String message;
    private String redirectURL = "/f/um/domain/list.view.do";

    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private ILogService logService;
    @Autowired
    private IAreaManagementService areaManagementService;
    @Autowired
    private ISystemUtilsService systemUtilsService;
    @Autowired
    private ICRMMangementService crmManagementService;

    @RequestMapping("/f/um/domain/add.action.do")
    public ModelAndView action(HttpServletRequest request, @Valid @ModelAttribute("formBean") AddDomainBean formBean, BindingResult result) {

        if (result.hasErrors()) {
            failed.addObject("pname", formBean.getPname());
            failed.addObject("model", formBean);
            failed.addObject("fields", crmManagementService.getFields());
            failed.addObject("message", "Error");
            failed.addObject("redirectURL", redirectURL);

            return failed;
        } else {
            try {
                if (systemUtilsService.isKeyWord(formBean.getName())) {
                    failed.addObject("pname", formBean.getPname());
                    failed.addObject("model", formBean);
                    result.rejectValue("name", null, "不能使用数据库保留字作为区域名");
                    failed.addObject("redirectURL", redirectURL);
                    return failed;
                } else if (areaManagementService.getPname(formBean.getPid()).contains(formBean.getName())) {
                    failed.addObject("pname", formBean.getPname());
                    failed.addObject("model", formBean);
                    //result.rejectValue("name", null, "不能与父区域同名！");
                    failed.addObject("message", "不能与父区域同名！");
                    failed.addObject("fields", crmManagementService.getFields());
                    failed.addObject("redirectURL", redirectURL);
                    return failed;
                } else {
                    areaManagementService.addArea(formBean);
                }
            } catch (Exception e) {
                failed.addObject("message", e.getMessage());
                failed.addObject("redirectURL", redirectURL);

                try {
                    logService.recordErrorLogs(

                            Modular.UM,
                            SubModular.UM_AREA,
                            Action.ADD,
                            Result.FALSE,
                            formBean.toString(),
                            e.getMessage(),
                            formBean,
                            request
                    );
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                return failed;

            }

            success.addObject("message", "添加区域成功");
            success.addObject("redirectURL", redirectURL);

            try {
                try {
                    logService.recordLogs(

                            Modular.UM,
                            SubModular.UM_AREA,
                            Action.ADD,
                            Result.TRUE,
                            formBean,
                            formBean.toString(),
                            request
                    );
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return success;
        }
    }
}
