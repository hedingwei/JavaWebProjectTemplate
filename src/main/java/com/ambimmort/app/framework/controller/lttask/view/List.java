package com.ambimmort.app.framework.controller.lttask.view;

import com.ambimmort.app.framework.lttask.LTTaskBean;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/list.view.do")
public class List {
    private ModelAndView list = new ModelAndView("f/lttask/list");

    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/f/lttask/manager/list.view.do")
    public ModelAndView action(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        longTimeTaskRunner.updateRegisteredTaskBeanStatus();

        for(LTTaskBean b:longTimeTaskRunner.getRegisteredTasks()){
            try {
                b.setNextScheduledTime(longTimeTaskRunner.getScheduler().getTrigger(b.getTriggerKey()).getNextFireTime().getTime());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }

            System.out.println("query:"+b.getTaskId());
            b.setTotalTimes(getTotalExecutedTimes(b.getTaskId()));

        }

        System.out.println(System.currentTimeMillis()-startTime);

        list.addObject("lttasks",longTimeTaskRunner.getRegisteredTasks());
        return list;

    }



    private int getTotalExecutedTimes(String id){
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("select count(*) from t_lttask_log WHERE taskid=?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }


}
