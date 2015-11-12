package com.ambimmort.app.framework.controller.lttask.json;

import com.ambimmort.app.framework.lttask.AbstractTask;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/status.json.do")

public class Status {

    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @RequestMapping("/f/lttask/manager/status.json.do")
    @ResponseBody
    public JSONObject action(HttpServletRequest request,@RequestParam("taskId")String taskId) {

        AbstractTask task = (AbstractTask) longTimeTaskRunner.getRunningTask(taskId);

        JSONObject ret = new JSONObject();

        if(task!=null){
            ret.put("hasTask",true);
            if(task.getProgress()!=100){
                ret.put("isRunning", true);
                ret.put("task_model",JSONObject.fromObject(task.getModel()));
            }else{
                ret.put("isRunning", false);
                ret.put("task_model", JSONObject.fromObject(task.getModel()));
            }
        }else{
            ret.put("hasTask",false);

        }

        return ret;

    }
}
