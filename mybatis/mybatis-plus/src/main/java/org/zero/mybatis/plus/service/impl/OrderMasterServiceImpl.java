package org.zero.mybatis.plus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zero.mybatis.plus.entity.OrderMaster;
import org.zero.mybatis.plus.mapper.OrderMasterMapper;
import org.zero.mybatis.plus.service.IOrderMaster2Service;
import org.zero.mybatis.plus.service.IOrderMasterService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
@Service
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements IOrderMasterService {

    @Autowired
    private IOrderMaster2Service orderMaster2Service;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void save(Integer stockId, Integer count) {
        OrderMaster order = new OrderMaster();
        order.setStockId(stockId);
        order.setBuyCount(count);
	    this.save(order);
	    orderMaster2Service.save(stockId, count);

    }
}
