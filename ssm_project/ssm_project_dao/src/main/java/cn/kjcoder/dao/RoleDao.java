package cn.kjcoder.dao;

import cn.kjcoder.domain.Permission;
import cn.kjcoder.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 14:16
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface RoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.kjcoder.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(Integer userId);

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role) throws Exception;

    @Select("select * from role where id = #{roleId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.kjcoder.dao.PermissionDao.findPermissionByRoleId"))
    })
    Role findById(Integer roleId) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(Integer roleId) throws Exception;

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId) throws Exception;

    @Delete("delete from role_permission where roleId = #{id}")
    public void delFromRole_Permission(Integer id) throws Exception;

    @Delete("delete from role where id = #{id}")
    public void delRoleById(Integer id) throws Exception;
}
