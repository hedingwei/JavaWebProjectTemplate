package com.ambimmort.nisp3.controller.f.crm.column.view;

import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ZZZZ on 2015/6/19.
 */
@Controller("/f/crm/column/add.view.do")
public class Add {
    private CRMColumnBean formBean;
    @RequestMapping("/f/crm/column/add.view.do")
    public ModelAndView action(){
        ModelAndView mv=new ModelAndView("/f/crm/column/add");
        mv.addObject("formBean",formBean);
        return  mv;
    }
}
