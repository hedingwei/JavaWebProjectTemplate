package com.ambimmort.nisp3.service.impl;

import com.ambimmort.nisp3.service.def.ICRMSearchService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ShiYun on 2015/6/26 0026.
 */
@Component
public class CRMSearchService implements ICRMSearchService {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Override
    public JSONArray getCRMs() throws Exception {
        Connection connection = null;
        Map<Integer, String>  map = getCRM();
        JSONArray jsonArray = new JSONArray();
        if (!map.isEmpty()) {
            try {
                connection = dataSource.getConnection();
                PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_crm");
                ResultSet rs = sql.executeQuery();
                while (rs.next()) {
                    JSONArray array = new JSONArray();
                    for(Integer i:map.keySet()){
                        String field = map.get(i);
                        Object one = rs.getObject(field);
                        array.add(one);
                    }
                    jsonArray.add(array);
                }
            } catch (Exception e) {
                e.printStackTrace();
                connection.close();
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return jsonArray;
    }

    public Map getCRM() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT  column_name FROM Information_schema.columns  WHERE table_Name = 't_crm';");
            ResultSet rs = sql.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                String field = rs.getString(1);
                map.put(i, field);
            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return map;
    }

    @Override
    public JSONArray getCRMsByPage(String condition, int start, int pageSize) throws Exception {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM t_crm limit " + start + "," + pageSize);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            connection.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
}
