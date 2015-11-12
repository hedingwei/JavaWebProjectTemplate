package com.ambimmort.nisp3.controller.f.crm.search.view;

import com.ambimmort.nisp3.service.def.ICRMSearchService;
import com.ambimmort.nisp3.service.impl.CRMSearchService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ShiYun on 2015/6/30 0030.
 */
//@Controller("/f/crm/search/search.action.do")
public class List {

    @Autowired
    private ICRMSearchService crmSearchService;

//    @RequestMapping("/f/crm/search/search.action.do")
    public ModelAndView action() throws Exception {
        ModelAndView modelAndView = new ModelAndView("/f/crm/search/search");
        try {
            JSONArray list = crmSearchService.getCRMs();
            modelAndView.addObject("columns", list);
            return modelAndView;
        } catch (Exception e) {
            System.out.println("list出现异常");
        }
        return null;
    }
}
