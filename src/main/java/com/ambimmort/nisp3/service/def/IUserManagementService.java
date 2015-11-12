package com.ambimmort.nisp3.service.def;


import com.ambimmort.nisp3.model.ui.f.area.AreaBean;
import com.ambimmort.nisp3.model.ui.f.area.DomainBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.user.AddUserBean;

import com.ambimmort.nisp3.model.ui.f.user.EditUserBean;
import com.ambimmort.nisp3.model.ui.f.user.UserBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hedingwei on 5/25/15.
 */
public interface IUserManagementService {


    /**
     * 创建一个新的系统用户
     * @param model
     * @throws Exception
     */
    public void addUser(AddUserBean model) throws Exception;

    /**
     * 更新系统用户信息
     * @param model
     * @throws Exception
     */
    public void updateUser(EditUserBean model) throws Exception;

    /**
     * 判断用户是否存在于数据库中
     * @param username
     * @return true：存在于数据库   false: 不存在于数据库
     * @throws Exception
     */
    public boolean existUser(String username) throws Exception;

    /**
     * 返回当前区域ID下的所有用户（包括子区域）
     * @return ShowList<UserBean>
     * @throws Exception
     */
    public List<UserBean> listUsers(String areaId) throws Exception;

    /**
     * 返回指定 username 的用户信息
     * @param username
     * @return UserBean
     * @throws Exception
     */
    public UserBean getUser(String username) throws Exception;

    /**
     * 删除指定 username 的用户,
     * 删除该账号时，不删除该账号所关联的业务数据以及操作日志
     * @param username
     * @throws Exception
     */
    public void deleteUser(String username) throws Exception;

    /**
     * 更新指定用户的密码
     * @param username
     * @param password
     * @throws Exception
     */
    public void updatePassword(String username,String password) throws  Exception;

    /**
     * 设置指定用户的"启用/不启用"状态

     * @throws Exception
     */
    public void setStatus(String username,boolean flag) throws Exception;

    /**
     * 取得指定用户的所有权限区域
     * @param username
     * @return ShowList<AreaBean>
     * @throws Exception
     */
    public List<DomainBean> getBelongsArea(String username) throws Exception;

    /**
     * 判断用户是否有对应区域的权限
     * @param username
     * @param areaId
     * @return True/False
     * @throws Exception
     */
    public boolean hasAuth(String username, String areaId) throws Exception;

    public String getPwd(String username) throws SQLException;

    public void updateInfo(UserBean userBean) throws Exception;

    public Boolean hasUser(String areaId) throws Exception;

    public List<AreaBean> listAreas(String areaId) throws Exception;

    public EditUserBean getEditUser(String username) throws Exception;

    public List<RoleBean> listRoles() throws Exception;
}
