package cn.kjcoder.dao;

import cn.kjcoder.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/16 10:05
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Repository
public interface ProductDao {

    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    @Select("select * from product where id = #{id}")
    public Product findById(Integer id) throws Exception;

    @Delete("delete from product where id = #{id}")
    public void delSelect(Integer id) throws Exception;

    @Delete("delete from product where id = #{id}")
    public void delById(Integer id) throws Exception;

    @Update("update product set productNum = #{productNum},productName = #{productName},cityName = #{cityName},departureTime = #{departureTime},productPrice = #{productPrice},productDesc = #{productDesc},productStatus = #{productStatus} where id = #{id}")
    void update(Product product) throws Exception;
}
