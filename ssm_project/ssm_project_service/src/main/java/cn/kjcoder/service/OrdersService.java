package cn.kjcoder.service;

import cn.kjcoder.domain.Orders;

import java.util.List;

/**
 * @author kjcoder源码
 * @date 2021/3/17 0:04
 * <p>
 * 该页源码所有权归胡楷杰所有，胡楷杰拥有对该代码的最终解释权
 * 源码允许修改，并且本人十分乐意有 大佬 来对代码进行 随 意 调 教
 **/
public interface OrdersService {

    List<Orders> findAll(Integer page,Integer size) throws Exception;

    Orders findById(Integer ordersId) throws Exception;

}
