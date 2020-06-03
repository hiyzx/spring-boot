package org.zero.mybatis.plus.service;

import org.zero.mybatis.plus.entity.OrderMaster;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
public interface IOrderMaster2Service extends IService<OrderMaster> {

    void save(Integer stockId, Integer count);
}
