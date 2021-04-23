package cn.kjcoder.service;

import cn.kjcoder.domain.Role;
import cn.kjcoder.domain.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 13:37
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface UserService extends UserDetailsService {

    List<UserInfo> findAll(Integer page,Integer size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(Integer id) throws Exception;

    List<Role> findOtherRoles(Integer userId) throws Exception;

    void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception;

    void updatePasswordById(Integer id,String newPassword) throws Exception;

    void delUser(Integer id) throws Exception;

    void delSelect(Integer[] ids) throws Exception;
}
