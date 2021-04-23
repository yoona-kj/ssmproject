package cn.kjcoder.controller;

import cn.kjcoder.domain.Orders;
import cn.kjcoder.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 0:04
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/findAll")
    /*@Secured("ROLE_ADMIN")*/
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "3")Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> all = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView  findById(@RequestParam(name = "id",required = true) Integer ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }





}
