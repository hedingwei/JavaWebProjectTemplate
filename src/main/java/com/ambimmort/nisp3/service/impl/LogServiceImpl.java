/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.model.ui.f.logs.LogBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.ILogService;
import com.ambimmort.nisp3.service.def.IModular.Action;
import com.ambimmort.nisp3.service.def.IModular.Modular;
import com.ambimmort.nisp3.service.def.IModular.Result;
import com.ambimmort.nisp3.service.def.IModular.SubModular;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LiuShang
 * @date 2015-6-18 14:21:14
 */
@Component
public class LogServiceImpl implements ILogService {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private UserManagementServiceImpl userManagementService;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;
    private long code;
    private long nextcode;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
    public long getNextcode() {
        return nextcode;
    }

    public void setNextcode(long nextcode) {
        this.nextcode = nextcode;
    }

    @Override
    public void recordLogs( Modular modular, SubModular submodular, Action action, Result result, Object obj, String newDetail,HttpServletRequest request) throws Exception {
        Connection connection = null;
        String uname=null;
        if(request.getUserPrincipal()!=null) {
            uname = request.getUserPrincipal().getName();
        }else{
            UserBean objBean=(UserBean)obj;
            uname=objBean.getUsername();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>logSer>>>uname:" + uname);
        }
        String areaId=null;
        String areaName=null;
        String name=null;
        //获取USER的cookie值
        String cookie=this.getCookie(request);

        String ip=this.getIpAddr(request);

        try {
            UserBean userBean=userManagementService.getUser(uname);
            areaId=userBean.getAreaId();
            areaName=userBean.getAreaName();
            name=userBean.getName();

            name=this.getBase64(name);
            uname=this.getBase64(uname);

            areaName=this.getBase64(areaName);
            String modularString=this.getBase64(modular.toString());
            String submodularString=this.getBase64(submodular.toString());
            String actionString=this.getBase64(action.toString());
            String resultString=this.getBase64(result.toString());
            newDetail=this.getBase64(newDetail);
            String objString=this.getBase64(JSONObject.fromObject(obj).toString());

            ip=this.getBase64(ip);
            cookie=this.getBase64(cookie);

            connection = dataSource.getConnection();
            String tablename = modular.name().toLowerCase();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + tablename);

            String time = new Date().getTime() + "";
            String sql = "INSERT INTO t_" + tablename + "_logs (l_name,l_username,l_area_id,l_area_name,l_modular,l_submodular,l_action,l_result,l_obj,l_newDetail,l_ip,l_cookie,l_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            Object[] params = {name, uname, areaId, areaName, modularString, submodularString, actionString, resultString,
                    objString, newDetail, ip, cookie, time};
//            System.out.println(modularString);

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            int updateRows = ps.executeUpdate();
            if (updateRows > 0) {
                System.out.println("插入成功！" + sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
                System.out.println("record log:connection closed.");
            }
        }


    }

    @Override
    public void recordUpdatedLogs(Modular modular, SubModular submodular, Action action, Result result,
                                  String newDetail, String oldDetail, Object obj,HttpServletRequest request) throws Exception {
        Connection connection = null;
        String uname = request.getUserPrincipal().getName();
        String areaId=null;
        String areaName=null;
        String name=null;
        //获取USER的cookie值
        String cookie=this.getCookie(request);

        String ip=this.getIpAddr(request);
        try {
            UserBean userBean=userManagementService.getUser(uname);
            areaId=userBean.getAreaId();
            areaName=userBean.getAreaName();
            name=userBean.getName();
           /* byte[] b = null;
            BASE64Encoder base=new BASE64Encoder();*/
            name=this.getBase64(name);
            uname=this.getBase64(uname);

            areaName=getBase64(areaName);
            String modularString=this.getBase64(modular.toString());
            String submodularString=this.getBase64(submodular.toString());
            String actionString=this.getBase64(action.toString());
            String resultString=this.getBase64(result.toString());
            newDetail=this.getBase64(newDetail);
            oldDetail=this.getBase64(oldDetail);
            String objString=this.getBase64(JSONObject.fromObject(obj).toString());

            ip=this.getBase64(ip);
            cookie=this.getBase64(cookie);


            connection = dataSource.getConnection();
            String tablename = modular.name().toLowerCase();
            System.out.println(">>>>>>>>>>>>>>>update>>>>>>>>>>>>>" + tablename);
            String time = new Date().getTime() + "";
            String sql = "INSERT INTO t_" + tablename + "_logs (l_name,l_username,l_area_id,l_area_name,l_modular,l_submodular,l_action,l_result,l_obj,l_newDetail,l_oldDetail,l_time,l_ip,l_cookie) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            Object[] params = {name, uname, areaId, areaName, modularString, submodularString, actionString, resultString, objString, newDetail, oldDetail, time, ip, cookie};
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            int updateRows = ps.executeUpdate();
            if (updateRows > 0) {
                System.out.println("插入成功！" + sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void recordErrorLogs( Modular modular,
                                SubModular submodular, Action action, Result result, String detail, String reason,
                                Object obj,HttpServletRequest request) throws Exception {
        Connection connection = null;
        String uname = request.getUserPrincipal().getName();
        String areaId=null;
        String areaName=null;
        String name=null;
        //获取USER的cookie值
        String cookie=this.getCookie(request);

        String ip=this.getIpAddr(request);

        try {
            UserBean userBean=userManagementService.getUser(uname);
            areaId=userBean.getAreaId();
            areaName=userBean.getAreaName();
            name=userBean.getName();

            name=this.getBase64(name);
            uname=this.getBase64(uname);

            areaName=this .getBase64(areaName);
            String modularString=this.getBase64(modular.toString());
            String submodularString=this.getBase64(submodular.toString());
            String actionString=this.getBase64(action.toString());
            String resultString=this.getBase64(result.toString());
            detail=this.getBase64(detail);
            reason=this.getBase64(reason);
            String objString=this.getBase64(JSONObject.fromObject(obj).toString());

            ip=this.getBase64(ip);
            cookie=this.getBase64(cookie);




            connection = dataSource.getConnection();

            String tablename = modular.name().toLowerCase();

            String time = new Date().getTime() + "";
            String sql = "INSERT INTO t_" + tablename + "_logs (l_name,l_username,l_area_id,l_area_name,l_modular,l_submodular,l_action,l_result,l_reason,l_obj,l_newDetail,l_ip,l_cookie,l_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            Object[] params = {name, uname, areaId, areaName, modularString, submodularString, actionString, resultString, StringEscapeUtils.escapeJava(reason), objString, detail, ip, cookie, time};
            System.out.println(">>>>>>>>>>logservice obj>>>>>" + JSONObject.fromObject(obj).toString());
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            int updateRows = ps.executeUpdate();
            if (updateRows > 0) {
                System.out.println("插入成功！" + sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
                System.out.println("recordErrorLog:connection closed.");
            }
        }
    }

    @Override
    public List<LogBean> listLogs(Modular modular, String startTime, String endTime, int startLine, int endLine,HttpServletRequest request) throws Exception {
        Connection connection = null;
        String uname = request.getUserPrincipal().getName();
        System.out.println(">>>>>>>>>>>>>user.logs.listLogs>>>>>>>>>>>>>>>>>>"+uname);
        UserBean userBean=userManagementService.getUser(uname);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            connection = dataSource.getConnection();
            long code=Long.parseLong(userBean.getAreaId());
            this.setCode(code);
            System.out.print(">>>>>>>>>>>>code>>>>>>>>>>>>>>>"+code);
            long nextcode=areaManagementService.getNextAreaCode(code);
            this.setNextcode(nextcode);
            System.out.print(">>>>>>>>>>>>code>>>>>>>>>>>>>>>"+nextcode);

            String tablename = modular.name().toLowerCase();
            String modularString=this.getBase64(modular.toString());
            System.out.println(">>>>>>>>>>>>>>list>>>>>>>>>>>>>>" + tablename);
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_" + tablename + "_logs WHERE l_modular='"
                    + modularString + "' AND l_time BETWEEN " + startTime+" AND  "+endTime + " AND l_area_id BETWEEN " +  code+" AND "+nextcode+
                    " ORDER BY l_time DESC LIMIT " + startLine + "," + endLine);


          /*  long code1=areaManagementService.getNextAreaCode(code);
            PreparedStatement ps3 = connection.prepareStatement("SELECT id,title,url,type,description,operator,createTime,application FROM t_com_wp WHERE areaCode BETWEEN "+code+" AND "+code1+" ORDER BY createTime DESC LIMIT "+startLine+","+endLine);
*/
            System.out.println("SELECT * FROM t_" + tablename + "_logs WHERE l_modular='"
                    + modularString + "' AND l_time BETWEEN '" + startTime+"' AND ' "+endTime + "' AND l_area_id BETWEEN " +  code+" AND "+nextcode+
                    " ORDER BY l_time DESC LIMIT " + startLine + "," + endLine);

            ResultSet rs = sql.executeQuery();
            List<LogBean> lbs = new ArrayList<>();

            while (rs.next()) {

                String dateTime = dateFormat.format(Long.parseLong(rs.getString("l_time")));

                LogBean lb = new LogBean();
                lb.setId(rs.getInt("id"));//ID
                lb.setTime(dateTime);//时间
                lb.setAreaid(this.getFromBase64(rs.getString("l_area_id")));//操作区域id
                System.out.println(this.getFromBase64(rs.getString("l_area_id")));
                lb.setAreaname(this.getFromBase64(rs.getString("l_area_name")));//操作区域name
                lb.setModular(this.getFromBase64(rs.getString("l_modular")));//子模块
                lb.setName(this.getFromBase64(rs.getString("l_name")));//用户的真实姓名
                lb.setUsername(this.getFromBase64(rs.getString("l_username")));//操作员
                lb.setIp(this.getFromBase64(rs.getString("l_ip")));//ip
                lb.setCookie(this.getFromBase64(rs.getString("l_cookie")));//cookie;
                lb.setAction(this.getFromBase64(rs.getString("l_action")));
                lb.setSubmodular(this.getFromBase64(rs.getString("l_submodular")));//系统子模块名
                lb.setResult(this.getFromBase64(rs.getString("l_result")));//执行结果：成功1、失败0
                lb.setReason(rs.getString("l_reason"));//执行失败原因
                lb.setNewDetail(rs.getString("l_newDetail").replaceAll("[\\t\\n\\r]",""));//详情（新执行细节：一段描述性语言描述当前的执行操作和细节）
               if(rs.getString("l_oldDetail")!=null) {
                   lb.setOldDetail(rs.getString("l_oldDetail").replaceAll("[\\t\\n\\r]",""));//详情（旧执行细节：一段描述性语言描述当前的执行操作和细节
               }// （方便新旧值对比，查看修改内容））
                lb.setObj(this.getFromBase64(rs.getString("l_obj")));//表单（执行对象：一般直接填入表单对象）
                lbs.add(lb);
            }
            return lbs;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public int getTotalLogsNumber(Modular modular, String startTime, String endTime) throws Exception {
        Connection connection = null;
        int total = 0;
        try {
            connection = dataSource.getConnection();

            String tablename = modular.name().toLowerCase();
            String modularString = this.getBase64(modular.toString());

            PreparedStatement sql = connection.prepareStatement("SELECT COUNT(*) FROM t_" + tablename + "_logs WHERE l_modular='"
                    + modularString +  "' AND l_time BETWEEN " + startTime+" AND  "+endTime + " AND l_area_id BETWEEN " +  this.getCode()+" AND "+this.getNextcode()
                   );

            System.out.println("SELECT COUNT(*) FROM t_" + tablename + "_logs WHERE l_modular='"
                    + modularString + "' AND l_time BETWEEN '" + startTime + "' AND '" + endTime + "'");

            ResultSet rs = sql.executeQuery();

            while (rs.next()){
                total = rs.getInt(1);
            }
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
    //Base64加密
    public  String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.encodeBase64String(b);
        }
        return s;
    }
    // 解密
    public  String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {

            try {
                b = Base64.decodeBase64(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    //获取cookie
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookie=null;
        for (int i = 0; i < cookies.length; i++) {
            Cookie cook = cookies[i];
            if (cook.getName().equalsIgnoreCase("USER")) {
                cookie = cookies[i].getValue();
            }
        }
        return cookie;
    }

}






