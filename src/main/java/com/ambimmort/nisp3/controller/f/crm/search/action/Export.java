package com.ambimmort.nisp3.controller.f.crm.search.action;

import com.ambimmort.nisp3.service.def.ICRMSearchService;
import com.ambimmort.nisp3.service.impl.CRMSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 * CRM数据导出
 * Created by ShiYun on 2015/6/26 0026.
 */
//@Controller("/f/crm/search/search.Export.do")
public class Export {

    @Autowired
    private ICRMSearchService crmSearchService;

//    @RequestMapping("/f/crm/search/search.Export.do")
    public ModelAndView action() throws Exception {
        ModelAndView modelAndView = new ModelAndView("");
        try {
            return modelAndView;
        } catch (Exception e) {
            System.out.println("list出现异常");
        }
        return null;
    }
}
