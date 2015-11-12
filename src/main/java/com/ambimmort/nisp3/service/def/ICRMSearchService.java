package com.ambimmort.nisp3.service.def;

import net.sf.json.JSONArray;

/**
 * CRM数据查询模块
 *
 * Created by ShiYun on 2015/6/26 0026.
 */
public interface ICRMSearchService {

    public JSONArray getCRMs() throws Exception;

    public JSONArray getCRMsByPage(String condition, int start, int pageSize) throws Exception;

}
