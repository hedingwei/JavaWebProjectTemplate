package com.ambimmort.nisp3.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/24.
 */
@Service
public class UserService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public List<Map> list(){
        Map userMap= new HashMap();
        List<Map> userList=sqlSessionTemplate.selectList("userList",userMap);
        return userList;
    }
}
