package com.ambimmort.nisp3.controller.f.crm.column.action;

import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import com.ambimmort.nisp3.service.def.ICRMMangementService;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.def.ISystemUtilsService;
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
@Controller("/f/crm/column/add.action.do")
public class Add {
    @Autowired
    private ICRMMangementService crmService;
    @Autowired
    private ISystemUtilsService systemUtilsService;
    @Autowired
    private ILogService logService;
    @RequestMapping("/f/crm/column/add.action.do")
    public ModelAndView action(@Valid @ModelAttribute("model") CRMColumnBean bean,BindingResult result,HttpServletRequest request) throws Exception{
        ModelAndView success=new ModelAndView("/pub/success");
        ModelAndView fail=new ModelAndView("/f/crm/column/add");
        success.addObject("message","添加成功！");
        success.addObject("redirectURL","/f/crm/column/list.view.do");
        if(result.hasErrors()) {
            fail.addObject("formBean", bean);
            return fail;
        }

            if (crmService.existField(bean.getField())){
                fail.addObject("field","该字段已存在！");
                fail.addObject("formBean", bean);
                return fail;
            }

        if(systemUtilsService.isKeyWord(bean.getField())){
            fail.addObject("field","不能使用数据库保留字作为字段名");
            fail.addObject("formBean", bean);
            return fail;
        }
        if (crmService.existName(bean.getName())){
            fail.addObject("name","该字段名已存在！");
            fail.addObject("formBean", bean);
            return fail;
        }
        try {
        crmService.addCrmColumn(bean);
        }
        catch (MySQLSyntaxErrorException e){
            fail.addObject("syntax",e.getMessage());
            fail.addObject("formBean", bean);
            return fail;
        }
        //添加成功日志
        logService.recordLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                , IModular.Action.ADD, IModular.Result.TRUE,bean,bean.toString(),request);
        return success;
    }
}
