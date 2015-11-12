package com.ambimmort.nisp3.controller.f.domain.action;

import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.area.EditDomainBean;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by qinxiaoyao on 2015/6/16.
 */
@Controller("/f/um/domain/edit.action.do")
public class Edit {
    private Log logger = LogFactory.getLog(Add.class);
    private ModelAndView success = new ModelAndView("pub/success");
    private ModelAndView failed = new ModelAndView("f/um/domain/edit");
    private ModelAndView error = new ModelAndView("pub/error");
    private String message;
    private String redirectURL = "/f/um/domain/list.view.do";
    @Autowired
    private IAreaManagementService areaMangementService;
    @Autowired
    private IUserManagementService userManagementService;
    @Autowired
    private ILogService logService;
    @Autowired
    private ISystemUtilsService systemUtilsService;
    @Autowired
    private ICRMMangementService crmManagementService;


    @RequestMapping("/f/um/domain/edit.action.do")
    public ModelAndView action(HttpServletRequest request,@RequestParam("pname") String name, @Valid @ModelAttribute("model") EditDomainBean formBean, BindingResult result) {
        DomainBean db = new DomainBean();
        try {
            String edname=formBean.getName();
            List<String> dbpname=areaMangementService.getPname(formBean.getPid());
            if(dbpname.contains(edname)){
                error.addObject("message", "不能与父区域同名！");
                error.addObject("redirectURL", redirectURL);
                return  error;
            }


            db = areaMangementService.getAreaByname(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.hasErrors()) {
            failed.addObject("pname", db.getPname());
            failed.addObject("formBean", formBean);
            failed.addObject("fields",crmManagementService.getFields());
            return failed;
        }

        if(systemUtilsService.isKeyWord(formBean.getName())){
            failed.addObject("pname",db.getPname());
            failed.addObject("model", formBean);
            result.rejectValue("name", null, "不能使用数据库保留字作为区域名");
            return failed;
        } else {
            String oldDetail=null;//旧值

            try {
                oldDetail=areaMangementService.getAreaByname(name).toString();
                areaMangementService.editArea(formBean, name);

            } catch (Exception e) {
                e.printStackTrace();
                error.addObject("message", e.getMessage());
                error.addObject("redirectURL", redirectURL);
                try {
                    //添加失败日志
                    logService.recordErrorLogs( Modular.UM, SubModular.UM_AREA,
                            Action.UPDATE, Result.FALSE, formBean.toString(), e.getMessage(), formBean,request);
                }catch (Exception e2){
                    e2.printStackTrace();
                }
                return error;
            }
            success.addObject("message", "修改区域成功");
            success.addObject("redirectURL", redirectURL);
            try {
                //添加成功日志
                logService.recordUpdatedLogs( Modular.UM, SubModular.UM_AREA
                        , Action.UPDATE, Result.TRUE, formBean.toString(),oldDetail
                        , formBean,request);
            }catch (Exception e){
                e.printStackTrace();
            }
            return success;
        }
    }
}
