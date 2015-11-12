package com.ambimmort.nisp3.service.def;

import com.ambimmort.nisp3.model.ui.f.area.AddDomainBean;
import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.area.EditDomainBean;

import java.util.List;

/**
 * Created by hedingwei on 6/9/15.
 */
public interface IAreaManagementService {

    /**
     * 返回当前区域和当前区域下的所有子区域信息
     * 全局区域ID默认为0
     * @return  ShowList<AreaBean>
     * @throws Exception
     */
    public List<DomainBean> listAreasByAreaId(String id) throws Exception;

    /**
     * 返回当前区域和当前区域下的所有子区域信息
     * 全局区域CODE默认为 100000000
     * @param code
     * @return  ShowList<AreaBean>
     * @throws Exception
     */
    public List<DomainBean> listAreasByAreaCode(long code) throws Exception;


    /**
     * 返回当前节点的所有父节点
     * @param id
     * @return
     * @throws Exception
     */
    public List<DomainBean> listParentAreasById(String id) throws Exception;



    /**
     * 添加区域信息
     * 确保同一父区域下创建的区域名不相同
     * @throws Exception
     */
    public void addArea(AddDomainBean adb) throws Exception;

    /**
     * 修改区域信息
     * 确保同一父区域下创建的区域名不相同
     * @param edb
     * @throws Exception
     */
    //public void editArea(EditDomainBean edb,String name) throws Exception;


    /**
     * 删除指定区域
     * 该区域下有子区域或者有已绑定系统用户时，均不能删除
     * @param id
     * @throws Exception
     */
    public void deleteArea(String id) throws Exception;

    /**
     * 返回指定区域的信息
     * @param id
     * @throws Exception
     */
    public EditDomainBean getEditBean(String id) throws Exception;

    /**
     * 判断在同一区域下是否存在同名区域
     * @param pid
     * @param name
     * @return boolean  true:有同名； false:无同名
     * @throws Exception
     */
    public boolean hasSameName(String pid, String name) throws Exception;


    /**
     * 判断指定区域下是否有子区域
     * @param id
     * @return boolean true:有子区域; false:无子区域
     * @throws Exception
     */
    public boolean hasChild(String id) throws Exception;

    /**
     * 根据父节点的编码生成子节点编码
     * @param pcode
     * @return
     * @throws Exception
     */
    public long getAreaCode(long pcode) throws Exception;

    /**
     * 获取所有的区域编码
     * @return
     * @throws Exception
     */
    public List<Long> getAllCodes() throws Exception;

    /**
     * 获取当前节点的下一位编码(同级)
     * @param code
     * @return
     * @throws Exception
     */
    public long getNextAreaCode(long code) throws Exception;


    /**
     * 获取当前节点的相关节点
     * @param id
     * @return
     * @throws Exception
     */
    public List<DomainBean> getRelationArea(String id) throws Exception;

    /**
     * 判断两个区域是否关联
     * @param c1
     * @param c2
     * @return
     */
    public boolean isRelated(long c1, long c2);

    public DomainBean getArea(String id);

    public Boolean hasUser(String areaId) throws Exception;

    public List<String> getPname(String pid) throws Exception;

    public DomainBean getAreaByname(String name) throws Exception;

    public void editArea(EditDomainBean edb, String name) throws Exception;


}
