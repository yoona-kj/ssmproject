package cn.kjcoder.controller;

import cn.kjcoder.domain.Role;
import cn.kjcoder.domain.UserInfo;
import cn.kjcoder.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 15:20
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "3") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) Integer userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,@RequestParam(name = "ids",required = true)Integer[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/updatePasswordById")
    public void updatePasswordById(@RequestParam(name = "id",required = true) Integer id,@RequestParam(name = "newPassword",required = true) String newPassword, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userService.updatePasswordById(id, newPassword);
        //调用Spring security的退出请求路径
        response.sendRedirect(request.getContextPath() + "/logout");
    }

    @RequestMapping("/delUser")
    public String delUser(Integer id) throws Exception {
        userService.delUser(id);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/delSelect")
    public String delSelect(Integer[] ids) throws Exception {
        userService.delSelect(ids);
        return "redirect:/user/findAll";
    }


}
