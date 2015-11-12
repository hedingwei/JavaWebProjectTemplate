package com.ambimmort.nisp3.controller.f.domain.view;

import com.ambimmort.nisp3.model.ui.f.area.AddDomainBean;
import com.ambimmort.nisp3.service.def.ICRMMangementService;
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
@Controller("/f/um/domain/add.view.do")
public class Add {

    private Log logger = LogFactory.getLog(Add.class);
    private ModelAndView error = new ModelAndView("pub/error");
    private ModelAndView mv = new ModelAndView("f/um/domain/add");
    private String redirectURL = "/f/um/domain/list.view.do";
    private AddDomainBean formBean;
    @Autowired
    private ICRMMangementService crmManagementService;


    @RequestMapping("/f/um/domain/add.view.do")
    public ModelAndView action(@RequestParam("pid") String pid, @RequestParam("pname") String pname, @RequestParam("pcode") String pcode, HttpServletRequest request) {
        if(pid.equals("0")){
            formBean=new AddDomainBean();
            formBean.setPcode(Long.parseLong(pcode));
            formBean.setPname(pname);
            formBean.setPid(pid);
            mv.addObject("formBean",formBean);
            mv.addObject("fields",crmManagementService.getFields());
            return mv;
        }
        String t_code=pid.substring(pid.length()-2,pid.length());
        int code=Integer.parseInt(t_code);
        if(code>0){
            error.addObject("message","此区域已是最下限，不能再添加子区域！");
            error.addObject("redirectURL",redirectURL);
            return error;
        }
        formBean=new AddDomainBean();
        formBean.setPcode(Long.parseLong(pcode));
        formBean.setPname(pname);
        formBean.setPid(pid);
        mv.addObject("formBean",formBean);
        mv.addObject("fields",crmManagementService.getFields());
        return mv;
    }
}
