package cn.kjcoder.controller;

import cn.kjcoder.domain.SysLog;
import cn.kjcoder.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/19 10:38
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAllSysLog")
    public ModelAndView findAllSysLog(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "20") Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> all = sysLogService.findAllSysLog(page,size);
        PageInfo pageInfo = new PageInfo(all);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

    @RequestMapping("delSelect")
    public String delSelect(Integer[] ids) throws Exception {
        sysLogService.delSelect(ids);
        return "redirect:/sysLog/findAllSysLog";
    }
}
