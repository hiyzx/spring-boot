package org.zero.cloud.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zero.cloud.server.entity.Stock;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
public interface IStockService extends IService<Stock> {

    void reduceCount(Integer id, Integer count);
}
