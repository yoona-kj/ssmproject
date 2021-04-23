package cn.kjcoder.controller;

import cn.kjcoder.domain.Permission;
import cn.kjcoder.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/18 19:49
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "3") Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Permission> all = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/delPermission")
    public String delPermission(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        permissionService.delPermission(id);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/delSelect")
    public String delSelect(Integer[] ids) throws Exception {
        permissionService.delSelect(ids);
        return "redirect:/permission/findAll";
    }
}
