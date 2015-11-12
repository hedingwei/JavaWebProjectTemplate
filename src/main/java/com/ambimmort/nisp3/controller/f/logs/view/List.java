package com.ambimmort.nisp3.controller.f.logs.view;
import com.ambimmort.nisp3.model.ui.f.pub.PaginationBean;
import com.ambimmort.nisp3.model.ui.f.pub.SearchBean;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by qinxiaoyao on 2015/6/18.
 */
@Controller("/f/logs/list.view.do")
public class List {

    @Autowired
    private LogServiceImpl logService;

    @RequestMapping("/f/logs/list.view.do")
    public ModelAndView _logs(@ModelAttribute("paginationBean") PaginationBean pb, @ModelAttribute("dateBean") SearchBean sb, HttpServletRequest request) throws ParseException {

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        ModelAndView mv = new ModelAndView("/f/logs/logs");

        //初始化日期
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历


        dBefore = calendar.getTime();   //得到前一天的时间

        if (sb.getStartDate()==null||sb.getEndDate()==null){
            dBefore=calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            System.out.println(">>>>>>>>>>>>>>>>>calendar.afterday======"+Calendar.DAY_OF_MONTH);
            dNow =calendar.getTime();//得到后一天的时间

            String start=dateFormat.format(dBefore);
            DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
            dBefore=format1.parse(start);
            String end=dateFormat.format(dNow);
            dNow=format1.parse(end);

            sb.setEndDate(dateFormat.format(dNow));
            sb.setStartDate(dateFormat.format(dBefore));
        }else{
            dBefore = new Date(sb.getStartDate());
            dNow = new Date(sb.getEndDate());
        }

        try {
//            String username = request.getUserPrincipal().getName();
            System.out.println("startTime: "+dateFormat.format(dNow)+" endTime: "+dateFormat.format(dBefore));
            mv.addObject("list",logService.listLogs(Modular.UM,dBefore.getTime()+"",dNow.getTime()+"",pb.getStart(),pb.getInterval(),request));
//            mv.addObject("list",new ArrayList<>());
//            System.out.println(logService.getTotalLogsNumber(ILogService.Modular.UM, dBefore.getTime() + "", dNow.getTime() +""));
            pb.setTotal(logService.getTotalLogsNumber(Modular.UM, dBefore.getTime() + "", dNow.getTime() +""));

            return mv;
        } catch (Exception e) {
            ModelAndView error = new ModelAndView("/pub/error");
            error.addObject("message", e.getMessage());
            error.addObject("redirectURL", "/f/logs/list.view.do");
            return error;
        }
    }
}
