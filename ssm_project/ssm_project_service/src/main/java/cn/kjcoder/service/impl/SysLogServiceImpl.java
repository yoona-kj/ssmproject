package cn.kjcoder.service.impl;

import cn.kjcoder.dao.SysLogDao;
import cn.kjcoder.domain.SysLog;
import cn.kjcoder.service.SysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/19 10:23
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Service("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAllSysLog(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAllSysLog();
    }

    @Override
    public void delSelect(Integer[] ids) throws Exception {
        for (Integer id : ids) {
            sysLogDao.delSelect(id);
        }
    }


}
