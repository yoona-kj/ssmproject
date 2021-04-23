package cn.kjcoder.service.impl;

import cn.kjcoder.dao.ProductDao;
import cn.kjcoder.domain.Product;
import cn.kjcoder.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/16 10:11
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }

    @Override
    public void delSelect(Integer[] ids) throws Exception {
        for (Integer id : ids) {
            productDao.delSelect(id);
        }
    }

    @Override
    public void delById(Integer id) throws Exception {
        productDao.delById(id);
    }

    @Override
    public Product findById(Integer id) throws Exception {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) throws Exception {
        productDao.update(product);
    }
}
