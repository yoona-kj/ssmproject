package cn.kjcoder.dao;

import cn.kjcoder.domain.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/19 10:24
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface SysLogDao {

    @Insert("insert into syslog(visitTime,username,url,executionTime,method) values(#{visitTime},#{username},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    public List<SysLog> findAllSysLog() throws Exception;

    @Delete("delete from syslog where id = #{id}")
    void delSelect(Integer id) throws Exception;
}
