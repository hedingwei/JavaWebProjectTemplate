package com.ambimmort.app.framework.controller.lttask.json;

import com.ambimmort.app.framework.lttask.AbstractTask;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by hedingwei on 6/28/15.
 */
@Controller("/f/lttask/manager/loglist1.json.do")
public class LogList {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/f/lttask/manager/loglist1.json.do")
    @ResponseBody
    public JSONObject action(HttpServletRequest request) {

        String start = request.getParameter("start");
        String length = request.getParameter("length");

        JSONObject object = new JSONObject();

        object.put("draw", request.getParameter("draw"));

        String condition = "1=1";
        String taskId = request.getParameter("taskId");
        if(taskId==null){
            condition="1=1";
        }else{
            condition="taskId='"+taskId+"'";
        }

        JSONArray datas = new JSONArray();
        JSONObject data = null;
        Connection connection = null;
        try{
            connection = dataSource.getConnection();

            PreparedStatement totalCountPs = connection.prepareStatement("select count(*) from t_lttask_log WHERE "+condition);
            ResultSet resultSet = totalCountPs.executeQuery();

            resultSet.next();
            object.put("recordsTotal", resultSet.getInt(1));//实际的行数
            object.put("recordsFiltered", resultSet.getInt(1));

            PreparedStatement ps = connection.prepareStatement("select * from t_lttask_log WHERE "+condition+" ORDER BY starttime DESC LIMIT ?,?");
            ps.setInt((int)1, Integer.parseInt(start));
            ps.setInt((int) 2, Integer.parseInt(length));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                data = new JSONObject();
                data.put("id",rs.getInt("id"));
                data.put("startTime",rs.getLong("starttime"));
                data.put("endTime",rs.getLong("endtime"));
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


}
