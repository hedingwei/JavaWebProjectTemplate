package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.model.ui.f.role.AddRoleBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.role.EditRoleBean;
import com.ambimmort.nisp3.service.def.IRoleManagementService;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by hedingwei on 6/9/15.
 */
@Component
public class RoleManagementServiceImpl implements IRoleManagementService {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private AreaManagementServiceImpl areaManagementService;

    @Override
    public void addRole(AddRoleBean bean,HttpServletRequest request) throws Exception {
        Connection connection = null;
        try{
            String uname=request.getUserPrincipal().getName();
            String[] selectFunction=bean.getSelectedFunctions().split(",");
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement sql = connection.prepareStatement("INSERT INTO t_role(name,description,role,t_tree,creater) VALUES (?,?,?,?,?) ");
            sql.setObject(1,bean.getName());
            sql.setObject(2,bean.getDescription());
            sql.setObject(3,"USER");
            sql.setObject(4,bean.getNewFunctionsJson());
            sql.setObject(5,uname);
            sql.executeUpdate();
            PreparedStatement sql1=null;
            PreparedStatement sql3=null;
            int roleid=-1;
            sql3=connection.prepareStatement("SELECT id FROM t_role WHERE name='"+bean.getName()+"'");
            ResultSet role=sql3.executeQuery();
            while(role.next()){
                roleid=role.getInt(1);
            }
            for (int i=0;i<selectFunction.length;i++){
                sql1=connection.prepareStatement("INSERT INTO t_role_has_t_functions(t_role_id,t_functions_id) VALUES(?,?) ");
                    sql1.setObject(1,roleid);
                    sql1.setObject(2,selectFunction[i]);
                    sql1.executeUpdate();
                }

           connection.commit();
            }
              catch (Exception e){
                    e.printStackTrace();
                  connection.rollback();
                    throw new Exception("添加出现错误，请等待修复！");
                }finally {
                    if(connection!=null){
                        if(!connection.getAutoCommit()){
                            connection.setAutoCommit(true);
                        }
                        connection.close();
                    }
                }
    }

    @Override
    public void deleteRole(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

                PreparedStatement sql = connection.prepareStatement("DELETE FROM t_role_has_t_functions WHERE t_role_id =" + id);
                sql.executeUpdate();
                PreparedStatement sql1 = connection.prepareStatement("DELETE FROM t_role where id=" + id);
                sql1.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
    }


    @Override
    public void updateRole(EditRoleBean bean) throws Exception {
        Connection connection=null;
        try{
            String[] selectFunction=bean.getSelectedFunctions().split(",");
            connection= dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement upsql = connection.prepareStatement("UPDATE t_role SET name='"+bean.getName()+"'"+",description='"+bean.getDescription()+"'"+",t_tree='"+bean.getNewFunctionsJson()+"'"+" WHERE id ="+bean.getId());
            upsql.executeUpdate();
            PreparedStatement sql = connection.prepareStatement("DELETE FROM t_role_has_t_functions WHERE t_role_id ="+bean.getId());
            sql.executeUpdate();
            PreparedStatement sql2=null;
            for (int i=0;i<selectFunction.length;i++) {
                    sql2 = connection.prepareStatement("INSERT INTO t_role_has_t_functions(t_role_id,t_functions_id) VALUES(?,?) ");
                    sql2.setObject(1, bean.getId());
                    sql2.setObject(2, selectFunction[i]);
                    sql2.executeUpdate();
            }

            connection.commit();

        }
        catch(Exception e){
            e.printStackTrace();
            connection.rollback();
            throw new Exception("编辑出现错误，请等待修复！");
        }
        finally {
            if(connection!=null){
                if(!connection.getAutoCommit()){
                    connection.setAutoCommit(true);
                }
                connection.close();
            }
        }
    }

    @Override
    public List<RoleBean> listRoles(String uname) throws Exception {
        Connection connection = null;
        List<RoleBean> list =new ArrayList();
        RoleBean r=null;
        try{
            connection=dataSource.getConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT t_role_id FROM t_role_has_t_user WHERE username = 'admin'");
//            ResultSet rs1 = ps.executeQuery();
//            int id=-1;
//            while(rs1.next()){
//                id = rs1.getInt(1);
//            }
//            if(id==-1){
//                return list;
//            }
            PreparedStatement sql=connection.prepareStatement("SELECT  id,name,description,creater FROM t_role WHERE id>="+0);
            ResultSet rs=sql.executeQuery();
            while(rs.next()){
                r =new RoleBean();
                r.setId(rs.getInt(1));
                r.setName(rs.getString(2));
                r.setDescription(rs.getString(3));
                r.setCreater(rs.getString(4));
                r.setUsername(uname);
                r.setT_code(this.getAreacode(uname));
                list.add(r);
            }
        }catch (Exception e){
            e.printStackTrace();
            connection.close();

        }finally {
            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }

    @Override
    public RoleBean getRole(int roleId) throws Exception {
        Connection connection=null;
        RoleBean bean=new RoleBean();
        String functions="";
        String functionsNames="";
        List list=new ArrayList();
        List functionNamesList=new ArrayList();
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql=connection.prepareStatement("SELECT t_functions_id,t_role.name,t_role.description,t_role.id FROM t_role INNER JOIN t_role_has_t_functions ON t_role.id=t_role_has_t_functions.t_role_id WHERE t_role.id="+roleId);
            ResultSet rs=sql.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
                bean.setName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setId(rs.getInt(4));
            }
            PreparedStatement sql2=connection.prepareStatement("SELECT t_functions_id,t_role.name,t_role.description,t_role.id FROM t_role INNER JOIN t_role_has_t_functions ON t_role.id=t_role_has_t_functions.t_role_id WHERE t_role.id="+roleId);
            ResultSet rs2=sql2.executeQuery();
            while(rs2.next()){
                functionNamesList.add(rs2.getString(1));

            }

            for (int i=0;i<list.size();i++){
                if (i!=list.size()-1){
                    functions+=list.get(i)+",";

                }else {
                    functions +=list.get(i);
                }
            }

            for (int i=0;i< functionNamesList.size();i++){

                if (i!= functionNamesList.size()-1){
                    functionsNames+= functionNamesList.get(i)+",";

                }else {
                    functionsNames += functionNamesList.get(i);
                }
            }
            bean.setSelectedFunctions(functions);
            bean.setSelectedFunctionNames(functionsNames);

        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }
        return bean;
    }
     public int  getRoleId(String roleName) throws SQLException {
         Connection connection=null;
        int roleid=0;
         try {
             connection=dataSource.getConnection();

             PreparedStatement ps=connection.prepareStatement("SELECT id FROM t_role WHERE name='"+roleName+"'");
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                 roleid=rs.getInt("id");

             }
             return roleid;
         } catch (SQLException e) {
             e.printStackTrace();
             return roleid;
         }finally {
             if(connection!=null){
                 connection.close();

             }
         }

     }



    public JSONArray getAllFunctionsView(HttpServletRequest request,int roleid) throws Exception {
        String uname=request.getUserPrincipal().getName();
        String s=null;
        if(this.getAreacode(uname).equals("100000000")){
            s= FileUtils.readFileToString(new File(request.getServletContext().getRealPath("/resources/assets/data/functions.js")), "utf-8");
        }else {
            if(this.getCreater(uname,roleid)){
                s=this.getUserFunction(uname);
            }else{
                s= FileUtils.readFileToString(new File(request.getServletContext().getRealPath("/resources/assets/data/functions.js")), "utf-8");
            }

        }
        JSONArray array = JSONArray.fromObject(s);
        return array;

    }
    @Override
    public JSONArray getAllFunctions(HttpServletRequest request) throws Exception {
        String uname=request.getUserPrincipal().getName();
        String s=null;
        if(this.getAreacode(uname).equals("100000000")){
            s= FileUtils.readFileToString(new File(request.getServletContext().getRealPath("/resources/assets/data/functions.js")), "utf-8");
        }else {
                s=this.getUserFunction(uname);
        }
        JSONArray array = JSONArray.fromObject(s);
        return array;

    }

    public String getAreacode(String uname) throws SQLException {
        Connection connection=null;
        String t_code=null;
        String sql="SELECT area.t_code FROM t_area area JOIN t_user u ON area.id=u.t_area_id WHERE u.username='"+uname+"' ";
        try{

            connection=dataSource.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                t_code=rs.getString(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.close();
                connection.close();
            }
        }
        return t_code;
    }
    public String getUserFunction(String uname) throws SQLException {
        Connection connection=null;
        String json=null;
        String sql="SELECT role.t_tree FROM t_role role JOIN t_role_has_t_user ru on role.id=ru.t_role_id WHERE ru.username='"+uname+"' ";
        try{

            connection=dataSource.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                json=rs.getString(1);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }
        return json;
    }

    @Override
    public boolean existRole(String name) throws Exception {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT name FROM t_role WHERE name ='"+name+"'");
            ResultSet rs = sql.executeQuery();
            if(rs.next()){

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
        return true;
    }
    public boolean getCreater(String uname,int id) throws SQLException {
        Connection connection = null;
        try{
            connection=dataSource.getConnection();
            String sql = "SELECT  creater FROM  t_role WHERE id="+id;
            String name=null;
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                name=rs.getString(1);
            }
            if(name.equals(uname)){
                return true;
            }
            else{
                return  false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }





////        String dbcode=null;
////        String ucode=null;
//        try {
//
//            connection = dataSource.getConnection();
//            PreparedStatement sql = connection.prepareStatement("SELECT area.t_code FROM t_role role JOIN t_user u ON role.creater=u.username" +
//                    " JOIN t_area area ON u.t_area_id=area.id WHERE role.id="+id);
//            ResultSet rs = sql.executeQuery();
//            while(rs.next()){
//                dbcode=rs.getString(1);
//            }
//            long code = Long.parseLong(dbcode);
//
//            PreparedStatement sql1=connection.prepareStatement("SELECT area.t_code FROM t_area area JOIN t_user u ON area.id=u.t_area_id WHERE username='"+uname+"'");
//            ResultSet rs1 = sql1.executeQuery();
//            while(rs1.next()){
//                ucode=rs1.getString(1);
//            }
//            long uucode=Long.parseLong(ucode);
//            long nextcode=areaManagementService.getNextAreaCode(uucode);
//            if(code<=nextcode &&code>=uucode){
//                return true;
//            }
//            else{
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(connection!=null){
//                connection.close();
//            }
//        }
        return false;
    }


    @Override
    public String getRoleName(int roleId) throws Exception {
        Connection connection = null;
        String name = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT name FROM t_role WHERE id ="+roleId);
            ResultSet rs = sql.executeQuery();
            while(rs.next()){
                name = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
        return name;
    }
    @Override
    public boolean getRoleName(String  name) throws Exception {
        Connection connection = null;
        boolean b=false;

        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT name FROM t_role WHERE name ='"+name+"'");
            ResultSet rs = sql.executeQuery();
            if(rs.next()){
                b=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.close();
            }
        }
        return b;
    }

    public int isTied(int id){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT username FROM t_role_has_t_user WHERE t_role_id=" + id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
