package cn.kjcoder.dao;

import cn.kjcoder.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author kjcoder源码
 * @date 2021/3/17 11:02
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Repository
public interface MemberDao {

    @Select("select * from member where id = #{id}")
    public Member findById(Integer id) throws Exception;
}
