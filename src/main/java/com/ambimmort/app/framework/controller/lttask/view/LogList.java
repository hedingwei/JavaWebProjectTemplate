package com.ambimmort.app.framework.controller.lttask.view;

import com.ambimmort.app.framework.controller.lttask.action.TaskLogBean;
import com.ambimmort.app.framework.lttask.LTTaskBean;
import com.ambimmort.app.framework.lttask.LTTaskManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hedingwei on 6/5/15.
 */
@Controller("/f/lttask/manager/loglist.view.do")
public class LogList {
    private ModelAndView list = new ModelAndView("f/lttask/loglist");

    @Autowired
    private LTTaskManager longTimeTaskRunner;

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/f/lttask/manager/loglist.view.do")
    public ModelAndView action_(HttpServletRequest request,@RequestParam("taskId")String taskId) {
        list.addObject("logs",getTotalExecutedTimes(taskId));
        list.addObject("taskId",taskId);
        return list;
    }

    @RequestMapping("/f/lttask/manager/loglist.json.do")
    @ResponseBody
    public JSONObject action(HttpServletRequest request,@RequestParam("taskId")String taskId) {

        String start = request.getParameter("start");
        String length = request.getParameter("length");


        String querySQL = request.getParameter("_query_sql");

        String d = "";
        try {
            d = new String(Base64.decodeBase64(querySQL),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JSONObject object = new JSONObject();

        object.put("draw", request.getParameter("draw"));

        JSONArray datas = new JSONArray();
        JSONObject data = null;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement totalCountPs = connection.prepareStatement("select count(*) from  t_lttask_log WHERE taskId='"+taskId+"' AND " +d);

            ResultSet resultSet = totalCountPs.executeQuery();

            resultSet.next();
            object.put("recordsTotal", resultSet.getInt(1));//实际的行数
            object.put("recordsFiltered", resultSet.getInt(1));

            PreparedStatement ps = connection.prepareStatement("select * from t_lttask_log WHERE taskId='"+taskId+"' AND "+d+" ORDER BY starttime  DESC LIMIT ?,?");
            ps.setInt((int)1, Integer.parseInt(start));
            ps.setInt((int) 2, Integer.parseInt(length));
            ResultSet rs = ps.executeQuery();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while(rs.next()){
                data = new JSONObject();
                data.put("id",rs.getInt("id"));
                data.put("startTime", sdf.format(new Date(rs.getTimestamp("starttime").getTime())) );
                data.put("endTime",sdf.format(new Date(rs.getTimestamp("endtime").getTime())) );
                data.put("startby",rs.getString("startby"));
                data.put("result",rs.getString("result"));
                data.put("taskId",rs.getString("taskid"));
                data.put("content",rs.getString("content"));
                data.put("startComment",rs.getString("startcomment"));
                data.put("endComment",rs.getString("cancelcomment"));
                data.put("type",rs.getString("type")==null?"":rs.getString("type"));
                datas.add(data);
            }

            object.put("data",datas);

        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;


    }





    private java.util.List<TaskLogBean> getTotalExecutedTimes(String id){
        java.util.List<TaskLogBean> logs = new ArrayList<>();
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from t_lttask_log WHERE taskid=?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            TaskLogBean bean = null;
            while(rs.next()){
                bean = new TaskLogBean();
                bean.setId(rs.getInt("id"));
                bean.setStartby(rs.getString("startby"));
                bean.setStarttime(rs.getTimestamp("starttime").getTime());
                bean.setEndtime(rs.getTimestamp("endtime").getTime());
                bean.setResult(rs.getString("result"));
                bean.setStartComment(rs.getString("startcomment"));
                bean.setCancelComment(rs.getString("cancelcomment"));
                logs.add(bean);
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
        return logs;
    }


}
