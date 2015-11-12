package com.ambimmort.nisp3.service.def;

import com.ambimmort.nisp3.model.ui.f.role.AddRoleBean;
import com.ambimmort.nisp3.model.ui.f.role.RoleBean;
import com.ambimmort.nisp3.model.ui.f.role.EditRoleBean;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hedingwei on 6/4/15.
 */
public interface IRoleManagementService {

    /**
     * 添加系统角色
     * @param bean
     * @throws Exception
     */
   public void addRole(AddRoleBean bean,HttpServletRequest request) throws Exception;

    /**
     * 删除系统角色,
     * 若有用户属于该角色，不论该用户的状态是否是启用，该角色都不得被删除
     * @param roleId
     * @throws Exception
     */
    public void deleteRole(int roleId) throws Exception;

    /**
     * 返回系统所有角色
     * @return
     * @throws Exception
     */
    public List<RoleBean> listRoles(String uname) throws Exception;

    /**
     * 编辑系统角色
     * @param bean
     * @throws Exception
     */
    public void updateRole(EditRoleBean bean) throws Exception;

    /**
     * 返回指定的角色
     * @param roleId
     * @return RoleBean
     * @throws Exception
     */
    public RoleBean getRole(int roleId) throws Exception;

    /**
     * 返回当前系统所有的权限
     * 读取文件：路径??
     * @return JSONArray
     * @throws Exception
     */
    public JSONArray getAllFunctions(HttpServletRequest request) throws Exception;



    public boolean existRole(String name)throws Exception;

    public String getRoleName(int roleId)throws Exception;

   public boolean getRoleName(String  name) throws Exception;

    public int isTied(int id);

    public boolean getCreater(String uname,int id) throws SQLException;

    public String getUserFunction(String uname) throws SQLException;

    public String getAreacode(String uname) throws SQLException;

    public JSONArray getAllFunctionsView(HttpServletRequest request,int roleid) throws Exception;

    public int  getRoleId(String roleName) throws SQLException;
}
