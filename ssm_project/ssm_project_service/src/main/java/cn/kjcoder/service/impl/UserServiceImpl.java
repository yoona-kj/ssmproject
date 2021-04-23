package cn.kjcoder.service.impl;

import cn.kjcoder.dao.UserDao;
import cn.kjcoder.domain.Role;
import cn.kjcoder.domain.UserInfo;
import cn.kjcoder.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 13:38
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //因为User类实现了UserDetails，所以返回user对象就是返回了UserDetails对象；把自己写的用户对象封装成UserDetails
        User user = new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword((bCryptPasswordEncoder.encode(userInfo.getPassword())));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception {
        for (Integer roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void updatePasswordById(Integer id, String newPassword) throws Exception {
        //对密码进行加密操作,否则登录时不加"{noop}"无法登录
        newPassword = bCryptPasswordEncoder.encode(newPassword);
        userDao.updatePasswordById(id,newPassword);
    }

    @Override
    public void delUser(Integer id) throws Exception {
        userDao.delFromUsers_Role(id);
        userDao.delUserById(id);
    }

    @Override
    public void delSelect(Integer[] ids) throws Exception {
        for (Integer id : ids) {
            userDao.delFromUsers_Role(id);
            userDao.delUserById(id);
        }
    }
}
