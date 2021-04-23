package cn.kjcoder.service.impl;

import cn.kjcoder.dao.RoleDao;
import cn.kjcoder.domain.Permission;
import cn.kjcoder.domain.Role;
import cn.kjcoder.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/18 19:27
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(Integer roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) throws Exception {
        for (Integer permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void delRole(Integer id) throws Exception {
        roleDao.delFromRole_Permission(id);
        roleDao.delRoleById(id);
    }

    @Override
    public void delSelect(Integer[] ids) throws Exception {
        for (Integer id : ids) {
            roleDao.delFromRole_Permission(id);
            roleDao.delRoleById(id);
        }

    }


}
