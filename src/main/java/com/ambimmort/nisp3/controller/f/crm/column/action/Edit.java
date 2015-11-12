package com.ambimmort.nisp3.controller.f.crm.column.action;

import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.CRMMangementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.SystemUtilsServiceImpl;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ZZZZ on 2015/6/19.
 */
@Controller("/f/crm/column/edit.action.do")
public class Edit {
    @Autowired
    CRMMangementServiceImpl crmService;
    @Autowired
    private SystemUtilsServiceImpl systemUtilsService;
    @Autowired
    private LogServiceImpl logService;
    @RequestMapping("/f/crm/column/edit.action.do")
    public ModelAndView action(@Valid @ModelAttribute("model") CRMColumnBean bean,BindingResult result,HttpServletRequest request) throws Exception {
        ModelAndView success=new ModelAndView("/pub/success");
        ModelAndView fail=new ModelAndView("/f/crm/column/edit");
        CRMColumnBean oldDetail=crmService.getBeanById(bean.getId());

        try {
            if (result.hasErrors()) {
                fail.addObject("formBean", bean);
                return fail;
            }

            if (bean.getField().equals(crmService.field(bean.getId())) && bean.getName().equals(crmService.name(bean.getId()))) {
                crmService.updateCrmColumn(bean, crmService.field(bean.getId()));
                success.addObject("message", "编辑成功！");
                success.addObject("redirectURL", "/f/crm/column/list.view.do");
                logService.recordUpdatedLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                        , IModular.Action.UPDATE, IModular.Result.TRUE, bean.toString(), oldDetail.toString(),bean, request);
                return success;
            }
            if (bean.getField().equals(crmService.field(bean.getId())) && !bean.getName().equals(crmService.name(bean.getId()))) {
                if (systemUtilsService.isKeyWord(bean.getField())) {
                    fail.addObject("name", "不能使用数据库保留字作为字段名！");
                    fail.addObject("formBean", bean);
                    return fail;
                }
                if (crmService.existName(bean.getName())) {
                    fail.addObject("name", "该字段名已存在！");
                    fail.addObject("formBean", bean);
                    return fail;
                } else {
                    crmService.updateCrmColumn(bean, crmService.field(bean.getId()));
                    success.addObject("message", "编辑成功！");
                    success.addObject("redirectURL", "/f/crm/column/list.view.do");
                    logService.recordUpdatedLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                            , IModular.Action.UPDATE, IModular.Result.TRUE, bean.toString(), oldDetail.toString(), bean, request);
                    return success;
                }
            }
            if (!bean.getField().equals(crmService.field(bean.getId())) && bean.getName().equals(crmService.name(bean.getId()))) {
                if (crmService.existField(bean.getField())) {
                    fail.addObject("field", "该字段已存在！");
                    fail.addObject("formBean", bean);
                    return fail;
                } else {
                    crmService.updateCrmColumn(bean, crmService.field(bean.getId()));
                    success.addObject("message", "编辑成功！");
                    success.addObject("redirectURL", "/f/crm/column/list.view.do");
                    return success;
                }
            }

            if (crmService.existField(bean.getField())) {
                fail.addObject("field", "该字段已存在！");
                fail.addObject("formBean", bean);
                return fail;
            }

            if (crmService.existName(bean.getName())) {
                fail.addObject("name", "该字段名已存在！");
                fail.addObject("formBean", bean);
                logService.recordErrorLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                        , IModular.Action.UPDATE, IModular.Result.TRUE,oldDetail.toString(),"未知错误！",bean,request);
                return fail;
            }
            crmService.updateCrmColumn(bean, crmService.field(bean.getId()));
            success.addObject("message", "编辑成功！");
            success.addObject("redirectURL", "/f/crm/column/list.view.do");

        }catch (MySQLSyntaxErrorException e){
            fail.addObject("syntax",e.getMessage());
            fail.addObject("formBean", bean);
            logService.recordErrorLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                    , IModular.Action.UPDATE, IModular.Result.TRUE,oldDetail.toString(),"未知错误！",bean,request);
            return fail;
        }
        logService.recordUpdatedLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                , IModular.Action.UPDATE, IModular.Result.TRUE, bean.toString(), oldDetail.toString(), bean, request);
        return  success;
    }
}
