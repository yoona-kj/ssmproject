package cn.kjcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kjcoder源码
 * @date 2021/3/16 11:40
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Controller
@RequestMapping("/forward")
public class ForwardPageController {

    /**
     * 通过视图解析器转发到WEB-INF下的product-add.jsp页面(WEB-INF下的资源时受保护的客户端不能直接访问)
     *
     * @return
     */
    @RequestMapping("/forwardProductAdd")
    public String forwardProductAdd() {
        return "product-add";
    }

    /**
     * 转发到changePassword.jsp页面
     *
     * @return
     */
    @RequestMapping("/forwardChangePassword")
    public String forwardChangePassword() {
        return "updatePassword";
    }

    /**
     * 转发到user-add.jsp页面
     *
     * @return
     */
    @RequestMapping("/forwardUserAdd")
    public String forwardUserAdd() {
        return "user-add";
    }

    /**
     * 转发到role-add.jsp页面
     *
     * @return
     */
    @RequestMapping("/forwardRoleAdd")
    public String forwardRoleAdd() {
        return "role-add";
    }

    /**
     * 转发到Permission-add.jsp页面
     *
     * @return
     */
    @RequestMapping("/forwardPermissionAdd")
    public String forwardPermissionAdd() {
        return "permission-add";
    }

    @RequestMapping("/forwardMain")
    public String forwardMain(){
        return "mains";
    }


}
