package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.model.ui.f.CRM.AddCRMImportBean;
import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import com.ambimmort.nisp3.model.ui.f.CRM.CRMSearchBean;
import com.ambimmort.nisp3.service.def.ICRMMangementService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ZZZZ on 2015/6/19.
 */
@Component
public class CRMMangementServiceImpl implements ICRMMangementService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Override
    public void addCrmColumn(CRMColumnBean model) throws Exception {
        Connection connection = null;
        Connection connection2 = null;
        try {
            String uuid= UUID.randomUUID().toString();
            model.setId(uuid);
            connection = dataSource.getConnection();
            connection2 = dataSource.getConnection();
            connection.setAutoCommit(false);
            connection2.setAutoCommit(false);
            PreparedStatement sql = connection.prepareStatement("INSERT INTO t_crm_column(id,t_field,t_name,t_fieldType,t_length,t_default,t_description)VALUES (?,?,?,?,?,?,?)");
                sql.setObject(1, model.getId());
                sql.setObject(2, model.getField());
                sql.setObject(3, model.getName());
                sql.setObject(4, model.getFieldType());
                sql.setObject(5, model.getLengthValue());
                sql.setObject(6, model.getDefaultValue());
                sql.setObject(7, model.getDescription());
            PreparedStatement sql1=null;
            if(model.getDefaultValue().equals("")){
                sql1=connection2.prepareStatement("ALTER TABLE t_crm ADD "+model.getField()+" "+model.getFieldType()+"("+model.getLengthValue()+")");
            }else {
                sql1 = connection2.prepareStatement("ALTER TABLE t_crm ADD " + model.getField() + " " + model.getFieldType() + "(" + model.getLengthValue() + ")" +" DEFAULT "+model.getDefaultValue());
            }

            sql.executeUpdate();
            sql1.executeUpdate();
            connection.commit();
            connection2.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            connection2.rollback();
            String message="Table 'nisp3.t_crm' doesn't exist";
            if(message.equals(e.getMessage())){
                throw new MySQLSyntaxErrorException("严重错误，数据库表t_crm不存在!");
            }else{
                throw new MySQLSyntaxErrorException("字段不能全为数字 或者 字段长度超过了类型范围 或者 默认值与类型不匹配，请检查！");
            }

        } finally {
            if (connection != null) {
                if(!connection.getAutoCommit()){
                        connection.setAutoCommit(true);
                }
                if(!connection2.getAutoCommit()){
                    connection2.setAutoCommit(true);
                }
                connection.close();
            }
        }

    }

    @Override
    public void updateCrmColumn(CRMColumnBean model,String field) throws Exception {
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement sql = connection.prepareStatement("UPDATE t_crm_column SET t_field='" + model.getField() + "'" + ",t_name='" + model.getName() + "'" + ",t_fieldType='" + model.getFieldType() + "'" + ",t_length='" + model.getLengthValue() + "'" + ",t_default='" + model.getDefaultValue() + "'" + ",t_description='" + model.getDescription() + "'" + "WHERE id='" + model.getId() + "'");
            PreparedStatement sql1=null;
            if(field.equals(model.getField())) {
                if (model.getDefaultValue().equals("")) {
                    sql1 = connection.prepareStatement("ALTER TABLE t_crm MODIFY " + model.getField() + " " + model.getFieldType() + "(" + model.getLengthValue() + ")");
                } else {
                    sql1 = connection.prepareStatement("ALTER TABLE t_crm MODIFY "  + model.getField() + " " + model.getFieldType() + "(" + model.getLengthValue() + ")"+"  "+"DEFAULT"+" "+model.getDefaultValue()+"");
                }
            }
            else{
                    if(model.getDefaultValue().equals("")){
                        sql1=connection.prepareStatement("ALTER TABLE t_crm CHANGE "+field+" "+model.getField()+" "+model.getFieldType()+"("+model.getLengthValue()+")");
                    }else {
                        sql1 = connection.prepareStatement("ALTER TABLE t_crm CHANGE "+field+" "  + model.getField() + " " + model.getFieldType() + "(" + model.getLengthValue() + ")"+" "+"DEFAULT"+" "+model.getDefaultValue());
                    }
                }
            sql1.executeUpdate();
            sql.executeUpdate();
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
            connection.rollback();
            String message="Table 'nisp3.t_crm' doesn't exist";
            if(message.equals(e.getMessage())){
                throw new MySQLSyntaxErrorException("严重错误，数据库表t_crm不存在!");
            }else{
                throw new MySQLSyntaxErrorException("字段不能全为数字 或者 字段长度超过了类型范围 或者 默认值与类型不匹配，请检查！");
            }
        }
        finally {
            if(connection!=null){
                if(connection.getAutoCommit()==false){
                    connection.setAutoCommit(true);
                }
                connection.close();
            }
        }
    }


    @Override
    public boolean existField(String field) throws Exception {
        Connection connection=null;
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql=connection.prepareStatement("SELECT * FROM t_crm_column WHERE t_field='"+field+"'");
            ResultSet rs=sql.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            if(connection!=null){
                connection.rollback();
            }
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }

        return false;
    }

    @Override
    public boolean existName(String name) throws Exception {
        Connection connection=null;
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql= connection.prepareStatement("SELECT * FROM t_crm_column WHERE t_name='"+name+"'");
            ResultSet rs=sql.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            if(connection!=null){
                connection.rollback();
            }
        }
        finally {
            if (connection!=null){
                connection.close();
            }
        }


        return false;
    }

    @Override
    public void deleteCrmColumn(String id) throws Exception {
        Connection connection=null;
        try{
            System.out.println(id);
            connection=dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement sql=connection.prepareStatement("DELETE FROM t_crm_column WHERE t_field='"+id+"'");
            PreparedStatement sql1=connection.prepareStatement("ALTER TABLE t_crm DROP "+id);
            sql1.executeUpdate();
            sql.executeUpdate();
            connection.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(connection!=null){
                connection.rollback();
            }
            String message="Table 'nisp3.t_crm' doesn't exist";
            if(message.equals(e.getMessage())){
                throw new MySQLSyntaxErrorException("删除失败，数据库表t_crm不存在!");
            }

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
    public List<CRMColumnBean> listCrmColumn(String pageSize, String currentPage) throws Exception {
        return null;
    }

    public List<CRMColumnBean> list() throws Exception{
        Connection connection=null;
        List<CRMColumnBean> list=new ArrayList<CRMColumnBean>();
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql=connection.prepareStatement("select * from t_crm_column");
            ResultSet rs=sql.executeQuery();
            while(rs.next()){
                CRMColumnBean crmColumnBean=new CRMColumnBean();
                crmColumnBean.setId(rs.getString("id"));
                crmColumnBean.setField(rs.getString("t_field"));
                crmColumnBean.setName(rs.getString("t_name"));
                crmColumnBean.setFieldType(rs.getString("t_fieldType"));
                crmColumnBean.setLengthValue(rs.getString("t_length"));
                crmColumnBean.setDefaultValue(rs.getString("t_default"));
                crmColumnBean.setDescription(rs.getString("t_description"));
                list.add(crmColumnBean);
            }
            return list;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public List<CRMSearchBean> listCrmSearch(String condition, String pageSize, String currentPage) throws Exception {
        return null;
    }

    @Override
    public void addCrmColumnConfig(AddCRMImportBean model) throws Exception {

    }

    @Override
    public CRMColumnBean edit(String id) throws Exception {
        Connection connection=null;
        CRMColumnBean bean=new CRMColumnBean();
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql=connection.prepareStatement("SELECT * FROM t_crm_column WHERE id='"+id+"'");
            ResultSet rs=sql.executeQuery();
            while (rs.next()){
                bean.setId(rs.getString("id"));
                bean.setField(rs.getString("t_field"));
                bean.setName(rs.getString("t_name"));
                bean.setFieldType(rs.getString("t_fieldType"));
                bean.setLengthValue(rs.getString("t_length"));
                bean.setDefaultValue(rs.getString("t_default"));
                bean.setDescription(rs.getString("t_description"));
            }

        }catch(Exception e){
            e.printStackTrace();
            if(connection!=null){
                connection.rollback();
            }
        }finally {
            if (connection!=null){
                connection.close();
            }
        }

        return bean;
    }

    @Override
    public String field(String id) throws Exception {
        Connection connection=null;
        String field=null;
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql=connection.prepareStatement("SELECT t_field FROM t_crm_column WHERE id='"+id+"'");
            ResultSet rs=sql.executeQuery();
            while (rs.next()){
                field=rs.getString(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            if(connection!=null){
                connection.rollback();
            }
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }
        return field;
    }

    @Override
    public String name(String id) throws Exception {
        Connection connection=null;
        String name=null;
        try{
            connection=dataSource.getConnection();
            PreparedStatement sql=connection.prepareStatement("SELECT t_name FROM t_crm_column WHERE id='"+id+"'");
            ResultSet rs=sql.executeQuery();
            while (rs.next()){
                name=rs.getString(1);
            }

        }catch(Exception e){
            e.printStackTrace();
            if(connection!=null){
                connection.rollback();
            }
        }
        finally {
            if(connection!=null){
                connection.close();
            }
        }
        return name;
    }

    public int isUseOrNot(String id){
        Connection connection = null;
        int i = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT isUse FROM t_crm_column WHERE id='" +id+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                i = rs.getInt(1);
            }
        } catch (SQLException e) {
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
        return i;
    }

    public CRMColumnBean getBeanById(String id){
        Connection connection = null;
        CRMColumnBean crmColumnBean = new CRMColumnBean();
        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT id,t_field,t_name,t_fieldType,t_length,t_default,t_isEmpty,t_unique,t_description FROM t_crm_column WHERE id='"+id+"'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                crmColumnBean.setId(rs.getString(1));
                crmColumnBean.setField(rs.getString(2));
                crmColumnBean.setName(rs.getString(3));
                crmColumnBean.setFieldType(rs.getString(4));
                crmColumnBean.setLengthValue(rs.getString(5));
                crmColumnBean.setDefaultValue(rs.getString(6));
                crmColumnBean.setIsEmpty(rs.getBoolean(7));
                crmColumnBean.setUnique(rs.getBoolean(8));
                crmColumnBean.setDescription(rs.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!= null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  crmColumnBean;
    }

    public List<String> getFields(){
        List list = new ArrayList();
        list.add("areacode");
        return list;
    }

}
