package cn.kjcoder.controller;

import cn.kjcoder.domain.Product;
import cn.kjcoder.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/16 10:36
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "3") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(ps);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/delSelect")
    public String delSelect(Integer[] ids) throws Exception {
        productService.delSelect(ids);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/delById")
    public String delById(Integer id) throws Exception {
        productService.delById(id);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(id);
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/update")
    public String update(Product product) throws Exception {
        productService.update(product);
        return "redirect:/product/findAll";
    }



}
