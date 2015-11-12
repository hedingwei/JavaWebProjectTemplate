package com.ambimmort.nisp3.controller.f;

import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.IModular;
import com.ambimmort.nisp3.service.impl.LogServiceImpl;
import com.ambimmort.nisp3.service.impl.UserManagementServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by hedingwei on 5/19/15.
 */
@Controller("/f")
@RequestMapping("/f")
public class _Controller {

    Logger logger = LoggerFactory.getLogger(_Controller.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private UserManagementServiceImpl userManagementService;

    public DataSource getDataSource() {
        System.out.println("getDataSource()=" + dataSource);
        return dataSource;
    }
    private String username;
    public  String getUsername()
    {
        return  username;
    }
    public void setUsername(String username){
        this.username=username;

    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping("index.do")
    public ModelAndView _f_index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("f/index");
        try {
            //1. 首先判断当前用户的Seesion是否存在
            if (request.getUserPrincipal() == null) {
                return mv;
            }

            //2. 读取导航文件
            String s = FileUtils.readFileToString(new File(request.getServletContext().getRealPath("/resources/assets/data/nav.js")), "utf-8");
            JSONArray array = JSONArray.fromObject(s);

            //3.  获取当前登录用户的所有权限
            String current = request.getUserPrincipal().getName();
            this.setUsername(current);
            System.out.println("当前登录： " + current);
            try {
                UserBean userBean=userManagementService.getUser(current);
                //登录成功时添加成功日志
                logService.recordLogs(IModular.Modular.SYSTEM, IModular.SubModular.SYSTEM_LOGIN
                        , IModular.Action.LOGIN, IModular.Result.TRUE, userBean, userBean.toString(), request);
            } catch (Exception e) {
                e.printStackTrace();
            }


            List<String> functions = getAuthorizedFunctions(current);

            //4.  生成导航JSON
            JSONArray userTree = treeList(current, array, functions, request.getServletContext().getContextPath());
            if (logger.isDebugEnabled()) {
                // logger.debug("All导航JSON : {}", array.toString(2));
                logger.debug("生成用户导航JSON : {}", userTree.toString(2));
            }
            mv.addObject("nav", userTree);

            mv.addObject("current", current);


            //5. 判断cookies
            Cookie[] cs = request.getCookies();
            if (cs != null) {
                //判断当前的cookies列表中是否存在USER字段
                int i = 0;
                for (Cookie cookie : cs) {
                    if (cookie.getName().equals("USER")) {
                        i++;
                    }
                }
                if (i == 0) {
                    Cookie c = new Cookie("USER", UUID.randomUUID().toString());
                    response.addCookie(c);
                }
            } else {
                Cookie c = new Cookie("USER", UUID.randomUUID().toString());
                response.addCookie(c);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return mv;
    }


    @RequestMapping("signin.do")
    public ModelAndView _f_signin(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "signout", required = false) String signout, HttpServletRequest request) {
        String username=this.getUsername();
        System.out.println(">>>>>>>>>>>>>signin.do>>>>>>>"+username);
        UserBean userBean=new UserBean();
        try {
           userBean=userManagementService.getUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "用户名或密码错误!");


            //login form for update page
            //if login error, get the targetUrl from session again.
            String targetUrl = getRememberMeTargetUrlFromSession(request);

            if (StringUtils.hasText(targetUrl)) {
                model.addObject("targetUrl", targetUrl);
                model.addObject("loginUpdate", true);
            }

        }

        if (signout != null) {

            model.addObject("msg", "您已经成功登出系统！");
            //添加成功日志
            try {
                logService.recordLogs(IModular.Modular.SYSTEM, IModular.SubModular.SYSTEM_EXIT
                        , IModular.Action.EXIT, IModular.Result.TRUE,userBean,userBean.toString(), request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.setViewName("f/signin");


        return model;

    }


    @RequestMapping("dashboard.do")
    public String _f_dashboard() {
        return "f/dashboard";
    }


    /**
     * get targetURL from session
     */
    private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
        String targetUrl = "";
        HttpSession session = request.getSession(false);
        if (session != null) {
            targetUrl = session.getAttribute("targetUrl") == null ? ""
                    : session.getAttribute("targetUrl").toString();
        }
        return targetUrl;
    }


    private List<String> getAuthorizedFunctions(String username) {
        List<String> functions = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT t_functions_id from t_role_has_t_user a JOIN t_role_has_t_functions b ON a.t_role_id=b.t_role_id where a.username = ?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                functions.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {

                }
            }
        }

        return functions;
    }

    /**
     * 递归遍历导航文件（JSON结构），判断用户权限并选择显示和不显示部分权限
     *
     * @param tree
     * @param auths
     * @param contextPath
     * @return
     */
    private JSONArray treeList(String username, JSONArray tree, List<String> auths, String contextPath) {
        System.out.println("当前contextPath："+contextPath);
        JSONArray newArray = new JSONArray();
        for (Object obj : tree) {
            JSONObject node = JSONObject.fromObject(obj);

            if (node.containsKey("children")) {
//                System.out.println("has children ------ node > children: "+node.get("children"));
                JSONArray newtree = JSONArray.fromObject(node.get("children"));
                if (newtree.size() != 0) {
                    node.put("children", treeList(username, newtree, auths, contextPath));
                }
            }

            if (node.containsKey("path")) {
                if ("admin".equals(username) || "首页".equals(node.getString("name")) || auths.contains(node.getString("path"))) {
                    node.put("active", true);
                    node.put("path", contextPath + node.getString("path"));
                    System.out.println("拥有权限>>>>:" + node.getString("name"));
                    System.out.println("当前路径：" + node.getString("path"));
                    newArray.add(node);
                }else if(node.getJSONArray("children").size() > 0) {
                    newArray.add(node);
                }else{
                    System.out.println("无权限>>>>:"+node.getString("name"));
                }
            }

        }
        return newArray;

    }


}
