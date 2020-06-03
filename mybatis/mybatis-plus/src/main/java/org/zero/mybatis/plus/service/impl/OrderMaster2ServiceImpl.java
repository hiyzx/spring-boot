package org.zero.mybatis.plus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zero.mybatis.plus.entity.OrderMaster;
import org.zero.mybatis.plus.mapper.OrderMasterMapper;
import org.zero.mybatis.plus.service.IOrderMaster2Service;
import org.zero.mybatis.plus.service.IOrderMasterService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
@Service
public class OrderMaster2ServiceImpl extends ServiceImpl<OrderMasterMapper, OrderMaster> implements
		IOrderMaster2Service {

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
	public void save(Integer stockId, Integer count) {
		OrderMaster order = new OrderMaster();
		order.setStockId(stockId * 2);
		order.setBuyCount(count);
		this.save(order);
		System.out.println(1/0);
	}
}
