package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.model.ui.f.area.AreaBean;
import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.user.AddUserBean;
import com.ambimmort.nisp3.model.ui.f.user.EditUserBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;
import com.ambimmort.nisp3.service.def.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class UserManagementServiceImpl implements IUserManagementService {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AreaManagementServiceImpl areaManagementService;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(AddUserBean model) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement(" INSERT INTO t_user (username,password,name,telephone,email,department,enabled,authenticateIPs,comment,t_area_id) VALUES (?,?,?,?,?,?,?,?,?,?)");
            sql.setObject(1, model.getUsername());
            sql.setObject(2, model.getPassword());
            sql.setObject(3, model.getName());
            sql.setObject(4, model.getTelephone());
            sql.setObject(5, model.getEmail());
            sql.setObject(6, model.getDepartment());
            if (model.getEnabled()) {
                sql.setObject(7, 1);
            } else {
                sql.setObject(7, 0);
            }
            sql.setObject(8, model.getAuthenticateIPs());
            sql.setObject(9, model.getComment());
            sql.setObject(10, model.getAreaId());
            sql.executeUpdate();
            PreparedStatement sql1 = connection.prepareStatement("INSERT INTO t_role_has_t_user (t_role_id,username) VALUES (?,?)");
            sql1.setObject(1, Integer.parseInt(model.getRoleId()));
            sql1.setObject(2, model.getUsername());
            sql1.executeUpdate();
        } catch (SQLException e) {
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
    public void updateUser(EditUserBean model) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql1 = connection.prepareStatement("update t_user  set username='" + model.getUsername() + "'," +
                    "name= '" + model.getName() + "'," +
                    "telephone= '" + model.getTelephone() + "',email= '" + model.getEmail() + "',department= '" + model.getDepartment() + "'," +
                    "enabled= " + model.isStatus() + ",authenticateIPs= '" + model.getAuthenticateIP() + "',comment= '" + model.getComment() + "'," +
                    "t_area_id= '" + model.getAreaId() + "'  where username= '" + model.getUsername() + "'");
            int count1 = sql1.executeUpdate();
            PreparedStatement sql2 = connection.prepareStatement("update t_role_has_t_user  set t_role_id= " + Integer.parseInt(model.getRoleId()) +
                    " where username='" + model.getUsername() + "'");
            int count2 = sql2.executeUpdate();
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<UserBean> listUsers(String areaId) throws Exception {
        Connection connection = null;
        List<UserBean> list = null;
        try {
            connection = dataSource.getConnection();
            long code=Long.parseLong(areaId);
            long nextcode=areaManagementService.getNextAreaCode(code);

            PreparedStatement sql = connection.prepareStatement("select t_user.username username,t_user.name name, t_role.name rolename,t_area.t_name areaname,department,email,enabled ,t_area.id areaid ,t_role_has_t_user.t_role_id roleid from t_user INNER JOIN t_role_has_t_user " +
                    " ON t_user.username = t_role_has_t_user.username INNER JOIN t_role " +
                    " ON t_role_has_t_user.t_role_id=t_role.id INNER JOIN t_area " +
                    " ON t_user.t_area_id=t_area.id WHERE t_user.username<>'admin'and t_user.t_area_id BETWEEN "+code+" AND "+nextcode);
            ResultSet rs = sql.executeQuery();
            list = new ArrayList<UserBean>();
            while (rs.next()) {
                UserBean userBean = new UserBean();
                userBean.setUsername(rs.getString("username"));
                userBean.setName(rs.getString("name"));
                userBean.setAreaName(rs.getString("areaname"));
                userBean.setRoleName(rs.getString("rolename"));
                userBean.setDepartment(rs.getString("department"));
                userBean.setEmail(rs.getString("email"));
                userBean.setStatus(rs.getBoolean("enabled"));
                userBean.setAreaId(rs.getString("areaid"));
                userBean.setRoleId(rs.getString("roleid"));
                list.add(userBean);
            }
            return list;

        } catch (SQLException e) {
            if (connection != null) {
                connection.close();
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public List<String> getChildAreaIds(Connection connection, String areaid) {
        List<String> list = new ArrayList<String>();
        list.add(areaid);
        try {
            PreparedStatement sql = connection.prepareStatement("select id from t_area where t_pid=?");
            sql.setString(1, areaid);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                list.addAll(this.getChildAreaIds(connection, id));
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
    }


    @Override
    public void deleteUser(String username) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("delete from  t_user where t_user.username=?");
            sql.setString(1, username);
            sql.executeUpdate();
            PreparedStatement sql2 = connection.prepareStatement("delete from t_role_has_t_user where t_role_has_t_user.username=?");
            sql2.setString(1, username);
            sql2.executeUpdate();
        } catch (SQLException e) {
            if (connection != null) {
                connection.close();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void updatePassword(String username, String password) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println("username==:" + username + "=====password:" + password);
            PreparedStatement sql = connection.prepareStatement("update t_user set password='" + password + "' where username='" + username + "'");
            int count = sql.executeUpdate();
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void setStatus(String username, boolean flag) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("UPDATE t_user set enabled=? WHERE username=?");
            sql.setBoolean(1, flag);
            sql.setString(2, username);
            int count = sql.executeUpdate();
            if (count == 1) {
                System.out.println("shezhichenggong");
            } else {
                System.out.println("设置失败");
            }
            connection.close();
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<DomainBean> getBelongsArea(String username) throws Exception {
        return null;
    }

    @Override
    public boolean hasAuth(String username, String areaId) throws Exception {
        System.out.println("username====" + username + "======areaId" + areaId);
        String userareaid=this.getUser(username).getAreaId();
        long aId=Long.parseLong(areaId);
        long code=Long.parseLong(userareaid);
        long nextcode=areaManagementService.getNextAreaCode(code);
        if(aId >= code && aId < nextcode){
            return true;

        }else {
            return false;
        }
    
    }


    @Override
    public boolean existUser(String username) throws Exception {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement(" SELECT username from t_user where username='" + username + "'");
            ResultSet rs = sql.executeQuery();
            flag = rs.next();
            return flag;
        } catch (SQLException e) {
            if (connection != null) {
                connection.close();
            }
            return flag;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    @Override
    public UserBean getUser(String username) throws Exception {
        Connection connection = null;
        UserBean userBean = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("select  t_user.authenticateIPs authenticateIPs,t_user.password password,t_user.username username,t_user.name name, t_role.name rolename,t_area.t_name areaname,telephone,department,comment,email,enabled,t_area.id areaid,t_role.id roleid from t_user INNER JOIN t_role_has_t_user" +
                    " ON t_user.username = t_role_has_t_user.username INNER JOIN t_role " +
                    " ON t_role_has_t_user.t_role_id=t_role.id INNER JOIN t_area " +
                    " ON t_user.t_area_id=t_area.id where t_user.username='" + username + "'");
            ResultSet rs = sql.executeQuery();
            userBean = new UserBean();
            while (rs.next()) {
                userBean.setPassword(rs.getString("password"));
                //System.out.println(">>>>>>>>>>>>>>>" + rs.getString("password"));
                userBean.setUsername(rs.getString("username"));
                userBean.setName(rs.getString("name"));
                userBean.setAreaName(rs.getString("areaname"));
                userBean.setRoleName(rs.getString("rolename"));
                userBean.setDepartment(rs.getString("department"));
                userBean.setEmail(rs.getString("email"));
                userBean.setStatus(rs.getBoolean("enabled"));
                userBean.setAreaId(rs.getString("areaid"));
                userBean.setRoleId(rs.getString("roleid"));
                userBean.setComment(rs.getString("comment"));
                userBean.setTelephone(rs.getString("telephone"));
                userBean.setStatus(rs.getBoolean("enabled"));
                userBean.setAuthenticateIP(rs.getString("authenticateIPs"));
            }
            return userBean;
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
            return userBean;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<RoleBean> listRoles() throws Exception {
        Connection connection = null;
        List<RoleBean> lt = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT name,id FROM t_role ");
            ResultSet rs = sql.executeQuery();
            lt = new ArrayList<>();
            while (rs.next()) {
                RoleBean roleBean = new RoleBean();
                roleBean.setRoleId(rs.getInt("id"));
                roleBean.setRoleName(rs.getString("name"));
                lt.add(roleBean);
            }
            return lt;
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
            return lt;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public EditUserBean getEditUser(String username) throws Exception {
        List list = new ArrayList();
        EditUserBean formmodel = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT t_user.username username,t_user.`password`,t_user.`name` name,t_user.telephone,\n" +
                    "t_user.email,t_user.department,t_user.enabled,t_user.authenticateIPs ip,t_user.t_area_id areaid,t_user.`comment`,\n" +
                    "t_area.t_name areaname,t_role.`id`roleid from t_user\n" +
                    "INNER JOIN t_role_has_t_user ON t_user.username = t_role_has_t_user.username \n" +
                    "INNER JOIN t_role            ON t_role_has_t_user.t_role_id=t_role.id \n" +
                    "INNER JOIN t_area            ON t_user.t_area_id=t_area.id where t_user.username='" + username + "'");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                formmodel = new EditUserBean();
                formmodel.setUsername(rs.getString("username"));
                formmodel.setName(rs.getString("name"));
                formmodel.setTelephone(rs.getString("telephone"));
                formmodel.setEmail(rs.getString("email"));
                formmodel.setRoleId("" + rs.getInt("roleid"));
                formmodel.setDepartment(rs.getString("department"));
                formmodel.setAuthenticateIP(rs.getString("ip"));
                formmodel.setAreaId(rs.getString("areaid"));
                formmodel.setStatus(rs.getBoolean("enabled"));
                formmodel.setComment(rs.getString("comment"));
            }
            return formmodel;
        } catch (SQLException e) {
            if (connection != null) {
                connection.close();
            }
            return formmodel;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<AreaBean> listAreas(String areaId) throws Exception {
        Connection connection = null;
        List<AreaBean> list = null;
        try {
            connection = dataSource.getConnection();
            long code=Long.parseLong(areaId);
            long nextcode=areaManagementService.getNextAreaCode(code);

            PreparedStatement sql = connection.prepareStatement("select t_area.t_name areaname,t_area.t_pid pid,t_area.id areaid from t_area WHERE  t_area.id BETWEEN "+code+" AND "+nextcode);
            ResultSet rs = sql.executeQuery();
            list = new ArrayList<AreaBean>();
            while (rs.next()) {
                AreaBean areaBean = new AreaBean();
                areaBean.setAreaName(rs.getString("areaname"));
                areaBean.setAreaId(rs.getString("areaid"));
                areaBean.setPid(rs.getString("pid"));
                list.add(areaBean);
            }
            for (AreaBean ab : list) {
                System.out.println(ab.getAreaName() + "==========");
            }
            return list;
        } catch (Exception e) {
            if (connection != null) {
                connection.close();
            }
            return list;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public Boolean hasUser(String areaId) throws Exception {

        Connection connection = null;
        List<AreaBean> list = null;
        Boolean flg = false;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("select * from t_user where t_area_id='" + areaId + "'");
            ResultSet rs = sql.executeQuery();
            if (rs != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }finally {
            connection.close();
        }

    }

    public void updateInfo(UserBean userBean) throws Exception {
        int i = jdbcTemplate.update("update t_user set name='" + userBean.getName() + "'," +
                "telephone='" + userBean.getTelephone() + "'," +
                "email='" + userBean.getEmail() + "'," +
                "department='" + userBean.getDepartment() + "'," +
                "comment='" + userBean.getComment() +
                "' where username='" + userBean.getUsername() + "'");
        if (i != 1) {
            throw new Exception("修改失败");
        }
    }

    public String getPwd(String username) throws SQLException {
        Connection con = null;

        String pwd = null;
        PreparedStatement ps = null;
        try {
            con=dataSource.getConnection();
            String sql="SELECT password FROM t_user WHERE username='" + username+"'";
            System.out.println(">>>>>>>>>>>>>>"+sql);
            ps = con.prepareStatement("SELECT password FROM t_user WHERE username='" + username+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pwd = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            con.close();
        }

        return pwd;

    }
}