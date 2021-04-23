package cn.kjcoder.service.impl;

import cn.kjcoder.dao.PermissionDao;
import cn.kjcoder.domain.Permission;
import cn.kjcoder.service.PermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/18 19:53
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public void delPermission(Integer id) throws Exception {
        permissionDao.delFromRole_Permission(id);
        permissionDao.delPermissionById(id);
    }

    @Override
    public Permission findById(Integer id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void delSelect(Integer[] ids) throws Exception {
        for (Integer id : ids) {
            permissionDao.delPermissionById(id);
        }
    }


}
