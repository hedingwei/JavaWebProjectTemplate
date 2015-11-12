package com.ambimmort.nisp3.controller.f.crm.search.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hedingwei on 7/9/15.
 */
@Controller("/f/lttask/manager/crm.json.do")
public class CRMQuery {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping("/f/lttask/manager/crm.json.do")
    @ResponseBody
    public JSONObject action_crm(HttpServletRequest request) throws UnsupportedEncodingException {

        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String querySQL = request.getParameter("_query_sql");

        String queryCondition = new String(Base64.decodeBase64(querySQL),"utf-8");

        JSONObject returnData = new JSONObject();
        returnData.put("draw", request.getParameter("draw"));


        HashMap params = new HashMap();
        params.put("username", request.getUserPrincipal().getName());

        Map userAreaCodeObj = (Map) sqlSessionTemplate.selectList("getUserAreaCode",params).get(0);

        long areaCode = (long)userAreaCodeObj.get("code");


        try{
            long totalCount = 0;
            if(queryCondition.trim().equals("")){

                HashMap condition = new HashMap();
                condition.put("userAreaId",areaCode);
                condition.put("condition", "1=1");


                Map map = (Map) sqlSessionTemplate.selectList("sd_crm_conditional_count",condition).get(0);
                totalCount = (long) map.get("count");

            }else{
                HashMap condition = new HashMap();
                condition.put("userAreaId",areaCode);
                condition.put("condition", ""+queryCondition);
                Map map = (Map) sqlSessionTemplate.selectList("sd_crm_conditional_count",condition).get(0);
                totalCount = (long) map.get("count");
            }
            returnData.put("recordsTotal", totalCount);//实际的行数
            returnData.put("recordsFiltered", totalCount);

            if(queryCondition.trim().equals("")){
                HashMap condition = new HashMap();
                condition.put("condition", " 1=1");
                condition.put("userAreaId",areaCode);
                condition.put("start",Integer.parseInt(start));
                condition.put("length",Integer.parseInt(length));
                List list = (List) sqlSessionTemplate.selectList("sd_crm_conditional_select",condition);
                returnData.put("data", JSONArray.fromObject(list));
            }else{
                HashMap condition = new HashMap();
                condition.put("condition",""+queryCondition);
                condition.put("userAreaId",areaCode);
                condition.put("start",Integer.parseInt(start));
                condition.put("length",Integer.parseInt(length));
                List list = (List) sqlSessionTemplate.selectList("sd_crm_conditional_select",condition);
                returnData.put("data", JSONArray.fromObject(list));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return returnData;


    }
}
