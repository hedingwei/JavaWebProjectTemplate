package com.ambimmort.nisp3.controller.f.domain.view;

import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.service.def.IAreaManagementService;
import com.ambimmort.nisp3.service.impl.AreaManagementServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by pc on 2015/6/10.
 */
@Controller("/f/um/domain/list.view.do")
public class List {
    private Log logger = LogFactory.getLog(List.class);
    private ModelAndView error = new ModelAndView("pub/error");
    private ModelAndView mv = new ModelAndView("f/um/domain/list");
    private String redirectURL = "/f/um/domain/list.view.do";

    @Autowired
    private IAreaManagementService areaMangementService;

    @RequestMapping("/f/um/domain/list.view.do")
    public ModelAndView action(HttpServletRequest request){


        //取得当前登录用户username
        //判断当前登录用户是否为 "admin"，只有admin能执行区域操作！
        String uname = request.getUserPrincipal().getName();
        if (!uname.equals("admin")){
            error.addObject("message","您无权限进行此操作");
            error.addObject("redirectURL",redirectURL);
            return error;
        }


        try {
            //获取当前用户所管辖的所有区域列表
            java.util.List<DomainBean> areas = areaMangementService.listAreasByAreaId("-1");

            //将区域列表转成JSONArray
            java.util.List<JSONObject> nl = new ArrayList<>();
            for (DomainBean db:areas){
                nl.add(JSONObject.fromObject(db));
            }
            mv.addObject("uiareas",JSONArray.fromObject(nl));

            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            error.addObject("message",e.getMessage());
            error.addObject("redirectURL", redirectURL);
            return error;
        }
    }
}
