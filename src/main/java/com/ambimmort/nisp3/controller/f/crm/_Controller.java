package com.ambimmort.nisp3.controller.f.crm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hedingwei on 5/19/15.
 */
@org.springframework.stereotype.Controller("/f/crm")
@RequestMapping("/f/crm")
public class _Controller {

    @RequestMapping("search/search.view.do")
    public ModelAndView _list(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/search/search");
        return mv;
    }

    @RequestMapping("column/list.do")
    public ModelAndView _setting(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/column/list");
        return mv;
    }

    @RequestMapping("column/add.do")
    public ModelAndView _add(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/column/add");
        return mv;
    }
    @RequestMapping("column/edit.do")
    public ModelAndView _edit(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/column/edit");
        return mv;
    }

    @RequestMapping("config/config.do")
    public ModelAndView _interface(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/config/config");
        return mv;
    }

    @RequestMapping("log/status.do")
    public ModelAndView _status(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/log/status");
        return mv;
    }

    @RequestMapping("log/list.view.do")
    public ModelAndView _ilog(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/log/list");
        return mv;
    }

    @RequestMapping("log/log.view.do")
    public ModelAndView _run(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/log/list");
        return mv;
    }

    @RequestMapping("log/import.do")
    public ModelAndView _import(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/crm/log/import");
        return mv;
    }


}
