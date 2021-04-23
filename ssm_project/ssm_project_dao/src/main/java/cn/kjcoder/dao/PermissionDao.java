package cn.kjcoder.dao;

import cn.kjcoder.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/18 13:20
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(Integer id) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission) throws Exception;

    @Delete("delete from role_permission where permissionId = #{id}")
    public void delFromRole_Permission(Integer id) throws Exception;

    @Delete("delete from permission where id = #{id}")
    public void delPermissionById(Integer id) throws Exception;

    @Select("select * from permission where id = #{id}")
    public Permission findById(Integer id) throws Exception;
}
