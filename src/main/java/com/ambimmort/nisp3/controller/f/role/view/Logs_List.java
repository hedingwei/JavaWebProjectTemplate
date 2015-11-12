package com.ambimmort.nisp3.controller.f.role.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pc on 2015/6/10.
 */
@Controller("/f/um/user/logs.view")
public class Logs_List {
    @RequestMapping("/f/um/user/logs.view.do")
    public ModelAndView action2(HttpServletRequest request){

        ModelAndView mv = new ModelAndView("/f/um/user/logs");

      /*  java.util.ShowList list = new ArrayList();
        try {
            list = userService.listUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("list",list);*/

        return mv;
    }
}
