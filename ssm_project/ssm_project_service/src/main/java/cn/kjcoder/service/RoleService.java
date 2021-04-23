package cn.kjcoder.service;

import cn.kjcoder.domain.Permission;
import cn.kjcoder.domain.Role;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/18 19:26
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface RoleService {

    public List<Role> findAll(Integer page,Integer size) throws Exception;

    public void save(Role role) throws Exception;

    Role findById(Integer roleId) throws Exception;

    List<Permission> findOtherPermissions(Integer roleId) throws Exception;

    void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception;

    void delRole(Integer id) throws Exception;

    void delSelect(Integer[] ids) throws Exception;
}
