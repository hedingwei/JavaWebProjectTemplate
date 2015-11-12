package com.ambimmort.nisp3.controller.pub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hedingwei on 5/19/15.
 */
@Controller("/pub")
@RequestMapping("/pub")
public class _Controller {

    @RequestMapping("404.do")
    public String _f_404(){
        return "pub/404";
    }

    @RequestMapping("forbidden.do")
    public String _f_forbidden(){
        return "pub/forbidden";
    }


}
