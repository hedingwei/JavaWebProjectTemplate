package com.ambimmort.nisp3.controller.f.crm.column.action;

import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import com.ambimmort.nisp3.service.def.ICRMMangementService;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.CRMMangementServiceImpl;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZZZZ on 2015/6/19.
 */
@Controller("/f/crm/column/delete.action.do")
public class Delete {
    @Autowired
    private ICRMMangementService crmService;
    @Autowired
    private ILogService logService;

    private ModelAndView success=new ModelAndView("/pub/success");
    private ModelAndView fail=new ModelAndView("/pub/error");
    private String redirectURL = "/f/crm/column/list.view.do";


    @RequestMapping("/f/crm/column/delete.action.do")
    public ModelAndView action(@RequestParam("id") String id,HttpServletRequest request) throws Exception {
        CRMColumnBean crmColumnBean = crmService.getBeanById(id);
        if(crmService.isUseOrNot(id)==0) {
            try {
                crmService.deleteCrmColumn(id);
                success.addObject("message","删除成功！");
                success.addObject("redirectURL",redirectURL);
            }catch(MySQLSyntaxErrorException e){
                fail.addObject("message",e.getMessage());
                fail.addObject("redirectURL",redirectURL);
                return fail;
            }

            //添加成功日志
            logService.recordLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                    , IModular.Action.DELETE, IModular.Result.TRUE,crmColumnBean,crmColumnBean.toString(),request);
            return success;
        }else{
            fail.addObject("message","删除失败，在使用中！");
            fail.addObject("redirectURL",redirectURL);
            logService.recordErrorLogs(IModular.Modular.CRM, IModular.SubModular.CRM_COLUMN
                    , IModular.Action.DELETE, IModular.Result.FALSE, crmColumnBean.toString(), "删除失败，在使用中!", crmColumnBean,request);
            return fail;
        }

    }
}

