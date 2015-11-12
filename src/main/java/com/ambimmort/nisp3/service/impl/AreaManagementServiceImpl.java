package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.model.ui.f.area.AddDomainBean;
import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.area.EditDomainBean;
import com.ambimmort.nisp3.service.def.IAreaManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

/**
 * Created by hedingwei on 6/9/15.
 */
@Component
public class AreaManagementServiceImpl implements IAreaManagementService {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    List<String> list = new ArrayList<String>();
    @Override
    public List<DomainBean> listAreasByAreaId(String id) throws Exception {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE t_pid<>-1");
            ResultSet rs = sql.executeQuery();
            List<DomainBean> dbs = new ArrayList<>();
            while (rs.next()) {
                DomainBean db = new DomainBean();
                db.setId(rs.getString("id"));
                db.setName(rs.getString("t_name"));
                db.setPid(rs.getString("t_pid"));
                db.setDescription(rs.getString("t_description"));
                db.setCrmCondition(rs.getString("t_crmCondition"));
                db.setiPRange(rs.getString("t_ipareas"));
                db.setPcode(Long.parseLong(rs.getString("t_code")));
                dbs.add(db);
            }
            return dbs;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (connection != null) {
                connection.close();
                System.out.println("excuted!!!! connection closed!");
            }
        }
    }

    @Override
    public List<DomainBean> listAreasByAreaCode(long code) throws Exception {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE t_code BETWEEN " + code + " AND " + this.getNextAreaCode(code));
            System.out.println("SELECT * FROM t_area WHERE t_code BETWEEN " + code + " AND " + this.getNextAreaCode(code));
            ResultSet rs = sql.executeQuery();
            List<DomainBean> dbs = new ArrayList<>();
            while (rs.next()) {
                DomainBean db = new DomainBean();
                db.setId(rs.getString("id"));
                db.setName(rs.getString("t_name"));
                db.setPid(rs.getString("t_pid"));
                db.setDescription(rs.getString("t_description"));
                db.setCrmCondition(rs.getString("t_crmCondition"));
                db.setiPRange(rs.getString("t_ipareas"));
                db.setPcode(Long.parseLong(rs.getString("t_code")));
                dbs.add(db);
            }
            return dbs;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<DomainBean> listParentAreasById(String id) throws Exception {
        List<DomainBean> dbs = new ArrayList<>();
        while (!id.equals("-1")){
            DomainBean db = this.getArea(id);
            id = db.getPid();
            dbs.add(db);
        }
        return dbs;
    }

    @Override
    public void addArea(AddDomainBean adb) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            long code = this.getAreaCode(adb.getPcode());
            //System.out.println(code);
            if (code != 0){
                if (!hasSameName(adb.getPid(),adb.getName())){
//                    String id = adb.getPid()+"_"+UUID.randomUUID().toString();
                    String id = Long.toString(code);
                    PreparedStatement sql = connection.prepareStatement(" INSERT INTO t_area (id,t_name,t_pid,t_crmColumn,t_crmCondition,t_description,t_ipareas,t_code) VALUES (?,?,?,?,?,?,?,?)");
                    sql.setObject(1,id);
                    sql.setObject(2,adb.getName());
                    sql.setObject(3,adb.getPid());
                    sql.setObject(4,adb.getCrmColumn());
                    sql.setObject(5,adb.getCrmCondition());
                    sql.setObject(6,adb.getDescription());
                    sql.setObject(7,adb.getiPRange());
                    sql.setObject(8,code);
                    sql.execute();
                }else{
                    throw new Exception("该区域下有同名区域！");
                }
            }else{
                throw new Exception("该区域下只支持99个子区域");
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

    //@Override
    public void editArea(EditDomainBean edb, String name) throws Exception {
        Connection connection = null;
        try {
            if (!hasSameName(edb.getPid(), edb.getName())||edb.getName().equals(name)){
                connection = dataSource.getConnection();
                PreparedStatement sql = connection.prepareStatement(" UPDATE t_area SET t_name='"+edb.getName()+"',t_crmCondition='"+edb.getCrmCondition()
                        +"',t_crmColumn='"+edb.getCrmColumn()+"',t_description='"+edb.getDescription()+"',t_ipareas='"+edb.getiPRange()+"' WHERE id='"+edb.getId()+"'");
                sql.executeUpdate();
            }else{
                throw new Exception("该区域下有同名区域！");
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
    public void deleteArea(String id) throws Exception {
        Connection connection = null;
        try {
            if (!hasChild(id)){
                if(this.hasUser(id)){

                    throw new Exception("删除失败，该区域已经绑定用户！");

                }else {
                    connection = dataSource.getConnection();
                    PreparedStatement sql = connection.prepareStatement("DELETE FROM t_area WHERE id='" + id + "'");
                    sql.execute();
                }
            } else {
                throw new Exception("该区域下有子区域,请先将子区域删除后删除此区域！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("deleteArea:connection closed");
                connection.close();
            }
        }
    }

    @Override
    public EditDomainBean getEditBean(String id) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE id='" + id + "'");
            ResultSet rs = sql.executeQuery();
            EditDomainBean edb = new EditDomainBean();
            while (rs.next()) {
                edb.setId(rs.getString("id"));
                edb.setName(rs.getString("t_name"));
                edb.setPid(rs.getString("t_pid"));
                edb.setDescription(rs.getString("t_description"));
                edb.setCrmCondition(rs.getString("t_crmCondition"));
                edb.setiPRange(rs.getString("t_ipareas"));
                edb.setCrmColumn(rs.getString("t_crmColumn"));
            }
            return edb;
        } catch (SQLException e) {
            e.printStackTrace();
            return new EditDomainBean();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public DomainBean getAreaByname(String name) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE t_name='"+name+"'");
            ResultSet rs = sql.executeQuery();
            DomainBean db = new DomainBean();
            while (rs.next()) {
                db.setId(rs.getString("id"));
                db.setName(rs.getString("t_name"));
                db.setPid(rs.getString("t_pid"));
                db.setDescription(rs.getString("t_description"));
                db.setCrmCondition(rs.getString("t_crmCondition"));
                db.setiPRange(rs.getString("t_ipareas"));
            }
            return db;
        } catch (SQLException e) {
            e.printStackTrace();
            return new DomainBean();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean hasChild(String id) throws Exception {
        Connection connection = dataSource.getConnection();
        try{
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE t_pid='"+id+"'");
            ResultSet rs = sql.executeQuery();
            return rs.next();
        }catch (Exception throwable){
            throw throwable;
        }finally {
            if(connection!=null){
                connection.close();
            }
        }

    }

    @Override
    public long getAreaCode(long pcode) throws Exception {
        int index = 0;
        long Max = 0;
        List<Long> list = this.getAllCodes();

        while (pcode%100==0){
            pcode/=100;
            index++;
        }

        if(index == 0){
            return 0;
        }

        System.out.println("my code is: "+pcode);

        long[] v = new long[100];
        for (int i = 0; i < list.size(); i++) {
            double tmp = list.get(i)%pow(100,index-1);
            if (tmp == 0) {
                if (list.get(i)-pcode*pow(100,index)<pow(100,index)&&list.get(i)-pcode*pow(100,index)>0){
                    int ind =(int)(list.get(i)/(pow(100,index-1))%100);
                    v[ind]=list.get(i);
                }
            }
        }
        for (int i = 1; i < 100; i++) {
            if (v[i] == 0) {
                return (long)((pcode*100+i)*pow(100,index-1));
            }
        }
        return 0;
    }

    @Override
    public List<Long> getAllCodes() throws Exception {
        Connection connection = null;
        List<Long> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT t_code FROM t_area");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                list.add((long)rs.getInt("t_code"));
                System.out.println(rs.getInt("t_code"));
            }
            return list;
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
    public long getNextAreaCode(long code) throws Exception {

        int index = 0;
        while (code%100==0){
            code/=100;
            index++;
        }
        code++;
        code*=pow(100,index);
        return code;
    }

    @Override
    public List<DomainBean> getRelationArea(String id) throws Exception {
        DomainBean db = this.getArea(id);
        List<DomainBean> list = this.listParentAreasById(id);
        List<DomainBean> temp = this.listAreasByAreaCode(db.getPcode());
        for (DomainBean t : temp){
            if(!list.contains(list)){
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public boolean isRelated(long c1, long c2) {
        long tmp1 = min(c1, c2);
        int index = 0;
        while (tmp1%100==0){
            tmp1/=100;
            index++;
        }
        long tmp2 = max(c1,c2);
        tmp2/=pow(100,index);
        if (tmp2==tmp1)  {
            return true;
        }
        return false;
    }

    @Override
    /**
     * pid:表单中的pid
     * name:表单中的name
     */
    public boolean hasSameName(String pid, String name) throws Exception {
        Connection connection = dataSource.getConnection();
        try{
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE t_pid='"+pid+"' AND t_name='"+name+"'");
            ResultSet rs = sql.executeQuery();
            return rs.next();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            return false;
        }finally {
            if(connection!=null){
                connection.close();
            }
        }

    }


    public List<String> getPname(String pid) throws Exception {
        Connection connection = dataSource.getConnection();
        try{
            String pname="";
            String ppid="";
            PreparedStatement sql = connection.prepareStatement("SELECT t_name,t_pid FROM t_area WHERE id='"+pid+"'");
            ResultSet rs = sql.executeQuery();
            while(rs.next()){
                pname=rs.getString(1);
                ppid=rs.getString(2);
                list.add(pname);
                this.getPname(ppid);
            }
            return list;
        }catch (Throwable throwable){
            throwable.printStackTrace();
            return new ArrayList<>();
        }finally {
            if(connection!=null){
                connection.close();
            }
        }

    }




    public Boolean hasUser(String areaId) throws Exception {

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("select t_area_id from t_user where t_area_id='"+areaId+"'");
            ResultSet rs = sql.executeQuery();

            return  rs.next();
        }catch (Exception e){
            e.printStackTrace();
            return false;

        }finally {
            if(connection!=null){
                connection.close();
            }
        }

    }

    public DomainBean getArea(String id) {
        Connection connection = null;
        DomainBean db = new DomainBean();
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_area WHERE id='" + id + "'");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                db.setId(rs.getString("id"));
                db.setName(rs.getString("t_name"));
                db.setPid(rs.getString("t_pid"));
                db.setDescription(rs.getString("t_description"));
                db.setCrmCondition(rs.getString("t_crmCondition"));
                db.setiPRange(rs.getString("t_ipareas"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new DomainBean();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("getArea:connection closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return db;

    }
}
