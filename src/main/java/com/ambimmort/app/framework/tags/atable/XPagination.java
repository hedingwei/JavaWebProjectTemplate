package com.ambimmort.app.framework.tags.atable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hedingwei on 6/1/15.
 */
public class XPagination<T> {

    private DataSource dataSource;

    private IDataWrapper<T> wrapper;

    private String searchSql;
    private int totalRecordSql;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public IDataWrapper<T> getWrapper() {
        return wrapper;
    }

    public void setWrapper(IDataWrapper<T> wrapper) {
        this.wrapper = wrapper;
    }

    public String getSearchSql() {
        return searchSql;
    }

    public void setSearchSql(String searchSql) {
        this.searchSql = searchSql;
    }

    public int getTotalRecordSql() {
        return totalRecordSql;
    }

    public void setTotalRecordSql(int totalRecordSql) {
        this.totalRecordSql = totalRecordSql;
    }

    public List<T> getDatas(int pageNumber, int pageLength){

        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(searchSql+" LIMIT "+((pageNumber-1)*pageLength+1)+" "+pageLength);

            ResultSet rs = ps.executeQuery();



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

        return null;
    }
}
