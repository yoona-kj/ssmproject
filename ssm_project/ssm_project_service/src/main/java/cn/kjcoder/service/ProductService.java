package cn.kjcoder.service;

import cn.kjcoder.domain.Product;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/16 10:10
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface ProductService {

    public List<Product> findAll(Integer page,Integer size) throws Exception;

    void save(Product product) throws Exception;

    void delSelect(Integer[] ids) throws Exception;

    void delById(Integer id) throws Exception;

    Product findById(Integer id) throws Exception;

    void update(Product product) throws Exception;
}
