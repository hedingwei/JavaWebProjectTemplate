package com.ambimmort.nisp3.controller.f.crm.column.view;

import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import com.ambimmort.nisp3.service.impl.CRMMangementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZZZZ on 2015/6/19.
 */
@Controller("/f/crm/column/list.view.do")
public class List {
    @Autowired
    public CRMMangementServiceImpl crmColumnManagementService;
    @RequestMapping("/f/crm/column/list.view.do")
    public ModelAndView action() throws Exception {
        ModelAndView modelAndView=new ModelAndView("/f/crm/column/list");
        try {
            java.util.List<CRMColumnBean> list=crmColumnManagementService.list();
            modelAndView.addObject("columns",list);
            return modelAndView;
        } catch (Exception e) {
            System.out.println("list出现异常");
        }
        return null;
    }

}
