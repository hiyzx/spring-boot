package org.zero.cloud.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.zero.cloud.server.entity.Stock;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hiyzx
 * @since 2020-01-04
 */
public interface StockMapper extends BaseMapper<Stock> {

    void reduceCount(@Param("id") Integer id, @Param("count") Integer count);
}
