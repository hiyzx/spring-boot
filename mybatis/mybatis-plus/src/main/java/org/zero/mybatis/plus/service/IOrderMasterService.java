package org.zero.mybatis.plus.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.zero.mybatis.plus.entity.OrderMaster;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
public interface IOrderMasterService extends IService<OrderMaster> {

	void save(Integer stockId, Integer count);
}
