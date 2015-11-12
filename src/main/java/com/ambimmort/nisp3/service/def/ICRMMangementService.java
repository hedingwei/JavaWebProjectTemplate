package com.ambimmort.nisp3.service.def;

import com.ambimmort.nisp3.model.ui.f.CRM.AddCRMImportBean;
import com.ambimmort.nisp3.model.ui.f.CRM.CRMColumnBean;
import com.ambimmort.nisp3.model.ui.f.CRM.CRMSearchBean;

import java.util.List;

/**
 * Created by ZZZZ on 2015/6/19.
 */
public interface ICRMMangementService {
    /**
     * 创建一个新的CRM结构信息
     * @param model
     * @throws Exception
     */
    public void addCrmColumn(CRMColumnBean model) throws Exception;

    /**
     * 更新CRM结构信息
     * @param model
     * @throws Exception
     */
    public void updateCrmColumn(CRMColumnBean model, String field) throws Exception;

    /**
     * 判断字段是否存在于数据库中
     * @param field
     * @return true：存在于数据库 false: 不存在于数据库
     * @throws Exception
     */
    public boolean existField(String field) throws Exception;

    /**
     * 判断字段名是否存在于数据库中
     * @param name
     * @return true：存在于数据库 false: 不存在于数据库
     * @throws Exception
     */
    public boolean existName(String name) throws Exception;



    /**
     * 删除指定 CRM结构的字段,
     * 删除该账号时，不删除该账号所关联的业务数据以及操作日志
     * @param id
     * @throws Exception
     */
    public void deleteCrmColumn(String id) throws Exception;


    /**
     * 取得的所有CRM结构信息，分页显示
     * @param pageSize 显示条数
     * @param currentPage 当前页数
     * @return ShowList<CRMColumBean>
     * @throws Exception
     */
    public List<CRMColumnBean> listCrmColumn(String pageSize, String currentPage) throws Exception;

    /**
     * CRM 信息查询
     * @param ，已逗号分隔 condition查询条件
     * @param currentPage 当前页数
     * @param pageSize 显示条数
     * @return ShowList<CRMSearchBean>
     * @throws Exception
     */
    public List<CRMSearchBean> listCrmSearch(String condition, String pageSize, String currentPage) throws Exception;



    /**
     * CRM 结构列值设置
     * @return ShowList<AddCRMImportBean>
     * @throws Exception
     */
    public void addCrmColumnConfig(AddCRMImportBean model) throws Exception;


    public CRMColumnBean edit(String id) throws Exception;

    public String field(String id) throws Exception;

    public String name(String id) throws Exception;

    public CRMColumnBean getBeanById(String id);

    public int isUseOrNot(String id);

    public List<CRMColumnBean> list() throws Exception;

    public List<String> getFields();


}
