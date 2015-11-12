package com.ambimmort.nisp3.controller.f.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * Created by hedingwei on 5/19/15.
 */
@org.springframework.stereotype.Controller("/f/role")
@RequestMapping("/f/role")
public class _Controller {

    @Autowired
    private DataSource dataSource;

    public DataSource getDataSource() {
        System.out.println("getDataSource()="+dataSource);
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping("list.do")
    public ModelAndView _list(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/um/role/list");

        return mv;
    }

    @RequestMapping("add.do")
    public ModelAndView _add(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/um/role/add");
        return mv;
    }

    @RequestMapping("update.do")
    public ModelAndView _update(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/um/user/view");
        return mv;
    }

    @RequestMapping("view.do")
    public ModelAndView _view(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/um/role/view");
        return mv;
    }

    @RequestMapping("delete.do")
    public ModelAndView _delete(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("forward:f/role/list");
        return mv;
    }

    @RequestMapping("functions.do")
    public ModelAndView _functions(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/um/role/functions");
        return mv;
    }

    @RequestMapping("edit.do")
    public ModelAndView _edit(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("f/um/role/edit");
        return mv;
    }

    @RequestMapping("logs.do")
    public ModelAndView _logs(HttpServletRequest request){

        ModelAndView mv = new ModelAndView("f/um/role/logs");

        return mv;
    }


    @RequestMapping("success.do")
    public ModelAndView _success(HttpServletRequest request){

        ModelAndView mv = new ModelAndView("f/um/role/success");

        return mv;
    }



}
