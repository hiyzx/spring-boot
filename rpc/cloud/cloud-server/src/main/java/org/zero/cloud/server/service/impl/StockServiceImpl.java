package org.zero.cloud.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zero.cloud.server.entity.Stock;
import org.zero.cloud.server.mapper.StockMapper;
import org.zero.cloud.server.service.IStockService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

    @Override
    public void reduceCount(Integer id, Integer count) {
        baseMapper.reduceCount(id, count);
    }
}
