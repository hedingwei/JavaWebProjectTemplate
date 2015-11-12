package com.ambimmort.nisp3.controller.f.domain.view;

import com.ambimmort.nisp3.model.ui.f.area.EditDomainBean;
import com.ambimmort.nisp3.service.def.IAreaManagementService;
import com.ambimmort.nisp3.service.def.ICRMMangementService;
import com.ambimmort.nisp3.service.impl.CRMMangementServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qinxiaoyao on 2015/6/16.
 */
@Controller("/f/um/domain/edit.view.do")
public class Edit {

    private Log logger = LogFactory.getLog(Edit.class);
    private ModelAndView error = new ModelAndView("pub/error");
    private ModelAndView mv = new ModelAndView("f/um/domain/edit");
    private String redirectURL = "/f/um/domain/list.view.do";
    private EditDomainBean edb;
    @Autowired
    private ICRMMangementService crmManagementService;

    @Autowired
    private IAreaManagementService areaMangementService;

    @RequestMapping("/f/um/domain/edit.view.do")
    public ModelAndView action(@RequestParam("id") String id, @RequestParam("pname") String pname, HttpServletRequest request) {
        try {
            edb = areaMangementService.getEditBean(id);
            mv.addObject("pname",pname);
            mv.addObject("fields",crmManagementService.getFields());
            mv.addObject("model",edb);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();

            error.addObject("message",e.getMessage());
            error.addObject("redirectURL",redirectURL);
            mv.addObject("fields",crmManagementService.getFields());
            return error;
        }
    }
}
