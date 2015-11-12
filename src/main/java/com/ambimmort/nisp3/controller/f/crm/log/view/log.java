package com.ambimmort.nisp3.controller.f.crm.log.view;

import com.ambimmort.nisp3.model.ui.f.pub.PaginationBean;
import com.ambimmort.nisp3.model.ui.f.pub.SearchBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular;
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
 * Created by ylj on 2015/8/13.
 */
@Controller("/f/crm/log.view.do")
public class log {
    @Autowired
    private ILogService logService;
    @RequestMapping("/f/crm/log.view.do")
    public ModelAndView _log(@ModelAttribute("paginationBean") PaginationBean pb, @ModelAttribute("dateBean") SearchBean sb, HttpServletRequest request){
        ModelAndView mv=new ModelAndView("/f/logs/logs");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");

        //初始化日期
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历


        if (sb.getStartDate()==null||sb.getEndDate()==null){
            dBefore=calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH,+1);
            System.out.println(">>>>>>>>>>>>>>>>>calendar.afterday======"+Calendar.DAY_OF_MONTH);
            dNow =calendar.getTime();//得到后一天的时间

            String start=dateFormat.format(dBefore);
            DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
            String end=null;
            try {
                dBefore=format1.parse(start);
                end=dateFormat.format(dNow);
                dNow=format1.parse(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            sb.setEndDate(dateFormat.format(dNow));
            sb.setStartDate(dateFormat.format(dBefore));
        }else{
            dBefore = new Date(sb.getStartDate());
            dNow = new Date(sb.getEndDate());
        }

        try {
//            String username = request.getUserPrincipal().getName();
            System.out.println("startTime: "+dateFormat.format(dNow)+" endTime: "+dateFormat.format(dBefore));
            mv.addObject("list",logService.listLogs(IModular.Modular.CRM,dBefore.getTime()+"",dNow.getTime()+"",pb.getStart(),pb.getInterval(),request));
//            mv.addObject("list",new ArrayList<>());
//            System.out.println(logService.getTotalLogsNumber(ILogService.Modular.UM, dBefore.getTime() + "", dNow.getTime() +""));
            pb.setTotal(logService.getTotalLogsNumber(IModular.Modular.CRM, dBefore.getTime() + "", dNow.getTime() +""));

            return mv;
        } catch (Exception e) {
            ModelAndView error = new ModelAndView("/pub/error");
            error.addObject("message", e.getMessage());
            error.addObject("redirectURL", "/f/crm/log.view.do");
            return error;
        }
    }


}
