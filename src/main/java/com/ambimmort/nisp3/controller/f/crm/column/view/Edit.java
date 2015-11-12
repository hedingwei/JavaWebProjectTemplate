package com.ambimmort.nisp3.controller.f.crm.column.view;

import com.ambimmort.nisp3.service.impl.CRMMangementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZZZZ on 2015/6/19.
 */
@Controller("/f/crm/column/edit.view.do")
public class Edit {
    @Autowired
    CRMMangementServiceImpl crmService;
    @RequestMapping("/f/crm/column/edit.view.do")
    public ModelAndView action(@RequestParam("id") String id) throws Exception {
        ModelAndView mv=new ModelAndView("/f/crm/column/edit");
        mv.addObject("model", crmService.edit(id));
        return mv;

    }
}
