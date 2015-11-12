package com.ambimmort.nisp3.controller.f.logs.action;

import com.ambimmort.nisp3.model.ui.f.logs.LogBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinxiaoyao on 2015/6/18.
 */
@Controller("/f/logs/search.action.do")
public class Search {


   /* *//**
     * 结合datatable传的参数
     * draw:  原封不动的返回给前端
     * start: 起始页数
     * length: 每页显示条数
     *//*
    @Autowired
    private LogServiceImpl logService;

    @RequestMapping("/f/logs/search.action.do")
    @ResponseBody
    public String tabeldata(@RequestParam String draw,@RequestParam String start,@RequestParam String length, @RequestParam String startTime,@RequestParam String endTime){

        int iDisplayStart = Integer.parseInt(start); // 起始索引
        int iDisplayLength = Integer.parseInt(length); // 每页显示的行数

        startTime = startTime;
        endTime = endTime;

        //从数据库获取数据
//        ShowList<String[]> lst = new ArrayList<String[]>();
        List<JSONObject> lst = new ArrayList<JSONObject>();
        List<LogBean> lbs = new ArrayList<>();
        try {


            lbs = logService.listLogs(IModular.Modular.UM, startTime, endTime, iDisplayStart, iDisplayLength);
            for (LogBean lb : lbs){
                //lb.setReason(base.getFromBase64(lb.getReason()));

                lst.add(JSONObject.fromObject(lb));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject getObj = new JSONObject();
        getObj.put("draw", draw);// 不知道这个值有什么用,有知道的请告知一下
        getObj.put("recordsTotal", lbs.size());//实际的行数
        getObj.put("recordsFiltered", lbs.size());

        getObj.put("data", lst);//要以JSON格式返回
        return getObj.toString();
    }*/

}
