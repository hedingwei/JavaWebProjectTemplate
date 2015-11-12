package com.ambimmort.nisp3.controller;

import com.ambimmort.nisp3.model.ui.f.user.EditUserBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by hedingwei on 5/19/15.
 */
@Controller
@RequestMapping("/")
public class _Controller {

    @Autowired
    private UserManagementServiceImpl userManagementService;
    private ModelAndView error = new ModelAndView("pub/error");
    private String redirectURL = "/f/um/domain/list.view.do";

    @RequestMapping("index.do")
    public ModelAndView _index(HttpServletRequest request){


        return new ModelAndView("redirect:/f/index.do");

    }

    @RequestMapping("resetpwd.do")
    public ModelAndView _resetpwdSetting(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/resetpwd");
        mv.addObject("data","");
        return mv;
    }


    @RequestMapping("blank.do")
    public ModelAndView _blankSetting(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/blank");
        mv.addObject("data","");
        return mv;
    }

}


