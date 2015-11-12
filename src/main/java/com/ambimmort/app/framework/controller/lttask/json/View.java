package com.ambimmort.app.framework.controller.lttask.json;

import com.ambimmort.app.framework.lttask.AbstractTask;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/taskview.json.do")
public class View {

    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/taskview.json.do")
    @ResponseBody
    public JSONObject action(HttpServletRequest request,@RequestParam("lttid")String lttid) {

            AbstractTask abstractTask = longTimeTaskRunner.getRunningTasks().get(lttid);

            if(abstractTask==null){

                AbstractTask ltask = longTimeTaskRunner.getLastTask(lttid);

                if(ltask==null){
                    JSONObject result = new JSONObject();
                    result.put("hasTask", false);
                    return result;
                }else{
                    JSONObject result = JSONObject.fromObject(ltask.getModel());
                    result.put("hasTask", true);
                    return result;
                }


            }else{
                JSONObject result = JSONObject.fromObject(abstractTask.getModel());
                result.put("hasTask", true);
                return result;
            }

    }
}
