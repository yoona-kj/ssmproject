package cn.kjcoder.dao;

import cn.kjcoder.domain.Role;
import cn.kjcoder.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 13:49
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Repository
public interface UserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.kjcoder.dao.RoleDao.findRoleByUserId"))

    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.kjcoder.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(Integer id) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(Integer userId) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId,@Param("roleId") Integer roleId) throws Exception;

    @Update("update users set password = #{password} where id = #{id}")
    void updatePasswordById(@Param("id") Integer id,@Param("password") String newPassword) throws Exception;

    @Delete("delete from users_role where userId = #{id}")
    void delFromUsers_Role(Integer id) throws Exception;

    @Delete("delete from users where id = #{id}")
    void delUserById(Integer id) throws Exception;


}
